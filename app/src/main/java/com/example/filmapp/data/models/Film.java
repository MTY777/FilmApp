package com.example.filmapp.data.models;

import android.net.Uri;

import com.google.gson.annotations.SerializedName;

public class Film {
    String id;
    String title;
    @SerializedName("oridinal_title")
    String originalTitle;
    String description;
    @SerializedName("image_name")
    String imageView;

    public String getImageView() {
        return imageView;
    }

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

}
