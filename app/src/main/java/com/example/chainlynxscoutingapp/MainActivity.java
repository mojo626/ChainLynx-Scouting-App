package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import java.io.IOException;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button scanButton = findViewById(R.id.button);
        scanButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Intent switchActivityIntent = new Intent(view.getContext(), MatchSetup.class);
                startActivity(switchActivityIntent);
            }
        });
    }

}