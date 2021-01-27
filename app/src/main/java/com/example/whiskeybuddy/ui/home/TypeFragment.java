package com.example.whiskeybuddy.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.whiskeybuddy.R;

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

        ((TextView) rootView.findViewById(R.id.name)).setText(type);

        initialize();

        return rootView;
    }

    private void initialize() {
        lView = this.rootView.findViewById(R.id.list);

    }
}
