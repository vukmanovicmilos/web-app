package com.microservices.faculty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.faculty.jpa.Student;

public interface StudentRepository extends JpaRepository<Student, Integer>{

}
