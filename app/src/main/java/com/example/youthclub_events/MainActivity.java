package com.example.youthclub_events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Objects;


public class MainActivity extends AppCompatActivity {
    Context context;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    TextView welcometext;
    Button listEventsButton, startEventButton, createEventButton, eventsInProgressButton, pastEventsButton;
    FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            loadActivity("LOGIN");
        }

        super.onCreate(savedInstanceState);

        this.context = getApplicationContext();
        setContentView(R.layout.activity_main);

        listEventsButton = findViewById(R.id.listEvents);
        startEventButton = findViewById(R.id.startEvent);
        createEventButton = findViewById(R.id.createEvent);
        eventsInProgressButton = findViewById(R.id.eventsInProgress);
        pastEventsButton = findViewById(R.id.viewPastEventsButton);

        drawerLayout = findViewById(R.id.drawer_layout);
        welcometext = findViewById(R.id.welcometext);
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    welcometext.setText("Welcome "+ user.getUsername()+"!");

                    if (user.getAccountType() == 1){
                        startEventButton.setVisibility(View.GONE);
                        createEventButton.setVisibility(View.GONE);
                    }else{
                        startEventButton.setVisibility(View.VISIBLE);
                        createEventButton.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.profile){
                    loadActivity("PROFILE");
                }else if (id == R.id.log_out){
                    firebaseAuth.signOut();
                    loadActivity("LOGIN");
                }
                return true;

            }
        });
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v == findViewById(R.id.listEvents)){
                    loadActivity("EVENTLIST");
                }else if(v == findViewById(R.id.startEvent)){
                    loadActivity("STARTEVENT");
                }else if(v == findViewById(R.id.createEvent)){
                    loadActivity("CREATEEVENT");
                }else if(v == findViewById(R.id.eventsInProgress)){
                    loadActivity("EVENTSINPROGRESS");
                }else if(v == findViewById(R.id.viewPastEventsButton)){
                    loadActivity("PASTEVENTS");
                }
            }
        };

        listEventsButton.setOnClickListener(clickListener);
        startEventButton.setOnClickListener(clickListener);
        createEventButton.setOnClickListener(clickListener);
        eventsInProgressButton.setOnClickListener(clickListener);
        pastEventsButton.setOnClickListener(clickListener);
    }

    public void loadActivity(String s){
        switch (s) {
            case "PROFILE": {
                Intent intent = new Intent(MainActivity.this, profileActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case "EVENTLIST": {
                Intent intent = new Intent(MainActivity.this, eventListActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case "STARTEVENT": {
                Intent intent = new Intent(MainActivity.this, startEventActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case "CREATEEVENT": {
                Intent intent = new Intent(MainActivity.this, createEventActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case "LOGIN": {
                Intent intent = new Intent(MainActivity.this, loginActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case "EVENTSINPROGRESS": {
                Intent intent = new Intent(MainActivity.this, eventsInProgressActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
            case "PASTEVENTS": {
                Intent intent = new Intent(MainActivity.this, pastEventsActivity.class);
                startActivityForResult(intent, 1);
                break;
            }
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}

