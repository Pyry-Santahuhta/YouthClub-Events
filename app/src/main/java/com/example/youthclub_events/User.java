package com.example.youthclub_events;

public class User {
    private String username;
    private String emailAddress;
    private String userID;
    private int accountType; // 1 for attendee and 2 for organizer, 0 for admin which can only be set manually from the firebase database, which I have already made
    public User(String username, String emailAddress, int accountType, String userID) {
        this.username = username;
        this.emailAddress = emailAddress;
        this.accountType = accountType;
        this.userID = userID;

    }
    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}
