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

@RestController
@RequestMapping("course")
public class CourseRestController {

	@Autowired
	private CourseRepository courseRepository;

	@GetMapping
	public Collection<Course> getCourses() {
		return courseRepository.findAll();
	}

	@GetMapping("{id}")
	public Course getOneCourse(@RequestParam("id") int id) {
		return courseRepository.getOne(id);
	}

	// insert
	@PostMapping
	public ResponseEntity<Course> insertCourse(@RequestBody Course course) {
		courseRepository.save(course);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	// update
	@PutMapping
	public ResponseEntity<Course> updateCourse(@RequestBody Course course) {
		if (!courseRepository.existsById(course.getId()))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		courseRepository.save(course);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Course> deleteCourse(@PathVariable("id") Integer id) {
		if (!courseRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		courseRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
