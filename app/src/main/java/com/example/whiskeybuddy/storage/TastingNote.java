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
    String nose;
    String palate;
    String finish;
    String date;
    String extra_notes;

    public TastingNote(String whiskey, String nose, String palate, String finish, String extra_notes, String date) {
        this.whiskey = whiskey;
        this.nose = nose;
        this.palate = palate;
        this.finish = finish;
        this.extra_notes = extra_notes;
        this.date = date;
    }

    public String getNose() {
        return nose;
    }

    public String getPalate() {
        return palate;
    }

    public String getFinish() {
        return finish;
    }

    public String getDate() {
        return date;
    }

    public String getExtra() { return extra_notes; }

    public long getmyId() {
        return id;
    }
}
