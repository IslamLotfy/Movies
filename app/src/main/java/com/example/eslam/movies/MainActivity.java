package com.example.eslam.movies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements callBack {
    private static final String DETAIL_FRAGMENT_TAG = "DFTAG";
    public boolean mTwoPane;
    private String munitype;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
         munitype = sharedPrefs.getString(("Sort"),getString(R.string.pref_sort_Most_popular));


        setContentView(R.layout.activity_main);
        if (findViewById(R.id.movie_detail_container) != null) {
            // The detail container view will be present only in the large-screen layouts
            // (res/layout-sw600dp). If this view is present, then the activity should be
            // in two-pane mode.
            mTwoPane = true;
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
//            if (savedInstanceState == null) {
//                getSupportFragmentManager().beginTransaction()
//                        .replace(R.id.movie_detail_container, new Detail_Fragment(), DETAIL_FRAGMENT_TAG)
//                        .commit();}

        } else {
            mTwoPane = false;
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        Detail_Fragment detailFragment=(Detail_Fragment)getSupportFragmentManager().findFragmentByTag(DETAIL_FRAGMENT_TAG);
        if(detailFragment!=null){
            getSupportFragmentManager().beginTransaction().remove(detailFragment).commit();
        }
//        SharedPreferences sharedPrefs =
//                PreferenceManager.getDefaultSharedPreferences(this);
//
//        String unitType = sharedPrefs.getString(("Sort"),getString(R.string.pref_sort_Most_popular));
//        if (unitType != null && !unitType.equals(munitype)&&mTwoPane) {
//            MainActivityFragment ff = (MainActivityFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_movie);
//            if ( null != ff ) {
//                ff.updatelist();
//            }
//            //Detail_Fragment df = (Detail_Fragment)getSupportFragmentManager().findFragmentByTag(DETAIL_FRAGMENT_TAG);
//
//            munitype=unitType;
//        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingsActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemSelected(sample_model movie) {
        if(mTwoPane){
            Bundle args=new Bundle();
            args.putSerializable("movie" ,movie);
            Detail_Fragment detailFragment=new Detail_Fragment();
            detailFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container, detailFragment , DETAIL_FRAGMENT_TAG)
                    .commit();
        }else{
            Intent intent=new Intent(this,Detail_Activity.class);
            intent.putExtra("movie",movie);
            startActivity(intent);
        }
    }

}
