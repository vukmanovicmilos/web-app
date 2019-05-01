package com.microservices.report.repositories;

import java.util.ArrayList;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="faculty")
@RibbonClient(name="faculty")
public interface FeignRepository {
	
	@GetMapping(value = "student/studentsForCourse/{courseId}")
	public ArrayList<Object> findByCourses(@PathVariable("courseId") Integer courseId);

}
