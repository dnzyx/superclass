package com.fivestar.superclass.roundImageview;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dnzyx on 2017/11/7.
 */

public class StreamUtils {
    //将流转化为string类型的数据
    public static String inputStreamToString(InputStream in) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int len = -1;
        byte[] buffer = new byte[1024];
        while((len = in.read(buffer))!= -1){
            baos.write(buffer,0,len);
        }
        in.close();
        String content = new String(baos.toByteArray());
        return content;

    }
}
