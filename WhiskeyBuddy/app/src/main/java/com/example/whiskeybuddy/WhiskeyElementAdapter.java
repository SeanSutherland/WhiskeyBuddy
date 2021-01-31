package com.example.whiskeybuddy;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.whiskeybuddy.storage.ImageSaver;
import com.example.whiskeybuddy.storage.Whiskey;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WhiskeyElementAdapter extends ArrayAdapter<Whiskey> {
    private final Activity context;
    private ArrayList<Whiskey> list;
    ImageButton delete_button;

    public WhiskeyElementAdapter(@NonNull Activity context, int resource, @NonNull List<Whiskey> objects) {
        super(context, R.layout.whiskey_element, objects);

        this.context = context;
        this.list = (ArrayList<Whiskey>) objects;

    }
    Whiskey whiskey;
    public View getView(int position, View view, ViewGroup parent) {

        whiskey = list.get(position);

        if (view == null) {
            LayoutInflater inflater=context.getLayoutInflater();
            view = inflater.inflate(R.layout.whiskey_element, parent,false);
        }
        System.out.println("Test*" + position);

        TextView name = (TextView) view.findViewById(R.id.name);
        ImageView image = (ImageView) view.findViewById(R.id.image);
        RatingBar rating = (RatingBar) view.findViewById(R.id.rating);

        if (whiskey.getImage()) {
            Bitmap bit = new ImageSaver(getContext())
                    .setFileName(whiskey.getName() + ".jpg")
                    .setExternal(false)//image save in external directory or app folder default value is false
                    .setDirectory("images")
                    .load();
            image.setImageBitmap(bit);
        }

        name.setText(whiskey.getName());
        rating.setRating(whiskey.getRating());

        delete_button = view.findViewById(R.id.delete);
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String whiskey_name = whiskey.getName();
                MainActivity.getDatabase().whiskeyDao().deleteWhiskey(whiskey_name);
                new ImageSaver(getContext())
                        .setFileName(whiskey.getName() + ".jpg")
                        .setExternal(false)//image save in external directory or app folder default value is false
                        .setDirectory("images")
                        .deleteFile();
                ((View)view.getParent()).setVisibility(View.GONE);
            }
        });



        return view;
    }


}
