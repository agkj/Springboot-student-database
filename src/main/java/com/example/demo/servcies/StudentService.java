package com.example.demo.servcies;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.dao.StudentRepository;
import com.example.demo.model.Student;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {

		this.studentRepository = studentRepository;
	}

	public List<Student> getStudents() {

		return studentRepository.findAll();

	}

	// check if email exist
	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

		if (studentByEmail.isPresent()) {
			throw new IllegalStateException("Email is taken");

		}

		else {
			studentRepository.save(student);
		}

		System.out.println(student);
	}

	public void deleteStudent(Long studentId) {

		boolean exist = studentRepository.existsById(studentId);

		if (!exist) {
			throw new IllegalStateException("Student with id " + studentId + " does not exist");
		}
		studentRepository.deleteById(studentId);

	}

	public void updateStudent(Long studentId, String name, String email) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist"));

		if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}

		if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {

			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

			if (studentOptional.isPresent()) {
				throw new IllegalStateException("Email is already taken");
			}

			student.setEmail(email);
		}

	}

}
