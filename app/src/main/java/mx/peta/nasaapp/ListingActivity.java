package mx.peta.nasaapp;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.net.Uri;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
//import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.peta.nasaapp.data.ApodService;
import mx.peta.nasaapp.data.Data;
import mx.peta.nasaapp.fragmentos.FragmentoFavorite;
import mx.peta.nasaapp.fragmentos.FragmentoMarsRover;
import mx.peta.nasaapp.fragmentos.FragmetoApod;
import mx.peta.nasaapp.model.MarsRoverImages2;
import mx.peta.nasaapp.model.Photo;
import mx.peta.nasaapp.reciclerview.NasaApodAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListingActivity extends AppCompatActivity {

    //@BindView(R.id.recyclerView)
    //RecyclerView marsRoverListingRecycler;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.listing_navigation_view)
    NavigationView navigationView;
    @BindView(R.id.listing_navigation_drawer)
    DrawerLayout drawerLayout;
    @BindView(android.R.id.content)
    View contentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_listing);
        setContentView(R.layout.listing_navigation_activity);
        ButterKnife.bind(this);

        GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse responce) {
                try {
                    SimpleDraweeView userImage = (SimpleDraweeView) findViewById(R.id.simpleDrawer);
                    userImage.setImageURI("http://graph.facebook.com/" + object.getString("id") + "/picture?type=large");
                    TextView userName = (TextView) findViewById(R.id.simpleDrawer_textview);
                    userName.setText(object.getString("name"));
                    //Log.d("name", object.getString("name"));
                    //Log.d("id", object.getString("id"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        request.executeAsync();

        // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                drawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.today_apod_item:
                        // Snackbar.make(findViewById(android.R.id.content), "today apod", Snackbar.LENGTH_LONG).show();
                        //Snackbar.make(contentView, "Today APOD", Snackbar.LENGTH_LONG).show();
                        FragmetoApod fApod = new FragmetoApod();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fApod).commit();
                        return true;
                    case R.id.mars_rover_item:
                        // Snackbar.make(findViewById(android.R.id.content), "mars rover", Snackbar.LENGTH_LONG).show();
                        //Snackbar.make(contentView, "Mars Rover", Snackbar.LENGTH_LONG).show();
                        FragmentoMarsRover fMarsRover = new FragmentoMarsRover();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fMarsRover).commit();
                        return true;
                    case R.id.favorite_item:
                        //Snackbar.make(contentView, "Favorite", Snackbar.LENGTH_LONG).show();
                        FragmentoFavorite fFavorite = new FragmentoFavorite();
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_container, fFavorite).commit();
                        return true;
                    default:
                        return false;
                }
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
          @Override
          public void onDrawerClosed(View drawerView) {
              super.onDrawerClosed(drawerView);
          }

          public void onDrawerOpened(View drawerView) {
              super.onDrawerOpened(drawerView);
          }
        };

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        /*
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.VERTICAL);
        marsRoverListingRecycler.setLayoutManager(linearLayoutManager);

        final NasaApodAdapter nasaApodAdapter = new NasaApodAdapter();

        nasaApodAdapter.setOnItemClickListener(new NasaApodAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Photo photo) {

                Log.d("tag", photo.getImgSrc());
                // En este punto conocesmos al objeto que se le dio click
                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);

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
        */
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
