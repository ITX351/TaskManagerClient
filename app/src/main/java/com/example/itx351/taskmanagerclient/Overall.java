package com.example.itx351.taskmanagerclient;

import android.app.Application;
import android.widget.ImageView;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Overall extends Application{
    public String DomainName; //域名

    public Socket clientSocket; //Socket对象
    public ObjectInputStream commandInputStream;
    public ObjectOutputStream commandOutputStream;

    public boolean connected; //是否成功连接

    public SysInfo sysInfo; //信息显示 任务列表

    public static final int InfoFragmentAutoUpdateSleepTime = 1000; //ms
    public static final int TasksFragmentAutoUpdateSleepTime = 3000; //ms
    public static final int ListenThreadSleepTime = 1000; //ms

    public ImageView screencapture;

    @Override
    public void onCreate()
    {
        super.onCreate();
        connected = false;
        sysInfo = null;
    }
}
