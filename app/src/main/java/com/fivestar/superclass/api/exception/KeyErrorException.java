package com.fivestar.superclass.api.exception;

/**
 * Created by Administrator on 2018/2/7.
 */

public class KeyErrorException extends Exception {
    public KeyErrorException(){
        super("this key is not available , please check you key!!!");
    }
    public KeyErrorException(String msg){
        super(msg);
    }
}
