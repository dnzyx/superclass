package com.fivestar.superclass.api.model;

public class course {
	private int id;
	private int date;//星期几
	private int number;//第几节课
	private String year;//学年
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	private String content;//
	private String teacher_id;
	public course() {
		super();
	}
	public int getDate() {
		return date;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTeacher_id() {
		return teacher_id;
	}
	public void setTeacher_id(String teacher_id) {
		this.teacher_id = teacher_id;
	}
	@Override
	public String toString() {
		return "course [id=" + id + ", date=" + date + ", number=" + number + ", content=" + content + ", teacher_id="
				+ teacher_id + "]";
	}
}
