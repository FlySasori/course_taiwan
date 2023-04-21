package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.time20230323V04Entity.CourseEntity;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentResponse {
	
	private String student_name;
	
	private List<CourseEntity> allCourseEntity;
	
	private String message;

	public String getStudent_name() {
		return student_name;
	}

	public void setStudent_name(String student_name) {
		this.student_name = student_name;
	}

	public List<CourseEntity> getAllCourseEntity() {
		return allCourseEntity;
	}

	public void setAllCourseEntity(List<CourseEntity> allCourseEntity) {
		this.allCourseEntity = allCourseEntity;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public StudentResponse() {}
	
	public StudentResponse(String student_name, List<CourseEntity> allCourseEntity) {
		this.student_name = student_name;
		this.allCourseEntity = allCourseEntity;
	}
	
	public StudentResponse(String message) {
		this.message = message;
	}

}
