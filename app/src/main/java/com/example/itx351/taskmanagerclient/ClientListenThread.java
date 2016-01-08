package com.example.itx351.taskmanagerclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientListenThread extends ClientMainThread implements Runnable{
    private Overall overall;
    private ImageView screenshot;
    ObjectInputStream in;
    boolean stopFlag = false;

    public ClientListenThread(ObjectInputStream in, Overall _overall) {
        super();
        this.in = in;
        this.overall = _overall;
    }

    public void run(){
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-hh-mm");
        while (!stopFlag){
            try{
                byte head  = (byte)in.readObject();
                if(head == DataHead.getDataHead("sysInfoHead")) {
                    overall.sysInfo = (SysInfo)in.readObject();
                }
                else if (head == DataHead.getDataHead("screenshotHead")) {
                        Log.i("Tag", "Received image");
                        //Receive screenshot

                        byte []buffer = (byte[])in.readObject();
                        System.out.println("Size of buffer = " + buffer.length);
                        if (buffer.length == 0 || buffer == null){
                            Log.i("Tag", "Length zero");
                        }
                        try {
                            Bitmap tmp = BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
                            screenshot.setImageBitmap(tmp);
                            Log.i("Tag", "success converting");
                        }catch (Exception e) {
                            Log.i("Tag", "error converting");
                            e.printStackTrace();
                            
                        }

//                    try {
//                            Bitmap tmp = BitmapFactory.decodeStream(this.in);
//                            screenshot.setImageBitmap(tmp);
//                            Log.i("Tag", "success converting");
//                        }catch (Exception e) {
//                            Log.i("Tag", "error converting");
//                            e.printStackTrace();
//                        }
                        //get bitmap
                    try {
                        screenshot.buildDrawingCache();
                        Bitmap bm=screenshot.getDrawingCache();
                        //save in gallery
                        File root = Environment.getExternalStorageDirectory();
                        Date now = new Date(System.currentTimeMillis());
                        String fileName = "screenshot"+sdf.format(now)+".png";
                        String fileAbsolutePath = root.getAbsolutePath() + "/DCIM/Camera/"+fileName;
                        Log.i("Tag", fileAbsolutePath);
                        File cachePath = new File(fileAbsolutePath);
                        cachePath.createNewFile();
                        FileOutputStream ostream = new FileOutputStream(cachePath);
                        bm.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                        ostream.flush();
                        Log.i("Tag", "saved");
                        ostream.close();
                    }
                    catch (Exception e)
                    {
                        Log.i("Tag", "error");
                        e.printStackTrace();
                    }
                    Message msg = new Message();
                    msg.obj = screenshot;
                    if (overall.nowScreenshotFragment != null) {
                        overall.nowScreenshotFragment.handler.sendMessage(msg);
                    }
                }
                Thread.sleep(Overall.ListenThreadSleepTime);
            }
            catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
        System.out.println("Leaving client data-listening thread");
    }
}
