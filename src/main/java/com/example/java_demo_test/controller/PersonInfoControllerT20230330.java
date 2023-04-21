package com.example.java_demo_test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.time20230323V04Entity.PersonInfoT20230330;
import com.example.java_demo_test.time20230323V04Service.PersonInfoServiceImplT0330;
import com.example.java_demo_test.vo.PersoninfoRequest;
import com.example.java_demo_test.vo.PersoninfoRequestA;

@RestController
public class PersonInfoControllerT20230330 {
	
	@Autowired
	private PersonInfoServiceImplT0330 personInfoServiceImpl;
	
	
	@PostMapping("/getPersonInfo_t20230330")/*取得資料庫全部資料*/
	public List<PersonInfoT20230330> getPersonInfo(){
		System.out.println("=====getPersonInfo_t20230330");
		return personInfoServiceImpl.getPersonInfo();
	}
	
}