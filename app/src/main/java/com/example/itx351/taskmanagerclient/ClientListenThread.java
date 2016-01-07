package com.example.itx351.taskmanagerclient;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.ObjectInputStream;
import java.util.concurrent.CountDownLatch;

//import javax.imageio.ImageIO;
//import javax.xml.crypto.Data;


public class ClientListenThread extends ClientMainThread implements Runnable{
    private Overall overall;

    private CountDownLatch cDownLatch;
    private SysInfo sysInfo = new SysInfo();
//    private Screenshot screenshot = new Screenshot();
    ObjectInputStream in;

    public ClientListenThread(ObjectInputStream in, CountDownLatch cDownLatch, Overall _overall) {
        super();
        this.in = in;
        this.cDownLatch = cDownLatch;
        this.overall = _overall;
    }

    public void run(){

//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-hh-mm");
        while (this.cDownLatch.getCount() > 0){
            Log.d("sysinfo", "one second passed");
            try{
                Looper.prepare();

                byte head  = (byte) this.in.readObject();
                if(head == DataHead.getDataHead("sysInfoHead")){
                    this.sysInfo = (SysInfo) this.in.readObject();
                    Log.d("sysinfo", "Receive: " + String.valueOf(this.sysInfo.cpuSys) + String.valueOf(sysInfo.memFree));
                    System.out.println("Client received " + this.sysInfo.cpuCombined);

                    if (overall.nowInfoFragment == null) {
                        Log.d("sysinfo", "Info fragment not started");
                    }
                    else {
                        Log.d("sysinfo", "Info fragment started get");
                        Handler h = new Handler();
                        Log.d("sysinfo", "Handler created");
                        h.post(overall.nowInfoFragment.new modifier(Double.toString(sysInfo.cpuSys),
                                Double.toString(sysInfo.memFree), "abc","def"));
                    }
                }
//                else if (head == DataHead.getDataHead("screenshotHead")) {
//                    ClientGeneralThread clientGeneralThread = new
//                            ClientGeneralThread(head, this.in);
//                    clientGeneralThread.run();
//                }
                else{
                }
                Thread.sleep(1000);
                Log.d("sysinfo", "aftersleep");
                //Looper.loop();
                //Log.d("sysinfo", "after loop");
            }
            catch (Exception e){
                e.printStackTrace();
                break;
            }
        }
        this.cDownLatch.countDown();
        System.out.println("Leaving client data-listening thread");
    }
}
