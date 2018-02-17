package com.google.android.gms.samples.vision.ocrreader;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class PrefrencesActivity extends AppCompatActivity {

    private String preferenceSelection;

    CheckBox milk;
    CheckBox egg;
    CheckBox peanut;
    CheckBox wheat;
    CheckBox soy;
    CheckBox seafood;
    CheckBox lactose;
    CheckBox vegan;
    CheckBox vegetarian;
    CheckBox gluten;

    Button apply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prefrences);

        preferenceSelection = getIntent().getExtras().getString("preferences");
        milk = (CheckBox) findViewById(R.id.Milk);
        egg = (CheckBox) findViewById(R.id.Egg_Allergy);
        peanut = (CheckBox) findViewById(R.id.Peanut);
        wheat = (CheckBox) findViewById(R.id.Wheat);
        soy = (CheckBox) findViewById(R.id.Soy);
        seafood = (CheckBox) findViewById(R.id.Seafood);
        lactose = (CheckBox) findViewById(R.id.Lactose);
        vegan = (CheckBox) findViewById(R.id.Vegan);
        vegetarian = (CheckBox) findViewById(R.id.Vegetarian);
        gluten = (CheckBox) findViewById(R.id.Gluten);

        apply = (Button) findViewById(R.id.Apply);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = getApplicationContext();
                CharSequence text = "Preferences have been saved.";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                preferenceSelection = getPreferenceString();
            }
        });
    }

    private String getPreferenceString() {
        StringBuilder sb = new StringBuilder();

        if (milk.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (egg.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (peanut.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (wheat.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (soy.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (seafood.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (lactose.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (vegan.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (vegetarian.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }

        if (gluten.isChecked()) {
            sb.append('1');
        } else {
            sb.append('0');
        }
        return sb.toString();
    }

    public void onBackPressed() {
        Intent i = new Intent(PrefrencesActivity.this, MainActivity.class);
        i.putExtra("preferences", preferenceSelection);
        startActivity(i);
        finish();
    }
}
