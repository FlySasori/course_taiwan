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



public class MySQLTestT20230418 {
	
	private static String url = "jdbc:mysql://localhost:3301/java_demo_test";

	private static String username = "root";
	
    private static String password = "root";

	
	public static void main(String[] args) {
				
		List<CourseEntity> courseEntitys = new ArrayList();
		try {
			courseEntitys = MySQLTestT202304.findAll(CourseEntity.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//匯入資料庫。
		Student_and_courseEntity student_and_courseEntity = 
				new Student_and_courseEntity(1, 
						2);
				
		FlyKiteMySQL.AddSave(student_and_courseEntity);

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