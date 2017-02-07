package com.example.eslam.movies;

/**
 * Created by Eslam on 8/12/2016.
 */
public class URL_builder {
    public String urlForMoviesList(String category)  {
        String APIKEY="e20080ab8b395f936423b819c9b6b689";
        final String base_url="https://api.themoviedb.org/3/movie/";
        final String apiKey="api_key=";
        final String sorting="sort_by";


        return base_url+category+"?"+apiKey+APIKEY;
    }
    public String urlforMovieTrailer(String movieID){
        String APIKEY="e20080ab8b395f936423b819c9b6b689";
        final String base_url="http://api.themoviedb.org/3/movie";
        final String apiKey="/videos?api_key=";
        String url=base_url+"/"+movieID+apiKey+APIKEY;
        return url;
    }
    public String urlforYoutube(String key){
        return "https://www.youtube.com/watch?v="+key;
    }
    public String urlforReviews(String key){
        String base_url="https://api.themoviedb.org/3/movie/";
        String apiKey="/reviews?api_key=";
        String ApiKey="e20080ab8b395f936423b819c9b6b689";
        return base_url+key+apiKey+ApiKey;
    }
}
