package com.example.itx351.taskmanagerclient;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class ScreenshotActivity extends AppCompatActivity {
    private Overall overall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screenshot);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        overall = (Overall)getApplication();
        overall.screencapture.setImageBitmap(BitmapFactory.decodeStream(overall.commandInputStream));
    }

}
