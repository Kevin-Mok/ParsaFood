/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.vision.ocrreader;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * recognizes text.
 */
    public class MainActivity extends Activity implements View.OnClickListener {

    private TextView statusMessage;
    private TextView textValue;
    private ImageView logo;

    private Button setPreferences;

    private Drawable logoDrawable;

    private static final int RC_OCR_CAPTURE = 9003;
    private static final String TAG = "MainActivity";

    private String preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setPreferences = (Button)findViewById(R.id.set_preferences);
/*        statusMessage = (TextView)findViewById(R.id.status_message);
        textValue = (TextView)findViewById(R.id.text_value);*/


        setPreferences.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Launch After Capture Activity
                Intent intent = new Intent(MainActivity.this, PrefrencesActivity.class);
                intent.putExtra("preferences", preferences);
                startActivity(intent);
            }
        });
        //logo = (ImageView)findViewById(R.id.Logo);

        //logoDrawable = getResources().getDrawable(R.drawable.final_logo);
        //logo.setImageDrawable(logoDrawable);

        preferences = getPreferencesFromActivity();


        findViewById(R.id.read_text).setOnClickListener(this);
    }

    private String getPreferencesFromActivity() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            return extras.getString("preferences");
        }
        return "0000000000";
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.read_text) {
            // launch Ocr capture activity.
            Intent intent = new Intent(this, OcrCaptureActivity.class);
            intent.putExtra(OcrCaptureActivity.AutoFocus, true);
            intent.putExtra(OcrCaptureActivity.UseFlash, false);
            intent.putExtra("preferences", preferences);
            startActivityForResult(intent, RC_OCR_CAPTURE);
        }
    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
//    @Override
    /*protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RC_OCR_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    String text = data.getStringExtra(OcrCaptureActivity.TextBlockObject);
                    try {
                        statusMessage.setText(R.string.ocr_success);
                    } catch (Exception e){
                        Log.e("Error", e.toString());
                    }

                    textValue.setText(text);
                    Log.d(TAG, "Text read: " + text);
                } else {
                    try {
                        statusMessage.setText(R.string.ocr_failure);
                        Log.d(TAG, "No Text captured, intent data is null");
                    } catch(Exception e) {
                        Log.i("Error: ", "d");
                    }

                }
            } else {
                statusMessage.setText(String.format(getString(R.string.ocr_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }*/
}
