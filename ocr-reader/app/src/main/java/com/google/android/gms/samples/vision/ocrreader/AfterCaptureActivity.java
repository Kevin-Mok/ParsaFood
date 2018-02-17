package com.google.android.gms.samples.vision.ocrreader;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class AfterCaptureActivity extends AppCompatActivity {

    ArrayList<String> itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_capture);

        itemList = (ArrayList<String>) getIntent().getSerializableExtra("ING-LIST");
        Log.i("","Inside the after capture");
        for (int i = 0; i < itemList.size(); i++) {
            Log.i("ITEM we're in" + Integer.toString(i), itemList.get(i));
        }

    }
}
