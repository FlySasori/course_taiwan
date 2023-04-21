package TestT2023;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;

import java.sql.*;

import com.example.java_demo_test.controller.FlyKiteTL;
import com.example.java_demo_test.time20230323V04Entity.CourseEntity;
import com.example.java_demo_test.time20230323V04Entity.Student_and_courseEntity;
import com.example.java_demo_test.time20230323V04Service.FlyKiteMySQL;
import com.example.java_demo_test.vo.CourseResponse;



public class MySQLTestT20230418A {
	
	private static String url = "jdbc:mysql://localhost:3301/java_demo_test";

	private static String username = "root";
	
    private static String password = "root";

	
	public static void main(String[] args) {
				
		int newStart_time = 1255;
		int newOver_time = 1355;
		int nowStart_time = 1433;
		int nowOver_time = 1333;

		
		if(newStart_time >= nowStart_time) {
			
			if(isStudentCourseTimeCount(
					newStart_time,
					newOver_time,
					nowStart_time,
					nowOver_time) 
					== true) {
				
				FlyKiteTL.Log("重複時間");
				return;
			}
		}else {
			if(isStudentCourseTimeCount(
					nowStart_time,
					nowOver_time,
					newStart_time,
					newOver_time) 
					== true) {
				FlyKiteTL.Log("重複時間");
				return;
			}
		}
		
		FlyKiteTL.Log("不重複時間");

	}
	
	public static boolean isStudentCourseTimeCount(
			int newCourseStartTime, int newCourseOverTime,
			int hasCourseStart_time, int hasCourseOver_time){
		
		/*
		 * 判斷原始時間startTime
			是否大於原始時間overTime
		 * */
		if(hasCourseStart_time > hasCourseOver_time) {
			return true;
		}
		
		/*
		 * 這樣代表新增時間start在
			原始時間start和over之間，
			所以必定重疊。
		 * */
		if(hasCourseOver_time >= newCourseStartTime) {
			return true;
		}

		/*
		 * 這樣代表新增時間start在
			原始時間start和over之外，
			除非隔天也一起做計算。不然這樣就是最終判斷結果。

			return 不重疊
		 * */
		return false;
	}

}


//String testStr = 
//"0044"
////				"2822"//前面2位數大於23
////				"aa5d"
////				"1866"//後面2位數大於59
////				"%%#5"
////				""//因為StringUtils.hasText()過了，所以不需要判斷是否為空。
//;
//
//if(testStr.matches(".*[^\\d].*")) {
//	FlyKiteTL.Log("true");
//}else{
//	FlyKiteTL.Log("false");
//}
//
//int number = Integer.parseInt("0545".substring(0, 2));
//
//FlyKiteTL.Log(number);
//
////		if(StringUtils.hasText(testStr)) {
////			FlyKiteTL.Log("true");
////		}else{
////			FlyKiteTL.Log("false");
////		}