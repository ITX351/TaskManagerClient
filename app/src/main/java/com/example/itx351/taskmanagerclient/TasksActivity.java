package com.example.itx351.taskmanagerclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class TasksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        Button btnRun = (Button)findViewById(R.id.tasks_btnRun);
        btnRun.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                Intent runActivity = new Intent();
                runActivity.setClass(TasksActivity.this, RunActivity.class);
                startActivity(runActivity);
            }
        });
    }
}
