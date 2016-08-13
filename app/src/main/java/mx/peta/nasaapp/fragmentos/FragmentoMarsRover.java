package mx.peta.nasaapp.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.peta.nasaapp.DetailActivity;
import mx.peta.nasaapp.R;
import mx.peta.nasaapp.data.ApodService;
import mx.peta.nasaapp.data.Data;
import mx.peta.nasaapp.model.MarsRoverImages2;
import mx.peta.nasaapp.model.Photo;
import mx.peta.nasaapp.reciclerview.NasaApodAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by rayo on 8/12/16.
 */
public class FragmentoMarsRover extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView marsRoverListingRecycler;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_mars_rover, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.VERTICAL);
        marsRoverListingRecycler.setLayoutManager(linearLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();

        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {

                Log.d("tag", photo.getImgSrc());
                // En este punto conocesmos al objeto que se le dio click
                Intent intent = new Intent(getActivity(), DetailActivity.class);

                Bundle bundle = new Bundle();
                bundle.putSerializable("photo", photo);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });

        ApodService apodService = Data.getRetrofitInstance().create(ApodService.class);

        Call<MarsRoverImages2> callMarsRovesImages = apodService.getMarsRovesImages2("1000", "1b0qt1wfXNdK7Pbz1E7kEETE3wDjAv6I6MA9dxz9");

        callMarsRovesImages.enqueue(new Callback<MarsRoverImages2>() {
            @Override
            public void onResponse(Call<MarsRoverImages2> call, Response<MarsRoverImages2> response) {
                Log.d("MarsRoverImages2", "fotos del mars rovers");
                nasaApodAdapter.setMarsPhotos(response.body().getPhotos());
                //marsRoverListingRecycler.setAdapter(new NasaApodAdapter(response.body().getPhotos()));
                marsRoverListingRecycler.setAdapter(nasaApodAdapter);
            }

            @Override
            public void onFailure(Call<MarsRoverImages2> call, Throwable t) {

            }
        });


        return view;
    }
}
