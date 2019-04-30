package com.microservices.report.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.report.repositories.FeignRepository;

@RequestMapping("report")
@RestController
public class ReportRestController {
	
	@Autowired
   	private FeignRepository feignRepository;
	
	@GetMapping("/studentsForCourse/{courseId}")
	public ArrayList<Object> getstudentsForCourse(@PathVariable("courseId") Integer id) {
		return feignRepository.findByCourses(id);
	}
}
