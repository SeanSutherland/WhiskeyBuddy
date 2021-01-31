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
    private float price;
    private int age;
    private boolean image;
    private boolean wishlist;

    public Whiskey(String name, float rating, String type, float price, int age, boolean image, boolean wishlist) {
        this.name = name;
        this.rating = rating;
        this.type = type;
        this.price = price;
        this.age = age;
        this.image = image;
        this.wishlist = wishlist;
    }

    public String getName() {
        return name;
    }

    public float getRating() {
        return rating;
    }

    public String getType() {return type;}

    public void setRating(float rating) {
        this.rating = rating;
    }

    public float getPrice() { return price; }

    public int getAge() { return age; }

    public boolean getImage() { return image; }

    public boolean getWishlist() {return wishlist; }

    public void setWishlist(boolean wishlist) {
        this.wishlist = wishlist;
    }
}
