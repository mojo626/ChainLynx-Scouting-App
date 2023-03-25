package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.io.IOException;

public class PreMatch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_match);

        Gson gson = new Gson();
        TeamData teamData = new TeamData();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String data = extras.getString("data");
            teamData = gson.fromJson(data, TeamData.class);
            //The key argument here must match that used in the other activity
        }
        TextView text = (TextView) findViewById(R.id.scoutingText);
        text.setText("You are scouting team " + teamData.teamNumber + "\nOn "  + teamData.alliance + " alliance\nIn match " + teamData.matchNumber);



        Button nextButton = findViewById(R.id.startScoutingButton);
        TeamData finalTeamData = teamData;
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Intent i = new Intent(PreMatch.this, AutoScouting.class);
                i.putExtra("data", gson.toJson(finalTeamData));
                startActivity(i);
            }
        });

    }
}