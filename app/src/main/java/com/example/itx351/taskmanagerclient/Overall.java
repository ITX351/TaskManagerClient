package com.example.itx351.taskmanagerclient;

import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

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
    public static final int TasksFragmentAutoUpdateSleepTime = 30000; //ms
    public static final int ListenThreadSleepTime = 1000; //ms

    //public ImageView screenshot;
    public ScreenshotFragment nowScreenshotFragment;

    @Override
    public void onCreate()
    {
        super.onCreate();
        connected = false;
        sysInfo = null;
    }

    //显示提示框
    public static void showDialog(Activity activity, String title, String content) {
        Dialog dialog = new Dialog(activity);
        dialog.setTitle(title);
        dialog.setContentView(R.layout.dialog);

        TextView txtShow = (TextView)dialog.findViewById(R.id.dialog_text_view);
        txtShow.setText(content);
        txtShow.setGravity(Gravity.CENTER_HORIZONTAL);
        dialog.show();
    }
}
