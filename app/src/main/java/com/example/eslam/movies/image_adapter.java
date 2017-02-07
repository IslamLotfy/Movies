package com.example.eslam.movies;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by Eslam on 8/12/2016.
 */
public class image_adapter extends ArrayAdapter<sample_model> {
    private final String LOG_TAG = image_adapter.class.getSimpleName();
    sample_model[] moviesdata;
    public image_adapter( Activity context ,sample_model[] models){
        super(context, 0,models);
        moviesdata=models;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.images_layout, parent, false);
        }
        ImageView imageView=(ImageView) convertView.findViewById(R.id.image_layout);
        Picasso.with(getContext()).load( moviesdata[position].getPoster()).into(imageView);
        return convertView;
    }

}
