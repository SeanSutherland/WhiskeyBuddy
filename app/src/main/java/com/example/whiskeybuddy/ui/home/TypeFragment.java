package com.example.whiskeybuddy.ui.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whiskeybuddy.AddWhiskeyActivity;
import com.example.whiskeybuddy.MainActivity;
import com.example.whiskeybuddy.R;
import com.example.whiskeybuddy.WhiskeyElementAdapter;
import com.example.whiskeybuddy.storage.Whiskey;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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

        initialize();

        return rootView;
    }


    SwipeRefreshLayout refresh;
    private void initialize() {

        lView = this.rootView.findViewById(R.id.list);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast toast = Toast.makeText(getContext(), ((Whiskey)adapterView.getItemAtPosition(i)).getName() + "nice", Toast.LENGTH_SHORT);
                //toast.show();

                ViewWhiskeyFragment nextFrag = new ViewWhiskeyFragment(((Whiskey)adapterView.getItemAtPosition(i)));
                ((MainActivity) getActivity()).openWhiskey(nextFrag);
            }
        });

        lView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.findViewById(R.id.delete).setVisibility(View.VISIBLE);
                return false;
            }
        });

        ArrayList<Whiskey> whiskeys = (ArrayList<Whiskey>) MainActivity.getDatabase().whiskeyDao().getWhiskeyByType(type);

        WhiskeyElementAdapter adapter = new WhiskeyElementAdapter(getActivity(), 0,  whiskeys);
        lView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        refresh = this.rootView.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                update();
            }
        });

    }

    public void update() {
        initialize();
        refresh.setRefreshing(false);
    }


}
