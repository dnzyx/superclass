package com.fivestar.superclass.api.util;

import java.io.ByteArrayInputStream;  
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;  
import java.io.ObjectOutputStream;

/**   
 * @Description: 字节与对象之间的转换
 */  
public class ObjByteStream { 
	
	private ObjByteStream(){
		;
	}
  
    public static Object change(byte[] b) {  
    
    	ByteArrayInputStream byteInt=new ByteArrayInputStream(b);  
        ObjectInputStream objInt;
		try {
			objInt = new ObjectInputStream(byteInt);
			return objInt.readObject();
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}  
        return null;
    }  
    public static byte[] change(Object o){
           
         ByteArrayOutputStream byt=new ByteArrayOutputStream();  
           
         ObjectOutputStream obj;
		try {
			obj = new ObjectOutputStream(byt);
			 obj.writeObject(o);  
		} catch (IOException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
			return null;
		}  
         byte[] bytes=byt.toByteArray();  
         return bytes;  
    }
}  