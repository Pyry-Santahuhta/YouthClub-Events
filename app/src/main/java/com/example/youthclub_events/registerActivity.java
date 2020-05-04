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

import java.util.ArrayList;


public class registerActivity extends AppCompatActivity {

    private EditText emailID;
    private EditText passwordID;
    private Button registerButton;
    ArrayList<String> accountTypeList;
    ArrayAdapter<String> stringArrayAdapter;
    Spinner accountTypeSpinner;
    private String emailAddress;
    private String password;
    FirebaseAuth fireBaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        emailID = findViewById(R.id.email);
        passwordID = findViewById(R.id.password);
        registerButton = findViewById(R.id.registerButton);
        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);
        accountTypeList = new ArrayList<>();
        accountTypeList.add("Select an account type");
        accountTypeList.add("Attendee"); // 1
        accountTypeList.add("Organizer"); // 2
        stringArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, accountTypeList);
        stringArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(stringArrayAdapter);
        fireBaseAuth = fireBaseAuth.getInstance();
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = emailID.getText().toString();
                password = passwordID.getText().toString();

                if ((emailAddress.isEmpty()) && (password.isEmpty())){
                    Toast.makeText(registerActivity.this, "Please enter credentials!", Toast.LENGTH_SHORT).show();
                }
                else if (emailAddress.isEmpty()){
                    emailID.setError("Please enter an email address");
                    emailID.requestFocus();
                }
                else if (password.isEmpty()) {
                    passwordID.setError("Please enter a password");
                    passwordID.requestFocus();
                }
                else{
                    fireBaseAuth.createUserWithEmailAndPassword(emailAddress, password).addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(registerActivity.this, "Couldn't sign up, try again later", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(registerActivity.this, "Sign up successful, please log in", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(registerActivity.this, loginActivity.class);
                                startActivity(intent);
                            }

                        }
                    });
                }
            }



        });

    }



    private void createUser(String username, String password, int accountType){
        account account = new account(username, password, accountType);
        if((username.equals("admin")) && (password.equals("ADMIN"))){

        }
        Intent intent = new Intent();
       // account here intent.putExtra( )
        setResult(RESULT_OK, intent);
        finish();

    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


}
