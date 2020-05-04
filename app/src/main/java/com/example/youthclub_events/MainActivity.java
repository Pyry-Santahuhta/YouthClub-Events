package com.example.youthclub_events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;




public class MainActivity extends AppCompatActivity {
    Context context;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private NavigationView navigationView;
    Button listEventsButton, startEventButton, createEventButton;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        firebaseAuth = firebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() == null){
            loadActivity("LOGIN");
        }else{
            Toast.makeText(MainActivity.this, firebaseAuth.getCurrentUser().toString(), Toast.LENGTH_SHORT).show();
        }
        super.onCreate(savedInstanceState);
        this.context = getApplicationContext();
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int id = menuItem.getItemId();
                if (id == R.id.settings) {
                    loadActivity("SETTINGS");
                }
                else if (id == R.id.profile){
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
                }
            }
        };

        listEventsButton = findViewById(R.id.listEvents);
        startEventButton = findViewById(R.id.startEvent);
        createEventButton = findViewById(R.id.createEvent);
        listEventsButton.setOnClickListener(clickListener);
        startEventButton.setOnClickListener(clickListener);
        createEventButton.setOnClickListener(clickListener);

    }

    public void loadActivity(String s){
        if(s.equals("SETTINGS")){
            Intent intent = new Intent(MainActivity.this, settingsActivity.class);
            startActivityForResult(intent, 1);
        }else if(s.equals("PROFILE")){
            Intent intent = new Intent(MainActivity.this, profileActivity.class);
            startActivityForResult(intent, 1);
        }else if(s.equals("EVENTLIST")){
            Intent intent = new Intent(MainActivity.this, eventListActivity.class);
            startActivityForResult(intent, 1);
        }else if(s.equals("STARTEVENT")){
            Intent intent = new Intent(MainActivity.this, startEventActivity.class);
            startActivityForResult(intent, 1);
        }else if(s.equals("CREATEEVENT")){
            Intent intent = new Intent(MainActivity.this, createEventActivity.class);
            startActivityForResult(intent, 1);
        }else if(s.equals("LOGIN")){
            Intent intent = new Intent(MainActivity.this, loginActivity.class);
            startActivityForResult(intent, 1);
        }
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }
}

