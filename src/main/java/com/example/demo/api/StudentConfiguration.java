package com.example.demo.api;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;

@Configuration
public class StudentConfiguration {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		
		return args ->{
			
			Student barry= new Student ("Barry","barry333@gmail.com", LocalDate.of(1999, Month.DECEMBER, 5));
			
			Student alex = new Student ("Alex","alex123@gmail.com", LocalDate.of(2004, Month.JANUARY, 9));
			
			repository.saveAll(List.of(barry, alex));
		};
		
		
	}
	
	
}
