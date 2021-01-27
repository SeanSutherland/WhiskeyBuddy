package com.example.whiskeybuddy.storage;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes",
        foreignKeys = {
                @ForeignKey(
                        entity = Whiskey.class,
                        parentColumns = "name",
                        childColumns = "whiskey",
                        onDelete = ForeignKey.RESTRICT
                )},

        indices = { @Index(value = "id")}
)

public class TastingNote {

    @PrimaryKey(autoGenerate = true)
    long id;

    public String whiskey;
    String notes;
    String nose;
    String palate;
    String finish;
    String date;

    public TastingNote(String whiskey, String notes, String nose, String palate, String finish, String date) {
        this.whiskey = whiskey;
        this.notes = notes;
        this.nose = nose;
        this.palate = palate;
        this.finish = finish;
        this.date = date;
    }

}
