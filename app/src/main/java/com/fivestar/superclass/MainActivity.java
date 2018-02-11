package com.fivestar.superclass;


import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView iv_main;
    ImageView iv_stopping;
    ImageView iv_station;
    ImageView iv_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        replaceFragment(new index_window());
        this.setTitle("超机课程表");

        iv_main = (ImageView) findViewById(R.id.iv_main);
        iv_stopping = (ImageView) findViewById(R.id.iv_stopping);
        iv_station = (ImageView) findViewById(R.id.iv_station);
        iv_me = (ImageView) findViewById(R.id.iv_me);

        iv_main.setOnClickListener(this);
        iv_stopping.setOnClickListener(this);
        iv_station.setOnClickListener(this);
        iv_me.setOnClickListener(this);

        MysetImageResource(R.id.iv_main);

        SharedPreferences sp=getSharedPreferences("config",0);
        String s=sp.getString("back","");
        if(s!=null&&s.length()>0){
            replaceFragment(new search_window());
            MysetImageResource(R.id.iv_stopping);
        }
    }

    private void replaceFragment(Fragment fragment){
        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_up ,fragment);
        transaction.commit();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.iv_main:
                replaceFragment(new index_window());
                MysetImageResource(R.id.iv_main);
                break;
            case R.id.iv_stopping:
                replaceFragment(new search_window());
                MysetImageResource(R.id.iv_stopping);
                break;
            case R.id.iv_station:
                MysetImageResource(R.id.iv_station);
                replaceFragment(new Station_window());
                break;
            case R.id.iv_me:
                MysetImageResource(R.id.iv_me);
                replaceFragment(new Me_window());
                break;
        }

    }

    public void MysetImageResource(int id){
        if(id == R.id.iv_main){
            iv_main.setImageResource(R.mipmap.menu_index1);
            iv_stopping.setImageResource(R.mipmap.menu_parking);
            iv_station.setImageResource(R.mipmap.menu_station);
            iv_me.setImageResource(R.mipmap.menu_me);

        }else if(id == R.id.iv_stopping){
            iv_main.setImageResource(R.mipmap.menu_index);
            iv_stopping.setImageResource(R.mipmap.menu_parking1);
            iv_station.setImageResource(R.mipmap.menu_station);
            iv_me.setImageResource(R.mipmap.menu_me);

        }else if(id == R.id.iv_station){
            iv_main.setImageResource(R.mipmap.menu_index);
            iv_stopping.setImageResource(R.mipmap.menu_parking);
            iv_station.setImageResource(R.mipmap.menu_station1);
            iv_me.setImageResource(R.mipmap.menu_me);

        }else if(id == R.id.iv_me){
            iv_main.setImageResource(R.mipmap.menu_index);
            iv_stopping.setImageResource(R.mipmap.menu_parking);
            iv_station.setImageResource(R.mipmap.menu_station);
            iv_me.setImageResource(R.mipmap.menu_me1);

        }
    }
}
