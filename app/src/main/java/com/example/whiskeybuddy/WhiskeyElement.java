package com.example.whiskeybuddy;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class WhiskeyElement {

    private String name;
    private Bitmap image;
    private float rating;
    private ArrayList<TastingNotes> notes;

    public WhiskeyElement() {

    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public Bitmap getImage() {
        return image;
    }
}
