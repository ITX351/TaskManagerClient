package com.example.itx351.taskmanagerclient;

import android.util.Log;

import java.io.ObjectInputStream;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;

//import javax.imageio.ImageIO;
//import javax.xml.crypto.Data;


public class ClientListenThread extends ClientMainThread implements Runnable{
    private CountDownLatch cDownLatch;
    private SysInfo sysInfo = new SysInfo();
//    private Screenshot screenshot = new Screenshot();
    ObjectInputStream in;

    public ClientListenThread(ObjectInputStream in, CountDownLatch cDownLatch) {
        super();
        this.in = in;
        this.cDownLatch = cDownLatch;
    }

    public void run(){

//        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-hh-mm");
        while(this.cDownLatch.getCount()>0){
            try{
                byte head  = (byte) this.in.readObject();
                if(head == DataHead.getDataHead("sysInfoHead")){
                    this.sysInfo = (SysInfo) this.in.readObject();
                    Log.d("sysinfo", String.valueOf(this.sysInfo.cpuSys));
                    System.out.println("Client received " + this.sysInfo.cpuCombined);
                    int size = this.sysInfo.procList.size();
                    System.out.println("size:" + size);

                    //遍历Vector中的元素
                    System.out.println("top 10 processes:");
                    for(int i = 0;i < 10;i++){
                        System.out.println(this.sysInfo.procList.get(i));
                    }
                }
//                else if (head == DataHead.getDataHead("screenshotHead")) {
//                    ClientGeneralThread clientGeneralThread = new
//                            ClientGeneralThread(head, this.in);
//                    clientGeneralThread.run();
//                }
                else{
                }
                Thread.sleep(10000);
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
