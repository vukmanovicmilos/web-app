package com.microservices.faculty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.faculty.jpa.Course;

public interface CourseRepository extends JpaRepository<Course, Integer>{

}
