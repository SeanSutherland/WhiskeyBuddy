package com.example.whiskeybuddy.ui.home;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whiskeybuddy.AddWhiskeyActivity;
import com.example.whiskeybuddy.MainActivity;
import com.example.whiskeybuddy.R;
import com.example.whiskeybuddy.storage.TastingNote;
import com.example.whiskeybuddy.storage.Whiskey;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddNotesActivity extends AppCompatActivity {

    EditText notes_item;
    EditText nose_item;
    EditText palate_item;
    EditText finish_item;
    TextView date_item;
    Button add_button;
    String whiskey;
    String date;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_notes_activity);

        whiskey = getIntent().getExtras().getString("whiskey");

        initialize();

    }

    private void initialize() {
        notes_item = findViewById(R.id.extra_notes);
        nose_item = findViewById(R.id.nose);
        palate_item = findViewById(R.id.palate);
        finish_item = findViewById(R.id.finish);
        date_item = findViewById(R.id.date);
        add_button = findViewById(R.id.add_button);
        ((TextView) findViewById(R.id.name)).setText(whiskey);

        Date c = Calendar.getInstance().getTime();
        System.out.println("Current time => " + c);
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        date = df.format(c);

        date_item.setText(date);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addNote();
            }
        });


    }

    private void addNote() {
        String notes = notes_item.getText().toString();
        String nose = nose_item.getText().toString();
        String palate = palate_item.getText().toString();
        String finish = finish_item.getText().toString();

        TastingNote tastingNote = new TastingNote(whiskey, nose, palate, finish, notes, date);

        MainActivity.getDatabase().noteDao().addTastingNote(tastingNote);

        super.onBackPressed();

    }

}
