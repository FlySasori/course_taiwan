package com.example.java_demo_test.time20230323V04Interface;

import com.example.java_demo_test.vo.CourseRequest;
import com.example.java_demo_test.vo.CourseResponse;

public interface CourseInterface {
	public CourseResponse addCourse(CourseRequest courseRequest);
	public CourseResponse reviseCourse(CourseRequest courseRequest);
	public CourseResponse findCourse_num(CourseRequest courseRequest);
	public CourseResponse findCourseName(CourseRequest courseRequest);
	public CourseResponse courseSelection(CourseRequest courseRequest);
}
