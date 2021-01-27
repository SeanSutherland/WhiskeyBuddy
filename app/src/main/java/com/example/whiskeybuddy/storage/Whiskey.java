package com.example.whiskeybuddy.storage;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.whiskeybuddy.TastingNotes;

import java.util.ArrayList;

@Entity
public class Whiskey {

    @PrimaryKey
    @NonNull
    private final String name;
    private float rating;
    private String type;

    public Whiskey(String name, float rating, String type) {
        this.name = name;
        this.rating = rating;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getType() {return type;}

}
