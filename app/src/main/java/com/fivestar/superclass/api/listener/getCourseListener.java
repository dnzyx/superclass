package com.fivestar.superclass.api.listener;

import java.util.ArrayList;

import com.fivestar.superclass.api.model.course;

public interface getCourseListener {
	public void onGetCourse(ArrayList<course> list);
	
	public void onGetCourseError(int code,String msg);
}
