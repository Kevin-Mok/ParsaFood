package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.vision.text.TextBlock;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AfterCaptureActivity extends AppCompatActivity {

    ArrayList<String> itemList;
    Button anotherPicture;
    ImageView icon;
    TextView titleText;
    TextParser parser  = new TextParser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_capture);

        anotherPicture = (Button) findViewById(R.id.AnotherPicture);
        itemList = (ArrayList<String>) getIntent().getSerializableExtra("ING-LIST");
        icon = (ImageView) findViewById(R.id.icon);
        titleText = (TextView) findViewById(R.id.TitleText);
        parser.setUserPreferences("000010");

        Drawable check = getResources().getDrawable(R.drawable.check);
        Drawable negative = getResources().getDrawable(R.drawable.negative);

        ArrayList<String> parserResult = parser.checkAllergens(itemList);

        if (parserResult.size() == 0) {
            Log.i("OK", "its a");
            icon.setImageDrawable(check);
        } else {
            Log.i("OK", "its n");
            titleText.setText("Ingredients are not OK.");
            titleText.setTextColor(Color.rgb(209,89,98));
            icon.setImageDrawable(negative);
        }

        anotherPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch After Capture Activity
                Intent intent = new Intent(AfterCaptureActivity.this, OcrCaptureActivity.class);
                startActivity(intent);
            }
        });

    }

    public void onBackPressed() {
        Intent i = new Intent(AfterCaptureActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
