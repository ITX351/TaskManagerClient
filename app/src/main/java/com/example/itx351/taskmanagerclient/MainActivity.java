package com.example.itx351.taskmanagerclient;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {

    private Overall overall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        overall = (Overall)getApplication();

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //TODO: Auto Connection

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnConnect = (Button)findViewById(R.id.main_btnConnect);
        final EditText txtDomainName = (EditText)findViewById(R.id.main_txtDomainName);
        final EditText txtKeyword = (EditText)findViewById(R.id.main_txtKeyword);

        btnConnect.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                //TODO: Authority Check?

                try {
                    overall.DomainName = txtDomainName.getText().toString();
                    overall.Keyword = txtKeyword.getText().toString();
                    overall.clientSocket = new Socket(overall.DomainName,10010);
                    overall.commandOutputStream = new ObjectOutputStream(overall.clientSocket.getOutputStream());
                    overall.commandInputStream = new ObjectInputStream(overall.clientSocket.getInputStream());
                    overall.connected = true;
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                    System.err.println("Don't know about host.");
                    Toast.makeText(MainActivity.this, "Unknown host", Toast.LENGTH_SHORT).show();
                    return;
                } catch (IOException e) {
                    e.printStackTrace();
                    System.err.println("Couldn't get I/O for "
                            + "the connection to.");
                    Toast.makeText(MainActivity.this, "Couldn't get I/O for \"\n" +
                            "                 + \"the connection", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent tasksActivity = new Intent();
                tasksActivity.setClass(MainActivity.this, TasksActivity.class);
                startActivity(tasksActivity);
                finish(); //In case of Backing to Login Activity

                if (false)
                {
                    //successFlag = true;

                    CountDownLatch cDownLatch = new CountDownLatch(1);
                    ClientListenThread clientListenThread = new ClientListenThread(overall.commandInputStream, cDownLatch);
                    new Thread(clientListenThread).start();

                    try {
                        cDownLatch.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Client start to close");
                    try {
                        overall.commandOutputStream.close(); //关闭Socket输出流
                        overall.commandInputStream.close(); //关闭Socket输入流
                        overall.clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Client close work finished");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
