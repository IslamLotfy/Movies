package com.example.eslam.movies;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Eslam on 8/12/2016.
 */
public class Connector extends AsyncTask<String, Void, String> {
    private static final String LOG_TAG=Connector.class.getSimpleName();
    private AsyncDatafetched listener;
    Context context;
    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;
        String movieslist = "";

        try {

            URL url = new URL(params[0]);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                movieslist = null;
            }
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                movieslist = null;
            }

            movieslist = buffer.toString();
        } catch (IOException e) {
            movieslist = null;
        }
//        Log.v(LOG_TAG,movieslist);
        return movieslist;
    }
    @Override
    public void onPostExecute(String movieStr){
        if(movieStr==null){
            Toast.makeText(context, "there is no internet Connection !",
                    Toast.LENGTH_LONG).show();
        }
        else if (listener !=null)
        listener.onDatafetched(movieStr);
    }
    public void setListener( AsyncDatafetched listener, Context context){
        this.listener=listener;
        this.context=context;
    }

}
