package com.example.java_demo_test.vo;

import java.util.List;

public class Time20230329DataTest {

	private List<Time20230329DataTestA> time20230329DataTestAList;
	
	public List<Time20230329DataTestA> getTime20230329DataTestAList() {
		return time20230329DataTestAList;
	}
	
	public void setTime20230329DataTestAList(List<Time20230329DataTestA> time20230329DataTestAList) {
		this.time20230329DataTestAList = time20230329DataTestAList;
	}
	
	public class Time20230329DataTestA{
		public String dataC;
		
		public String getDataC() {
			return dataC;
		}
		
		public void setDataC(String dataC) {
			this.dataC = dataC;
		}

		public Time20230329DataTestA() {
		}
		
		public Time20230329DataTestA(String dataC) {
			this.dataC = dataC;
		}
	}
}
