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

        ArrayList<ArrayList<String>> allergenItems = parser.checkAllergens(itemList);
        ArrayList<String> lactoseItems = parser.checkLactose(itemList);
        ArrayList<String> veganItems = parser.checkVegan(itemList);
        ArrayList<String> vegetarianItems = parser.checkVegaterian(itemList);
        ArrayList<String> glutenItems = parser.checkGluten(itemList);

        Log.i("size allergerns", "" + allergenItems.size());
        Log.i("size lactoseItems", "" + lactoseItems.size());
        Log.i("size veganItems", "" + veganItems.size());
        Log.i("size vegetarianItems", "" + vegetarianItems.size());
        Log.i("size glutenItems", "" + glutenItems.size());


        if (noBadIngredients(allergenItems, lactoseItems, veganItems, vegetarianItems, glutenItems)) {
            Log.i("OK", "its a");
            icon.setImageDrawable(check);
        } else {
            Log.i("OK", "its n");
            titleText.setText("Ingredients are not OK.");
            titleText.setTextColor(Color.rgb(209,89,98));
            icon.setImageDrawable(negative);

            if (allergenItems.size() > 0) {
                displayNegativeNested(allergenItems);
            }

            if (lactoseItems.size() > 0) {
                displayNegative(lactoseItems);
            }

            if (veganItems.size() > 0) {
                displayNegative(veganItems);
            }

            if (vegetarianItems.size() > 0) {
                displayNegative(vegetarianItems);
            }

            if (glutenItems.size() > 0) {
                displayNegative(glutenItems);
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

    private boolean noBadIngredients(ArrayList<ArrayList<String>> a,
                                     ArrayList<String> b,
                                     ArrayList<String> c,
                                     ArrayList<String> d,
                                     ArrayList<String> e) {

        return (a.size() == 0) && (b.size() == 0) && (c.size() == 0) && (d.size() == 0) && (e.size() == 0);
    }

    public void onBackPressed() {
        Intent i = new Intent(AfterCaptureActivity.this, MainActivity.class);
        i.putExtra("preferences", preferences);
        startActivity(i);
        finish();
    }

    private void displayNegativeNested(ArrayList<ArrayList<String>> result) {

        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < result.get(i).size(); j++) {
                Log.i("OK", result.get(i).get(j));
                TextView text = new TextView(this);
                text.setText(result.get(i).get(j));
                text.setTextColor(Color.rgb(209,89,98));
                text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                text.setGravity(Gravity.CENTER_HORIZONTAL);
                badIngredientsBox.addView(text);
            }
        }
    }

    private void displayNegative(ArrayList<String> result) {
        Log.i("displayneg", "in" + result.size());
        for (int i = 0; i < result.size(); i++) {
            Log.i("OK", result.get(i));
            TextView text = new TextView(this);
            text.setText(result.get(i));
            text.setTextColor(Color.rgb(209,89,98));
            text.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            text.setGravity(Gravity.CENTER_HORIZONTAL);
            badIngredientsBox.addView(text);
        }
    }



}
