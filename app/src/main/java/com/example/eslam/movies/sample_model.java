package com.example.eslam.movies;

import java.io.Serializable;

/**
 * Created by Eslam on 8/12/2016.
 */
public class sample_model implements Serializable {
    private String poster;
    private String title;
    private String release_date;
    private String description;
    private String ID;
    private String trailerUrl;
    private String youtubeUrl;
    private String reviewUrl;
    private String[] reviews;
    private String movieJsonObject;
    public sample_model(){
        poster="";
        title="";
        release_date="";
        description="";
        ID="";
        trailerUrl="";
        youtubeUrl="";
        reviewUrl="";
        reviews=null;
        movieJsonObject="";
    }
    public void setPoster(String u){
        poster=u;
    }
    public String getPoster(){
        return poster;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getYoutubeUrl() {
        return youtubeUrl;
    }

    public void setYoutubeUrl(String youtubeUrl) {
        this.youtubeUrl = youtubeUrl;
    }

    public String getReviewUrl() {
        return reviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        this.reviewUrl = reviewUrl;
    }

    public String[] getReviews() {
        return reviews;
    }

    public void setReviews(String[] reviews) {
        this.reviews = reviews;
    }

    public String getMovieJsonObject() {
        return movieJsonObject;
    }

    public void setMovieJsonObject(String movieJsonObject) {
        this.movieJsonObject = movieJsonObject;
    }

}
