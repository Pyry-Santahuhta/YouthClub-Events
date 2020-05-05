package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class SecondaryAdapter extends RecyclerView.Adapter<SecondaryAdapter.ViewHolder> {
    ArrayList<eventInProgress> eventsInProgressList;
    Context context;
    int itemPosition;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameView;
        public TextView locationView;

        public Button editButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.eventNameTv);
            locationView = itemView.findViewById(R.id.eventLocationTv);

            editButton = itemView.findViewById(R.id.editButtonID);
        }
    }
    public SecondaryAdapter(ArrayList<eventInProgress> eventsList, Context context) {
        this.eventsInProgressList = eventsList;
        this.context = context;
    }
    @NonNull
    @Override
    public SecondaryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_event_inProgress_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecondaryAdapter.ViewHolder holder, final int position) {

        eventInProgress currentEvent = eventsInProgressList.get(position);
        holder.nameView.setText(currentEvent.name);
        holder.locationView.setText(currentEvent.location);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPosition = position;
                Intent intent = new Intent(context, editEventActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("selectedPosition", itemPosition);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return eventsInProgressList.size();
    }


}
