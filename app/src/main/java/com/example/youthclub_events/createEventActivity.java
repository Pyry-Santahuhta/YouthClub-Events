package com.example.youthclub_events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.io.FileOutputStream;
import java.io.IOException;

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
    String eventName, eventLocation, eventDateAndTime, ageGroup, eventDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_event);
        createEventButton = findViewById(R.id.createButton);
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
                ageGroup = findViewById(checkedRadioID).toString();
                eventDescription = eventDescriptionID.getText().toString();
                if(eventName.isEmpty() && eventLocation.isEmpty() && eventDateAndTime.isEmpty()){
                    Toast.makeText(createEventActivity.this, "Please enter event info!", Toast.LENGTH_SHORT).show();
                }else if(eventName.isEmpty()){
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
                }else{
                   event Event = new event(eventName, eventLocation, ageGroup, eventDateAndTime, eventDescription);
                   saveToXML(Event);

                }

            }
        });




    }
    public void saveToXML(event Event) {
        Document dom;
        Element e = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            dom = db.newDocument();
            Element rootEle = dom.createElement("Event");

            e = dom.createElement("Name");
            e.appendChild(dom.createTextNode(Event.name));
            rootEle.appendChild(e);

            e = dom.createElement("Location");
            e.appendChild(dom.createTextNode(Event.location));
            rootEle.appendChild(e);

            e = dom.createElement("Age group");
            e.appendChild(dom.createTextNode(Event.ageRange));
            rootEle.appendChild(e);

            e = dom.createElement("Date and time");
            e.appendChild(dom.createTextNode(Event.dateAndTime));
            rootEle.appendChild(e);

            e = dom.createElement("Description");
            e.appendChild(dom.createTextNode(Event.description));
            rootEle.appendChild(e);

            dom.appendChild(rootEle);

            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
                tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "roles.dtd");
                tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

                tr.transform(new DOMSource(dom),
                        new StreamResult(new FileOutputStream("eventData.xml")));

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch (ParserConfigurationException pce) {
            System.out.println("UsersXML: Error trying to instantiate DocumentBuilder " + pce);
        }
    }



    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }


}
