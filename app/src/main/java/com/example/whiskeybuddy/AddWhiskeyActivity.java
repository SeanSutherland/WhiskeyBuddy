package com.example.whiskeybuddy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.whiskeybuddy.storage.ImageSaver;
import com.example.whiskeybuddy.storage.Whiskey;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;

public class AddWhiskeyActivity extends AppCompatActivity {

    EditText age;
    EditText price;
    EditText name;
    Spinner type;
    ImageButton image;
    boolean imageAdded = false;
    Button add_button;
    Bitmap bitmap = null;
    CheckBox wishlist;

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

        image = (ImageButton) findViewById(R.id.photo);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), 3);

            }
        });

        name = (EditText) findViewById(R.id.name);
        age = (EditText) findViewById(R.id.age);
        price = (EditText) findViewById(R.id.price);
        type = (Spinner) findViewById(R.id.type);
        wishlist = (CheckBox) findViewById(R.id.wishlist);



        return;
    }

    private void addWhiskey() throws InvalidWhiskey{
        int whiskey_age;
        float whiskey_price;
        byte[] byteArray = null;

        String whiskey_name = name.getText().toString();
        String whiskey_type = type.getSelectedItem().toString();

        if (imageAdded) {
            try {
                new ImageSaver(this)
                        .setFileName(whiskey_name + ".jpg")
                        .setExternal(false)//image save in external directory or app folder default value is false
                        .setDirectory("images")
                        .save(bitmap); //Bitmap from your code

                System.out.println("Test SUcess");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

        boolean wish = wishlist.isChecked();

        System.out.println("Name : " + whiskey_name + ", Type : " + whiskey_type + ", Age : " + whiskey_age + ", Price : " + whiskey_price);
        Whiskey whiskey = new Whiskey(whiskey_name, 0, whiskey_type, whiskey_price, whiskey_age, imageAdded, wish);

        MainActivity.getDatabase().whiskeyDao().addWhiskey(whiskey);

        super.onBackPressed();
        //write to database

    }

    private class InvalidWhiskey extends Exception{
        InvalidWhiskey() {
            super("Missing argument");
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        //Detects request codes
        if(requestCode==3 && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImage);
                image.setImageBitmap(bitmap);
                imageAdded = true;
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}

