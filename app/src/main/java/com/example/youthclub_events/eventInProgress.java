package com.example.youthclub_events;

import java.util.ArrayList;

public class eventInProgress extends event {
    ArrayList<String> feedBack;

    public eventInProgress(String name, String location, String ageRange,  String dateAndTime, String description, ArrayList<String> list, int participants){
        this.name = name;
        this.location = location;
        this.ageRange = ageRange;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.feedBack = list;
        this.attendeeCount = participants;

    }


    public void addFeedBack(String s) {
        this.feedBack.add(s);
    }
}
