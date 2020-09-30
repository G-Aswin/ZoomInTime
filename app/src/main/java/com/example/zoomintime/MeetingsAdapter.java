package com.example.zoomintime;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MeetingsAdapter extends RecyclerView.Adapter<MeetingsAdapter.MeetingsViewHolder> {


    private List<Meeting> meetings = new ArrayList<>();

    @NonNull
    @Override
    public MeetingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.meeting_row, parent, false);

        return new MeetingsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingsViewHolder holder, int position) {
        Meeting current = meetings.get(position);
        holder.containerView.setTag(current);
        holder.meetingName.setText(current.Name);
        holder.meetingTime.setText("" + current.hour + ":" + current.minute);
        holder.meetingUrl.setText(current.Url);

    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    class MeetingsViewHolder extends RecyclerView.ViewHolder{
        public LinearLayout containerView;
        public TextView meetingName;
        public TextView meetingTime;
        public TextView meetingUrl;
        public Button startMeeting;

        public MeetingsViewHolder(View view){
            super(view);
            this.containerView = view.findViewById(R.id.meeting_row);
            this.meetingName = view.findViewById(R.id.meeting_name);
            this.meetingTime = view.findViewById(R.id.meeting_time);
            this.meetingUrl = view.findViewById(R.id.meeting_url);
            this.startMeeting = view.findViewById(R.id.startMeeting);

            this.containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO
                }
            });
            this.startMeeting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    String url = meetingUrl.getText().toString();
                    if (!url.startsWith("http://") || !url.startsWith("https://"))
                    {
                        url = "https://" + url;
                    }
                    Uri uri = Uri.parse(url);
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    if (intent.resolveActivity(context.getPackageManager()) != null) {
                        context.startActivity(intent);
                    }
                    else {
                        Toast.makeText(context, "NULL!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void reload() {
        meetings = MainScreen.database.meetingDao().getAll();
        notifyDataSetChanged();
    }

}
