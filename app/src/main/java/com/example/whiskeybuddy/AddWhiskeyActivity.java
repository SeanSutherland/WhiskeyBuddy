package com.example.whiskeybuddy;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWhiskeyActivity extends AppCompatActivity {

    EditText age;
    EditText price;
    EditText name;
    Spinner type;

    Button add_button;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        initialize();

    }

    private void initialize() {
        add_button = (Button) findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    addWhiskey();
                } catch (InvalidWhiskey error) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Missing arguments", Toast.LENGTH_SHORT);
                    toast.show();
                }
            }
        });

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        price = (EditText) findViewById(R.id.price);
        type = (Spinner) findViewById(R.id.type);



        return;
    }

    private void addWhiskey() throws InvalidWhiskey{
        int whiskey_age;
        float whiskey_price;


        String whiskey_name = name.getText().toString();
        String whiskey_type = type.getSelectedItem().toString();

        try {
            whiskey_age = Integer.parseInt(age.getText().toString());
        } catch (NumberFormatException error) {
            whiskey_age = 0;
        }

        try {
            whiskey_price = Float.parseFloat(price.getText().toString());
        } catch (NumberFormatException error) {
            whiskey_price = 0;
        }

        if (whiskey_name.isEmpty()) {
            throw new InvalidWhiskey();
        }

        System.out.println("Name : " + whiskey_name + ", Type : " + whiskey_type + ", Age : " + whiskey_age + ", Price : " + whiskey_price);

        super.onBackPressed();
        //write to database

    }

    private class InvalidWhiskey extends Exception{
        InvalidWhiskey() {
            super("Missing argument");
        }
    }
}

