
package com.example.filmapp.ui.film_detail;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.filmapp.App;

import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.FragmentFilmDetailBinding;

import com.example.filmapp.interfaces.OnClickListener;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FilmDetailFragment extends Fragment implements OnClickListener {
private FragmentFilmDetailBinding binding;
    private Film film = new Film();

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
        App.api.getFilmsId(bundle.getString("key")).enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful() && response.body() != null){
                   film = (response.body());
                   metod();
                }else {
                    Snackbar.make(binding.getRoot(), response.message(), BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }



            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Snackbar.make(binding.getRoot(), t.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_LONG).show();

            }
        });
    }

    @Override
    public void onClick(Film film) {

    }
    private void metod() {
        binding.title.setText(film.getTitle());
        binding.description.setText(film.getDescription());
        Glide.with(binding.image).load(film.getImage()).into(binding.image);
    }

}