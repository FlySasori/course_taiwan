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
@Table(name = "course_data"/*<<<*/)
public class CourseEntity {

	@Id//ªí¥Ü¥DÁä
	@Column(name = "course_num")
	private int course_num;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "week")
	private int week;
	
	@Column(name = "start_time")
	private String start_time;
	
	@Column(name = "over_time")
	private String over_time;

	@Column(name = "credit")
	private int credit;
	
	public Integer getCourse_num() {
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

	public Integer getWeek() {
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

	public CourseEntity(){
	}
			
	public CourseEntity(int course_num,String name, int week,
			String start_time, String over_time, int credit) {
		this.course_num = course_num;
		this.name = name;
		this.week = week;
		this.start_time = start_time;
		this.over_time = over_time;
		this.credit = credit;
	}
	
}
