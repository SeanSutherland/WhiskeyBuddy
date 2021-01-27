package com.example.whiskeybuddy.storage;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface WhiskeyDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void addWhiskey(Whiskey whiskey);

    @Query("select * from Whiskey")
    public List<Whiskey> getAllWhiskey();

    @Query("select * from whiskey where name = :whiskeyName")
    public List<Whiskey> getWhiskey(String whiskeyName);

    @Query("select * from whiskey where type = :whiskeyType")
    public List<Whiskey> getWhiskeyByType(String whiskeyType);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateWhiskey(Whiskey whiskey);

    @Query("delete from whiskey")
    void removeAllUsers();


}
