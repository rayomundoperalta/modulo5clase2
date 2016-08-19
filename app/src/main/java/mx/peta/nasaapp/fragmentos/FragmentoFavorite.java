package mx.peta.nasaapp.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mx.peta.nasaapp.R;
import mx.peta.nasaapp.SQL.APODDataSource;
import mx.peta.nasaapp.adapter.AdapterItemList;
import mx.peta.nasaapp.model.Apod;

/**
 * Created by rayo on 8/17/16.
 */
public class FragmentoFavorite extends Fragment {
    ListView listView;
    private List<Apod> array = new ArrayList<>();
    APODDataSource ds;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmento_favorite, container, false);

        // Llenamos la lista de la actividad con la información de la base de datos
        //DataSource ds = new DataSource(getContext());
        ds = new APODDataSource(getActivity().getApplicationContext());
        listView = (ListView) view.findViewById(R.id.lista_fragmento_listview);

        List<Apod> modelItemList = ds.getAllItems();

        //listView.setAdapter(new AdapterItemList(getContext(),modelItemList));
        listView.setAdapter(new AdapterItemList(getActivity().getApplicationContext(),modelItemList));
        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        ds.close();
    }
}
