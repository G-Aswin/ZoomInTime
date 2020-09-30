package com.example.zoomintime;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.Calendar;
import java.util.List;

@Dao
public interface MeetingDao {

    @Query("INSERT INTO meetings_list (name, url, hour, minute) VALUES(:name, :url, :hour, :minute)")
    void create(String name, String url, int hour, int minute);

    @Query("SELECT * FROM meetings_list")
    List<Meeting> getAll();

    @Query("DELETE FROM meetings_list")
    void DeleteAll();

}
