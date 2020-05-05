package com.example.youthclub_events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class eventsInProgressActivity extends AppCompatActivity {
    RecyclerView eventsInProgressRecyclerView;
    ArrayList<eventInProgress> eventInProgressList;
    Context context;
    RecyclerView.Adapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_in_progress);
        eventsInProgressRecyclerView = findViewById(R.id.recyclerView);
        this.context = getApplicationContext();
        eventInProgressList = readAndWriteXML.readInProgressEventXML(context);
        eventsInProgressRecyclerView.setHasFixedSize(true);
        eventsInProgressRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new SecondaryAdapter(eventInProgressList, context);
        eventsInProgressRecyclerView.setAdapter(recyclerAdapter);
    }


}
