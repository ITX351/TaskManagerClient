package com.example.itx351.taskmanagerclient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.util.concurrent.CountDownLatch;

public class ClientSendThread implements Runnable{
    private CountDownLatch cDownLatch;
    private ObjectOutputStream out;
    private BufferedReader bufferedReader =
            new BufferedReader(new InputStreamReader(System.in));
    private String message="";
    public ClientSendThread(ObjectOutputStream out, CountDownLatch cDownLatch) {
        super();
        this.out = out;
        this.cDownLatch = cDownLatch;
    }
    public void run(){
        while(!this.message.equals("sdprogram")){
            try {
                this.message = this.bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                out.writeObject(this.message);
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.cDownLatch.countDown();
        System.out.println("Leaving client command-sending thread");
    }
}
