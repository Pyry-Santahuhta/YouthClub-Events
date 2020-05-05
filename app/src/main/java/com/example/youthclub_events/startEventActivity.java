package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class startEventActivity extends AppCompatActivity {
    Context context;
    ArrayList<event> eventsList;
    ArrayAdapter<event> arrayAdapter;
    Spinner eventSpinner;
    int selectedPosition;
    Button startEventButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startevent);
        this.context = getApplicationContext();

        eventsList = readAndWriteXML.readXML(context);
        startEventButton = findViewById(R.id.startEventButton);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, eventsList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        eventSpinner = findViewById(R.id.eventSpinner);
        eventSpinner.setAdapter(arrayAdapter);

        startEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedPosition = eventSpinner.getSelectedItemPosition();
                eventInProgress EventInProgress = new eventInProgress(eventsList.get(selectedPosition).name,
                        eventsList.get(selectedPosition).location,
                        eventsList.get(selectedPosition).ageRange,
                        eventsList.get(selectedPosition).dateAndTime,
                        eventsList.get(selectedPosition).description,
                        0, true);

                readAndWriteXML.saveInProgressEventToXML(EventInProgress, context);

                Intent intent = new Intent(startEventActivity.this, eventsInProgressActivity.class);

                startActivity(intent);
            }
        });


    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }


}
