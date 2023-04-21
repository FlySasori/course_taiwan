package com.example.java_demo_test.time20230323V04Interface;

import com.example.java_demo_test.vo.StudentRequest;
import com.example.java_demo_test.vo.StudentResponse;

public interface StudentInterface {
	public StudentResponse addStudent(StudentRequest studentRequest);
	public StudentResponse reviseStudent(StudentRequest studentRequest);
	public StudentResponse 
	findStudentData(StudentRequest studentRequest);
}
