package com.example.chainlynxscoutingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.chainlynxscoutingapp.qrcodegen.QrCode;

public class QrCodeDisplay extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code_display);
        String data = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            data = extras.getString("data");
            Log.d("Data Debug", data);
            //The key argument here must match that used in the other activity
        }

        QrCode.Ecc qrCodeEcc = QrCode.Ecc.LOW;

        QrCode qr = QrCode.encodeText(data, qrCodeEcc);

        Canvas canvas;
        Bitmap bitmap;

        int scale = 10;
        bitmap = Bitmap.createBitmap(qr.getWidth() * scale, qr.getHeight() * scale, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

        for (int x = 0; x < qr.getWidth() * scale; x+= scale)
        {
            for (int y = 0; y < qr.getHeight() * scale; y+= scale)
            {
                Log.d("Qr Debug", qr.getModule(x, y) + "");
                if (qr.getModule(x/scale, y/scale))
                {
                    for (int x1 = x; x1 < x + scale; x1++)
                    {
                        for (int y1 = y; y1 < y + scale; y1++)
                        {
                            bitmap.setPixel(x1, y1, Color.rgb(0, 0, 0));
                        }
                    }
                } else {
                    bitmap.setPixel(x, y, Color.rgb(255, 255, 255));
                }

            }
        }


        ImageView image = findViewById(R.id.imageView);
        image.setImageBitmap(bitmap);
    }
}