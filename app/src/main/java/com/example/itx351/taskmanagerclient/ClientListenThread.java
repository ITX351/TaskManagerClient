package com.example.itx351.taskmanagerclient;

import android.os.Message;

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
            try{
                //Looper.prepare();

                byte head  = (byte) this.in.readObject();
                if(head == DataHead.getDataHead("sysInfoHead")){
                    this.sysInfo = (SysInfo) this.in.readObject();
                    System.out.println("Client received " + this.sysInfo.cpuCombined);

                    Message msg = new Message();
                    msg.obj = sysInfo;

                    if (overall.nowInfoFragment != null) {
                        overall.nowInfoFragment.handler.sendMessage(msg);
                    }
                }
//                else if (head == DataHead.getDataHead("screenshotHead")) {
//                    ClientGeneralThread clientGeneralThread = new
//                            ClientGeneralThread(head, this.in);
//                    clientGeneralThread.run();
//                }
                Thread.sleep(1000);
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
