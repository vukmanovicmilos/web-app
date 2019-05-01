package com.microservices.report.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.report.repositories.FeignRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = { "Report operations" })
@RestController
public class ReportRestController {
	
	@Autowired
   	private FeignRepository feignRepository;
	
	@ApiOperation("Returns students enrolled to a course with a id that is passed as a path variable (from ms faculty)")
	@GetMapping("/studentsForCourse/{courseId}")
	public ArrayList<Object> getstudentsForCourse(@PathVariable("courseId") Integer id) {
		return feignRepository.findByCourses(id);
	}
}
