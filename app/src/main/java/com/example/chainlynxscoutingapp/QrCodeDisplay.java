package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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


//        ImageView image = findViewById(R.id.imageView);
//        image.setImageBitmap(bitmap); TODO add this back in if we need qr code again

        generateNoteOnSD(getApplicationContext(), "teamData" + Build.SERIAL + ".txt", gson.toJson(teamData));


        Button goBackButton = findViewById(R.id.goBackButton);
        goBackButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                doRestart(getApplicationContext());
            }
        });
    }


    public static void doRestart(Context c) {
        try {
            //check if the context is given
            if (c != null) {
                //fetch the packagemanager so we can get the default launch activity
                // (you can replace this intent with any other activity if you want
                PackageManager pm = c.getPackageManager();
                //check if we got the PackageManager
                if (pm != null) {
                    //create the intent with the default start activity for your application
                    Intent mStartActivity = pm.getLaunchIntentForPackage(
                            c.getPackageName()
                    );
                    if (mStartActivity != null) {
                        mStartActivity.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        //create a pending intent so the application is restarted after System.exit(0) was called.
                        // We use an AlarmManager to call this intent in 100ms
                        int mPendingIntentId = 223344;
                        PendingIntent mPendingIntent = PendingIntent
                                .getActivity(c, mPendingIntentId, mStartActivity,
                                        PendingIntent.FLAG_CANCEL_CURRENT);
                        AlarmManager mgr = (AlarmManager) c.getSystemService(Context.ALARM_SERVICE);
                        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent);
                        //kill the application
                        System.exit(0);
                    } else {
                        Log.e("Error", "Was not able to restart application, mStartActivity null");
                    }
                } else {
                    Log.e("Error", "Was not able to restart application, PM null");
                }
            } else {
                Log.e("Error", "Was not able to restart application, Context null");
            }
        } catch (Exception ex) {
            Log.e("Error", "Was not able to restart application");
        }
    }


    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            Log.d("File Name", sFileName);
            File root = new File(Environment.getExternalStorageDirectory(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile, true);
            writer.append("\n" + sBody); //TODO This might break things on tablets
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}