package com.example.whiskeybuddy;

import java.util.Date;

public class TastingNotes {

    private String nose;
    private String palate;
    private String finish;
    private String extraNotes;
    private String date;


    public TastingNotes(String nose, String palate, String finish, String date, String extraNotes) {
        this.nose = nose;
        this.palate = palate;
        this.finish = finish;
        this.date = date;
        this.extraNotes = extraNotes;
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

    public String getExtraNotes() {
        return extraNotes;
    }

    public String getDate() {
        return date;
    }
}
