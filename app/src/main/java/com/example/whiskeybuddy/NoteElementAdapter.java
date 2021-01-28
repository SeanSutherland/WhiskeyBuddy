package com.example.whiskeybuddy;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.whiskeybuddy.storage.TastingNote;
import com.example.whiskeybuddy.storage.Whiskey;

import java.util.ArrayList;
import java.util.List;

public class NoteElementAdapter extends ArrayAdapter<TastingNote> {
    private final Activity context;
    private ArrayList<TastingNote> list;

    public NoteElementAdapter(@NonNull Activity context, int resource, @NonNull List<TastingNote> objects) {
        super(context, R.layout.tasting_notes_fragment, objects);

        this.context = context;
        this.list = (ArrayList<TastingNote>) objects;
    }

    public View getView(int position, View view, ViewGroup parent) {
        if (view == null) {
            LayoutInflater inflater=context.getLayoutInflater();
            view = inflater.inflate(R.layout.tasting_notes_fragment, parent,false);
        }

        TextView nose = (TextView) view.findViewById(R.id.nose);
        TextView palate = (TextView) view.findViewById(R.id.palate);
        TextView finish = (TextView) view.findViewById(R.id.finish);
        TextView notes = (TextView) view.findViewById(R.id.extra_notes);
        TextView date = (TextView) view.findViewById(R.id.date);

        nose.setText(list.get(position).getNose());
        palate.setText(list.get(position).getPalate());
        finish.setText(list.get(position).getFinish());
        String t = list.get(position).getFinish();
        if (t.isEmpty()) {
            notes.setVisibility(View.GONE);
        } else {
            notes.setText(t);
        }

        date.setText(list.get(position).getDate());



        return view;
    }


}
