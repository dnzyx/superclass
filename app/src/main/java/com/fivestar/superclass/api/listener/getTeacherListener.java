package com.fivestar.superclass.api.listener;

import java.util.ArrayList;

import com.fivestar.superclass.api.model.teacher;

public interface getTeacherListener {
	public void onGetTeacher(ArrayList<teacher> list);
	
	public void OnGetTeacherError(int code,String msg);

}
