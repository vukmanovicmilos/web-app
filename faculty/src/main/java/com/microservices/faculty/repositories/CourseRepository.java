package com.microservices.faculty.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.faculty.jpa.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{
	Page<Course> findByNameContainingIgnoreCaseOrLabelContainingIgnoreCaseOrStartDateContainingIgnoreCase(String name, String label, String date, Pageable p);

}
