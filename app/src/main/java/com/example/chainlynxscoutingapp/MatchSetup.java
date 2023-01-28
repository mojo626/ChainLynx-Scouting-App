package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class MatchSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_setup);


        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText matchNumber = findViewById(R.id.matchNumberInput);
                EditText teamNumber = findViewById(R.id.teamNumberInput);
                RadioGroup blueRedTeam = findViewById(R.id.blueRedGroup);
                String team = "";

                if (blueRedTeam.getCheckedRadioButtonId() == R.id.blueRadioButton)
                {
                    team = "Blue";
                } else {
                    team = "Red";
                }

                String value = matchNumber.getText().toString() + "/" + team + "/" + teamNumber.getText().toString() + "/";
                Intent i = new Intent(MatchSetup.this, AutoScouting.class);
                i.putExtra("data", value);
                startActivity(i);
            }
        });
    }
}