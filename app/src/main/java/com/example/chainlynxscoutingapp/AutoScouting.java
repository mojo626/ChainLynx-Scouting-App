package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;

public class AutoScouting extends AppCompatActivity {

    private int autoHighConesScored = 0;
    private int autoHighCubesScored = 0;
    private int autoMidConesScored = 0;
    private int autoMidCubesScored = 0;
    private int autoHybridConesScored = 0;
    private int autoHybridCubesScored = 0;
    private int misses = 0;
    private String data = "";

    private boolean holdingCone = false;

    private boolean holdingCube = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_scouting);

        Gson gson = new Gson();
        TeamData teamData = new TeamData();


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getString("data");
            teamData = gson.fromJson(data, TeamData.class);
            //The key argument here must match that used in the other activity
        }
        TextView text = (TextView)findViewById(R.id.teamScouting);
        text.setText("YOU ARE SCOUTING " + teamData.teamNumber + " IN MATCH " + teamData.matchNumber);

        Button coneScoredButton = findViewById(R.id.autoConePickup);
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

        Button cubeScoredButton = findViewById(R.id.autoCubePickup);
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

        Button highScoreButton = findViewById(R.id.autoHighScored);
        highScoreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (holdingCone)
                {
                    autoHighConesScored++;
                    holdingCone = false;
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                } else if (holdingCube)
                {
                    autoHighCubesScored++;
                    holdingCube = false;
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

                TextView textView = (TextView) findViewById(R.id.autoConeCounter);
                textView.setText("Cubes Scored: " + autoHighConesScored);
                textView = (TextView) findViewById(R.id.autoCubeCounter);
                textView.setText("Cubes Scored: " + autoHighCubesScored);
            }
        });


        Button missedButton = findViewById(R.id.autoMiss);
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

        Button cubeScoredButtonDown = findViewById(R.id.autoHybridScored);
        cubeScoredButtonDown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (holdingCone)
                {
                    autoHybridConesScored++;
                    holdingCone = false;
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                } else if (holdingCube)
                {
                    autoHybridCubesScored++;
                    holdingCube = false;
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

                TextView textView = (TextView) findViewById(R.id.autoConeCounter);
                textView.setText("Cubes Scored: " + autoHybridConesScored);
                textView = (TextView) findViewById(R.id.autoCubeCounter);
                textView.setText("Cubes Scored: " + autoHybridCubesScored);
            }
        });

        Button autoMidScore = findViewById(R.id.autoMidScored);
        autoMidScore.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (holdingCone)
                {
                    autoMidConesScored++;
                    holdingCone = false;
                    coneScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                } else if (holdingCube)
                {
                    autoMidCubesScored++;
                    holdingCube = false;
                    cubeScoredButton.setBackgroundColor(Color.rgb(184, 19, 28));
                }

                TextView textView = (TextView) findViewById(R.id.autoConeCounter);
                textView.setText("Cubes Scored: " + autoMidConesScored);
                textView = (TextView) findViewById(R.id.autoCubeCounter);
                textView.setText("Cubes Scored: " + autoMidCubesScored);
            }
        });


        Button autoOverButton = findViewById(R.id.autoOverButton);
        TeamData finalTeamData = teamData;
        autoOverButton.setOnClickListener(new View.OnClickListener() {
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

                CheckBox mobilityCheck = findViewById(R.id.mobilityCheck);
                boolean mobility = mobilityCheck.isChecked();
                //String value= autoHighConesScored + "/" + autoMidConesScored + "/" + autoHybridConesScored + "/" + autoHighCubesScored + "/" + autoMidCubesScored + "/" + autoHybridCubesScored + "/" + misses + "/" + mobility + "/" + docked + "/";
                finalTeamData.autoConesScoredHigh = autoHighConesScored;
                finalTeamData.autoConesScoredMid = autoMidConesScored;
                finalTeamData.autoConesScoredHybrid = autoHybridConesScored;
                finalTeamData.autoCubesScoredHigh = autoHighCubesScored;
                finalTeamData.autoCubesScoredMid = autoMidCubesScored;
                finalTeamData.autoCubesScoredHybrid = autoHybridCubesScored;
                finalTeamData.autoMissed = misses;
                finalTeamData.autoDocked = docked;
                finalTeamData.autoMobility = mobility;

                Intent i = new Intent(AutoScouting.this, TeleopScouting.class);
                i.putExtra("data",gson.toJson(finalTeamData));
                startActivity(i);
            }
        });
    }
}