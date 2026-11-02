package com.example.studentcourseregistration.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Student {
	private long studentId;
	@NotBlank(message = "Student name cannot be empty")
	private String name;
	@NotBlank(message = "Course name cannot be empty")
	private String courseName;
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Enter valid email")
	private String email;
	@Min(value = 18, message = "Age Must be Above 18")
	private int age;
}
