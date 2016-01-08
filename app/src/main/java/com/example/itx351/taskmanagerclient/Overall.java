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

//    public InfoFragment nowInfoFragment;
//    public TasksFragment nowTasksFragment;
//    public ProcessFragment nowProcessFragment;

    public ImageView screencapture;

    @Override
    public void onCreate()
    {
        super.onCreate();
        connected = false;
//        nowInfoFragment = null;
//        nowTasksFragment = null;
//        nowProcessFragment = null;
    }
}
