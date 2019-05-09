package com.microservices.faculty.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	@GetMapping("{filter}")
	public Page<Student> getStudents(@PathVariable ("filter") String filter, @PageableDefault(sort = {"id"}) Pageable p, HttpServletRequest request){
		System.out.println("GET Student "+
				" ip: "+request.getRemoteAddr()+
				" datum i vreme pristupa: "+new SimpleDateFormat("dd.MM.yyyy. HH:mm:ss").format(Calendar.getInstance().getTime()));
		if(filter.equals("*"))
			return studentRepository.findAll(p);
		return studentRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrIndexNumberContainingIgnoreCase(filter, filter, filter, p);
	}
	
	@ApiOperation("Returns students enrolled to a course with an course id that is passed as a path variable (from ms faculty)")
	@GetMapping("studentsForCourse/{courseId}")
	public Page<Student> getStudentsForCourse(@PathVariable("courseId") Integer courseId, @PageableDefault(sort = {"id"}) Pageable p) {
		return studentRepository.findByCourses_IdOrderByLastName(courseId, p);
	}
	
	@ApiOperation("Returns students enrolled to a course with an course id that is passed as a path variable (from ms faculty)")
	@GetMapping("studentsForCoursePdf/{courseId}")
	public ArrayList<Object> getStudentsForCoursePdf(@PathVariable("courseId") Integer courseId) {
		return studentRepository.findByCourses_IdOrderByLastName(courseId);
	}

	// insert
	@ApiOperation("Insert student in database")
	@PostMapping
	@CrossOrigin
	public ResponseEntity<Student> insertStudent(@RequestBody Student student) {
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// update
	@ApiOperation("Update student in database")
	@PutMapping
	@CrossOrigin
	public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
		if (!studentRepository.existsById(student.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@ApiOperation("Delete student from database")
	@DeleteMapping("{id}")
	@CrossOrigin
	public ResponseEntity<Student> deleteStudent(@PathVariable("id") Integer id) {
		if (!studentRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		studentRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
