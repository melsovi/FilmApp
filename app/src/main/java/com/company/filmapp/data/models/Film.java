package com.company.filmapp.data.models;

import com.google.gson.annotations.SerializedName;

public class Film {

    String id;
    String title;
    @SerializedName("original_title")
    String originalTitle;
    String description;
    @SerializedName("release_date")
    String releaseDate;
    String director;
    @SerializedName("movie_banner")
    String movieBanner;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getDirector() {
        return director;
    }

    public String getMovieBanner() {
        return movieBanner;
    }
}
