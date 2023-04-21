package com.example.java_demo_test.time20230323V04Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.time20230323V04Entity.CourseEntity;
import com.example.java_demo_test.time20230323V04Entity.StudentEntily;
import com.example.java_demo_test.time20230323V04Entity.Student_and_courseEntity;
import com.example.java_demo_test.time20230323V04Interface.StudentInterface;
import com.example.java_demo_test.vo.StudentRequest;
import com.example.java_demo_test.vo.StudentResponse;

@Service
public class StudentService implements 
StudentInterface{

	@Override
	public StudentResponse addStudent(StudentRequest studentRequest) {
		//學號可否使用判斷。
		if(/*小於等於0判斷*/
				studentRequest.getStudent_num() <= 0){
			return new StudentResponse("學號不可等於小於0。");
		}//課程代碼重複判斷(略)點3
		
		//名字可否使用判斷。
		if(/*null判斷*/
				!StringUtils.hasText(studentRequest.getName())){
			return new StudentResponse("姓名不可為null");
		}//課程代碼重複判斷(略)點3
		
		StudentEntily studentEntily = new StudentEntily(
				studentRequest.getStudent_num(),
				studentRequest.getName());
		
		return new StudentResponse/*<<<*/(
				FlyKiteMySQL.AddSave(studentEntily));/*//點3:*//*<<<*//*
				再調用FlyKiteMySQL.AddSave，會自動輸出是否資料重複String訊息，
				或新增成功訊息。
				注意:
				因為課程代碼是主鍵，所以調用方法時自動判斷*/
	}
	
	@Override
	public StudentResponse reviseStudent(StudentRequest studentRequest) {
		//學號可否使用判斷。
		if(/*小於等於0判斷*/
				studentRequest.getStudent_num() <= 0){
			return new StudentResponse("學號不可等於小於0。");
		}//課程代碼重複判斷(略)點3
		
		//名字可否使用判斷。
		if(/*null判斷*/
				!StringUtils.hasText(studentRequest.getName())){
			return new StudentResponse("姓名不可為null");
		}//課程代碼重複判斷(略)點3
		
		StudentEntily studentEntily = new StudentEntily(
				studentRequest.getStudent_num(),
				studentRequest.getName());
		
		return new StudentResponse/*<<<*/(
				FlyKiteMySQL.ReviseSave(studentEntily));
	}

	@Override
	public StudentResponse findStudentData(StudentRequest studentRequest) {
		
		List<StudentEntily> studentEntilyList = new ArrayList<>();
		
		try {
			studentEntilyList = 
					FlyKiteMySQL.findAll(StudentEntily.class);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		StudentEntily studentEntily = null;
		
		for(StudentEntily studentEntilyV001 : studentEntilyList) {
			
			if(studentEntilyV001.getStudent_num() != 
					studentRequest.getStudent_num()) {
				continue;
			}
			studentEntily = studentEntilyV001;
			break;
		}
		
		if(studentEntily == null) {
			return new StudentResponse("無此學生資料");
		}
		
		/*======================================================*/
		
		List<Student_and_courseEntity> student_and_courseEntityList = 
				new ArrayList<>();
		
		try {
			student_and_courseEntityList = 
					FlyKiteMySQL.findAll(Student_and_courseEntity.class);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//所有已選課程
		List<Integer> myCourse_nums = new ArrayList<>();
		
		for(Student_and_courseEntity student_and_courseEntity : 
			student_and_courseEntityList) {
			if(student_and_courseEntity.getStudent_num() != 
					studentRequest.getStudent_num()) {
				continue;
			}
			myCourse_nums.add(student_and_courseEntity.getCourse_num());
		}
		
		if(myCourse_nums.size() == 0) {
			return new StudentResponse("學生無選任何課程");
		}
		
		List<CourseEntity> courseEntityList = new ArrayList<>();
		
		try {
			courseEntityList = 
					FlyKiteMySQL.findAll(CourseEntity.class);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//找尋已選課程
		List<CourseEntity> myCourseEntitys = new ArrayList<>();
		
		for(CourseEntity courseEntity : courseEntityList) {
			
			for(int myCourse_num : myCourse_nums) {
				
				if(courseEntity.getCourse_num() !=
						myCourse_num) {
					continue;
				}
				
				myCourseEntitys.add(courseEntity);
			}
		}
		
		return new StudentResponse
				(studentEntily.getName(), myCourseEntitys);
	}
}
