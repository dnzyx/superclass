package com.fivestar.superclass;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fivestar.superclass.roundImageview.StreamUtils;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2017/10/26.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_login;
    TextView tv_reg,tv_tour;
    EditText et_phoneNum,et_password;
    CheckBox cb_isCheck;

    SharedPreferences sp;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //权限处理
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(this, permissions, 1);
        }

        //auto login according to phoneNumsession
        sp=this.getSharedPreferences("config",0);
        if(sp.getString("phoneNumsession","")!=null&&sp.getString("phoneNumsession","").length()>0)
        {
            //要清理栈区!!!
            Intent intent = new Intent(LoginActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent,null);
        }
        this.setTitle("请登录");
        setContentView(R.layout.login_activity);
        et_phoneNum = (EditText) findViewById(R.id.et_phoneNum);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);

        tv_reg= (TextView) findViewById(R.id.login_tv_reg);
        tv_tour=(TextView) findViewById(R.id.login_tv_tour);
        cb_isCheck = (CheckBox) findViewById(R.id.cb_isCheck);
        btn_login.setOnClickListener(this);
        cb_isCheck.setOnClickListener(this);
        tv_reg.setOnClickListener(this);
        tv_tour.setOnClickListener(this);
        //把信息回填在输入框中。
        writeBackInfo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "必须同意所有权限才能使用本程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                } else {
                    Toast.makeText(this, "发生未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                isCheakMethod();
                loginMethod();
                break;
            case R.id.cb_isCheck:
                isCheakMethod();
                break;
            case R.id.login_tv_reg:
                Intent intent=new Intent(LoginActivity.this,reg_activity.class);
                startActivity(intent);
                break;
            case R.id.login_tv_tour:
                Toast.makeText(LoginActivity.this,"开启体验模式...",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }

    }

    /**
     * 把用户的复选框信息返显在界面上
     */
    public void writeBackInfo(){
            sp = getSharedPreferences("config",0);
            cb_isCheck.setChecked(sp.getBoolean("isCheak",false));
            et_phoneNum.setText(sp.getString("phoneNum",""));
            et_password.setText(sp.getString("password",""));
    }

    /**
     * 将信息保存起来
     */
    public void isCheakMethod(){
        sp = getSharedPreferences("config",0);
        SharedPreferences.Editor editor = sp.edit();
        if(cb_isCheck.isChecked()) {
            editor.putBoolean("isCheak",true);
            String phoneNum = et_phoneNum.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            editor.putString("phoneNum",phoneNum);
            editor.putString("password",password);
            editor.commit();
        }else{
            editor.putBoolean("isCheak",false);
            editor.remove("phoneNum");
            editor.remove("password");
            editor.commit();
        }
    }

    /**
     * 登录按钮的判断逻辑
     */
    public void loginMethod(){
        Intent intent = new Intent(LoginActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
