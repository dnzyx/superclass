package com.fivestar.superclass;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fivestar.superclass.core.model.teacher;
import com.fivestar.superclass.db.DBHelper;
import com.fivestar.superclass.db.teacherDBOP;
import com.fivestar.superclass.roundImageview.RoundedImageView;

import java.util.List;

/**
 * Created by admin on 2018/1/9.
 */

public class TeacherAdapter extends ArrayAdapter<teacher> {
    private int resourceId;
    private Context context;
    private List<teacher> list;
    private com.fivestar.superclass.db.teacherDBOP teacherDBOP;
    private int[] head={R.drawable.head1,R.drawable.head2,R.drawable.head3,R.drawable.head4,R.drawable.head5,
            R.drawable.head6,R.drawable.head7,R.drawable.head8,R.drawable.head9,R.drawable.head10,R.drawable.head11,
            R.drawable.head12,R.drawable.head13,R.drawable.head14,R.drawable.head15,R.drawable.head16,};
    public TeacherAdapter( Context context,  int resource,  List<teacher> objects) {
        super(context, resource, objects);
        this.resourceId = resource;
        this.context = context;
        this.list=objects;
        DBHelper helper=new DBHelper(context,"superclass.db", null,1);
        teacherDBOP = new teacherDBOP(helper);
    }
        @Override
        public View getView(int position, final View convertView , ViewGroup parent){
            final teacher teacher = getItem(position);
            View view;
            if(convertView==null) {
                view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            }
            else{
                view=convertView;
            }
            LinearLayout l=(LinearLayout)view.findViewById(R.id.teacher_l);
            RoundedImageView teacherImage = (RoundedImageView) view.findViewById(R.id.teacher_image);
            TextView teacherName = (TextView) view.findViewById(R.id.teacher_name);

            if(teacher.getState()==-1){
                l.setBackgroundColor(Color.GRAY);
            }
            teacherImage.setImageResource(head[this.rand()]);
            teacherName.setText(teacher.getName());
            l.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(teacher.getState()==1||teacher.getState()==-1){
                        String xq=((TeacherListActivity)context).getYear();
                        Intent intent=new Intent(context,courseListActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);;
                        intent.putExtra("id", teacher.getId());
                        intent.putExtra("year",xq);
                        context.startActivity(intent);
                    }
                    else {
                        SharedPreferences sp=((AppCompatActivity)TeacherAdapter.this.context).getSharedPreferences("config",0);
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putString("id",teacher.getId());
                        editor.putString("name",teacher.getName());
                        editor.putString("back","back");
                        editor.commit();
                        ((AppCompatActivity)TeacherAdapter.this.context).finish();
                    }
                }
            });

            return view;
        }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public teacher getItem(int position) {
        return list.get(position);
    }

    private int rand(){
        double d = Math.random();
        String s1 = String.valueOf(d);
        String s2 = s1.substring(0, s1.indexOf(".")) + s1.substring(s1.indexOf(".")+1);
        int i = (int)(Long.parseLong(s2)%head.length);
        return i;
    }
}
