package com.example.eslam.movies;

/**
 * Created by Eslam on 9/18/2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {

    public  String PREFS_NAME = "PRODUCT_APP";
    public  String FAVORITES = "Product_Favorite";
    URL_builder urlBuilder;

    public SharedPreference() {

        super();

    }
    public void clearData(Context context){
        SharedPreferences settings;
        Editor editor;
        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.clear().commit();
    }

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<String> favorites) {
        SharedPreferences settings;
        Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        editor.putString(FAVORITES, favorites.toString());

        editor.commit();
    }

    public void addFavorite(Context context, String movie) throws JSONException {
        List<String> favorites = getFavorites(context);

        if (favorites == null)
            favorites = new ArrayList<String>();
        if(!favorites.contains(movie))
        favorites.add(movie);
        saveFavorites(context, favorites);
    }

//    public void removeFavorite(Context context, sample_model sampleModel) throws JSONException {
//        ArrayList<sample_model> favorites = getFavorites(context);
//        if (favorites != null) {
//            favorites.remove(sampleModel);
//            saveFavorites(context, favorites);
//        }
//    }


    public sample_model[] getJsonMovies(ArrayList<String> list) {
        urlBuilder = new URL_builder();
        List<sample_model> favorites;
        sample_model[] moviesdata=null;
        JSONArray jsonArray = null;
        Log.v("List size", "" + list.size());
        String str = list.toString().substring(0, list.toString().length());
        try {


            jsonArray = new JSONArray(str);
            moviesdata = new sample_model[jsonArray.length()];
            // Log.v("JsonArraySize:",""+str2);

            // Log.v("JsonArraySize:",""+jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    JSONObject movie = jsonArray.getJSONObject(i);
//               Log.v("Movie title : ",movie.toString());
//            posters[i]=("http://image.tmdb.org/t/p/w185"+movie.getString("poster_path"));
                    moviesdata[i] = new sample_model();
                    moviesdata[i].setTitle(movie.getString("title"));
                    moviesdata[i].setPoster("http://image.tmdb.org/t/p/w185" + movie.getString("poster_path"));
                    moviesdata[i].setDescription(movie.getString("overview"));
                    moviesdata[i].setRelease_date(movie.getString("release_date"));
                    moviesdata[i].setID(movie.getString("id"));
                    moviesdata[i].setTrailerUrl(urlBuilder.urlforMovieTrailer(moviesdata[i].getID()));
                    moviesdata[i].setReviewUrl(urlBuilder.urlforReviews(moviesdata[i].getID()));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return moviesdata;
    }
        public ArrayList<String> getFavorites(Context context) throws JSONException {
        SharedPreferences settings;
        ArrayList<String> favorites = null;
        urlBuilder = new URL_builder();
        sample_model[] moviesdata;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            jsonFavorites=jsonFavorites.substring(1,jsonFavorites.length()-1);
            favorites = new ArrayList<String>(Arrays.asList(jsonFavorites));
            Log.v("from Hared zoft: ",favorites.toString());
//            Log.v("stored data : ",jsonFavorites);
//            JSONArray jsonArray = new JSONArray(jsonFavorites);
//            moviesdata=new sample_model[jsonArray.length()];
////        Log.v("Movies: ",jsonArray.toString());
//            int sz=jsonArray.length();
//            for (int i = 0; i < sz ; i++) {
//                JSONObject movie=jsonArray.getJSONObject(i);
////               Log.v("Movie title : ",movie.toString());
////            posters[i]=("http://image.tmdb.org/t/p/w185"+movie.getString("poster_path"));
//                moviesdata[i]=new sample_model();
//                moviesdata[i].setTitle(movie.getString("title"));
//                moviesdata[i].setPoster("http://image.tmdb.org/t/p/w185"+movie.getString("poster_path"));
//                moviesdata[i].setDescription(movie.getString("overview"));
//                moviesdata[i].setRelease_date(movie.getString("release_date"));
//                moviesdata[i].setID(movie.getString("id"));
//                moviesdata[i].setTrailerUrl(urlBuilder.urlforMovieTrailer(moviesdata[i].getID()));
//                moviesdata[i].setReviewUrl(urlBuilder.urlforReviews(moviesdata[i].getID()));
//
////            Log.d("pos path : ",posters[i]);
//            }
////            Gson gson = new Gson();
////            sample_model[] favoriteItems = gson.fromJson(jsonFavorites,
////                    sample_model[].class);
////
////            favorites = Arrays.asList(favoriteItems);
////            favorites = new ArrayList<sample_model>(favorites);
//        } else
//            return null;
//        favorites = Arrays.asList(moviesdata);
//        favorites = new ArrayList<sample_model>(favorites);
//        Log.v("Movies: ",moviesdata[0].getTitle());
//        return (ArrayList<sample_model>) favorites;

        }
        return (ArrayList<String>) favorites;
    }
}
