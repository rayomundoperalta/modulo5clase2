package mx.peta.nasaapp.login;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
//import android.util.Log;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginFragment;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.peta.nasaapp.ListingActivity;
import mx.peta.nasaapp.R;
import mx.peta.nasaapp.internet.InternetConection;


public class FBLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult> {
    @BindView(R.id.fb_login_button)
    LoginButton loginButton;

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fblogin);
        ButterKnife.bind(this);
        InternetConection iC = new InternetConection();

        if (!iC.isNetworkAvailable(getApplicationContext())) {
            Snackbar.make(findViewById(android.R.id.content), "No hay conexion a Internet", Snackbar.LENGTH_LONG).show();
            Handler h = new Handler();
            // new Handler()
            h.postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000 * 4);
        } else {
            try {
                //Log.d("KeyHash:", "<---------------------");
                // el nombre del paquete depende del BuildVariant que se esta usando
                PackageInfo info = getPackageManager().getPackageInfo(
                        "mx.peta.nasaapp.actual",
                        PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                    //Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {
                //Log.d("KeyHash", "Name not Found");
            } catch (NoSuchAlgorithmException e) {
                //Log.d("KeyHash", "No such algorithm");
            }
            callbackManager = CallbackManager.Factory.create();
            loginButton.registerCallback(callbackManager, this);

            if (AccessToken.getCurrentAccessToken() != null) {
                Snackbar.make(findViewById(android.R.id.content), "access token", Snackbar.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ListingActivity.class);
                // startActivity(intent);
                // modo alterno
                startActivity(new Intent(this, ListingActivity.class));
            }
        }
    }
    @Override
    public void onSuccess(LoginResult loginResult) {
        Snackbar.make(findViewById(android.R.id.content),"on Success", Snackbar.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ListingActivity.class);
        // startActivity(intent);
        // finish();
        startActivity(new Intent(this, ListingActivity.class));
    }

    @Override
    public void onCancel() {
        Snackbar.make(findViewById(android.R.id.content),"on Cancel", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onError(FacebookException error) {
        Snackbar.make(findViewById(android.R.id.content),"on Error", Snackbar.LENGTH_SHORT).show();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode,resultCode,data);
    }
}
