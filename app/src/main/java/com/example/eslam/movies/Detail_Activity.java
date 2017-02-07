package com.example.eslam.movies;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;


public class Detail_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//        if(savedInstanceState==null){
//            getSupportFragmentManager().beginTransaction()
//                    .add(R.id.movie_detail_container,new Detail_Fragment())
//                    .commit();
//        }
    }
}
