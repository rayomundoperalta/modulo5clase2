package mx.peta.nasaapp.fragmentos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_apod, container, false);
        ButterKnife.bind(this, view);

        // Log.d("Frag Apod", BuildConfig.BUILD_TYPE + " " + BuildConfig.URL);
        // Toast.makeText(getApplicationContext(),"arranque",Toast.LENGTH_SHORT).show();

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);
        // Call<Apod> callApodService = apodService.getTodayApod();
        Call<Apod> callApodService = apodService.getTodayApodWithQuery("1b0qt1wfXNdK7Pbz1E7kEETE3wDjAv6I6MA9dxz9");

        callApodService.enqueue(new Callback<Apod>() {
            @Override
            public void onResponse(Call<Apod>  call, Response<Apod> response) {
                Log.d("APOD", response.body().getTitle());
                Picasso.with(getContext()).load(response.body().getUrl()).into(imagen);
                titulo.setText(response.body().getTitle());
                fecha.setText(response.body().getDate());
                explicacion.setText(response.body().getExplanation());
                copyright.setText("(C) " + response.body().getCopyright());
            }

            @Override
            public void onFailure(Call<Apod> call, Throwable t) {

            }
        });
        return view;
    }
}
