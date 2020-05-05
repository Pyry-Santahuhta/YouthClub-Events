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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;


public class createEventActivity extends AppCompatActivity {

    Button createEventButton;
    EditText eventNameID, eventLocationID, dateAndTimeID, eventDescriptionID;
    RadioGroup ageGroupID;
    RadioButton ageGroupButton;
    String eventName, eventLocation, eventDateAndTime, ageGroup, eventDescription;
    Context context;
    int ageGroupNum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        setContentView(R.layout.activity_create_event);
        createEventButton = findViewById(R.id.createbutton);
        eventNameID = findViewById(R.id.eventNameText);
        eventLocationID = findViewById(R.id.eventLocationText);
        dateAndTimeID = findViewById(R.id.eventDateText);
        ageGroupID = findViewById(R.id.radioGroup);
        eventDescriptionID = findViewById(R.id.eventDescriptionText);

        createEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eventName = eventNameID.getText().toString();
                eventLocation = eventLocationID.getText().toString();
                eventDateAndTime = dateAndTimeID.getText().toString();
                int checkedRadioID = ageGroupID.getCheckedRadioButtonId();
                eventDescription = eventDescriptionID.getText().toString();
                if (checkedRadioID == -1){
                    Toast.makeText(createEventActivity.this, "Please select an age group", Toast.LENGTH_SHORT).show();
                    ageGroupID.requestFocus();
                }else if(eventName.isEmpty() && eventLocation.isEmpty() && eventDateAndTime.isEmpty() && eventDescription.isEmpty()) {
                    Toast.makeText(createEventActivity.this, "Please enter event info!", Toast.LENGTH_SHORT).show();
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
                    readAndWriteXML.saveEventToXML(Event, context);
                    Intent intent = new Intent(createEventActivity.this, MainActivity.class);
                    startActivity(intent);

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
