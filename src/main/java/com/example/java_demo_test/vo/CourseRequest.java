package com.example.java_demo_test.vo;

import javax.persistence.Column;
import javax.persistence.Id;

public class CourseRequest {
	
	//學生資料
	
	private int student_num;
		
	//課程資料
	
	private int course_num;
	
	private String name;
	
	private int week;
	
	private String start_time;
	
	private String over_time;

	private int credit;
	
	public int getStudent_num() {
		return student_num;
	}


	public void setStudent_num(int student_num) {
		this.student_num = student_num;
	}


	public int getCourse_num() {
		return course_num;
	}


	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getWeek() {
		return week;
	}


	public void setWeek(int week) {
		this.week = week;
	}


	public String getStart_time() {
		return start_time;
	}


	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}


	public String getOver_time() {
		return over_time;
	}


	public void setOver_time(String over_time) {
		this.over_time = over_time;
	}


	public int getCredit() {
		return credit;
	}


	public void setCredit(int credit) {
		this.credit = credit;
	}


	public CourseRequest() {}
	
	public CourseRequest(int course_num, String name, int week, 
			String start_time, String over_time, int credit) {
		this.course_num = course_num;
		this.name = name;
		this.week = week;
		this.start_time = start_time;
		this.over_time = over_time;
		this.credit = credit;

	}
}
