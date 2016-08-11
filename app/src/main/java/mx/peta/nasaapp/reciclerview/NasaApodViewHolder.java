package mx.peta.nasaapp.reciclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mx.peta.nasaapp.R;
import mx.peta.nasaapp.model.Photo;

/**
 * Created by Alumno on 05/08/2016.
 */
public class NasaApodViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.item_apod_image)
    SimpleDraweeView itemApodImage;
    //ImageView itemApodImage;
    @BindView(R.id.item_apod_text)
    TextView itemApodText;

    private NasaApodAdapter.OnItemClickListener onItemListener;
    private Photo photo;


    public NasaApodViewHolder (View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void setItemClick(Photo photo, NasaApodAdapter.OnItemClickListener onItemListener) {
        this.photo = photo;
        this.onItemListener = onItemListener;
    }

    @OnClick(R.id.item_apod_image)  // esto hace el setClick, este es el click de la imagen
    public void onViewClick(View view) {
        if (onItemListener != null)
            onItemListener.onItemClick(photo);
    }
}
