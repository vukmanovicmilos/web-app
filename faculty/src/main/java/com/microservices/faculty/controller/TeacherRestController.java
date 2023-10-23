package com.microservices.faculty.controller;

import com.microservices.faculty.dto.TeacherRecord;
import com.microservices.faculty.service.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Teacher CRUD operations")
@RequiredArgsConstructor
@RestController
@RequestMapping("teachers")
public class TeacherRestController {

    private final TeacherService teacherService;

    @Operation(summary = "Returns all teachers from database")
    @GetMapping(value = { "", "{filter}" })
    public PageImpl<TeacherRecord> getStudents(@PathVariable(required = false) String filter,
                                            @PageableDefault(sort = {"id"}) Pageable page) {
        return teacherService.getAll(filter, page);
    }

    @Operation(summary = "Returns teacher with an id that is passed as a path variable")
    @GetMapping("{id}")
    public TeacherRecord getOne(@PathVariable int id) {
        return teacherService.getOne(id);
    }

    @Operation(summary = "Insert teacher in database")
    @PostMapping
    public TeacherRecord save(@RequestBody TeacherRecord teacher) {
        return teacherService.save(teacher);
    }

    @Operation(summary = "Update teacher in database")
    @PutMapping("{id}")
    public TeacherRecord update(@RequestBody TeacherRecord teacher,
                             @PathVariable int id) {
        return teacherService.update(teacher, id);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Delete teacher from database")
    public ResponseEntity<TeacherRecord> deleteTeacher(@PathVariable("id") Integer id) {
        teacherService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
