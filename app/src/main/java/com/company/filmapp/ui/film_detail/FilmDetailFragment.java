package com.company.filmapp.ui.film_detail;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.company.filmapp.App;
import com.company.filmapp.R;
import com.company.filmapp.data.models.Film;
import com.company.filmapp.databinding.FragmentFilmDetailBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmDetailFragment extends Fragment {

    private FragmentFilmDetailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String filmId = bundle.getString("filmId");
        App.api.getFilmDetail(filmId).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Glide.with(binding.filmImage).load(response.body().getMovieBanner()).into(binding.filmImage);
                    binding.filmTitle.setText(response.body().getTitle());
                    binding.filmDate.setText("("+response.body().getReleaseDate()+")");
                    binding.filmDescription.setText(response.body().getDescription());
                    binding.filmOriginalTitle.setText(response.body().getOriginalTitle());
                    binding.filmDirector.setText(response.body().getDirector());
                } else {
                    Snackbar.make(
                            binding.getRoot(),
                            response.message(),
                            BaseTransientBottomBar.LENGTH_LONG
                    ).setBackgroundTint(Color.RED)
                            .show();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Snackbar.make(
                        binding.getRoot(),
                        t.getLocalizedMessage(),
                        BaseTransientBottomBar.LENGTH_LONG
                ).setBackgroundTint(Color.RED)
                        .show();
            }
        });
    }
}