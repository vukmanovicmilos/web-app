package com.microservices.faculty.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservices.faculty.jpa.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
