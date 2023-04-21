package com.example.java_demo_test.reposopory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.java_demo_test.time20230323V04Entity.CourseEntity;

public interface CourseReposopory extends JpaRepository<
CourseEntity, Integer/*<<<這裡我有做了變更，需要注意*/>{

}
