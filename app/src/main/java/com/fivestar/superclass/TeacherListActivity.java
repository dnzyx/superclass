package com.fivestar.superclass;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.fivestar.superclass.api.listener.getTeacherListener;
import com.fivestar.superclass.api.model.teacher;
import com.fivestar.superclass.api.client.getTeacherClient;
import com.fivestar.superclass.db.DBHelper;
import com.fivestar.superclass.db.teacherDBOP;

import java.util.ArrayList;

public class TeacherListActivity extends AppCompatActivity implements getTeacherListener {

    //private String[] data = {"贾老师","易老师","并老师"};
    private TeacherAdapter myadapter;
    private ListView lv;
    private EditText input;
    private teacherDBOP teacherDBOP;
    private String year;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_list);

        input=(EditText)findViewById(R.id.search_teacher);
        lv=(ListView)findViewById(R.id.teachers) ;
        year=this.getIntent().getStringExtra("year");
        DBHelper helper = new DBHelper(this,"superclass.db", null,1);
        teacherDBOP=new teacherDBOP(helper);
        ArrayList<teacher> l=teacherDBOP.queryAll();
        if(l.size()==0) {
            getTeacherClient client = new getTeacherClient();
            client.setListener(TeacherListActivity.this);
            client.star();
        }
        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence cs, int start, int before, int count) {
                if (cs.length() < 0) {
                    return;
                }
                ArrayList<teacher> l=teacherDBOP.queryLike(cs.toString());
                myadapter=new TeacherAdapter(TeacherListActivity.this,R.layout.teacher_item,l);
                lv.setAdapter(myadapter);
                Log.i("hhh","select");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @Override
    public void onGetTeacher(ArrayList<teacher> list) {
        for(teacher t:list){
            teacherDBOP.insert(t);
        }
        Log.i("hhh","insert");
    }

    @Override
    public void OnGetTeacherError(int code, String msg) {
        Toast.makeText(this,msg+code,Toast.LENGTH_LONG).show();
    }

    public String getYear() {
        return year;
    }
}
