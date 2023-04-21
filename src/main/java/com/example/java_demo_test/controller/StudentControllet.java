package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.time20230323V04Service.StudentService;
import com.example.java_demo_test.vo.StudentRequest;
import com.example.java_demo_test.vo.StudentResponse;

@RestController
public class StudentControllet {
	@Autowired
	StudentService studentService;
	
	@PostMapping("/CourseController_addStudent")
	public StudentResponse addStudent(@RequestBody StudentRequest studentRequest) {
		return studentService.addStudent(studentRequest);
	}
	
	@PostMapping("/CourseController_reviseStudent")
	public StudentResponse reviseStudent(@RequestBody 
			StudentRequest studentRequest) {
		return studentService.reviseStudent(studentRequest);
	}
	
	@PostMapping("/CourseController_findStudentData")
	public StudentResponse findStudentData(@RequestBody 
			StudentRequest studentRequest) {
		return studentService.findStudentData(studentRequest);
	}
}
