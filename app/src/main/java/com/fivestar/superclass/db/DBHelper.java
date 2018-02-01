package com.fivestar.superclass.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ASUS-PC on 2018/1/11.
 */

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table teachers(teacher_id text primary key ,teacher_name text,teacher_state integer);" ;
        db.execSQL(sql);
       sql= "create table courses(course_id integer primary key autoincrement,date integer,number integer,year text,teacher_id text,course_content text);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
