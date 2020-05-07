package com.example.youthclub_events;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class pastEventsAdapter extends RecyclerView.Adapter<pastEventsAdapter.ViewHolder> {
    private ArrayList<eventInProgress> eventsInProgressList;
    private Context context;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameView;
        public TextView locationView;
        public TextView attendantView;
        public Button viewFeedbacks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.eventNameTv);
            locationView = itemView.findViewById(R.id.eventLocationTv);
            attendantView = itemView.findViewById(R.id.attendeeCountTv);
            viewFeedbacks = itemView.findViewById(R.id.viewComments);
        }
    }
    public pastEventsAdapter(ArrayList<eventInProgress> eventsList, Context context) {
        this.eventsInProgressList = eventsList;
        this.context = context;
    }
    @NonNull
    @Override
    public pastEventsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_past_event, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final pastEventsAdapter.ViewHolder holder, final int position) {
        final String name;
        eventInProgress currentEvent = eventsInProgressList.get(position);
        if (currentEvent.ongoing){
            holder.itemView.setVisibility(View.GONE);
        }else{

            holder.nameView.setText(currentEvent.name);
            holder.locationView.setText(currentEvent.location);
            holder.attendantView.setText(("Participants: "+String.valueOf(currentEvent.attendeeCount)));
            holder.viewFeedbacks.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, feedbackObserveActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("eventname", holder.nameView.getText());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return eventsInProgressList.size();
    }


}
