package com.fivestar.superclass.db;

import android.database.Cursor;

import com.fivestar.superclass.core.model.teacher;

import java.util.ArrayList;

/**
 * Created by ASUS-PC on 2018/1/11.
 */

public class teacherDBOP {
    private DBHelper helper;
    private  Cursor c;

    public teacherDBOP(DBHelper helper){
        this.helper=helper;
    }


    public void insert(teacher t){
        try {
            String sql = "insert into teachers(teacher_id,teacher_name,teacher_state)values(" + "'" + t.getId() + "'," + "'" + t.getName() + "',"+t.getState()+")";
            helper.getWritableDatabase().execSQL(sql);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<teacher> queryAll(){
        try {
            String sql = "select * from teachers";//显示teachers表中的所有数据
            c = helper.getReadableDatabase().rawQuery(sql,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<teacher> list = new ArrayList<teacher>();
        while(c.moveToNext()){
            teacher t = new teacher();

            t.setId(c.getString(0));
            t.setName(c.getString(1));
            t.setState(c.getInt(2));
            list.add(t);
        }
        return list;
    }

    public teacher queryOne(String id){
        try{
            String sql = "select * from teachers where teacher_id = '"+id+"';";
            c = helper.getReadableDatabase().rawQuery(sql,null);
        }catch (Exception e){
            e.printStackTrace();
        }

        teacher t = new teacher();
        if (c.moveToNext()==false){

            return null;
        }
        t.setId(c.getString(0));
        t.setName(c.getString(1));
        t.setState(c.getInt(2));

        return t;
    }

    public ArrayList queryLike(String name){
        try {
            String sql = "select * from teachers where teacher_name like '%"+name+"%'";
            c = helper.getReadableDatabase().rawQuery(sql,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<teacher> list = new ArrayList<teacher>();
        while(c.moveToNext()){
            teacher t = new teacher();

            t.setId(c.getString(0));
            t.setName(c.getString(1));
            t.setState(c.getInt(2));

            list.add(t);
        }
        return list;
    }

    public void delete(String id){
        String sql = "delete from teachers where teacher_id = '" +id+"';";
        helper.getWritableDatabase().execSQL(sql);
    }

    public void update(teacher t){
        String sql = "update teachers set teacher_id='"+t.getId()+"' ,teacher_name='"+t.getName()+"', teacher_state="+t.getState()+ " where teacher_id= '" + t.getId() + "';";;
        helper.getWritableDatabase().execSQL(sql);
    }

}
