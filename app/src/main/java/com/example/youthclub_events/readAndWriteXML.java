package com.example.youthclub_events;

import android.content.Context;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

public class readAndWriteXML {
    public static void saveToXML(event Event, Context context) {
        OutputStreamWriter osw = null;
        String s = ("<event>\n" +
                "   <name>" + Event.name + "</name>\n" +
                "    <location>" + Event.location + "</location>\n" +
                "    <agegroup>" + Event.ageRange + "</agegroup>\n" +
                "    <datetime>" + Event.dateAndTime + "</datetime>\n" +
                "    <description>" + Event.description + "</description>\n"+
                "</event>");
        try {
            osw = new OutputStreamWriter(context.openFileOutput("eventdata.xml", Context.MODE_PRIVATE));
            osw.write(s);
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void readXML(Context context){
        String output = "";
        try{
            InputStream inputStream = context.openFileInput("eventdata.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document XMLDoc = documentBuilder.parse(inputStream);
            String name = XMLDoc.getElementsByTagName("name").item(0).getTextContent();
            String location = XMLDoc.getElementsByTagName("location").item(0).getTextContent();
            String ageGroup = XMLDoc.getElementsByTagName("agegroup").item(0).getTextContent();
            String Datetime = XMLDoc.getElementsByTagName("datetime").item(0).getTextContent();
            String description = XMLDoc.getElementsByTagName("description").item(0).getTextContent();

            System.out.println(name);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }





















}
