package com.microservices.report.feign;

import com.microservices.report.dto.CourseDto;
import com.microservices.report.dto.StudentDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@FeignClient(name = "faculty")
public interface FacultyFeignProxy {

    @GetMapping("students/{courseId}/course-students-report")
    List<StudentDto> getStudentsForCourseReport(@PathVariable("courseId") Integer courseId);

    @Operation(summary = "Returns course with an id that is passed as a path variable (from faculty ms)")
    @GetMapping("courses/one/{id}")
    CourseDto getOneCourse(@PathVariable("id") int id);

}
