package com.example.studentcourseregistration.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.studentcourseregistration.model.Student;

@Service
public class StudentService {
	private Map<Long, Student> students = new ConcurrentHashMap<>();
	private AtomicLong idgenertor = new AtomicLong(100);

	public Student registerStudent(Student student) {
		Long id = idgenertor.incrementAndGet();
		student.setStudentId(id);
		students.put(id, student);
		return student;
	}

	public List<Student> getStudents() {
		return new ArrayList<>(students.values());

	}

	public Student getstudentById(Long id) {
		Student student = students.get(id);
		if (student == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not Found");
		}
		return student;

	}
	public Student updateStudent(Long id, Student updatedStudent) {

	    Student existingStudent = students.get(id);

	    if (existingStudent == null) {
	        return null;
	    }

	    updatedStudent.setStudentId(id);
	    students.put(id, updatedStudent);

	    return updatedStudent;
	}
	public String deleteStudent(Long id) {
		Student student = students.remove(id);
		if (student == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student not Found");
		}
		return "Student registration deleted successfully";
	}
}
