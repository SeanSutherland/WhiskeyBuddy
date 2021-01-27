package com.example.whiskeybuddy.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TastingNoteDAO {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addTastingNote(TastingNote tastingNote);

    @Query("select * from notes WHERE whiskey=:whiskeyName")
    List<TastingNote> findNotesForWhiskey(String whiskeyName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTastingNote(TastingNote tastingNote);

    @Query("delete from notes where id = :id")
    void delete(long id);
}
