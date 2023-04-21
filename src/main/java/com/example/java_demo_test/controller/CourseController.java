package com.example.java_demo_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.java_demo_test.time20230323V04Service.CourseService;
import com.example.java_demo_test.vo.CourseRequest;
import com.example.java_demo_test.vo.CourseResponse;

@RestController
public class CourseController {

	@Autowired
	private CourseService courseService;
	
	@PostMapping("/CourseController_addCourse")
	public CourseResponse addCourse(@RequestBody CourseRequest courseRequest) {
		return courseService.addCourse(courseRequest);
	}
	
	@PostMapping("/CourseController_reviseCourse")
	public CourseResponse reviseCourse(@RequestBody CourseRequest courseRequest) {
		return courseService.reviseCourse(courseRequest);
	}
	
	@PostMapping("/CourseController_findCourse_num")/*取得資料庫全部資料*/
	public CourseResponse findCourse_num(@RequestBody CourseRequest courseRequest) {
		return courseService.findCourse_num(courseRequest);
	}
	
	@PostMapping("/CourseController_findCourseName")/*取得資料庫全部資料*/
	public CourseResponse findCourseName(@RequestBody CourseRequest courseRequest) {
		return courseService.findCourseName(courseRequest);
	}
	
	@PostMapping("/CourseController_courseSelection")/*選課操作*/
	public CourseResponse courseSelection(@RequestBody CourseRequest courseRequest) {
		return courseService.courseSelection(courseRequest);
	}
}
