package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class eventListActivity extends AppCompatActivity {
    Context context;
    ArrayList<event> eventsList;
    RecyclerView eventsRecyclerView;
    RecyclerView.Adapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventlist);
        eventsRecyclerView = findViewById(R.id.recyclerView);
        this.context = getApplicationContext();
        eventsList = readAndWriteXML.readXML(context);
        eventsRecyclerView.setHasFixedSize(true);
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new MainAdapter(eventsList, context);
        eventsRecyclerView.setAdapter(recyclerAdapter);
        eventsRecyclerView.addOnItemTouchListener(new recyclerViewOnClickListener(context, eventsRecyclerView, new recyclerViewOnClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if(view == findViewById(R.id.editButtonID)){

                }else {
                    Intent intent = new Intent(eventListActivity.this, fullEventInfo.class);
                    intent.putExtra("selectedPosition", position);
                    startActivity(intent);
                }
            }

            @Override
            public void onLongItemClick(View view, int position) {
            }
        }));
    }



    @Override
    public void onBackPressed(){
        Intent intent = new Intent();
        setResult(RESULT_OK, intent);
        finish();
    }


}
