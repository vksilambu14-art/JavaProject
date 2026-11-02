package com.example.studentcourseregistration.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.studentcourseregistration.model.Student;
import com.example.studentcourseregistration.service.StudentService;

import jakarta.validation.Valid;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/students")
public class StudentController {
	private final StudentService service;

	public StudentController(StudentService service) {
		this.service = service;
	}

	@PostMapping("/register")
	public Student registerStudent(@Valid @RequestBody Student student) {
		return service.registerStudent(student);
	}

	@GetMapping
	public List<Student> getAllStudents() {
		return service.getStudents();
	}

	@GetMapping("/{id}")
	public Student getStudentById(@PathVariable Long id) {
		return service.getstudentById(id);
	}
	@PutMapping("/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable Long id,@Valid @RequestBody Student student) {
	    Student updatedStudent = service.updateStudent(id, student);
	    if (updatedStudent == null) {
	        return ResponseEntity.notFound().build();
	    }

	    return ResponseEntity.ok(updatedStudent);
	}
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable Long id) {

		return service.deleteStudent(id);
	}

}
