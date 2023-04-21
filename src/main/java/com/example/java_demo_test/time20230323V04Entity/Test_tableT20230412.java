package com.example.java_demo_test.time20230323V04Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "test_table"/*<<<*/)
public class Test_tableT20230412 {
	
	@Column(name = "testName")
	private String testName;
	
	@Id
	@Column(name = "testID")
	private int id;
	
	public String getTestName() {
		return testName;
	}

	public void setTestName(String testName) {
		this.testName = testName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Test_tableT20230412() {
	}
	
	public Test_tableT20230412(String testName, int id) {
		this.testName = testName;
		this.id = id;
	}

}
