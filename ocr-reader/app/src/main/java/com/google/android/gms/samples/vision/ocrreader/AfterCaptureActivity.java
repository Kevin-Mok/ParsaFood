package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
    LinearLayout badIngredientsBox;
    Drawable check;
    Drawable negative;
    String preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_capture);

        anotherPicture = (Button) findViewById(R.id.AnotherPicture);
        preferences = getIntent().getExtras().getString("preferences");
        Log.i("Prefs:", "In the after capture act " + preferences);

        itemList = (ArrayList<String>) getIntent().getSerializableExtra("ING-LIST");
        icon = (ImageView) findViewById(R.id.icon);
        titleText = (TextView) findViewById(R.id.TitleText);
        badIngredientsBox = (LinearLayout)findViewById(R.id.BadIngredientsBox);

        parser.setUserPreferences(preferences);

        check = getResources().getDrawable(R.drawable.check);
        negative = getResources().getDrawable(R.drawable.negative);

        for (int i = 0; i < itemList.size(); i++) {
            Log.i("ITEM " + i, itemList.get(i));
        }

        ArrayList<ArrayList<String>> parserResult = parser.checkAllergens(itemList);

        if (parserResult.size() == 0) {
            Log.i("OK", "its a");
            icon.setImageDrawable(check);
        } else {
            Log.i("OK", "its n");
            displayNegative(parserResult);
            for (int i = 0; i < parserResult.size(); i++) {
                for (int j = 0; j < parserResult.get(i).size(); j++) {
                    Log.i("OK", parserResult.get(i).get(j));
                    TextView text = new TextView(this);
                    text.setText(parserResult.get(i).get(j));
                    text.setTextColor(Color.rgb(209,89,98));
                    text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    text.setGravity(Gravity.CENTER_HORIZONTAL);
                    badIngredientsBox.addView(text);
                }
            }
        }

        anotherPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch After Capture Activity
                Intent intent = new Intent(AfterCaptureActivity.this, OcrCaptureActivity.class);
                intent.putExtra("preferences", preferences);
                startActivity(intent);
            }
        });

    }

    public void onBackPressed() {
        Intent i = new Intent(AfterCaptureActivity.this, MainActivity.class);
        i.putExtra("preferences", preferences);
        startActivity(i);
        finish();
    }

    private void displayNegative(ArrayList<ArrayList<String>> result) {

        titleText.setText("Ingredients are not OK.");
        titleText.setTextColor(Color.rgb(209,89,98));
        icon.setImageDrawable(negative);


    }
}
