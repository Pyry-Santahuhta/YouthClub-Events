package com.example.youthclub_events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class EventsInProgressAdapter extends RecyclerView.Adapter<EventsInProgressAdapter.ViewHolder> {
    private ArrayList<eventInProgress> eventsInProgressList;
    private Context context;
    private User user;
    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView nameView;
        public TextView attendantView;
        public TextView locationView;
        public EditText feedbackEditText;
        public Button endButton, addParticipantButton, feedBackButton;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.eventNameTv);
            locationView = itemView.findViewById(R.id.eventLocationTv);
            endButton = itemView.findViewById(R.id.endEventButton);
            addParticipantButton = itemView.findViewById(R.id.addParticipant);
            feedBackButton = itemView.findViewById(R.id.addFeedbackButton);
            feedbackEditText = itemView.findViewById(R.id.feedBackEt);
            attendantView = itemView.findViewById(R.id.participantTV);
        }
    }
    public EventsInProgressAdapter(ArrayList<eventInProgress> eventsList, Context context, User user) {
        this.eventsInProgressList = eventsList;
        this.context = context;
        this.user = user;
    }
    @NonNull
    @Override
    public EventsInProgressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_event_in_progress_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsInProgressAdapter.ViewHolder holder, final int position) {
        eventInProgress currentEvent = eventsInProgressList.get(position);
        if (currentEvent.ongoing){
            holder.nameView.setText(currentEvent.name);
            holder.locationView.setText(currentEvent.location);
            holder.attendantView.setText(String.valueOf(currentEvent.attendeeCount));
            if(user.getAccountType() == 1){
                holder.endButton.setVisibility(View.GONE);
                holder.addParticipantButton.setVisibility(View.GONE);
                holder.feedbackEditText.setVisibility(View.VISIBLE);
                holder.feedBackButton.setVisibility(View.VISIBLE);
                holder.attendantView.setVisibility(View.GONE);
            }else if(user.getAccountType() == 2){
                holder.endButton.setVisibility(View.VISIBLE);
                holder.addParticipantButton.setVisibility(View.VISIBLE);
                holder.feedbackEditText.setVisibility(View.GONE);
                holder.feedBackButton.setVisibility(View.GONE);
                holder.attendantView.setVisibility(View.VISIBLE);
            }

            holder.endButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readAndWriteXML.editOngoingXML(context, false, position);
                    holder.itemView.setVisibility(View.GONE);
                    ViewGroup.LayoutParams layoutParameter = holder.itemView.getLayoutParams();
                    layoutParameter.height = 0;
                }
            });

            holder.feedBackButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   String comment = String.valueOf(holder.feedbackEditText.getText());
                   String eventName = (String) holder.nameView.getText();
                   feedback Feedback = new feedback(comment, eventName);
                   readAndWriteXML.writeFeedbackXML(context, Feedback);
                   Toast.makeText(context, "Feedback submitted", Toast.LENGTH_SHORT).show();
                   holder.feedbackEditText.clearFocus();
                   holder.feedbackEditText.getText().clear();
                }
            });

            holder.addParticipantButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    readAndWriteXML.addParticipantInprogressXML(context, position);
                    Toast.makeText(context, "Added participant!", Toast.LENGTH_SHORT).show();
                    updateList();
                    eventInProgress currentEvent = eventsInProgressList.get(position);
                    System.out.println(currentEvent.attendeeCount);
                    holder.attendantView.setText(String.valueOf(currentEvent.attendeeCount));

                }

            });

        }else{
           holder.itemView.setVisibility(View.GONE);
            ViewGroup.LayoutParams layoutParameter = holder.itemView.getLayoutParams();
            layoutParameter.height = 0;
        }

    }

    @Override
    public int getItemCount() {
        return eventsInProgressList.size();
    }

    public void updateList(){
        this.eventsInProgressList = readAndWriteXML.readInProgressEventXML(context);
    }
}
