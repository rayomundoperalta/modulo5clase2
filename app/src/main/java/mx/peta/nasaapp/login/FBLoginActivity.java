package mx.peta.nasaapp.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginFragment;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.peta.nasaapp.ListingActivity;
import mx.peta.nasaapp.R;


public class FBLoginActivity extends AppCompatActivity implements FacebookCallback<LoginResult>{
    @BindView(R.id.fb_login_button)
    LoginButton loginButton;

    private CallbackManager callbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_fblogin);
        ButterKnife.bind(this);

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
