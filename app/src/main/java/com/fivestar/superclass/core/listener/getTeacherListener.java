package com.fivestar.superclass.core.listener;

import java.util.ArrayList;

import com.fivestar.superclass.core.model.teacher;

public interface getTeacherListener {
	public void onGetTeacher(ArrayList<teacher> list);
	
	public void OnGetTeacherError(int code,String msg);

}
