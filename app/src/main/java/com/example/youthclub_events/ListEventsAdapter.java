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

class ListEventsAdapter extends RecyclerView.Adapter<ListEventsAdapter.ViewHolder> {
    private ArrayList<event> eventsList;
    private Context context;
    private int itemPosition;
    private User user;
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
            editButton = itemView.findViewById(R.id.viewComments);
        }
    }
    public ListEventsAdapter(ArrayList<event> eventsList, Context context, User user) {
        this.eventsList = eventsList;
        this.context = context;
        this.user = user;
    }
    @NonNull
    @Override
    public ListEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_event_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListEventsAdapter.ViewHolder holder, final int position) {

        //Depending on userType, hiding different buttons and texts, admins can see everything
        if (user.getAccountType() == 1){
            holder.editButton.setVisibility(View.GONE);
        }else {
            holder.editButton.setVisibility(View.VISIBLE);
        }
        event currentEvent = eventsList.get(position);
        holder.nameView.setText(currentEvent.name);
        holder.locationView.setText(currentEvent.location);
        holder.datetimeView.setText(currentEvent.dateAndTime);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemPosition = position;
                Intent intent = new Intent(context, editEventActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
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
