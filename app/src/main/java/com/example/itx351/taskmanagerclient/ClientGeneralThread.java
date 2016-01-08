package com.example.itx351.taskmanagerclient;

import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ClientGeneralThread extends Thread {
	private Overall overall;

	byte dataHead;
	ObjectInputStream in;
	ObjectOutputStream out;
	String command;

	public  ClientGeneralThread(byte _dataHead, ObjectInputStream _in, Overall _overall){
		this.dataHead = _dataHead;
		this.in = _in ;
		this.out = null;
		this.overall = _overall;
	}
	public  ClientGeneralThread(byte _dataHead, ObjectOutputStream _out, Overall _overall){
		this.dataHead = _dataHead;
		this.out = _out ;
		this.in = null;
		this.overall = _overall;
		this.command = null;
	}
	public  ClientGeneralThread(byte _dataHead, ObjectOutputStream _out, Overall _overall, String command){
		this.dataHead = _dataHead;
		this.out = _out ;
		this.in = null;
		this.overall = _overall;
		this.command = command;
	}

	@Override
	public  void run(){
		if (this.out!=null){
			try {
				out.writeObject(this.dataHead);
				if (this.command!=null){
					out.writeObject(this.command);
				}
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
