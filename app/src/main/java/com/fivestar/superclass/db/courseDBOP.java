package com.fivestar.superclass.db;

import android.database.Cursor;

import com.fivestar.superclass.core.model.course;

import java.util.ArrayList;

/**
 * Created by ASUS-PC on 2018/1/11.
 */

public class courseDBOP {
    private DBHelper helper;
    private Cursor c;

    public courseDBOP(DBHelper helper) {
        this.helper = helper;
    }

    public void insert(course c) {
        try {
            String sql = "insert into " +
                    "courses(date,number,year,teacher_id,course_content)" +
                    "values(" + c.getDate() + "," + c.getNumber() + ",'" + c.getYear() + "','" + c.getTeacher_id() + "','" + c.getContent() + "');";
            helper.getWritableDatabase().execSQL(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<course> queryAll(String id,String year){

        ArrayList<course> list = new ArrayList<course>();
        try {
            String sql="select * from courses where teacher_id='"+id+"' and year='"+year+"';";//显示Teacher_id和year
            c = helper.getReadableDatabase().rawQuery(sql,null);
        }catch (Exception e){
            e.printStackTrace();
        }
       while(c.moveToNext()){
           course s = new course();
           s.setId(c.getInt(0));
           s.setDate(c.getInt(1));
           s.setNumber(c.getInt(2));
           s.setYear(c.getString(3));
           s.setTeacher_id(c.getString(4));
           s.setContent(c.getString(5));
           list.add(s);
       }

        return list;
    }
    public course queryOne(int id){
        course s = new course();
        try {
            String sql = "slecte * from where course_id="+id+";";
            c = helper.getReadableDatabase().rawQuery(sql,null);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (c.moveToNext()==false){
            return null;
        }
        s.setId(c.getInt(0));
        s.setDate(c.getInt(1));
        s.setNumber(c.getInt(2));
        s.setYear(c.getString(3));
        s.setTeacher_id(c.getString(4));
        s.setContent(c.getString(5));
        return s;
    }
    public void delete( int id){
        String sql = "delete from courses where course_id="+id+";";//删除表中你需要删除的ID
        helper.getWritableDatabase().execSQL(sql);
    }

    public void update(course s){

    }
}
