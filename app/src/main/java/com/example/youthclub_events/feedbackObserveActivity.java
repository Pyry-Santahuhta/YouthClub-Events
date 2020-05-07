package com.example.youthclub_events;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class feedbackObserveActivity extends AppCompatActivity {
    TextView eventNameTv;
    ListView Lw;
    ArrayList<String> feedbackList;
    Context context;
    String eventname;
    ArrayAdapter<String> stringArrayAdapter;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_observe);
        eventNameTv = findViewById(R.id.eventNameHeaderTv);
        this.context = getApplicationContext();
        Lw = findViewById(R.id.feedbacklistview);


        eventname = getIntent().getStringExtra("eventname");

        //Search for the correct events feedback from XML
        feedbackList = readAndWriteXML.readFeedbackXML(context, eventname);
        stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout. simple_list_item_1, feedbackList);
        eventNameTv.setText(eventname+"'s feedback: ");
        Lw.setAdapter(stringArrayAdapter);

    }
}
