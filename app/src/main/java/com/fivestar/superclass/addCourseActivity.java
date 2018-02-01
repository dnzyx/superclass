package com.fivestar.superclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fivestar.superclass.core.model.course;
import com.fivestar.superclass.core.model.teacher;
import com.fivestar.superclass.db.DBHelper;
import com.fivestar.superclass.db.courseDBOP;
import com.fivestar.superclass.db.teacherDBOP;

public class addCourseActivity extends AppCompatActivity {

    private TextView ok;
    private EditText dt;
    private String id;
    private String xq;
    private int date;
    private int number;
    private courseDBOP courseDBOP;
    private teacherDBOP teacherDBOP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addcourse);

        ok=(TextView) findViewById(R.id.okadd);
        dt=(EditText)findViewById(R.id.addcourse);
        Intent intent=this.getIntent();
        xq=intent.getStringExtra("year");
        id=intent.getStringExtra("id");
        date=intent.getIntExtra("date",0);
        number=intent.getIntExtra("number",0);
        this.setTitle("添加 星期"+date+" 第"+number+"节课");
        DBHelper helper = new DBHelper(addCourseActivity.this,"superclass.db", null,1);
        courseDBOP=new courseDBOP(helper);
        teacherDBOP=new teacherDBOP(helper);

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                course c=new course();
                String content=dt.getText().toString();
                if(content.length()==0){
                    Toast.makeText(addCourseActivity.this,"内容不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                c.setContent(content);
                c.setYear(xq);
                c.setTeacher_id(id);
                c.setDate(date);
                c.setNumber(number);
                courseDBOP.insert(c);
                teacher t= teacherDBOP.queryOne(id);
                t.setState(1);
                teacherDBOP.update(t);
                Intent intent = new Intent(addCourseActivity.this, courseListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id",id);
                intent.putExtra("year",xq);
                addCourseActivity.this.startActivity(intent);
            }
        });

    }
}
