package com.example.java_demo_test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.java_demo_test.time20230323V04Service.BankServiceImpl;
import com.example.java_demo_test.time20230323V005MenuService.Time20230323V005OrderServiceImpl;

import java.util.Scanner;

@SpringBootTest
class JavaDemoTestApplicationTests {
	
	@Autowired
	private BankServiceImpl bankServiceImpl;
	
//	@Autowired
//	private Time20230323V005OrderServiceImpl time20230323V005OrderServiceImpl;
	
	
//	@Test
//	void contextLoads() {
//		Bird bird = new Bird("blue", 15);
//		active.fly(bird.getName(), bird.getAge());
//	}
	
	@Test
	void contextLoad0s01() {
		bankServiceImpl.UnitTest();;
		
		System.out.println("結束系統運行...");
	}

	
//	@Test
//	void contextLoads02() {
//		time20230323V005OrderServiceImpl.UnitTest();
//		
//		System.out.println("結束系統運行...");
//	}
}


