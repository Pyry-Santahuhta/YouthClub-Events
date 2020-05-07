package com.example.youthclub_events;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class fullEventInfo extends AppCompatActivity {



    Context context;
    ArrayList<event> eventsList;
    int selectedPosition;
    TextView eventName, eventLocation, eventDateTime, eventAgeGroup, eventDescription;
    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();
        eventsList = new ArrayList<>();
        selectedPosition = getIntent().getIntExtra("selectedPosition", 0);
        eventsList = readAndWriteXML.readXML(context);

        setContentView(R.layout.activity_full_event_info);

        event Event = eventsList.get(selectedPosition);

        eventName = findViewById(R.id.eventNameTv);
        eventLocation = findViewById(R.id.locationTv);
        eventDateTime = findViewById(R.id.eventTimeTv);
        eventAgeGroup = findViewById(R.id.eventAgeGroupTv);
        eventDescription = findViewById(R.id.eventDescriptionTv);


        eventName.setText(Event.name);
        eventLocation.setText("At: " + Event.location);
        eventDateTime.setText("On: "+ Event.dateAndTime);
        eventAgeGroup.setText("For ages: " + Event.ageRange);
        eventDescription.setText(Event.description);


    }





    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }


}
