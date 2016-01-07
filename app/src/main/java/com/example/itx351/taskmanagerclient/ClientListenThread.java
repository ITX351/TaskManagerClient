package com.example.itx351.taskmanagerclient;

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

        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-hh-mm");
        while(this.cDownLatch.getCount()>1){
            try{
                byte dataHead  = (byte) this.in.readObject();
                if(dataHead == (byte)0x00){
                    this.sysInfo = (SysInfo) this.in.readObject();
                    System.out.println("Client received " + this.sysInfo.cpuCombined);
                }
//                else if (dataHead == (byte)0x01) {
//                    this.screenshot = (Screenshot)this.in.readObject();
//                    this.screenshot.byteToImage();
//                    Date now = new Date(System.currentTimeMillis());
//                    ImageIO.write(this.screenshot.capture, "png", new File("screenshot"+sdf.format(now)+".png"));
//                    System.out.println("Client received screenshot");
//                }
                Thread.sleep(30000);
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
