package com.example.itx351.taskmanagerclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RunActivity extends Activity {
    private ClientGeneralThread clientGeneralThread;
    public Overall overall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run);


        Button btnCancel = (Button)findViewById(R.id.run_btnCancel);
        btnCancel.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View view) {
                clientGeneralThread = new ClientGeneralThread(
                        DataHead.getDataHead("runProcessCommandHead"), overall.commandOutputStream, overall);
                clientGeneralThread.start();
            }
        });

    }
}
