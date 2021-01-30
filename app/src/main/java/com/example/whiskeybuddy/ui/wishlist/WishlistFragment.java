package com.example.whiskeybuddy.ui.wishlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.whiskeybuddy.MainActivity;
import com.example.whiskeybuddy.R;
import com.example.whiskeybuddy.WhiskeyElementAdapter;
import com.example.whiskeybuddy.storage.Whiskey;
import com.example.whiskeybuddy.ui.home.ViewWhiskeyFragment;

import java.util.ArrayList;

public class WishlistFragment extends Fragment {

    ListView lView;
    View rootView;
    SwipeRefreshLayout refresh;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_wishlist, container, false);
        initialize();
        return rootView;
    }

    public void openWhiskey(Whiskey whiskey) {
        ViewWhiskeyFragment nextFrag = new ViewWhiskeyFragment(whiskey);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, nextFrag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
    }

    private void initialize() {

        lView = this.rootView.findViewById(R.id.list);
        lView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast toast = Toast.makeText(getContext(), ((Whiskey)adapterView.getItemAtPosition(i)).getName() + "nice", Toast.LENGTH_SHORT);
                //toast.show();
                openWhiskey((Whiskey)adapterView.getItemAtPosition(i));
            }
        });

        lView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                view.findViewById(R.id.delete).setVisibility(View.VISIBLE);
                return false;
            }
        });

        ArrayList<Whiskey> whiskeys = (ArrayList<Whiskey>) MainActivity.getDatabase().whiskeyDao().getWishlist(true);

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