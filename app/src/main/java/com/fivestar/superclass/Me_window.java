package com.fivestar.superclass;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fivestar.superclass.roundImageview.RoundedImageView;

/**
 * Created by dnzyx on 2017/10/26.
 */

public class Me_window extends Fragment implements OnClickListener {

    ImageButton img_set;
    RoundedImageView img_myphoto;
    Button btn_center,btn_order,btn_belongs,btn_market;
    LinearLayout l_inf,l_suggest,l_share,l_about;
    TextView tv_name ;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_window, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        FragmentActivity a=this.getActivity();

        SharedPreferences sp = getActivity().getSharedPreferences("config",0);

        //找到自己关心的控件。
        img_myphoto = (RoundedImageView) a.findViewById(R.id.me_ibn_myphoto);
        img_set=(ImageButton)a.findViewById(R.id.me_ibn_setting);
        btn_center = (Button) a.findViewById(R.id.me_btn_infCenter);
        btn_order = (Button) a.findViewById(R.id.me_btn_myOrder);
        btn_belongs = (Button) a.findViewById(R.id.me_btn_myBelongings);
        btn_market = (Button) a.findViewById(R.id.me_btn_pointMarket);
        l_about = (LinearLayout) a.findViewById(R.id.me_about);
        l_inf = (LinearLayout) a.findViewById(R.id.me_myinf);
        l_share = (LinearLayout) a.findViewById(R.id.me_share);
        l_suggest = (LinearLayout) a.findViewById(R.id.me_suggest);
        tv_name = (TextView) a.findViewById(R.id.me_tv_username);

        tv_name.setText(sp.getString("phoneNumsession","")==null||sp.getString("phoneNumsession","").length()==0 ?"请登录/注册" :sp.getString("phoneNumsession",""));

        //设置监听事件。
        img_myphoto.setOnClickListener(this);
        img_set.setOnClickListener(this);
        btn_center.setOnClickListener(this);
        btn_belongs.setOnClickListener(this);
        btn_market.setOnClickListener(this);
        btn_order.setOnClickListener(this);
        l_inf.setOnClickListener(this);
        l_suggest.setOnClickListener(this);
        l_share.setOnClickListener(this);
        l_about.setOnClickListener(this);
        tv_name.setOnClickListener(this);
    }

    public void onClick(View v) {
        SharedPreferences sp = getActivity().getSharedPreferences("config",0);
        SharedPreferences.Editor editor = sp.edit();
        switch (v.getId())
        {
            case R.id.me_tv_username:
                if(sp.getString("phoneNumsession","")==null||sp.getString("phoneNumsession","").length()==0 )
                {
                    //要清理栈区!!!
                    Intent intent = new Intent(this.getActivity(),LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
                break;
            case R.id.me_ibn_myphoto:
                if(sp.getString("phoneNumsession","")==null||sp.getString("phoneNumsession","").length()==0 )
                {
                    //要清理栈区!!!
                    Intent intent = new Intent(this.getActivity(),LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
               else
                Toast.makeText(this.getActivity(),"change photo", Toast.LENGTH_SHORT).show();
                break;
            case R.id.me_ibn_setting:
                editor.remove("phoneNumsession");
                editor.commit();
                //要清理栈区!!!
                Intent intent = new Intent(this.getActivity(),LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
            case R.id.me_btn_infCenter:
                ;
                break;
            case R.id.me_btn_myOrder:
              ;
                break;
            case R.id.me_btn_myBelongings:
                ;
                break;
            case R.id.me_btn_pointMarket:
                ;
                break;
            case R.id.me_about:
                startActivity(new Intent(this.getActivity(),aboutActivity.class));
                break;
            case R.id.me_myinf:
                Intent intent_myinfo = new Intent(this.getActivity(),Me_MyInfo_Activity.class);
                startActivity(intent_myinfo);
                break;
            case R.id.me_suggest:
                Intent intent_suggest = new Intent(this.getActivity(),Me_Suggestion_Activity.class);
                startActivity(intent_suggest);
                break;
            case R.id.me_share:
                Intent intent_share = new Intent(this.getActivity(),Me_Share_Activity.class);
                startActivity(intent_share);
                break;
        }
    }
}
