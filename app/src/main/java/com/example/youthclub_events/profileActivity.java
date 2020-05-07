package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

import java.util.Objects;


public class profileActivity extends AppCompatActivity implements EditProfileDialog.EditProfileDialogListener {
    Button editProfileButton;
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

        firebaseAuth = FirebaseAuth.getInstance();

        usernameTV = findViewById(R.id.userNameTv);
        accountTypeTV = findViewById(R.id.accountTypeTv);
        emailTV = findViewById(R.id.emailTv);
        editProfileButton = findViewById(R.id.editProfileButton);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference(Objects.requireNonNull(firebaseAuth.getUid()));

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    usernameTV.setText(user.getUsername());

                    if(user.getAccountType() == 1){
                        accountTypeTV.setText("Attendee");
                    }else if(user.getAccountType() == 2){
                        accountTypeTV.setText("Organizer");
                    }else if(user.getAccountType() == 0){
                        accountTypeTV.setText("ADMIN");
                    }
                    emailTV.setText(user.getEmailAddress());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

        editProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
    }

    public void openDialog(){
        EditProfileDialog editProfileDialog = new EditProfileDialog();
        editProfileDialog.show(getSupportFragmentManager(), "Edit dialog");

    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }


    @Override
    public void applyTexts(String username, int accountType) {
        databaseReference.child("username").setValue(username);
        databaseReference.child("accountType").setValue(accountType);
    }
}
