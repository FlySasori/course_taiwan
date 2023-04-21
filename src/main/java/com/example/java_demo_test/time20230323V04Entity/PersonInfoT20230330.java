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
@Table(name = "person_info"/*<<<*/)
public class PersonInfoT20230330 {

	@Id//表示主鍵
	@Column(name = "id")
	private String id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "city")
	private String city;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Transient // 標記該屬性不會儲存至資料庫
	private String massage;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	public PersonInfoT20230330(){
	}
		
	public PersonInfoT20230330(String massage){
		this.massage = massage;
	}
	
	public PersonInfoT20230330(String id ,String name, Integer age, String city) {
		this.id=id;
		this.name = name;
		this.age =age;
		this.city = city;
	}
	

	
	public PersonInfoT20230330(String id ,String name, Integer age) {
		this.id=id;
		this.name = name;
		this.age =age;
	}
	
}
