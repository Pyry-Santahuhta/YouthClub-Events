package com.example.youthclub_events;
import android.content.Context;
import android.util.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
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
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class readAndWriteXML {

    public static void saveEventToXML(event Event, Context context) {
        File file = context.getFileStreamPath("eventdata.xml");
        if (!file.exists()) {
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

            Element ageGroupNum = document.createElement("ageGroupNum");
            ageGroupNum.appendChild(document.createTextNode(String.valueOf(Event.ageRangeID)));
            newEvent.appendChild(ageGroupNum);

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


    public static void saveInProgressEventToXML(eventInProgress EventInProgress, Context context) {
        File file = context.getFileStreamPath("eventsInProgressdata.xml");
        if (!file.exists()) {
            try {
                XmlSerializer serializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                serializer.setOutput(writer);
                FileOutputStream fileOutputStream = context.openFileOutput("eventsInProgressdata.xml", Context.MODE_APPEND);
                serializer.setOutput(writer);
                serializer.startDocument("UTF-8", true);
                serializer.startTag(null, "eventsInProgress");
                serializer.endTag(null, "eventsInProgress");
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
            Element newEvent = document.createElement("eventInProgress");

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(EventInProgress.name));
            newEvent.appendChild(name);

            Element location = document.createElement("location");
            location.appendChild(document.createTextNode(EventInProgress.location));
            newEvent.appendChild(location);

            Element agegroup = document.createElement("agegroup");
            agegroup.appendChild(document.createTextNode(EventInProgress.ageRange));
            newEvent.appendChild(agegroup);

            Element datetime = document.createElement("datetime");
            datetime.appendChild(document.createTextNode(EventInProgress.dateAndTime));
            newEvent.appendChild(datetime);

            Element description = document.createElement("description");
            description.appendChild(document.createTextNode(EventInProgress.description));
            newEvent.appendChild(description);

            for (int i = 0; i < EventInProgress.feedBack.size(); i++) {
                Element feedback = document.createElement("feedback" + i);
                feedback.appendChild(document.createTextNode(EventInProgress.feedBack.get(i)));
                newEvent.appendChild(feedback);
            }

            Element participants = document.createElement("participants");
            participants.appendChild(document.createTextNode(String.valueOf(EventInProgress.attendeeCount)));
            newEvent.appendChild(participants);

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


    public static void editXML(Context context, event Event, int selectedPosition) {
        File file = context.getFileStreamPath("eventdata.xml");
        if (!file.exists()) {
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
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(context.openFileInput("eventdata.xml"));

            NodeList nodeList = document.getElementsByTagName("event");
            Element element = null;

            element = (Element) nodeList.item(selectedPosition);
            Node name = element.getElementsByTagName("name").item(0).getFirstChild();
            name.setNodeValue(Event.name);

            Node location = element.getElementsByTagName("location").item(0).getFirstChild();
            location.setNodeValue(Event.location);

            Node ageGroup = element.getElementsByTagName("agegroup").item(0).getFirstChild();
            ageGroup.setNodeValue(Event.ageRange);

            Node ageGroupNum = element.getElementsByTagName("ageGroupNum").item(0).getFirstChild();
            ageGroupNum.setNodeValue(String.valueOf(Event.ageRangeID));

            Node dateTime = element.getElementsByTagName("datetime").item(0).getFirstChild();
            dateTime.setNodeValue(Event.dateAndTime);

            Node description = element.getElementsByTagName("description").item(0).getFirstChild();
            description.setNodeValue(Event.description);

            DOMSource domSource = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(file.getPath());
            transformer.transform(domSource, result);


        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
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
                String name = XMLDoc.getElementsByTagName("name").item(i).getTextContent();
                String location = XMLDoc.getElementsByTagName("location").item(i).getTextContent();
                String ageGroup = XMLDoc.getElementsByTagName("agegroup").item(i).getTextContent();
                int ageGroupNum = Integer.parseInt(XMLDoc.getElementsByTagName("ageGroupNum").item(i).getTextContent());
                String Datetime = XMLDoc.getElementsByTagName("datetime").item(i).getTextContent();
                String description = XMLDoc.getElementsByTagName("description").item(i).getTextContent();
                event Event = new event(name, location, ageGroup, ageGroupNum, Datetime, description);
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



    public static ArrayList readInProgressEventXML(Context context){
        int n = 0;
        ArrayList<eventInProgress> eventsInProgressList = new ArrayList<>();
        ArrayList<String> feedbacklist = new ArrayList<>();
        try{
            InputStream inputStream = context.openFileInput("eventsInProgressdata.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document XMLDoc = documentBuilder.parse(inputStream);
            NodeList nodeList = XMLDoc.getElementsByTagName("event");
            for (int i = 0; i < nodeList.getLength(); i++){
                String name = XMLDoc.getElementsByTagName("name").item(i).getTextContent();
                String location = XMLDoc.getElementsByTagName("location").item(i).getTextContent();
                String ageGroup = XMLDoc.getElementsByTagName("agegroup").item(i).getTextContent();
                String Datetime = XMLDoc.getElementsByTagName("datetime").item(i).getTextContent();
                String description = XMLDoc.getElementsByTagName("description").item(i).getTextContent();
                while (true){
                    if (XMLDoc.getElementsByTagName("feedback"+n) == null){
                        break;
                    }
                    String feedback = XMLDoc.getElementsByTagName("feedback"+n).item(i).getTextContent();
                    n++;
                    i++;
                    feedbacklist.add(feedback);
                }
                int participants =Integer.parseInt(XMLDoc.getElementsByTagName("participants").item(i).getTextContent());


                eventInProgress EventInProgress = new eventInProgress(name, location, ageGroup, Datetime, description, feedbacklist, participants);
                eventsInProgressList.add(EventInProgress);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return eventsInProgressList;

    }

}
