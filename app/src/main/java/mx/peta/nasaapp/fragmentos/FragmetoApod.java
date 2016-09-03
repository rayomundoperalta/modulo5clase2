package mx.peta.nasaapp.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.peta.nasaapp.BuildConfig;
import mx.peta.nasaapp.R;
import mx.peta.nasaapp.SQL.APODDataSource;
import mx.peta.nasaapp.data.ApodService;
import mx.peta.nasaapp.data.Data;
import mx.peta.nasaapp.model.Apod;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rayo on 8/12/16.
 */
public class FragmetoApod extends Fragment {
    @BindView(R.id.frag_apod_imagen) ImageView imagen;
    @BindView(R.id.frag_apod_titulo) TextView titulo;
    @BindView(R.id.frag_apod_fecha) TextView fecha;
    @BindView(R.id.frag_apod_explicacion) TextView explicacion;
    @BindView(R.id.frag_apod_copyright) TextView copyright;
    private String imageurl;
    Apod apod;
    APODDataSource apodDs;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        apodDs = new APODDataSource(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_apod, container, false);
        ButterKnife.bind(this, view);

        apod = new Apod();

        // Log.d("Frag Apod", BuildConfig.BUILD_TYPE + " " + BuildConfig.URL);
        // Toast.makeText(getApplicationContext(),"arranque",Toast.LENGTH_SHORT).show();

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        // Call<Apod> callApodService = apodService.getTodayApod();
        Call<Apod> callApodService = apodService.getTodayApodWithQuery("1b0qt1wfXNdK7Pbz1E7kEETE3wDjAv6I6MA9dxz9");

        callApodService.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod>  call, Response<Apod> response) {
                //Log.d("APOD", response.body().getTitle());
                Picasso.with(getContext()).load(response.body().getUrl()).into(imagen);
                titulo.setText(response.body().getTitle());
                fecha.setText(response.body().getDate());
                explicacion.setText(response.body().getExplanation());
                copyright.setText("(C) " + response.body().getCopyright());
                imageurl = response.body().getUrl();
                apod = response.body();
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share_today_apod:
                //Snackbar.make(getView(), "Menu share today", Snackbar.LENGTH_SHORT).show();
                shareText("Diplomado Unam " + imageurl);
                return true;
            case R.id.favorito:
                //Snackbar.make(getView(), "Agregando a favoritos", Snackbar.LENGTH_SHORT).show();
                apodDs.writeAPOD(apod);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.apod_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void shareText(String text) {
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(shareIntent, "compartir"));
    }

    @Override
    public void onPause() {
        super.onPause();
        apodDs.close();
    }
}
