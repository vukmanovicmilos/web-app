package com.microservices.faculty.controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.faculty.jpa.Student;
import com.microservices.faculty.repositories.StudentRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Student CRUD operations" })
@RestController
@RequestMapping("student")
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;
	
	@ApiOperation("Returns all students from database")
	@GetMapping
	public Collection<Student> getStudents() {
		return studentRepository.findAll();
	}

	@ApiOperation("Returns student with an id that is passed as a path variable")
	@GetMapping("{id}")
	public Student getOneStudent(@RequestParam("id") int id) {
		return studentRepository.getOne(id);
	}
	
	@ApiOperation("Returns students enrolled to a course with an course id that is passed as a path variable (from ms faculty)")
	@GetMapping("studentsForCourse/{courseId}")
	public ArrayList<Object> getStudentsForCourse(@PathVariable("courseId") Integer courseId) {
		return studentRepository.findByCourses_IdOrderByLastName(courseId);
	}

	// insert
	@ApiOperation("Insert student in database")
	@PostMapping
	public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// update
	@ApiOperation("Update student in database")
	@PutMapping
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if (!studentRepository.existsById(student.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@ApiOperation("Delete student from database")
	@DeleteMapping("{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
		if (!studentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
