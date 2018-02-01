package com.fivestar.superclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
/**
 * Created by Administrator on 2017/11/3.
 */

public class aboutActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setTitle("关于");
        setContentView(R.layout.about_activity);
    }
}
