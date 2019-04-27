package com.microservices.faculty.controllers;

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

@RestController
@RequestMapping("student")
public class StudentRestController {

	@Autowired
	private StudentRepository studentRepository;

	@GetMapping
	public Collection<Student> getStudents() {
		return studentRepository.findAll();
	}

	@GetMapping("{id}")
	public Student getOneStudent(@RequestParam("id") int id) {
		return studentRepository.getOne(id);
	}

	// insert
	@PostMapping
	public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// update
	@PutMapping
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if (!studentRepository.existsById(student.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
		if (!studentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
