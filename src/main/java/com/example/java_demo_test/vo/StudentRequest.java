package com.example.java_demo_test.vo;

import javax.persistence.Column;
import javax.persistence.Id;

public class StudentRequest {
	
	private int student_num;
	
	private String name;
	
	public int getStudent_num() {
		return student_num;
	}

	public void setStudent_num(int student_num) {
		this.student_num = student_num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public StudentRequest() {}
	
	public StudentRequest(int student_num,
			String name) {
		this.student_num = student_num;
		this.name = name;
	}

}
