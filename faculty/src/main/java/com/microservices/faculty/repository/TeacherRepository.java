package com.microservices.faculty.repository;

import com.microservices.faculty.model.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Page<Teacher> findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrTitleContainingIgnoreCase(
            String firstName, String lastName, String title, Pageable p);
}
