package com.example.whiskeybuddy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.whiskeybuddy.storage.Whiskey;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class WhiskeyElementAdapter extends ArrayAdapter<Whiskey> {
    private final Activity context;
    private ArrayList<Whiskey> list;

    public WhiskeyElementAdapter(@NonNull Activity context, int resource, @NonNull List<Whiskey> objects) {
        super(context, R.layout.whiskey_element, objects);

        this.context = context;
        this.list = (ArrayList<Whiskey>) objects;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater=context.getLayoutInflater();
            view = inflater.inflate(R.layout.whiskey_element, parent,false);
        }
        System.out.println("Test*" + position);

        TextView name = (TextView) view.findViewById(R.id.name);
        //ImageView image = (ImageView) rowView.findViewById(R.id.image);
        RatingBar rating = (RatingBar) view.findViewById(R.id.rating);

        name.setText(list.get(position).getName());
        //image.setImageBitmap(list.get(position).getImage());
        rating.setRating(list.get(position).getRating());



        return view;
    }


}
