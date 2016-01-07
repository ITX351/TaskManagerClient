package com.example.itx351.taskmanagerclient;

public final class DataHead {
//	byte dataHead = 0x70;
//	byte screenshotHead = 0x71;
//	byte authorityHead = 0x72;
//  byte unknownHead = 0x73;
	public DataHead() {
	}

   public static byte getDataHead(String type){
		if(type.equals("sysInfoHead")){
			return 0x70;
		}
		else if (type.equals("screenshotHead")) {
			return 0x71;
		}
		else if (type.equals("authorityHead")){
			return 0x72;
		}
		else if (type.equals("sceenshotCommandHead")){
			return 0x60;
		}
		else if (type.equals("disconnectCommandHead")){
			return 0x61;
		}
		else if (type.equals("killProcessCommandHead")){
			return 0x62;
		}
		else if (type.equals("runProcessCommandHead")){
			return 0x63;
		}
		else if (type.equals("shutdownCommandHead")){
			return 0x64;
		}
		else {
			return 0x74;
		}
		
	}
}
