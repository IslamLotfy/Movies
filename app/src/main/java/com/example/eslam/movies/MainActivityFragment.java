package com.example.eslam.movies;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements DataParsed {
    public image_adapter imageAdapter;
    public JSON_parser parser;
    public GridView gridView;
    URL_builder url_builder;
    public String sort;
    public sample_model[] data;
    public callBack itemSelectedListener;
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_main,container,false);
        setHasOptionsMenu(true);
        gridView=(GridView) rootView.findViewById(R.id.grid_view);
        url_builder=new URL_builder();
        data=new sample_model[0];
        sort="popular";
        String url= url_builder.urlForMoviesList(sort);
        parser=new JSON_parser();
        parser.setListener(this,getContext());

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                sample_model movie=imageAdapter.getItem(position);
                ((callBack)getActivity()).onItemSelected(movie);

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        updatelist();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }
    public void updatelist(){


        Connector  connector=new Connector();
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(getActivity());

        String unitType = sharedPrefs.getString(("Sort"),getString(R.string.pref_sort_Most_popular));
        if(unitType.equals("favourite")){
            SharedPreference sharedPreference=new SharedPreference();
            try {
               // sharedPreference.clearData(getContext());
                ArrayList<String> movies= sharedPreference.getFavorites(getContext());

                onDataparsed( sharedPreference.getJsonMovies(movies));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            String url = url_builder.urlForMoviesList(unitType);
            connector.setListener(parser,getContext());
            connector.execute(url);
        }
    }

    @Override
    public void onDataparsed(sample_model[] data) {
        imageAdapter = new image_adapter(getActivity(),data);
        this.data=data;
        gridView.setAdapter(imageAdapter);
    }
}
