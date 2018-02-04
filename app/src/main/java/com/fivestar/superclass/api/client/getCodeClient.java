package com.fivestar.superclass.api.client;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.fivestar.superclass.api.listener.getCodeListener;
import com.fivestar.superclass.api.util.inf;

public class getCodeClient {
	private getCodeListener listener;
	private int stop=0;
    private byte[] buf;
	public void setCodeListener(getCodeListener l){
		this.listener = l;
	}
	public void start(){
		int end=0;
		//while(end==0) {
			new Thread(new Runnable() {
				public void run() {
					String path="http://"+ inf.host+"/abc.jpeg";
					try {
						String referer ="http://"+ inf.host+"/getcode.jsp";
						URL url = new URL(path);
						HttpURLConnection con = (HttpURLConnection) url.openConnection();
						con.setRequestMethod("GET");
						con.setDoOutput(true);
						con.setDoInput(true);
						con.setRequestProperty("Referer", referer);
						InputStream in = con.getInputStream();
						ByteArrayOutputStream bout = new ByteArrayOutputStream();
						byte[] tmp = new byte[1024];
						int tmpLen = 0;
						while ((tmpLen = in.read(tmp)) != -1) {
							bout.write(tmp, 0, tmpLen);
						}
						buf = bout.toByteArray();
						in.close();
						con.disconnect();
					} catch (Exception e) {
						e.printStackTrace();
						stop=inf.CONNECT_EXCEPTION;
					}
				}
			}).start();
			if(stop!=0){
				listener.onGetCodeError(stop,"连接服务器错误");
				return;
			}
			try {
				Thread.sleep(inf.timeout);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(buf == null){
			listener.onGetCodeError(inf.DATA_EXCEPTION, "获取验证码失败");
			return;
		}
		listener.onGetCode(buf);
		
	}
	private static int cutJpg(byte[] buf,int len){
		int end = 0;
		for(int i=0;i<len-1;i++){
			byte p = buf[i];
			byte n = buf[i+1];
			if(p==-1 && n==-39){
				end = i+2;
				break;
			}
		}
		return end;
	}
	
}
