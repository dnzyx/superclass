package com.fivestar.superclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import com.fivestar.superclass.roundImageview.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2017/10/30.
 */

public class reg_activity extends AppCompatActivity implements View.OnClickListener {
    ImageView btn_back;
    Button btn_get,btn_ok;
    EditText et_phone,et_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg_activity);
        btn_back=(ImageView) findViewById(R.id.reg_iv_backLogin);
//        btn_get=(Button)findViewById(R.id.reg_btn_getcode);
        btn_ok=(Button)findViewById(R.id.reg_btn_ok);//注册
        et_phone=(EditText)findViewById(R.id.reg_et_phone);//手机号
        et_password =(EditText)findViewById(R.id.reg_et_password);//密码
        btn_ok.setOnClickListener(this);
//        btn_get.setOnClickListener(this);
        btn_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
             Intent intent;
            switch (v.getId()){
                case R.id.reg_iv_backLogin:
                    intent=new Intent(reg_activity.this,LoginActivity.class);
                    startActivity(intent);
                    break;
//                case R.id.reg_btn_getcode:
//                    ;
//                    break;
                case R.id.reg_btn_ok:
                    //实现注册逻辑
                    register();
            }
    }



    public void register(){

        MyToast("注册成功");

        //表示用户注册成功，跳转到登录界面。
        Intent intent;
        intent=new Intent(reg_activity.this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }




    //将数据进行弹窗
    public void MyToast(final String feedbackInfo){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),feedbackInfo,Toast.LENGTH_SHORT).show();
            }
        });

    }


}
