package com.microservices.report.repositories;

import java.util.ArrayList;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.microservices.faculty.jpa.Course;


@FeignClient(name="faculty")
@RibbonClient(name="faculty")
public interface FeignRepository {
	
	@GetMapping(value = "student/studentsForCourse/{courseId}")
	public ArrayList<Object> findByCourse(@PathVariable("courseId") Integer courseId);
	
	@GetMapping(value = "course/{courseId}")
	public Course getOneCourse(@PathVariable("courseId") Integer courseId);

}
