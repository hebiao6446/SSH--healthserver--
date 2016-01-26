package com.health.common;

import java.util.ArrayList;
import java.util.List;

public class PushThread extends Thread {
    private String p12Path;
    private ArrayList<String> deviceTokens;
    private String content;
    
	public PushThread(String p12Path, ArrayList<String> deviceTokens, String content){
		this.p12Path =  p12Path;
		this.deviceTokens = deviceTokens;
		this.content = content;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		PushUtils.push2More(p12Path, deviceTokens, content);
		
	}

	
}
