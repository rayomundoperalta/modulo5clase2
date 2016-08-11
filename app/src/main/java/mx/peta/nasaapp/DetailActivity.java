package mx.peta.nasaapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import mx.peta.nasaapp.model.Photo;

/**
 * Created by rayo on 8/6/16.
 */
public class DetailActivity extends AppCompatActivity {
    Photo photo;
    @BindView(R.id.photoId)
    TextView photoId;

    @BindView(R.id.photoSol)
    TextView photoSol;

    @BindView(R.id.photoEarthDate)
    TextView photoEarthDate;

    @BindView(R.id.cameraName)
    TextView cameraName;

    @BindView(R.id.roverName)
    TextView roverName;

    @BindView(R.id.img_src)
    TextView img_src;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailactivity);
        ButterKnife.bind(this);
        photo = (Photo) getIntent().getExtras().getSerializable("photo");
        photoId.setText("Photo: " + photo.getId().toString());
        photoSol.setText("SOL: " + photo.getSol().toString());
        photoEarthDate.setText("Date: " + photo.getEarthDate());
        cameraName.setText("Camera: " + photo.getCamera().getName());
        roverName.setText("Rover: " + photo.getRover().getName());
        img_src.setText("Link: " + photo.getImgSrc());
    }

}
