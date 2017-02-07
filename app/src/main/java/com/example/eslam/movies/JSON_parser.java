package com.example.eslam.movies;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Eslam on 8/12/2016.
 */
public class JSON_parser implements AsyncDatafetched  {
   // private MainActivityFragment mainActivityFragment;
    URL_builder urlBuilder;
    private DataParsed listener;
    private image_adapter image_adapter;
    private String[] posters;
    sample_model[] moviesdata;
    Context context;

    JSON_parser(){
        urlBuilder=new URL_builder();
    }
    public String[] getImage(String Jsonmovies) throws JSONException {
        Log.v("Movies: ",Jsonmovies);
        JSONObject jsonObject = new JSONObject(Jsonmovies);
        JSONArray jsonArray = (JSONArray) jsonObject.getJSONArray("results");
        posters = new String[jsonArray.length()];
        moviesdata=new sample_model[jsonArray.length()];
//        Log.v("Movies: ",jsonArray.toString());
        int sz=jsonArray.length();
        for (int i = 0; i < sz ; i++) {

               JSONObject movie=jsonArray.getJSONObject(i);
               Log.v("Movie title : ",movie.toString());
//            posters[i]=("http://image.tmdb.org/t/p/w185"+movie.getString("poster_path"));

                moviesdata[i]=new sample_model();
                moviesdata[i].setMovieJsonObject(movie.toString());
                moviesdata[i].setTitle(movie.getString("title"));
                moviesdata[i].setPoster("http://image.tmdb.org/t/p/w185"+movie.getString("poster_path"));
                moviesdata[i].setDescription(movie.getString("overview"));
                moviesdata[i].setRelease_date(movie.getString("release_date"));
                moviesdata[i].setID(movie.getString("id"));
                moviesdata[i].setTrailerUrl(urlBuilder.urlforMovieTrailer(moviesdata[i].getID()));
                moviesdata[i].setReviewUrl(urlBuilder.urlforReviews(moviesdata[i].getID()));

//            Log.d("pos path : ",posters[i]);
        }
        return posters;
    }
    @Override
    public void onDatafetched(String JsonStr) {
        if(JsonStr==null){
            Toast.makeText(context, "there is no internet Connection !",
                    Toast.LENGTH_LONG).show();
        }else {
            try {
                getImage(JsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        listener.onDataparsed(moviesdata);
    }

    public DataParsed getListener() {
        return listener;
    }

    public void setListener(DataParsed listener , Context context) {
        this.listener = listener;
        this.context=context;
    }
}
