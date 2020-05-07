package com.example.youthclub_events;


public class eventInProgress extends event {
    boolean ongoing;

    public eventInProgress(String name, String location, String ageRange,  String dateAndTime, String description,  int participants, boolean ongoing){
        this.name = name;
        this.location = location;
        this.ageRange = ageRange;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.attendeeCount = participants;
        this.ongoing = ongoing;
    }

}
