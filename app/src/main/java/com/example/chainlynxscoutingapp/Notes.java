package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;

public class Notes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
        Gson gson = new Gson();
        TeamData teamData = new TeamData();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("data");
            teamData = gson.fromJson(data, TeamData.class);
            //The key argument here must match that used in the other activity
        }




        Button notesNextButton = findViewById(R.id.notesNextButton);
        TeamData finalTeamData = teamData;
        notesNextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText notesBox = findViewById(R.id.notesTextBox);
                String text = notesBox.getText().toString();

                finalTeamData.notes = text;
                

                Intent i = new Intent(Notes.this, QrCodeDisplay.class);
                i.putExtra("data", gson.toJson(finalTeamData));
                startActivity(i);
            }
        });
    }
}