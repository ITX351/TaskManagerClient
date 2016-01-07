package com.example.itx351.taskmanagerclient;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Auto Connection

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnConnect = (Button)findViewById(R.id.main_btnConnect);
        final EditText txtDomainName = (EditText)findViewById(R.id.main_txtDomainName);
        final EditText txtKeyword = (EditText)findViewById(R.id.main_txtKeyword);


        btnConnect.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View view) {
                //TODO: Authority Check?
//                Socket clientSocket = null;
//                ObjectOutputStream commandOutStream = null;
//                ObjectInputStream in = null;
//                boolean successFlag =  false;
//
//                try {
////                    clientSocket = new Socket(txtDomainName.getText().toString(),
////                                                Integer.parseInt(txtPort.getText().toString()));
//                    clientSocket = new Socket(txtDomainName.getText().toString(),10010);
//                    commandOutStream = new ObjectOutputStream(clientSocket.getOutputStream());
//                    in = new ObjectInputStream(clientSocket.getInputStream());
//
//                } catch (UnknownHostException e) {
//                    System.err.println("Don't know about host.");
//                    Toast.makeText(MainActivity.this, "Unknown host", Toast.LENGTH_SHORT).show();
//                    return;
//                } catch (IOException e) {
//                    System.err.println("Couldn't get I/O for "
//                            + "the connection to.");
//                    Toast.makeText(MainActivity.this, "Couldn't get I/O for \"\n" +
//                            "                 + \"the connection", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//
//
//                if(successFlag)
//                {
//                    successFlag = true;
//
//                    CountDownLatch cDownLatch = new CountDownLatch(2);
//                    ClientListenThread clientListenThread = new ClientListenThread(in, cDownLatch);
//                    new Thread(clientListenThread).start();
//
//                    ClientSendThread clientSendThread = new ClientSendThread(commandOutStream,cDownLatch);
//                    new Thread(clientSendThread).start();
//
//                    try {
//                        cDownLatch.await();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("Client start to close");
//                    try {
//                        commandOutStream.close(); //关闭Socket输出流
//                        in.close(); //关闭Socket输入流
//                        clientSocket.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("Client close work finished");
//
//                }
                Intent tasksActivity = new Intent();
                tasksActivity.putExtra("DomainName", txtDomainName.getText());
                tasksActivity.putExtra("Keyword", txtKeyword.getText());
                tasksActivity.setClass(MainActivity.this, TasksActivity.class);
                startActivity(tasksActivity);
                finish(); //In case of Backing to Login Activity
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
