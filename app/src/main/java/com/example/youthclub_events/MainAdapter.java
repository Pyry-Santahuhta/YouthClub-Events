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

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<event> eventsList;
    Context context;
    int itemPosition;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameView;
        public TextView locationView;
        public TextView datetimeView;
        public Button editButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.eventNameTv);
            locationView = itemView.findViewById(R.id.eventLocationTv);
            datetimeView = itemView.findViewById(R.id.eventDatetimeTv);
            editButton = itemView.findViewById(R.id.editButtonID);
        }
    }
    public MainAdapter(ArrayList<event> eventsList, Context context) {
        this.eventsList = eventsList;
        this.context = context;
    }
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_event_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, final int position) {

        event currentEvent = eventsList.get(position);
        holder.nameView.setText(currentEvent.name);
        holder.locationView.setText(currentEvent.location);
        holder.datetimeView.setText(currentEvent.dateAndTime);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPosition = position;
                Intent intent = new Intent(context, editEventActivity.class);
                intent.putExtra("selectedPosition", itemPosition);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return eventsList.size();
    }


}
