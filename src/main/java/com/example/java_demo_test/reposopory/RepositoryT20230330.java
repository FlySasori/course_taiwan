package com.example.java_demo_test.reposopory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_demo_test.time20230323V04Entity.PersonInfoT20230330;


public interface RepositoryT20230330 extends JpaRepository<PersonInfoT20230330,
String>{
	/*這個Age要對照找查變數的名稱*/
	List<PersonInfoT20230330> findByAge(int age);
 
 	List<PersonInfoT20230330> findByAgeGreaterThan(int age);
 	
 	List<PersonInfoT20230330> findByAgeLessThanEqual(int age);
 	
 	List<PersonInfoT20230330> findByCityAndAgeGreaterThan(String city,int age);
 	
 	List<PersonInfoT20230330> findByCityContaining(String city);
 	
// 	List<PersonInfoT20230330> findByAge(int age, int age02);

}
