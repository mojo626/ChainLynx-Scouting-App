package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MatchSetup extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_setup);

        Gson gson = new Gson();
        TeamData teamData = new TeamData();
        Log.d("gson test", gson.toJson(teamData));



        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                EditText matchNumber = findViewById(R.id.matchNumberInput);


                //Code for the text boxes
//                EditText teamNumber = findViewById(R.id.teamNumberInput);
//                RadioGroup blueRedTeam = findViewById(R.id.blueRedGroup);
//                String team = "";
//
//                if (blueRedTeam.getCheckedRadioButtonId() == R.id.blueRadioButton)
//                {
//                    team = "Blue";
//                } else {
//                    team = "Red";
//                }


                String data = "";
                try {
                    data = getFileContent("/storage/sdcard0/ChainLynxScouting/tabletDataTeam" + Build.SERIAL + ".txt"); //TODO Change the file name depending on which tablet it is
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                int index = data.indexOf("/" + matchNumber.getText().toString() + "/");
                String str1 = data.substring(index + 2 + matchNumber.getText().toString().length(), data.length()); //get the string of data after the match number
                String[] str2 = str1.split("/"); //get the data from the last string before the next "/"
                String teamAlliance = str2[0];

                String[] str3 = str2[1].split("/"); //Get the data before the next "/"
                String teamNum = str3[0];
                Log.d("test", str3[0]);



                //String value = matchNumber.getText().toString() + "/" + teamAlliance + "/" + teamNum.substring(3, teamNum.length()) + "/";
                teamData.matchNumber = Integer.parseInt(matchNumber.getText().toString());
                teamData.alliance = teamAlliance;
                teamData.teamNumber = Integer.parseInt(teamNum.substring(3, teamNum.length()));


                Intent i = new Intent(MatchSetup.this, PreMatch.class);
                i.putExtra("data", gson.toJson(teamData));
                startActivity(i);
            }
        });
    }

    private String getFileContent(String targetFilePath) throws IOException {
        File file = new File(targetFilePath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuilder sb = null;
        while (fileInputStream.available() > 0) {
            if (null == sb) {
                sb = new StringBuilder();
            }
            sb.append((char) fileInputStream.read());
        }

        String fileContent = null;
        if (null != sb) {
            fileContent = sb.toString();
            // This is your file content in String.
        }
        try {
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}