package com.example.youthclub_events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class registerActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;
    ArrayList<String> accountTypeList;
    ArrayAdapter<String> stringArrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);
        accountTypeList = new ArrayList<>();
        accountTypeList.add("Select an account type");
        accountTypeList.add("Attendee");
        accountTypeList.add("Organizer");




            }

    private void validateUser(String username, String password){
        if((username.equals("admin")) && (password.equals("ADMIN"))){
            Intent intent = new Intent(registerActivity.this, MainActivity.class);
            intent.putExtra();
        }


    }


    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }


}
