package com.example.whiskeybuddy;

import android.appwidget.AppWidgetProvider;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.whiskeybuddy.storage.AppDatabase;
import com.example.whiskeybuddy.storage.Whiskey;
import com.example.whiskeybuddy.ui.gallery.GalleryFragment;
import com.example.whiskeybuddy.ui.home.HomeFragment;
import com.example.whiskeybuddy.ui.home.ViewWhiskeyFragment;
import com.example.whiskeybuddy.ui.wishlist.WishlistFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.PrintWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private static AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton fab = findViewById(R.id.fab);
        ImageButton wishlist = findViewById(R.id.wishlist);
        ImageButton notes = findViewById(R.id.notes);
        LinearLayout extra_buttons = findViewById(R.id.extra_buttons);
        LinearLayout buttons = findViewById(R.id.buttons);


        fab.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch(motionEvent.getAction()) {

                    case MotionEvent.ACTION_DOWN:
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    Thread.sleep(100);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                buttons.setBackground(getDrawable(R.drawable.buttons_background));
                                extra_buttons.setVisibility(View.VISIBLE);
                            }
                        });
                        return true;

                    case MotionEvent.ACTION_UP:
                        float x = motionEvent.getRawX();
                        if (x < getPointOfView(fab).x) {
                            buttons.setBackground(null);
                            extra_buttons.setVisibility(View.GONE);
                            return false;
                        }
                        float y = motionEvent.getRawY();
                        if (y > getPointOfView(fab).y) {

                            Intent mIntent = new Intent(MainActivity.this, AddWhiskeyActivity.class);
                            MainActivity.this.startActivity(mIntent);

                        } else if (y > getPointOfView(wishlist).y) {

                            Fragment fragmentOnTop = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                            while (!(fragmentOnTop instanceof NavHostFragment)) {
                                onBackPressed();
                                fragmentOnTop = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                            }
                            WishlistFragment nextFrag = new WishlistFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, nextFrag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();

                        } else if (y > getPointOfView(notes).y) {
                            Fragment fragmentOnTop = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                            while (!(fragmentOnTop instanceof NavHostFragment)) {
                                onBackPressed();
                                fragmentOnTop = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                            }
                            GalleryFragment nextFrag = new GalleryFragment();
                            getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment, nextFrag).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).addToBackStack(null).commit();
                        }
                        buttons.setBackground(null);
                        extra_buttons.setVisibility(View.GONE);
                        return true;
                }
                return false;
            }
        });

        initializeDatabase();
    }

    private Point getPointOfView(View view) {
        int[] location = new int[2];
        view.getLocationInWindow(location);
        return new Point(location[0], location[1]);
    }

    private void initializeDatabase() {
        database = AppDatabase.getDatabase(getApplicationContext());
    }

    public static AppDatabase getDatabase() {
        return database;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        //this.getSupportFragmentManager().popBackStack();
        //super.onBackPressed();
        //Fragment fragmentOnTop = this.getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        super.onBackPressed();
    }

}