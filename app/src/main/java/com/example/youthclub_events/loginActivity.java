package com.example.youthclub_events;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class loginActivity extends AppCompatActivity {

    private EditText emailID;
    private EditText passwordID;
    private Button loginButton;
    private Button registerButton;
    FirebaseAuth fireBaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private String emailAddress;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailID = findViewById(R.id.email);
        passwordID = findViewById(R.id.password);
        loginButton = findViewById(R.id.login);

        fireBaseAuth = fireBaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = fireBaseAuth.getCurrentUser();
                if(firebaseUser != null ){
                    loadActivity("MAIN");
                }
            }
        };


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddress = emailID.getText().toString();
                password = passwordID.getText().toString();
                if (emailAddress.isEmpty()){
                    emailID.setError("Please enter an email address");
                    emailID.requestFocus();
                }
                else if (password.isEmpty()){
                    passwordID.setError("Please enter a password");
                    passwordID.requestFocus();
                }else if ((emailAddress.isEmpty()) && (password.isEmpty())){
                    Toast.makeText(loginActivity.this, "Please enter credentials!", Toast.LENGTH_SHORT).show();
                }else{
                    fireBaseAuth.signInWithEmailAndPassword(emailAddress, password).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()){
                                Toast.makeText(loginActivity.this, "Couldn't sign in, try again later", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(loginActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                                loadActivity("MAIN");
                            }

                        }
                    });
                }
            }
        });
        registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadActivity("REGISTER");
            }
        });



    }

    private void validateUser(String username, String password){
        if((username.equals("admin")) && (password.equals("ADMIN"))){
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivityForResult(intent, 1);

        }


    }

    public void loadActivity(String s){
        if(s.equals("REGISTER")){
            Intent intent = new Intent(loginActivity.this, registerActivity.class);
            startActivityForResult(intent, 1);
        }else if(s.equals("MAIN")){
            Intent intent = new Intent(loginActivity.this, MainActivity.class);
            startActivityForResult(intent, 1);
        }
    }
    @Override
    public void onBackPressed(){
        Intent intent = new Intent();

        setResult(RESULT_OK, intent);
        finish();
    }


}
