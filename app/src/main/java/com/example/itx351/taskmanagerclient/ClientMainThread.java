package com.example.itx351.taskmanagerclient;

import java.io.*;
import java.net.*;
import java.util.concurrent.CountDownLatch;

public class ClientMainThread {
    protected byte dataHead = (byte)0x00;		//0x00 receiving system resource status
    //0x01 receiving screen capture
    public synchronized byte getDataHead(){
        return this.dataHead;
    }
    public synchronized void setDataHead(byte _dataHead) {
        this.dataHead = _dataHead;
    }
    public static void main(String[] args) throws IOException {
        Socket clientSocket = null;
        ObjectOutputStream commandOutStream = null;
        ObjectInputStream in = null;

        try {
            clientSocket = new Socket("127.0.0.1", 10010);
            commandOutStream = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                    + "the connection to.");
            System.exit(1);
        }

        CountDownLatch cDownLatch = new CountDownLatch(2);
        ClientListenThread clientListenThread = new ClientListenThread(in, cDownLatch);
        new Thread(clientListenThread).start();

        ClientSendThread clientSendThread = new ClientSendThread(commandOutStream,cDownLatch);
        new Thread(clientSendThread).start();

        try {
            cDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Client start to close");
        commandOutStream.close(); //关闭Socket输出流
        in.close(); //关闭Socket输入流
        clientSocket.close();
        System.out.println("Client close work finished");
    }
}
