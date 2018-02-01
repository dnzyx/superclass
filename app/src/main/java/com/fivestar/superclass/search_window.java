package com.fivestar.superclass;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.fivestar.superclass.core.listener.getCodeListener;
import com.fivestar.superclass.core.model.term;
import com.fivestar.superclass.data.getCodeClient;

import java.util.ArrayList;

public class search_window extends Fragment implements getCodeListener,AdapterView.OnItemSelectedListener {
    private TextView button1;
    private ArrayAdapter<String> adapter;
    private Button search;
    private EditText code;
    private ProgressDialog progressBar;
    private ImageView codeImg;
    private SharedPreferences sp;
    private Spinner spDown;
    private ArrayList<String> terms=new ArrayList<String>();
    private String xq="",js="",yzm="20170";

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.search, container, false);

        initTerms();
        button1 = (TextView)v.findViewById(R.id.input_user_password_two);
        search=(Button)v.findViewById(R.id.search);
        spDown=(Spinner)v.findViewById(R.id.year);
        code=(EditText)v.findViewById(R.id.code);
        codeImg=(ImageView)v.findViewById(R.id.codeImg);
        adapter=new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,terms);


        spDown.setAdapter(adapter);
        spDown.setOnItemSelectedListener(this);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(search_window.this.getActivity(),TeacherListActivity.class);
                intent.putExtra("year",xq);
                startActivity(intent);
            }
        });

        return v;
    }


    private void initTerms(){
        term t;
        t=new term();
        t.setContent("2017-2018第1学期");
        t.setId("20170");
        terms.add(t.getContent()+"-"+t.getId());

        t=new term();
        t.setContent("2016-2017第2学期");
        t.setId("20161");
        terms.add(t.getContent()+"-"+t.getId());

        t.setContent("2016-2017第1学期");
        t.setId("20160");
        terms.add(t.getContent()+"-"+t.getId());


    }




    @Override
    public void onStart() {
        super.onStart();
        FragmentActivity a = this.getActivity();
        sp=a.getSharedPreferences("config",0);
        js=sp.getString("id","");
        if(js!=null&&js.length()>0){
            button1.setText(sp.getString("name",""));
            button1.setBackgroundColor(Color.WHITE);
        }
        else{
            button1.setText("");
            button1.setBackgroundResource(R.drawable.shuru2);
        }
        codeImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(js==null||js.length()==0){
                    Toast.makeText(search_window.this.getActivity(),"请先选择老师！",Toast.LENGTH_LONG).show();
                }
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yzm=code.getText().toString();
                if(js==null||js.length()==0){
                    Toast.makeText(search_window.this.getActivity(),"请选择老师！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(yzm==null||yzm.length()!=4){
                    Toast.makeText(search_window.this.getActivity(),"请输入4位的验证码！",Toast.LENGTH_LONG).show();
                    return;
                }
                Intent intent = new Intent(search_window.this.getActivity(),courseListActivity.class);
                intent.putExtra("id",js);
                intent.putExtra("year",xq);
                intent.putExtra("code",yzm);
                startActivity(intent);
            }
        });
        String s=sp.getString("back","");
        if(s!=null&&s.length()>0){
            progressBar = ProgressDialog.show(this.getActivity(), "正在处理",
                    "正在进入，请稍等...");
            SharedPreferences.Editor editor = sp.edit();
            editor.remove("back");
            editor.remove("id");
            editor.remove("name");
            editor.commit();
            getCodeClient codeClient = new getCodeClient();
            codeClient.setCodeListener(this);
            codeClient.start();
        }
    }

    @Override
    public void onGetCode(byte[] b) {
        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }
            Bitmap bitmap = BitmapFactory.decodeByteArray(b,0, b.length);
            codeImg.setImageBitmap(bitmap);
    }

    @Override
    public void onGetCodeError(int code, String msg) {
        if (progressBar.isShowing()) {
            progressBar.dismiss();
        }
        codeImg.setImageResource(android.R.drawable.ic_delete);
        Toast.makeText(this.getActivity(),msg+code,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String content=adapter.getItem(position);//获取选中的那一项
        int index=content.lastIndexOf("-");
        xq=content.substring(index+1,content.length());
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        yzm="20170";
    }
}
