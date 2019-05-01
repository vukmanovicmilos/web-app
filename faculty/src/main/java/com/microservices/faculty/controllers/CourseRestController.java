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

import com.microservices.faculty.jpa.Course;
import com.microservices.faculty.repositories.CourseRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Course CRUD operations" })
@RestController
@RequestMapping("course")
public class CourseRestController {

	@Autowired
	private CourseRepository courseRepository;

	@ApiOperation("Returns all courses from database")
	@GetMapping
	public Collection<Course> getCourses() {
		return courseRepository.findAll();
	}
	
	@ApiOperation("Returns course with an id that is passed as a path variable")
	@GetMapping("{id}")
	public Course getOneCourse(@RequestParam("id") int id) {
		return courseRepository.getOne(id);
	}

	// insert
	@ApiOperation("Insert course in database")
	@PostMapping
	public ResponseEntity<Course> insertCourse(@RequestBody Course course) {
		courseRepository.save(course);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// update
	@ApiOperation("Update course in database")
	@PutMapping
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		if (!courseRepository.existsById(course.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		courseRepository.save(course);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@ApiOperation("Delete course from database")
	@DeleteMapping("{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable("id") Integer id) {
		if (!courseRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		courseRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
