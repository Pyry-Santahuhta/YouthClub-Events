package com.example.youthclub_events;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class loginActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);


            }

    private void validateUser(String username, String password){
        if((username.equals("admin")) && (password.equals("ADMIN"))){
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
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
