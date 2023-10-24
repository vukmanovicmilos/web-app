package com.microservices.report.controller;

import com.microservices.report.service.ReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Course CRUD operations")
@RestController
@RequiredArgsConstructor
@RequestMapping("reports")
public class ReportFeignRestController {

    private final ReportService reportService;

    @Operation(summary = "Returns list of all students enrolled to a course with an course id that is passed as a path variable")
    @GetMapping("{courseId}")
    public void getStudentsForCourseReport(@PathVariable("courseId") Integer courseId, HttpServletResponse httpServletResponse) throws Exception {
        reportService.generateReport(courseId, httpServletResponse);
    }

    @Operation(summary = "Returns list of all students enrolaled to a course with an course id that is passed as a path variable")
    @GetMapping("all/{courseId}")
    public void getStudents(@PathVariable("courseId") Integer courseId) {
        reportService.getStudentsForCourseReport(courseId);
    }

}
