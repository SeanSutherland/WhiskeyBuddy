package com.example.whiskeybuddy;

import android.app.Activity;
import android.content.Context;
import android.media.Rating;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import kotlinx.coroutines.selects.WhileSelectKt;

public class WhiskeyElementAdapter extends ArrayAdapter<WhiskeyElement> {
    private final Activity context;
    private ArrayList<WhiskeyElement> list;

    public WhiskeyElementAdapter(@NonNull Activity context, int resource, @NonNull List<WhiskeyElement> objects) {
        super(context, R.layout.whiskey_element);

        this.context = context;
        this.list = (ArrayList<WhiskeyElement>) objects;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.whiskey_element, null,true);

        TextView name = (TextView) rowView.findViewById(R.id.name);
        ImageView image = (ImageView) rowView.findViewById(R.id.image);
        RatingBar rating = (RatingBar) rowView.findViewById(R.id.rating);

        name.setText(list.get(position).getName());
        //image.setImageBitmap(list.get(position).getImage());
        rating.setRating(list.get(position).getRating());



        return rowView;
    }
}
