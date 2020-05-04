package com.example.youthclub_events;

import java.io.Serializable;

public class account implements Serializable {
    String name;
    String password;
    int accountType; // 0 = admin, 1 = attendee, 2 = organizer
    public account(String name, String password, int accountType){
       this.name = name;
       this.password = password;
       this.accountType = accountType;


    }

    account adminAccount = new account("admin", "ADMIN", 0);
}
