package com.microservices.faculty.repository;

import com.microservices.faculty.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Page<Student> findByCourses_IdOrderByLastName(@Param("id") Integer id, Pageable p);

    List<Student> findByCourses_IdOrderByLastName(@Param("id") Integer id);

    Page<Student> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrIndexNumberContainingIgnoreCaseOrderByLastNameAsc(
            String firstName, String lastName, String indexNumber, Pageable p);

    Page<Student> findAllByOrderByLastNameAsc(Pageable page);
}
