package com.example.whiskeybuddy.ui.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Rating;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.whiskeybuddy.AddWhiskeyActivity;
import com.example.whiskeybuddy.MainActivity;
import com.example.whiskeybuddy.NoteElementAdapter;
import com.example.whiskeybuddy.R;
import com.example.whiskeybuddy.WhiskeyElementAdapter;
import com.example.whiskeybuddy.storage.ImageSaver;
import com.example.whiskeybuddy.storage.TastingNote;
import com.example.whiskeybuddy.storage.Whiskey;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewWhiskeyFragment extends Fragment {

    private final String PRICE = "Price: $";
    private final String AGE = "Age: ";

    ViewGroup rootView;
    Whiskey whiskey;
    ListView lView;
    TextView price;
    TextView age;
    CheckBox wishlist;

    public ViewWhiskeyFragment(Whiskey whiskey) {
        this.whiskey = whiskey;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.whiskey_fragment, container, false);

        initialize();

        return rootView;
    }

    private void initialize() {
        String whiskey_price = Float.toString(whiskey.getPrice());
        String whiskey_age = Integer.toString(whiskey.getAge());


        if (whiskey.getPrice() != 0) {
            ((TextView) rootView.findViewById(R.id.price)).setText(PRICE + whiskey_price);
        }
        if (whiskey.getAge() != 0) {
            ((TextView) rootView.findViewById(R.id.age)).setText(AGE + whiskey_age);
        }

        Bitmap bit = new ImageSaver(getContext())
                .setFileName(whiskey.getName() + ".jpg")
                .setExternal(false)//image save in external directory or app folder default value is false
                .setDirectory("images")
                .load();

        ((ImageView) rootView.findViewById(R.id.image)).setImageBitmap(bit);

        ((TextView) rootView.findViewById(R.id.name)).setText(whiskey.getName());
        RatingBar rate = ((RatingBar) rootView.findViewById(R.id.rating));
        rate.setRating(whiskey.getRating());
        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                whiskey.setRating(ratingBar.getRating());
                MainActivity.getDatabase().whiskeyDao().updateWhiskey(whiskey);
            }
        });
        ((Button) rootView.findViewById(R.id.add_note)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(getContext(), AddNotesActivity.class);
                myIntent.putExtra("whiskey", whiskey.getName());
                getActivity().startActivity(myIntent);
            }
        });

        lView = this.rootView.findViewById(R.id.list);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                ViewWhiskeyFragment nextFrag = new ViewWhiskeyFragment(((Whiskey)adapterView.getItemAtPosition(i)));
                ((MainActivity) getActivity()).openWhiskey(nextFrag);
            }
        });

        lView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //delete note
                return false;
            }
        });

        ArrayList<TastingNote> tastingNotes = (ArrayList<TastingNote>) MainActivity.getDatabase().noteDao().findNotesForWhiskey(whiskey.getName());

        NoteElementAdapter adapter = new NoteElementAdapter(getActivity(), 0,  tastingNotes);
        lView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        wishlist = this.rootView.findViewById(R.id.wishlist);
        if (whiskey.getWishlist()) {wishlist.setChecked(true);}
        wishlist.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                whiskey.setWishlist(b);
                MainActivity.getDatabase().whiskeyDao().updateWhiskey(whiskey);
            }
        });

    }


}
