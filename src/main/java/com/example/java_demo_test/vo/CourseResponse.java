package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.time20230323V04Entity.CourseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseResponse {
	
	//學生資料
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int student_num;
	
	private String student_name;
		
	//課程資料
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int course_num;
	
	private String name;
	
	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int week;
	
	private String start_time;
	
	private String over_time;

	@JsonInclude(JsonInclude.Include.NON_DEFAULT)
	private int credit;
	
	private String message;
	
	//多課程
	
	private List<CourseEntity> courseEntityList;
	
	public List<CourseEntity> getCourseEntityList() {
		return courseEntityList;
	}

	public void setCourseEntityList(List<CourseEntity> courseEntityList) {
		this.courseEntityList = courseEntityList;
	}

	public int getStudent_num() {
		return student_num;
	}

	public void setStudent_num(int student_num) {
		this.student_num = student_num;
	}

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public CourseResponse() {}
	
	public CourseResponse(String message) {
		this.message = message;
	}
	
	public CourseResponse(List<CourseEntity> courseEntityList) {
		this.courseEntityList = courseEntityList;
	}
	
	public CourseResponse(int student_num, String student_name) {
		this.student_num = student_num;
		this.student_name = student_name;
	}

	
	public CourseResponse(int course_num, String name, int week, 
			String start_time, String over_time, int credit) {
		this.course_num = course_num;
		this.name = name;
		this.week = week;
		this.start_time = start_time;
		this.over_time = over_time;
		this.credit = credit;
	}


}
