package com.fivestar.superclass.core.listener;

import java.util.ArrayList;

import com.fivestar.superclass.core.model.course;

public interface getCourseListener {
	public void onGetCourse(ArrayList<course> list);
	
	public void onGetCourseError(int code,String msg);
}
