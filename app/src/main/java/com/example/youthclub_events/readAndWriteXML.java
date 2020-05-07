package com.example.youthclub_events;
import android.content.Context;
import android.util.Xml;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlSerializer;
import java.io.File;
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
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        All of the data from events, events that are in progress, events that have ended and their feedback's are saved to XML.
//        This class provides all of the reading, writing, editing and deleting for our XML files.
//        Every time an XML file is being written on or edited, we check that the file exists and if(!file.exists()) We create it with a serializer and set the root element.
//        Writing happens by firs parsing the file into a document, then getting the root and editing and creating new children to the root, the transformer turns our strings into XMl objects.
//        XML reading returns simple arrayLists of the wanted items.
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


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


            Element participants = document.createElement("participants");
            participants.appendChild(document.createTextNode(String.valueOf(EventInProgress.attendeeCount)));
            newEvent.appendChild(participants);

            Element ongoing = document.createElement("ongoing");
            ongoing.appendChild(document.createTextNode(String.valueOf(EventInProgress.ongoing)));
            newEvent.appendChild(ongoing);

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


        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
        }


    }

    public static void deleteEvent(Context context, int selectedPosition) {
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
            element.getParentNode().removeChild(element);

            DOMSource domSource = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(file.getPath());
            transformer.transform(domSource, result);


        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }


    }


    public static ArrayList<event> readXML(Context context){
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
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return eventsList;

    }



    public static ArrayList<eventInProgress> readInProgressEventXML(Context context){
        int n = 0;
        ArrayList<eventInProgress> eventsInProgressList = new ArrayList<>();

        try{
            InputStream inputStream = context.openFileInput("eventsInProgressdata.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document XMLDoc = documentBuilder.parse(inputStream);
            NodeList nodeList = XMLDoc.getElementsByTagName("eventInProgress");
            for (int i = 0; i < nodeList.getLength(); i++){
                String name = XMLDoc.getElementsByTagName("name").item(i).getTextContent();
                String location = XMLDoc.getElementsByTagName("location").item(i).getTextContent();
                String ageGroup = XMLDoc.getElementsByTagName("agegroup").item(i).getTextContent();
                String Datetime = XMLDoc.getElementsByTagName("datetime").item(i).getTextContent();
                String description = XMLDoc.getElementsByTagName("description").item(i).getTextContent();
                int participants = Integer.parseInt(XMLDoc.getElementsByTagName("participants").item(i).getTextContent());
                boolean Boolean = java.lang.Boolean.parseBoolean(XMLDoc.getElementsByTagName("ongoing").item(i).getTextContent());
                eventInProgress EventInProgress = new eventInProgress(name, location, ageGroup, Datetime, description, participants, Boolean);
                eventsInProgressList.add(EventInProgress);
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return eventsInProgressList;
    }


    public static void editOngoingXML(Context context, boolean onGoing, int selectedPosition) {
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

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(context.openFileInput("eventsInProgressdata.xml"));

            NodeList nodeList = document.getElementsByTagName("eventInProgress");
            Element element = null;

            element = (Element) nodeList.item(selectedPosition);
            Node ongoing = element.getElementsByTagName("ongoing").item(0).getFirstChild();
            ongoing.setNodeValue(String.valueOf(onGoing));

            DOMSource domSource = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(file.getPath());
            transformer.transform(domSource, result);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }


    }
    public static void addParticipantInprogressXML(Context context, int selectedPosition) {
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

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(context.openFileInput("eventsInProgressdata.xml"));

            NodeList nodeList = document.getElementsByTagName("eventInProgress");
            Element element = null;

            element = (Element) nodeList.item(selectedPosition);
            Node participants = element.getElementsByTagName("participants").item(0).getFirstChild();

            participants.setNodeValue(String.valueOf(Integer.parseInt(participants.getNodeValue())+1));

            DOMSource domSource = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(file.getPath());
            transformer.transform(domSource, result);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

    }

    public static void writeFeedbackXML(Context context, feedback Feedback) {
        File file = context.getFileStreamPath("eventsFeedback.xml");
        if (!file.exists()) {
            try {
                XmlSerializer serializer = Xml.newSerializer();
                StringWriter writer = new StringWriter();
                serializer.setOutput(writer);
                FileOutputStream fileOutputStream = context.openFileOutput("eventsFeedback.xml", Context.MODE_APPEND);
                serializer.setOutput(writer);
                serializer.startDocument("UTF-8", true);
                serializer.startTag(null, "eventsfeedback");
                serializer.endTag(null, "eventsfeedback");
                serializer.endDocument();
                serializer.flush();
                fileOutputStream.write(writer.toString().getBytes());
                fileOutputStream.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(context.openFileInput("eventsFeedback.xml"));
            Element root = document.getDocumentElement();
            Element newEvent = document.createElement("event");

            Element name = document.createElement("name");
            name.appendChild(document.createTextNode(Feedback.eventName));
            newEvent.appendChild(name);

            Element feedback = document.createElement("feedback");
            feedback.appendChild(document.createTextNode(Feedback.comment));
            newEvent.appendChild(feedback);

            root.appendChild(newEvent);

            DOMSource source = new DOMSource(document);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            StreamResult result = new StreamResult(file.getPath());
            transformer.transform(source, result);

        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<String> readFeedbackXML(Context context, String eventName){

        ArrayList<String> feedbackList= new ArrayList<>();
        try{
            InputStream inputStream = context.openFileInput("eventsFeedback.xml");
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document XMLDoc = documentBuilder.parse(inputStream);
            NodeList nodeList = XMLDoc.getElementsByTagName("event");
            for (int i = 0; i < nodeList.getLength(); i++){
                String name = XMLDoc.getElementsByTagName("name").item(i).getTextContent();
                String feedbackComment = XMLDoc.getElementsByTagName("feedback").item(i).getTextContent();

                if(name.equals(eventName)){
                    feedbackList.add(feedbackComment);
                }

            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return feedbackList;

    }

}
