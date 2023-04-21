package com.example.java_demo_test.time20230323V04Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.java_demo_test.reposopory.RepositoryT20230330;
import com.example.java_demo_test.time20230323V04Entity.PersonInfoT20230330;
import com.example.java_demo_test.time20230323V04Interface.PersonInfoServiceT0330;
import com.example.java_demo_test.vo.PersoninfoRequest;
import com.example.java_demo_test.vo.PersoninfoRequestA;

@Service
public class PersonInfoServiceImplT0330 implements PersonInfoServiceT0330{

	@Autowired
	RepositoryT20230330 repositoryT20230330;
	
	
	@Override
	public List<PersonInfoT20230330> getPersonInfo() {
				
		List<PersonInfoT20230330> personInfoList = repositoryT20230330.findAll();
		
		if(personInfoList.size() == 0) {
			personInfoList.add(new PersonInfoT20230330("資料庫無資料"));/*<<<*/
			return personInfoList;
		}
		
		return personInfoList;
	}
	
}
