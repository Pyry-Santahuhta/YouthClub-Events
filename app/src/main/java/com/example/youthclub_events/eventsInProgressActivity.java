package com.example.youthclub_events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class eventsInProgressActivity extends AppCompatActivity {
    RecyclerView eventsInProgressRecyclerView;
    ArrayList<eventInProgress> eventInProgressList;
    Context context;
    RecyclerView.Adapter recyclerAdapter;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_in_progress);
        eventsInProgressRecyclerView = findViewById(R.id.recyclerView);
        this.context = getApplicationContext();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                recyclerAdapter = new EventsInProgressAdapter(eventInProgressList, context, user);
                eventsInProgressRecyclerView.setAdapter(recyclerAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        eventInProgressList = readAndWriteXML.readInProgressEventXML(context);
        eventsInProgressRecyclerView.setHasFixedSize(true);
        eventsInProgressRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
