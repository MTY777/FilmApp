package com.example.filmapp.ui.films;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapp.data.models.Film;
import com.example.filmapp.databinding.ItemBinding;

import java.util.ArrayList;
import java.util.List;

public class FilmAdapter extends RecyclerView.Adapter<FilmAdapter.FilmViewHolder> {
private List<Film> films = new ArrayList<>();

    public void setFilms(List<Film> films) {
        this.films = films;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FilmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBinding binding = ItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new FilmViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmViewHolder holder, int position) {
    holder.onBind(films.get(position));
    }

    @Override
    public int getItemCount() {
        return films.size();
    }

    protected class FilmViewHolder extends RecyclerView.ViewHolder{
        private ItemBinding binding;
        public FilmViewHolder(ItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void onBind(Film film) {
            binding.title.setText(film.getTitle());
            binding.description.setText(film.getDescription());
        }
    }
}
