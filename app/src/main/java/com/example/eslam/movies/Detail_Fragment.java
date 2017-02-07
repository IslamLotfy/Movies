package com.example.eslam.movies;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class Detail_Fragment extends Fragment {

    URL_builder urlBuilder;
    RequestQueue requestQueue;
    ArrayList<sample_model> movieList;
    SharedPreference sharedPreferences;
    sample_model movie;

    public Detail_Fragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        Bundle args=getArguments();
        sharedPreferences=new SharedPreference();
        urlBuilder=new URL_builder();
        movieList=new ArrayList<sample_model>();
        Intent intent=getActivity().getIntent();
        View rootView=inflater.inflate(R.layout.fragment_detail,container,false);
        if(args!=null){
            movie=(sample_model)args.getSerializable("movie");
            Log.v("movie: ",movie.toString());
        }else{
            movie= (sample_model)intent.getSerializableExtra("movie");
        }

        TextView title= (TextView) rootView.findViewById(R.id.movie_title);
        title.setText(movie.getTitle());
        final TextView release_date=(TextView)rootView.findViewById(R.id.release_date);
        release_date.setText(movie.getRelease_date());
        TextView description=(TextView) rootView.findViewById(R.id.description);
        description.setText(movie.getDescription());
        ImageView poster=(ImageView)rootView.findViewById(R.id.poster);
        Picasso.with(getContext()).load( movie.getPoster()).into(poster);
        final String ReviewContent="";
        final TextView content=(TextView)rootView.findViewById(R.id.content);
        requestQueue= Volley.newRequestQueue(getContext());
        final ArrayList<String> rev=new ArrayList<String>();
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, movie.getTrailerUrl(),null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("on response",response.toString());

                        try{
                            JSONArray  jsonArray=response.getJSONArray("results");
                            JSONObject jsonObject=jsonArray.getJSONObject(0);
                            String key=jsonObject.getString("key");
                            movie.setYoutubeUrl(urlBuilder.urlforYoutube(key));
                            Log.v("youtube link: ",movie.getYoutubeUrl());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("Json Eror",error.getMessage());
            }
        });

        JsonObjectRequest jsonObjectRequest1=new JsonObjectRequest(Request.Method.GET, movie.getReviewUrl(), null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.v("on response ",response.toString());

                        try{
                            JSONArray jsonArray=response.getJSONArray("results");
                            String[] reviews = new String[jsonArray.length()];
                            for (int i = 0; i < jsonArray.length(); i++) {
                                reviews[i]=new String();
                                JSONObject jsonObject=jsonArray.getJSONObject(i);
                                reviews[i]=jsonObject.getString("content");
                                rev.add("review "+i+1+": ");
                                rev.add("\n");
                                rev.add(reviews[i]);
                                rev.add("\n\n");


                            }

                            movie.setReviews(reviews);
                            content.setText(rev.toString());

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);
        requestQueue.add(jsonObjectRequest1);
        Button trailerButton=(Button)rootView.findViewById(R.id.trailer);
        trailerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(movie.getYoutubeUrl())));
            }
        });
        Button favouriteButton=(Button)rootView.findViewById(R.id.favourite);
        favouriteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                try {


                    sharedPreferences.addFavorite(getContext(),movie.getMovieJsonObject());

                Log.v("movie Stored: ",movie.getMovieJsonObject());
                    ArrayList<String> list=sharedPreferences.getFavorites(getContext());
                Log.v("data from sharefprefs:",list.get(0));

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
        return rootView;
    }

//    private void saveArrayeList(ArrayList<sample_model> movieList) {
//        sharedPreferences.edit().putString("favourite",new Gson().toJson(movieList));
//        sharedPreferences.edit().commit();
//    }
//
//    private ArrayList<sample_model> getArrayList() {
//        return new Gson().fromJson(sharedPreferences.getString("favourite",null),ArrayList.class);
//    }
}
