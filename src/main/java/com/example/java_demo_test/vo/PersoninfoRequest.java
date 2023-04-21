package com.example.java_demo_test.vo;

import com.example.java_demo_test.time20230323V04Entity.PersonInfoT20230330;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

//加在class上可以不顯示所有值為null的變數。
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersoninfoRequest {

	@JsonProperty("person_info")
	private PersonInfoT20230330 personInfoT20230330;
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String massage;
	
	private int age;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@JsonProperty("person_info")
	public PersonInfoT20230330 getPersonInfoT20230330() {
		return personInfoT20230330;
	}
	@JsonProperty("person_info")
	public void setPersonInfoT20230330(PersonInfoT20230330 personInfoT20230330) {
		this.personInfoT20230330 = personInfoT20230330;
	}

	public String getMassage() {
		return massage;
	}

	public void setMassage(String massage) {
		this.massage = massage;
	}

	
	public PersoninfoRequest(){
	}
	
	public PersoninfoRequest(String massage){
		super();
		this.massage = massage;
	}
	
	public PersoninfoRequest(PersonInfoT20230330 personInfo){
		super();
		this.personInfoT20230330 = personInfo;
	}
	
	public PersoninfoRequest(PersonInfoT20230330 personInfo, String massage){
		super();
		this.massage = massage;
		this.personInfoT20230330 = personInfo;
	}

		
}
