package mx.peta.nasaapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.peta.nasaapp.data.ApodService;
import mx.peta.nasaapp.data.Data;
import mx.peta.nasaapp.model.Apod;
import mx.peta.nasaapp.model.MarsRoverImages2;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.imagen)
    ImageView imagen;
    @BindView(R.id.titulo)
    TextView titulo;
    @BindView(R.id.fecha)
    TextView fecha;
    @BindView(R.id.explicacion)
    TextView explicacion;
    @BindView(R.id.copyright)
    TextView copyright;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        //imagen = (ImageView) findViewById(R.id.imagen);
        //titulo = (TextView)  findViewById(R.id.titulo);
        //fecha  = (TextView)  findViewById(R.id.fecha);
        //explicacion = (TextView) findViewById(R.id.explicacion);
        //copyright = (TextView) findViewById(R.id.copyright);


        Log.d("Build Config", BuildConfig.BUILD_TYPE + " " + BuildConfig.URL);
        Toast.makeText(getApplicationContext(), "arranque", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), ListingActivity.class);
        startActivity(intent);
        finish();

        // Call<Apod> callApodService = apodService.getTodayApod();
        /*
        Call<Apod> callApodService = apodService.getTodayApodWithQuery("1b0qt1wfXNdK7Pbz1E7kEETE3wDjAv6I6MA9dxz9");

        callApodService.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod> call, Response<Apod> response) {
                Log.d("APOD", response.body().getTitle());
                Picasso.with(getApplicationContext()).load(response.body().getUrl()).into(imagen);
                titulo.setText(response.body().getTitle());
                fecha.setText(response.body().getDate());
                explicacion.setText(response.body().getExplanation());
                copyright.setText("(C) " + response.body().getCopyright());
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });
*/
    }
}
