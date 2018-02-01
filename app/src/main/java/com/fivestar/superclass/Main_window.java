package com.fivestar.superclass;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


/**
 * Created by Lenovo on 2017/10/26.
 */

public class Main_window extends Fragment implements View.OnClickListener {
    private ImageView iv_moreApplication,ad,ad1;
    View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.main_window,container,false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        ad=(ImageView)view.findViewById(R.id.iv_ad);
        ad1=(ImageView)view.findViewById(R.id.iv_ad2);
        iv_moreApplication = (ImageView) view.findViewById(R.id.iv_moreApplication);
        //设置注册监听。
        ad.setOnClickListener(this);
        ad1.setOnClickListener(this);
        iv_moreApplication.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Toast.makeText(this.getActivity(),"程序员正在拼命加班中...",Toast.LENGTH_SHORT).show();

    }
}
