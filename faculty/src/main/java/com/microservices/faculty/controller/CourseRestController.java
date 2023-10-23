package com.microservices.faculty.controller;

import com.microservices.faculty.dto.CourseRecord;
import com.microservices.faculty.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Course CRUD operations")
@RestController
@RequiredArgsConstructor
@RequestMapping("courses")
public class CourseRestController {

    private final CourseService courseService;

    @Operation(summary = "Returns all courses from database")
    @GetMapping(value = {"", "{filter}"})
    public PageImpl<CourseRecord> getCourses(@PathVariable(required = false) String filter,
                                             @PageableDefault(sort = {"id"}) Pageable page) {
        return courseService.getAll(filter, page);
    }

    @Operation(summary = "Returns course with an id that is passed as a path variable")
    @GetMapping("one/{id}")
    public CourseRecord getOneCourse(@PathVariable("id") int id) {
        return courseService.getOne(id);
    }

    @Operation(summary = "Insert course in database")
    @PostMapping
    public CourseRecord insertCourse(@RequestBody CourseRecord course) {
        return courseService.save(course);
    }

    @Operation(summary = "Update course in database")
    @PutMapping("{id}")
    public CourseRecord updateStudent(@RequestBody CourseRecord course,
                                      @PathVariable int id) {
        return courseService.update(course, id);
    }

    @Operation(summary = "Delete course from database")
    @DeleteMapping("{id}")
    public ResponseEntity<CourseRecord> deleteCourse(@PathVariable("id") Integer id) {
        courseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
