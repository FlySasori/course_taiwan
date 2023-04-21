package com.example.java_demo_test.time20230323V04Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "student_and_course"/*<<<*/)
public class Student_and_courseEntity {

	@Column(name = "student_num")
	private int student_num;
	
	@Column(name = "course_num")
	private int course_num;
	
	@Id//ªí¥Ü¥DÁä
	@Column(name = "num")
	private int num;
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

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

	public Student_and_courseEntity(){
	}
			
	public Student_and_courseEntity(int student_num ,int course_num) {
		this.student_num = student_num;
		this.course_num = course_num;

	}
	
}
