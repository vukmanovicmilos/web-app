package com.microservices.faculty.controller;

import com.microservices.faculty.dto.StudentRecord;
import com.microservices.faculty.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Student CRUD operations")
@RestController
@RequiredArgsConstructor
@RequestMapping("students")
public class StudentRestController {

    private final StudentService studentService;

    @Operation(summary = "Returns all students from database")
    @GetMapping(value = { "", "{filter}" })
    public PageImpl<StudentRecord> getStudents(@PathVariable(required = false) String filter,
                                            @PageableDefault(sort = {"id"}) Pageable page) {
            return studentService.getAll(filter, page);
    }

    @Operation(summary = "Returns students enrolled to a course with an course id that is passed as a path variable")
    @GetMapping("{courseId}/course-students")
    public PageImpl<StudentRecord> getStudentsForCourse(@PathVariable("courseId") Integer courseId,
                                                     @PageableDefault(sort = {"id"}) Pageable page) {
        return studentService.findByCourse(courseId, page);
    }

    @Operation(summary = "Returns list od all students enrolled to a course with an course id that is passed as a path variable")
    @GetMapping("{courseId}/course-students-report")
    public List<StudentRecord> getStudentsForCourseReport(@PathVariable("courseId") Integer courseId) {
        return studentService.findByCourse(courseId);
    }

    @Operation(summary = "Insert student in database")
    @PostMapping
    public StudentRecord save(@RequestBody StudentRecord student) {
        return studentService.save(student);
    }

    @Operation(summary = "Update student in database")
    @PutMapping("{id}")
    public StudentRecord updateStudent(@RequestBody StudentRecord student,
                                    @PathVariable int id) {
        return studentService.update(student, id);
    }

    @Operation(summary = "Delete student from database")
    @DeleteMapping("{id}")
    public ResponseEntity<StudentRecord> deleteStudent(@PathVariable("id") Integer id) {
        studentService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
