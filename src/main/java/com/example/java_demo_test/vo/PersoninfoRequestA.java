package com.example.java_demo_test.vo;

import java.util.List;

import com.example.java_demo_test.time20230323V04Entity.PersonInfoT20230330;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//加在class上可以不顯示所有值為null的變數。
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersoninfoRequestA {
	private String id;
	
	private String name;
	
	private int age;
	
	private String city;
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@JsonProperty("person_info")
	private List<PersonInfoT20230330> personInfoT20230330List;
	
	private String massage;

	@JsonProperty("person_info")
	public List<PersonInfoT20230330> getpersonInfoT20230330List() {
		return personInfoT20230330List;
	}
	
	@JsonProperty("person_info")
	public void setpersonInfoT20230330List(List<PersonInfoT20230330> personInfoT20230330List) {
		this.personInfoT20230330List = personInfoT20230330List;
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
	
	public PersoninfoRequestA(){
	}
		
	public PersoninfoRequestA(String massage){
		this.massage = massage;
	}
	
	public PersoninfoRequestA(List<PersonInfoT20230330> personInfoT20230330List, 
			String massage){
		this.personInfoT20230330List = personInfoT20230330List;
		this.massage = massage;
	}
	
	public PersoninfoRequestA(String id,String name, Integer age, String massage) {
		this.id=id;
		this.name = name;
		this.age = age;
		this.massage = massage;
	}
}
