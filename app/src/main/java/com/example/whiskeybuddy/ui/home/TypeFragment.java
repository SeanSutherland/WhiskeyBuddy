package com.example.whiskeybuddy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.whiskeybuddy.MainActivity;
import com.example.whiskeybuddy.R;
import com.example.whiskeybuddy.WhiskeyElementAdapter;
import com.example.whiskeybuddy.storage.Whiskey;

import java.util.ArrayList;
import java.util.List;

public class TypeFragment extends Fragment {
    String type;
    ListView lView;
    ViewGroup rootView;


    public TypeFragment(String type) {
        this.type = type;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = (ViewGroup) inflater.inflate(
                R.layout.type_fragment, container, false);

        //((TextView) rootView.findViewById(R.id.name)).setText(type);

        initialize();

        return rootView;
    }

    private void initialize() {
        lView = this.rootView.findViewById(R.id.list);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast toast = Toast.makeText(getContext(), ((Whiskey)adapterView.getItemAtPosition(i)).getName(), Toast.LENGTH_SHORT);
                toast.show();

            }
        });

        ArrayList<Whiskey> whiskeys = (ArrayList<Whiskey>) MainActivity.getDatabase().whiskeyDao().getWhiskeyByType(type);

        WhiskeyElementAdapter adapter = new WhiskeyElementAdapter(getActivity(), 0,  whiskeys);
        lView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}
