package com.example.youthclub_events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Objects;


public class registerActivity extends AppCompatActivity {

    private EditText emailID;
    private EditText passwordID;
    private EditText usernameID;
    private Button registerButton;
    ArrayList<String> accountTypeList;
    ArrayAdapter<String> stringArrayAdapter;
    Spinner accountTypeSpinner;
    private String emailAddress;
    private String password;
    private String username;
    FirebaseAuth fireBaseAuth;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailID = findViewById(R.id.email);
        passwordID = findViewById(R.id.password);
        usernameID = findViewById(R.id.userNameEditText);
        registerButton = findViewById(R.id.registerButton);
        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);

        accountTypeList = new ArrayList<>();
        accountTypeList.add("Select an account type");
        accountTypeList.add("Attendee"); // 1
        accountTypeList.add("Organizer"); // 2

        stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accountTypeList);
        stringArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(stringArrayAdapter);
        fireBaseAuth = FirebaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = emailID.getText().toString();
                password = passwordID.getText().toString();
                username = usernameID.getText().toString();
                // Checking all of the user data to be set
                if ((emailAddress.isEmpty()) && (password.isEmpty()) && (username.isEmpty())){
                    Toast.makeText(registerActivity.this, "Please enter credentials!", Toast.LENGTH_SHORT).show();
                }
                else if (emailAddress.isEmpty()){
                    emailID.setError("Please enter an email address");
                    emailID.requestFocus();
                }
                else if (username.isEmpty()) {
                    usernameID.setError("Please enter an username");
                    usernameID.requestFocus();
                }
                else if (password.isEmpty()) {
                    passwordID.setError("Please enter a password");
                    passwordID.requestFocus();
                }
                else if (accountTypeSpinner.getSelectedItemPosition() == 0) {
                    Toast.makeText(registerActivity.this, "Please choose an account type", Toast.LENGTH_SHORT).show();
                    accountTypeSpinner.requestFocus();
                }
                else{
                    //If everything is set we create an user to firebase using email authentication
                    fireBaseAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(registerActivity.this, "Couldn't sign up, try again later", Toast.LENGTH_SHORT).show();
                            }else {
                                //We add the users data to the firebase database
                                user = new User(username, emailAddress, accountTypeSpinner.getSelectedItemPosition(), fireBaseAuth.getUid());
                                registerToDatabase(user);

                                Toast.makeText(registerActivity.this, "Sign up successful, please log in", Toast.LENGTH_SHORT).show();
                                //We sign out to log back in, in order to test the new account
                                fireBaseAuth.signOut();
                                Intent intent = new Intent(registerActivity.this , loginActivity.class);
                                startActivity(intent);
                            }

                        }
                    });
                }
            }



        });

    }

    // making a new user to the firebase database
    public void registerToDatabase(User user){
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference(Objects.requireNonNull(fireBaseAuth.getUid()));
        databaseReference.setValue(user);
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


}
