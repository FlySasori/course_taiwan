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
		//�Ǹ��i�_�ϥΧP�_�C
		if(/*�p�󵥩�0�P�_*/
				studentRequest.getStudent_num() <= 0){
			return new StudentResponse("�Ǹ����i����p��0�C");
		}//�ҵ{�N�X���ƧP�_(��)�I3
		
		//�W�r�i�_�ϥΧP�_�C
		if(/*null�P�_*/
				!StringUtils.hasText(studentRequest.getName())){
			return new StudentResponse("�m�W���i��null");
		}//�ҵ{�N�X���ƧP�_(��)�I3
		
		StudentEntily studentEntily = new StudentEntily(
				studentRequest.getStudent_num(),
				studentRequest.getName());
		
		return new StudentResponse/*<<<*/(
				FlyKiteMySQL.AddSave(studentEntily));/*//�I3:*//*<<<*//*
				�A�ե�FlyKiteMySQL.AddSave�A�|�۰ʿ�X�O�_��ƭ���String�T���A
				�ηs�W���\�T���C
				�`�N:
				�]���ҵ{�N�X�O�D��A�ҥH�եΤ�k�ɦ۰ʧP�_*/
	}
	
	@Override
	public StudentResponse reviseStudent(StudentRequest studentRequest) {
		//�Ǹ��i�_�ϥΧP�_�C
		if(/*�p�󵥩�0�P�_*/
				studentRequest.getStudent_num() <= 0){
			return new StudentResponse("�Ǹ����i����p��0�C");
		}//�ҵ{�N�X���ƧP�_(��)�I3
		
		//�W�r�i�_�ϥΧP�_�C
		if(/*null�P�_*/
				!StringUtils.hasText(studentRequest.getName())){
			return new StudentResponse("�m�W���i��null");
		}//�ҵ{�N�X���ƧP�_(��)�I3
		
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
			return new StudentResponse("�L���ǥ͸��");
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
		//�Ҧ��w��ҵ{
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
			return new StudentResponse("�ǥ͵L�����ҵ{");
		}
		
		List<CourseEntity> courseEntityList = new ArrayList<>();
		
		try {
			courseEntityList = 
					FlyKiteMySQL.findAll(CourseEntity.class);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		//��M�w��ҵ{
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
