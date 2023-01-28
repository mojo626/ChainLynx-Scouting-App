package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;

public class TeleopScouting extends AppCompatActivity {

    private int teleopHighConesScored = 0;
    private int teleopHighCubesScored = 0;
    private int teleopMidConesScored = 0;
    private int teleopMidCubesScored = 0;
    private int teleopHybridConesScored = 0;
    private int teleopHybridCubesScored = 0;
    private int misses = 0;
    private String data = "";

    private boolean holdingCone = false;

    private boolean holdingCube = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teleop_scouting);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getString("data");
            //The key argument here must match that used in the other activity
        }

        Button coneScoredButton = findViewById(R.id.teleopConePickup);
        coneScoredButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                holdingCone = !holdingCone;

                if (holdingCone)
                {
                    coneScoredButton.setBackgroundColor(Color.rgb(100, 0, 0));
                } else {
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

            }
        });

        Button cubeScoredButton = findViewById(R.id.teleopCubePickup);
        cubeScoredButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                holdingCube = !holdingCube;

                if (holdingCube)
                {
                    cubeScoredButton.setBackgroundColor(Color.rgb(100, 0, 0));
                } else {
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }
            }
        });

        Button highScoreButton = findViewById(R.id.teleopHighScored);
        highScoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (holdingCone)
                {
                    teleopHighConesScored++;
                    holdingCone = false;
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                } else if (holdingCube)
                {
                    teleopHighCubesScored++;
                    holdingCube = false;
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

                TextView textView = (TextView) findViewById(R.id.teleopConeCounter);
                textView.setText("Cubes Scored: " + teleopHighConesScored);
                textView = (TextView) findViewById(R.id.teleopCubeCounter);
                textView.setText("Cubes Scored: " + teleopHighCubesScored);
            }
        });


        Button missedButton = findViewById(R.id.teleopMiss);
        missedButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (holdingCone)
                {
                    misses++;
                    holdingCone = false;
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                } else if (holdingCube)
                {
                    misses++;
                    holdingCube = false;
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

            }
        });

        Button cubeScoredButtonDown = findViewById(R.id.teleopHybridScored);
        cubeScoredButtonDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (holdingCone)
                {
                    teleopHybridConesScored++;
                    holdingCone = false;
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                } else if (holdingCube)
                {
                    teleopHybridCubesScored++;
                    holdingCube = false;
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

                TextView textView = (TextView) findViewById(R.id.teleopConeCounter);
                textView.setText("Cubes Scored: " + teleopHybridConesScored);
                textView = (TextView) findViewById(R.id.teleopCubeCounter);
                textView.setText("Cubes Scored: " + teleopHybridCubesScored);
            }
        });

        Button teleopMidScore = findViewById(R.id.teleopMidScored);
        teleopMidScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (holdingCone)
                {
                    teleopMidConesScored++;
                    holdingCone = false;
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                    
                } else if (holdingCube)
                {
                    teleopMidCubesScored++;
                    holdingCube = false;
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

                TextView textView = (TextView) findViewById(R.id.teleopConeCounter);
                textView.setText("Cubes Scored: " + teleopMidConesScored);
                textView = (TextView) findViewById(R.id.teleopCubeCounter);
                textView.setText("Cubes Scored: " + teleopMidCubesScored);
            }
        });


        Button teleopOverButton = findViewById(R.id.teleopOverButton);
        teleopOverButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                RadioGroup radioGroup = findViewById(R.id.radioGroup);
                int radioId = radioGroup.getCheckedRadioButtonId();
                String docked = "";
                switch (radioId)
                {
                    case R.id.neitherRadioButton:
                        docked = "Not Docked";
                        break;
                    case R.id.unengagedRadioButton:
                        docked = "Unengaged";
                        break;
                    case R.id.engagedRadioButton:
                        docked = "Engaged";
                        break;
                }
                String value= teleopHighConesScored + "/" + teleopMidConesScored + "/" + teleopHybridConesScored + "/" + teleopHighCubesScored + "/" + teleopMidCubesScored + "/" + teleopHybridCubesScored + "/" + misses + "/" + docked + "/";
                Intent i = new Intent(TeleopScouting.this, QrCodeDisplay.class);
                i.putExtra("data",data + value);
                startActivity(i);
            }
        });
    }
}