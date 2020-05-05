package com.example.youthclub_events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import java.util.ArrayList;

public class pastEventsActivity extends AppCompatActivity {
    Context context;
    ArrayList<eventInProgress> eventsList;
    RecyclerView eventsRecyclerView;
    RecyclerView.Adapter recyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_events);
        eventsRecyclerView = findViewById(R.id.recyclerView);
        this.context = getApplicationContext();
        eventsList = readAndWriteXML.readInProgressEventXML(context);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new pastEventsAdapter(eventsList, context);
        eventsRecyclerView.setAdapter(recyclerAdapter);



    }
}
