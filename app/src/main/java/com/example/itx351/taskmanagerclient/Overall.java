package com.example.itx351.taskmanagerclient;

import android.app.Application;
import android.widget.ImageView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by ITX351 on 1/7/2016.
 */
public class Overall extends Application{
    public String DomainName;
    public String Keyword;
    public Socket clientSocket;
    public ObjectInputStream commandInputStream;
    public ObjectOutputStream commandOutputStream;
    public boolean connected;

    public SysInfo sysInfo;

    public static final int InfoFragmentAutoUpdateSleepTime = 1000; //ms
    public static final int TasksFragmentAutoUpdateSleepTime = 3000; //ms
    public static final int ListenThreadSleepTime = 1000; //ms

    public ImageView screencapture;

    @Override
    public void onCreate()
    {
        super.onCreate();
        connected = false;
    }
}
