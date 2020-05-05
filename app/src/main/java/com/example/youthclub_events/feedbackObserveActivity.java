package com.example.youthclub_events;

import androidx.appcompat.app.AppCompatActivity;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_observe);
        eventNameTv = findViewById(R.id.eventNameHeaderTv);
        this.context = getApplicationContext();
        Lw = findViewById(R.id.feedbacklistview);


        eventname = getIntent().getStringExtra("eventname");
        System.out.println(eventname);
        feedbackList = readAndWriteXML.readFeedbackXML(context, eventname);
        stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout. simple_list_item_1, feedbackList);
        System.out.println(feedbackList.size());
        for(int i = 0; i< feedbackList.size(); i++){
            System.out.println(feedbackList.get(i));

        }
        eventNameTv.setText(eventname+"'s feedback: ");
        Lw.setAdapter(stringArrayAdapter);



    }
}
