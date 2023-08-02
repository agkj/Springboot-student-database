package com.example.demo.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long>{

	//this transforms to SQL : SELECT * FROM student WHERE EMAIL =
	
	Optional<Student> findStudentByEmail(String email);
	
}
