package com.example.youthclub_events;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.ArrayList;

public class EditProfileDialog extends AppCompatDialogFragment {
    private EditText usernameET;
    private Spinner accountTypeSpinner;
    private ArrayList<String> accountTypeList;
    private ArrayAdapter<String> stringArrayAdapter;
    private EditProfileDialogListener listener;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //Building the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.profile_edit_dialog, null);

        /* Set the layout for the dialogue */
        builder.setView(view)
                .setTitle("Edit account")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) { }})
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String username = usernameET.getText().toString();
                int accountType = accountTypeSpinner.getSelectedItemPosition();

                //Secure an admin account can't be made
                if(accountType != 0){
                    listener.applyTexts(username,  accountType);
                }else {
                    Toast.makeText(getActivity(), "Select a valid account type", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //Add the different account types
        accountTypeList = new ArrayList<>();
        accountTypeList.add("Select an account type");
        accountTypeList.add("Attendee"); // 1
        accountTypeList.add("Organizer"); // 2

        usernameET = view.findViewById(R.id.usernameEditText);
        accountTypeSpinner = view.findViewById(R.id.account);

        stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, accountTypeList);
        stringArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        accountTypeSpinner.setAdapter(stringArrayAdapter);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {

            listener = (EditProfileDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "Must implement editprofiledialoglistener");
        }
    }

    //This is for overriding to get the username and accountType out of the dialog
    public interface EditProfileDialogListener{
        void applyTexts(String username,  int accountType);
    }
}
