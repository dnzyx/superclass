package com.fivestar.superclass.core.listener;

public interface getCodeListener {
	//获取验证码以后的回调函数
	public void onGetCode(byte[] b);
	public void onGetCodeError(int code,String msg);
}
