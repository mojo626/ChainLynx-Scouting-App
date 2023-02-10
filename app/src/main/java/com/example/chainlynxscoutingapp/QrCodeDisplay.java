package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chainlynxscoutingapp.qrcodegen.QrCode;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Logger;

public class QrCodeDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_display);
        String data = "";

        Gson gson = new Gson();
        TeamData teamData = new TeamData();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getString("data");
            Log.d("Data Debug", data);
            teamData = gson.fromJson(data, TeamData.class);
            //The key argument here must match that used in the other activity
        }

        QrCode.Ecc qrCodeEcc = QrCode.Ecc.LOW;

        QrCode qr = QrCode.encodeText(gson.toJson(teamData), qrCodeEcc);

        Canvas canvas;
        Bitmap bitmap;

        int scale = 10;
        bitmap = Bitmap.createBitmap(qr.getWidth() * scale, qr.getHeight() * scale, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        for (int x = 0; x < qr.getWidth() * scale; x+= scale)
        {
            for (int y = 0; y < qr.getHeight() * scale; y+= scale)
            {
                if (qr.getModule(x/scale, y/scale))
                {
                    for (int x1 = x; x1 < x + scale; x1++)
                    {
                        for (int y1 = y; y1 < y + scale; y1++)
                        {
                            bitmap.setPixel(x1, y1, Color.rgb(0, 0, 0));
                        }
                    }
                }
            }
        }


        ImageView image = findViewById(R.id.imageView);
        image.setImageBitmap(bitmap);

        generateNoteOnSD(getApplicationContext(), "teamData.txt", gson.toJson(teamData));
    }


    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile, true);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}