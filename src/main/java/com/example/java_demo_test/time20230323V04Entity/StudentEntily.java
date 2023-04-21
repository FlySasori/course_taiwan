package com.example.java_demo_test.time20230323V04Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "student"/*<<<*/)
public class StudentEntily {

	@Id//ªí¥Ü¥DÁä
	@Column(name = "student_num")
	private int student_num;
	
	@Column(name = "name")
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

	public StudentEntily(){
	}
			
	public StudentEntily(int student_num ,String name) {
		this.student_num = student_num;
		this.name = name;

	}
	
}
