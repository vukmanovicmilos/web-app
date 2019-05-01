package com.microservices.faculty.controllers;

import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.faculty.jpa.Teacher;
import com.microservices.faculty.repositories.TeacherRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Teacher CRUD operations" })
@RestController
@RequestMapping("teacher")
public class TeacherRestController {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@ApiOperation("Returns all teachers from database")
	@GetMapping
	public Collection<Teacher> getTeachers() {
		return teacherRepository.findAll();
	}

	@ApiOperation("Returns teacher with an id that is passed as a path variable")
	@GetMapping("{id}")
	public Teacher getOneTeacher(@RequestParam("id") int id) {
		return teacherRepository.getOne(id);
	}

	// insert
	@ApiOperation("Insert teacher in database")
	@PostMapping
	public ResponseEntity<Teacher> insertTeacher(@RequestParam String teacherJson,
			@RequestParam(value = "picture", required = false) MultipartFile picture) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Teacher teacher = objectMapper.readValue(teacherJson, Teacher.class);
			if (picture != null)
				teacher.setPicture(picture.getBytes());
			teacherRepository.save(teacher);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	// update
	@ApiOperation("Update teacher in database")
	@PutMapping
	public ResponseEntity<Teacher> updateTeacher(@RequestParam String teacherJson, @RequestParam(value = "picture", required = false) MultipartFile picture) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			Teacher teacher = objectMapper.readValue(teacherJson, Teacher.class);
			if (!teacherRepository.existsById(teacher.getId()))
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			if (picture != null)
				teacher.setPicture(picture.getBytes());
			teacherRepository.save(teacher);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("{id}")
	@ApiOperation("Delete teacher from database")
	public ResponseEntity<Teacher> deleteTeacher(@PathVariable("id") Integer id) {
		if (!teacherRepository.existsById(id))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		teacherRepository.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
