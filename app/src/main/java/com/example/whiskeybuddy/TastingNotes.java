package com.example.whiskeybuddy;

import java.util.Date;

public class TastingNotes {

    private String nose;
    private String palate;
    private String finish;
    private String extraNotes;
    private Date date;


    public TastingNotes(String nose, String palate, String finish, Date date, String extraNotes) {
        this.nose = nose;
        this.palate = palate;
        this.finish = finish;
        this.date = date;
        this.extraNotes = extraNotes;
    }

}
