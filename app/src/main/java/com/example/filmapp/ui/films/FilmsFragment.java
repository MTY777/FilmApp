package com.example.filmapp.ui.films;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.filmapp.App;
import com.example.filmapp.R;
import com.example.filmapp.data.models.Film;
import com.example.filmapp.data.remote.FilmApi;
import com.example.filmapp.databinding.FragmentFilmsBinding;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmsFragment extends Fragment {
    private FragmentFilmsBinding binding;
    private FilmAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new FilmAdapter();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentFilmsBinding.inflate(inflater, container, false);
        binding.recycle.setAdapter(adapter);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        App.api.getFilms().enqueue(new Callback<List<Film>>() {
            @Override
            public void onResponse(Call<List<Film>> call, Response<List<Film>> response) {
                if (response.isSuccessful() && response.body() == null){
                    adapter.setFilms(response.body());
                }else {
                    Snackbar.make(binding.getRoot(), response.message(), BaseTransientBottomBar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Film>> call, Throwable t) {
                Snackbar.make(binding.getRoot(), t.getLocalizedMessage(), BaseTransientBottomBar.LENGTH_LONG).show();

            }
        });
    }
}