package com.example.zoomintime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.RadioAccessSpecifier;
import android.view.View;

public class MainScreen extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static MeetingsAdapter meetingsAdapter;
    public static MeetingsDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        database = Room
                .databaseBuilder(getApplicationContext(), MeetingsDatabase.class, "meetings")
                .allowMainThreadQueries()
                .build();

        recyclerView = findViewById(R.id.recycler_view);
        layoutManager = new LinearLayoutManager(this);
        meetingsAdapter = new MeetingsAdapter();

        meetingsAdapter.reload();

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(meetingsAdapter);
    }

    public void AddMeeting(View view){
        Intent intent = new Intent(this, AddMeeting.class);
        startActivity(intent);
    }

    public void RemoveAll(View view){
        database.meetingDao().DeleteAll();
        Reload();
    }

    public static void Reload(){
        meetingsAdapter.reload();
    }


}