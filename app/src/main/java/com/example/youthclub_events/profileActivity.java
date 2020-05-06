package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class profileActivity extends AppCompatActivity {

    TextView usernameTV, accountTypeTV, emailTV;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth firebaseAuth;
    User user;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.context = getApplicationContext();

        firebaseAuth = firebaseAuth.getInstance();

        usernameTV = findViewById(R.id.userNameTv);
        accountTypeTV = findViewById(R.id.accountTypeTv);
        emailTV = findViewById(R.id.emailTv);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());
        final FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                usernameTV.setText(user.getUsername());
                if(user.getAccountType() == 1){
                    accountTypeTV.setText("Attendee");
                }else if(user.getAccountType() == 2){
                    accountTypeTV.setText("Organizer");
                }
                emailTV.setText(user.getEmailAddress());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });




            }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }


}
