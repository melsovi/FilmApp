package com.company.filmapp;

import android.app.Application;

import com.company.filmapp.data.remote.FilmApi;
import com.company.filmapp.data.remote.RetrofitClient;

import retrofit2.Retrofit;

public class App extends Application {

    private RetrofitClient retrofitClient;
    public static FilmApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofitClient = new RetrofitClient();
        api = retrofitClient.createFilmApi();
    }
}
