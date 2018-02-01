package com.fivestar.superclass;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.util.ArrayList;

/**
 * Created by dnzyx on 2017/10/31.
 */

public class Station_window_item_Des extends AppCompatActivity {

    private ProgressDialog progressBar;

    WebView mWebView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        setContentView(R.layout.station_window_item_des);

        int position = getIntent().getIntExtra("position", -1);
        ArrayList<String> list_title = getIntent().getStringArrayListExtra("list_title");


        mWebView = (WebView) findViewById(R.id.station_wv_item_page);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("http://www.qq.com");



        //设置系统内容。
//        TextView tv_content_title = (TextView)findViewById(R.id.tv_content_title);
//        TextView tv_content_content = (TextView) findViewById(R.id.tv_content_content);
//        ArrayList<String> list_title = getIntent().getStringArrayListExtra("list_title");
//        ArrayList<String> list_content = getIntent().getStringArrayListExtra("list_content");
////        Toast.makeText(Station_window_item_Des.this,"---"+list_title.get(position),Toast.LENGTH_SHORT).show();
//        tv_content_title.setText(list_title.get(position));
//        tv_content_content.setText(list_content.get(position));
    }



}
