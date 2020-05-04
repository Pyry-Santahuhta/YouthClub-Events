package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class eventListActivity extends AppCompatActivity {
    Context context;
    ArrayList eventsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);
        context = eventListActivity.this;
        eventsList = readAndWriteXML.readXML(context);



    }



    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


}
