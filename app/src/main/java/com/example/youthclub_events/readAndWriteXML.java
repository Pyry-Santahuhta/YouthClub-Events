package com.example.youthclub_events;

import android.content.Context;
import android.util.Xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class readAndWriteXML {

    public static void saveToXML(event Event, Context context) {
        File file = context.getFileStreamPath("eventdata.xml");
        if (!file.exists()){
            try {
                XmlSerializer serializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                serializer.setOutput(writer);
                FileOutputStream fileOutputStream = context.openFileOutput("eventdata.xml", Context.MODE_APPEND);
                serializer.setOutput(writer);
                serializer.startDocument("UTF-8", true);
                serializer.startTag(null, "events");
                serializer.endTag(null, "events");
                serializer.endDocument();
                serializer.flush();
                fileOutputStream.write(writer.toString().getBytes());
                fileOutputStream.close();

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            Element root = document.getDocumentElement();
            Element newEvent = document.createElement("event");

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(Event.name));
            newEvent.appendChild(name);

            Element location = document.createElement("location");
            location.appendChild(document.createTextNode(Event.location));
            newEvent.appendChild(location);

            Element agegroup = document.createElement("agegroup");
            agegroup.appendChild(document.createTextNode(Event.ageRange));
            newEvent.appendChild(agegroup);

            Element datetime = document.createElement("datetime");
            datetime.appendChild(document.createTextNode(Event.dateAndTime));
            newEvent.appendChild(datetime);

            Element description = document.createElement("description");
            description.appendChild(document.createTextNode(Event.description));
            newEvent.appendChild(description);

            root.appendChild(newEvent);

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(file.getPath());
            transformer.transform(source, result);

        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            e.printStackTrace();
        }
    }



    public static ArrayList readXML(Context context){
        ArrayList<event> eventsList = new ArrayList<>();
        try{
            InputStream inputStream = context.openFileInput("eventdata.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document XMLDoc = documentBuilder.parse(inputStream);
            NodeList nodeList = XMLDoc.getElementsByTagName("event");

            for (int i = 0; i < nodeList.getLength(); i++){
                String name = XMLDoc.getElementsByTagName("name").item(0).getTextContent();
                String location = XMLDoc.getElementsByTagName("location").item(0).getTextContent();
                String ageGroup = XMLDoc.getElementsByTagName("agegroup").item(0).getTextContent();
                String Datetime = XMLDoc.getElementsByTagName("datetime").item(0).getTextContent();
                String description = XMLDoc.getElementsByTagName("description").item(0).getTextContent();
                event Event = new event(name, location, ageGroup, Datetime, description);
                eventsList.add(Event);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return eventsList;

    }





















}
