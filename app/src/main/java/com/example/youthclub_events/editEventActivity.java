package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class editEventActivity extends AppCompatActivity {

    Button editEventButton;
    EditText eventNameID, eventLocationID, dateAndTimeID, eventDescriptionID;
    RadioGroup ageGroupID;
    RadioButton ageGroupButton;
    String eventName, eventLocation, eventDateAndTime, ageGroup, eventDescription;
    int ageGroupNum;
    Context context;
    ArrayList<event> eventsList;
    int selectedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();
        eventsList = new ArrayList<>();
        selectedPosition = getIntent().getIntExtra("selectedPosition", 0);
        eventsList = readAndWriteXML.readXML(context);

        setContentView(R.layout.activity_edit_event);
        editEventButton = findViewById(R.id.editButton);
        eventNameID = findViewById(R.id.eventNameText);
        eventLocationID = findViewById(R.id.eventLocationText);
        dateAndTimeID = findViewById(R.id.eventDateText);
        ageGroupID = findViewById(R.id.radioGroup);
        eventDescriptionID = findViewById(R.id.eventDescriptionText);


        event Event = eventsList.get(selectedPosition);



        eventNameID.setText(Event.name);
        eventLocationID.setText(Event.location);
        dateAndTimeID.setText(Event.dateAndTime);
        eventDescriptionID.setText(Event.description);
        ageGroupID.check(Event.ageRangeID);

        editEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = eventNameID.getText().toString();
                eventLocation = eventLocationID.getText().toString();
                eventDateAndTime = dateAndTimeID.getText().toString();
                int checkedRadioID = ageGroupID.getCheckedRadioButtonId();
                eventDescription = eventDescriptionID.getText().toString();
                if (checkedRadioID == -1){
                    Toast.makeText(editEventActivity.this, "Please select an age group", Toast.LENGTH_SHORT).show();
                    ageGroupID.requestFocus();
                }else if(eventName.isEmpty() && eventLocation.isEmpty() && eventDateAndTime.isEmpty() && eventDescription.isEmpty()) {
                    Toast.makeText(editEventActivity.this, "Please enter event info!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ageGroupButton = findViewById(checkedRadioID);
                    ageGroup = ageGroupButton.getText().toString();
                    ageGroupNum = checkedRadioID;
                }

                if(eventName.isEmpty()){
                    eventNameID.setError("Please enter a name for the event");
                    eventNameID.requestFocus();
                }else if(eventLocation.isEmpty()){
                    eventLocationID.setError("Please enter a location for the event");
                    eventLocationID.requestFocus();
                }else if(eventDateAndTime.isEmpty()){
                    dateAndTimeID.setError("Please enter a date and time for the event");
                    dateAndTimeID.requestFocus();
                }else if(eventDescription.isEmpty()){
                    eventDescriptionID.setError("Please enter a description for the event");
                    eventDescriptionID.requestFocus();
                }
                else{
                   event Event = new event(eventName, eventLocation, ageGroup, ageGroupNum, eventDateAndTime, eventDescription);
                   readAndWriteXML.saveToXML(Event, context);

                }
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
