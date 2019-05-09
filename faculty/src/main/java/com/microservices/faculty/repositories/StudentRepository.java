package com.microservices.faculty.repositories;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.microservices.faculty.jpa.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

	public Page<Student> findByCourses_IdOrderByLastName(@Param("id") Integer id, Pageable p);
	public ArrayList<Object> findByCourses_IdOrderByLastName(@Param("id") Integer id);
	public Page<Student> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrIndexNumberContainingIgnoreCase(String firstName, String lastName, String indexNumber, Pageable p);

}
