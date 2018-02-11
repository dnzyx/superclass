package com.fivestar.superclass;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.fivestar.superclass.api.listener.getCourseListener;
import com.fivestar.superclass.api.model.course;
import com.fivestar.superclass.api.model.teacher;
import com.fivestar.superclass.api.util.inf;
import com.fivestar.superclass.api.client.getCourseClient;
import com.fivestar.superclass.db.DBHelper;
import com.fivestar.superclass.db.courseDBOP;
import com.fivestar.superclass.db.teacherDBOP;

import java.util.ArrayList;

public class courseListActivity extends AppCompatActivity implements getCourseListener {

    private courseDBOP courseDBOP;
    private teacherDBOP teacherDBOP;
    private String id;
    private String xq;
    private String yzm;
    int i,j;
    private ProgressDialog progressBar;

    private int[][] tvs={
            {R.id.Tv22,R.id.Tv23,R.id.Tv24,R.id.Tv25,R.id.Tv26,R.id.Tv27,R.id.Tv28,},
            {R.id.Tv32,R.id.Tv33,R.id.Tv34,R.id.Tv35,R.id.Tv36,R.id.Tv37,R.id.Tv38,},
            {R.id.Tv42,R.id.Tv43,R.id.Tv44,R.id.Tv45,R.id.Tv46,R.id.Tv47,R.id.Tv48,},
            {R.id.Tv52,R.id.Tv53,R.id.Tv54,R.id.Tv55,R.id.Tv56,R.id.Tv57,R.id.Tv58,},
            {R.id.Tv62,R.id.Tv63,R.id.Tv64,R.id.Tv65,R.id.Tv66,R.id.Tv67,R.id.Tv68,},
            {R.id.Tv72,R.id.Tv73,R.id.Tv74,R.id.Tv75,R.id.Tv76,R.id.Tv77,R.id.Tv78,},
            {R.id.Tv82,R.id.Tv83,R.id.Tv84,R.id.Tv85,R.id.Tv86,R.id.Tv87,R.id.Tv88,}

    };
    private  int[] colors={Color.RED,Color.MAGENTA,Color.YELLOW,Color.GREEN,Color.CYAN};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courselist_layout);

        progressBar = ProgressDialog.show(this,"处理中。。。",
                "正在找到结果，请稍等...…");

        DBHelper helper = new DBHelper(this,"superclass.db", null,1);
        courseDBOP=new courseDBOP(helper);
        teacherDBOP=new teacherDBOP(helper);

        Intent intent=this.getIntent();
        xq=intent.getStringExtra("year");
        id=intent.getStringExtra("id");
        yzm=intent.getStringExtra("code");
        this.setTitle(teacherDBOP.queryOne(id).getName()+" "+id);

        if(teacherDBOP.queryOne(id).getState()==0){
            getCourseClient client = new getCourseClient();
            client.setListener(this);
            client.start(xq,id,yzm);
        }
        else {
            ArrayList<course> l = courseDBOP.queryAll(id, xq);
            if (l.isEmpty()) {
                this.onGetCourseError(inf.DATA_EMPTY,"该教师无课");
            } else {
                for (final course course : l) {
                    TextView t = (TextView) findViewById(tvs[course.getNumber() - 1][course.getDate() - 1]);
                    if (course.getContent().length() != 0) {
                        int i = rand();
                        Log.i("hhh", i + "");
                        t.setBackgroundColor(colors[i]);
                        t.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //Toast.makeText(courseListActivity.this, course.getContent(), Toast.LENGTH_LONG).show();
                                courseListActivity.this.showCourseDialog("详细信息",course.getContent());
                            }
                        });
                    }
                    String str = course.getContent();
                    if (str.length() > 10) {
                        str = course.getContent().substring(0, 10) + "...";
                    }
                    t.setText(str);
                }
            }
        }

        addListener();

        if (progressBar!=null&&progressBar.isShowing()) {
            progressBar.dismiss();
        }
    }

    private void addListener(){
        for(i=0;i<tvs.length;i++){
            for(j=0;j<tvs[i].length;j++){
                final int k=i,l=j;
                final TextView tmp=(TextView)findViewById(tvs[i][j]);
                if(tmp.getText().length()==0) {
                    tmp.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(courseListActivity.this, addCourseActivity.class);
                            intent.putExtra("id", id);
                            intent.putExtra("year", xq);
                            intent.putExtra("date", l+1 );
                            intent.putExtra("number", k+1);
                            startActivity(intent);

                        }
                    });
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);;
        startActivity(intent);
    }

    private int rand(){
        double d = Math.random();
        String s1 = String.valueOf(d);
        String s2 = s1.substring(0, s1.indexOf(".")) + s1.substring(s1.indexOf(".")+1);
        int i = (int)(Long.parseLong(s2)%colors.length);
        return i;
    }
    @Override
    public void onGetCourse(ArrayList<course> list) {
        for(final course course:list){
            TextView t= (TextView) findViewById(tvs[course.getNumber()-1][course.getDate()-1]);
            if(course.getContent().length()!=0){
               int i=rand();
                Log.i("hhh",i+"");
                t.setBackgroundColor(colors[i]);
                courseDBOP.insert(course);
                t.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       courseListActivity.this.showCourseDialog("详细信息",course.getContent());
                    }
                });

            }
            String str=course.getContent();
            if(str.length()>10)
            {
                str=course.getContent().substring(0,10)+"...";
            }
            t.setText(str);
        }
        teacher t=teacherDBOP.queryOne(id);
        t.setState(1);
        teacherDBOP.update(t);
    }

    @Override
    public void onGetCourseError(int code, String msg) {
        if(code== inf.DATA_EMPTY){
            teacher t=teacherDBOP.queryOne(id);
            t.setState(-1);
            teacherDBOP.update(t);
            this.showNormalDialog("无课 =_+", msg,code);
        }
        else if(code==inf.CODE_ERROR){
            this.showNormalDialog("验证码错误 +_+||", msg,code);
        }
        else {
            this.showNormalDialog("错误 =_=||"+code, msg,code);
        }
    }

    @Override
    public void finish() {
        Intent intent = new Intent(this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void showNormalDialog(String title, String msg, final int code){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(courseListActivity.this);
        if(code==inf.DATA_EMPTY) {
            normalDialog.setIcon(android.R.drawable.star_big_on);
        }
        else {
            normalDialog.setIcon(android.R.drawable.ic_delete);
        }
        if(code==inf.CODE_ERROR) {
            normalDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    Intent intent = new Intent(courseListActivity.this, MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    courseListActivity.this.startActivity(intent);
                }
            });
        }
        normalDialog.setTitle(title);
        normalDialog.setMessage(msg);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=new Intent(courseListActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);;
                        courseListActivity.this.startActivity(intent);
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(code==inf.CODE_ERROR){
                            Intent intent=new Intent(courseListActivity.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);;
                            courseListActivity.this.startActivity(intent);
                        }
                    }
                });
        // 显示
        normalDialog.show();
    }
    private void showCourseDialog(String title,String msg){
        /* @setIcon 设置对话框图标
         * @setTitle 设置对话框标题
         * @setMessage 设置对话框消息提示
         * setXXX方法返回Dialog对象，因此可以链式设置属性
         */
        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(courseListActivity.this);
        normalDialog.setIcon(android.R.drawable.sym_def_app_icon);
        normalDialog.setTitle(title);
        normalDialog.setMessage(msg);
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        normalDialog.setNegativeButton("关闭",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                    }
                });
        // 显示
        normalDialog.show();
    }
}
