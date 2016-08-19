package mx.peta.nasaapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import mx.peta.nasaapp.R;
import mx.peta.nasaapp.model.Apod;

/**
 * Created by rayo on 8/17/16.
 */
public class AdapterItemList extends ArrayAdapter<Apod> {
    public AdapterItemList(Context context, int resource) { super(context, resource); }

    public AdapterItemList(Context context, List<Apod> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        }
        /*
            Inicializamos los widgets del list_item
         */
        ImageView apodImage           = (ImageView) convertView.findViewById(R.id.list_frag_apod_imagen);
        TextView apodTitulo           = (TextView)  convertView.findViewById(R.id.list_frag_apod_titulo);
        TextView apodFecha            = (TextView)  convertView.findViewById(R.id.list_frag_apod_fecha);
        TextView apodCopyright        = (TextView)  convertView.findViewById(R.id.list_frag_apod_copyright);
        TextView apodExplicacion      = (TextView)  convertView.findViewById(R.id.list_frag_apod_explicacion);

        Apod apod = getItem(position);
        // Usar picasso o fresco para traer la imagen de la red e insertarla en su lugar
        Picasso.with(getContext()).load(apod.getUrl()).into(apodImage);
        apodTitulo.setText(apod.getTitle());
        apodFecha.setText(apod.getDate());
        apodCopyright.setText("(C) " + apod.getCopyright());
        apodExplicacion.setText(apod.getExplanation());

        return convertView;
    }
}
