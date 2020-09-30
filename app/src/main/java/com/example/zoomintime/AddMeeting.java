package com.example.zoomintime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddMeeting extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {

    private EditText name = null;
    private Button time;
    private EditText url = null;
    public int hour;
    public int minute;
    private boolean CalSet = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting);

        name = findViewById(R.id.name_input);
        url = findViewById(R.id.url_input);
        time = findViewById(R.id.time_input);


        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment timePicker = new TimePickerFragment();
                timePicker.show(getSupportFragmentManager(), "time picker");
            }
        });

    }


    public void Save(View view){
        String mname = name.getText().toString();
        String murl = url.getText().toString();

        if (mname.equals("") || murl.equals("") || !CalSet){
            Toast.makeText(this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
            return;
        }
        else{
            MainScreen.database.meetingDao().create(mname, murl, hour, minute);
            MainScreen.Reload();
            onBackPressed();
        }

    }

    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        hour = i;
        minute = i1;
        CalSet = true;
    }
}