package com.example.youthclub_events;

import androidx.annotation.NonNull;

public class event {


    String dateAndTime;
    String name;
    String ageRange;
    //AgeRangeID is for setting the radiogroup when editing
    int ageRangeID;
    String description;
    String location;
    int attendeeCount;
    public event(String name, String location, String ageRange, int agerangeID, String dateAndTime, String description){
        this.name = name;
        this.location = location;
        this.ageRange = ageRange;
        this.ageRangeID = agerangeID;
        this.dateAndTime = dateAndTime;
        this.description = description;
        this.attendeeCount = 0;

    }

    public event(){ }
    @NonNull
    @Override
    public String toString() {
        return name;
    }
}
