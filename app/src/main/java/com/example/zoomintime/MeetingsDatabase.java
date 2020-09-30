package com.example.zoomintime;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Meeting.class}, version = 1)
public abstract class MeetingsDatabase extends RoomDatabase {
    public abstract MeetingDao meetingDao();
}
