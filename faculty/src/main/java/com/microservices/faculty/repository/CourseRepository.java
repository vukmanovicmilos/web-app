package com.microservices.faculty.repository;

import com.microservices.faculty.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
    Page<Course> findByNameContainingIgnoreCaseOrLabelContainingIgnoreCaseOrTeacher_FirstNameContainingIgnoreCaseOrTeacher_LastNameContainingIgnoreCase(
            String name, String label, String firstName, String lastName, Pageable page);
}
