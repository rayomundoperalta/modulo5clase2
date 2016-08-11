package mx.peta.nasaapp.reciclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import mx.peta.nasaapp.R;
import mx.peta.nasaapp.model.Apod;
import mx.peta.nasaapp.model.Photo;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodAdapter extends RecyclerView.Adapter<NasaApodViewHolder> {
    private List<Photo> apods;
    private OnItemClickListener onItemClickListener;

    public NasaApodAdapter(){}
    public NasaApodAdapter(List<Photo> apods) {this.apods = apods;}

    @Override
    public NasaApodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NasaApodViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.nasa_apod_item, parent, false));
    }

    @Override
    public void onBindViewHolder(NasaApodViewHolder holder, int position) {
        Photo photo = apods.get(position);

        holder.itemApodText.setText(photo.getCamera().getFullName()); // asociación

        holder.itemApodImage.setImageURI(photo.getImgSrc());
        //Picasso.with(holder.itemApodImage.getContext())
        //        .load(photo.getImgSrc())
        //        .into(holder.itemApodImage);
        holder.setItemClick(photo, onItemClickListener);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setMarsPhotos(List<Photo> marsPhotos) {
        this.apods = marsPhotos;
    }


    @Override
    public int getItemCount() {
        return apods != null? apods.size():0;
    }

    public interface OnItemClickListener {
        void onItemClick(Photo photo);
    }
}
