package com.fivestar.superclass.api.model;

public class teacher {
	private String id;
	private String name;
	private int state;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public teacher() {
		super();
	}
	public teacher(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "teacher{" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", state=" + state +
				'}';
	}
}
