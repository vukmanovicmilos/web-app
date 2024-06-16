package com.microservices.report.service;

import com.microservices.report.dto.CourseDto;
import com.microservices.report.dto.CourseSummaryDto;
import com.microservices.report.dto.CourseSummaryRequestDto;
import com.microservices.report.dto.StudentDto;
import com.microservices.report.feign.FacultyFeignProxy;
import com.microservices.report.feign.OpenAIFeignProxy;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ReportService {
    private final FacultyFeignProxy facultyFeignProxy;
    private final ApplicationContext appContext;
    private final OpenAIFeignProxy openAIFeignProxy;
    private final KafkaProducerService kafkaProducerService;

    public void getStudentsForCourseReport(@PathVariable("courseId") Integer courseId) {
        facultyFeignProxy.getStudentsForCourseReport(courseId);
    }

    public void generateReport(@PathVariable("courseId") Integer courseId, HttpServletResponse httpServletResponse) throws Exception {
        List<StudentDto> students = facultyFeignProxy.getStudentsForCourseReport(courseId);
        CourseDto course = facultyFeignProxy.getOneCourse(courseId);

        Resource resource = appContext.getResource("classpath:/reports/studentsForCourse.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(resource.getInputStream());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(students);
        Map<String, Object> params = getParams(course);

        generateReportPDF(httpServletResponse, jasperReport, dataSource, params);


        CourseSummaryDto courseSummary = CourseSummaryDto.builder()
                .id(courseId)
                .summary(String.valueOf(params.get("summary")))
                .build();
        kafkaProducerService.sendMessage(courseSummary);
    }

    private Map<String, Object> getParams(CourseDto course) throws IOException {
        Map<String, Object> params = new HashMap<>();
        params.put("courseLabel", course.getLabel());
        params.put("courseName", course.getName());
        params.put("courseDate", course.getStartDate());
        params.put("teacherTitle", course.getTeacher().getTitle());
        params.put("teacherFirstName", course.getTeacher().getFirstName());
        params.put("teacherLastName", course.getTeacher().getLastName());
        BufferedImage img = null;
        if (course.getTeacher().getPicture() != null) {
            img = ImageIO.read(new ByteArrayInputStream(course.getTeacher().getPicture()));
        }
        params.put("summary", openAIFeignProxy.getSummary(populateCourseRequestDto(course)));
        params.put("teacherPicture", img);
        return params;
    }

    private CourseSummaryRequestDto populateCourseRequestDto(CourseDto course) {
        return CourseSummaryRequestDto.builder()
                .courseName(course.getName())
                .courseDate(course.getStartDate())
                .teacherTitle(course.getTeacher().getTitle())
                .teacherLastName(course.getTeacher().getLastName())
                .build();
    }

    private void generateReportPDF(HttpServletResponse resp, JasperReport jasperReport, JRDataSource dataSource,
                                   Map<String, Object> params) throws Exception {
        byte[] bytes = JasperRunManager.runReportToPdf(jasperReport, params, dataSource);
        resp.reset();
        resp.resetBuffer();
        resp.setContentType("application/pdf");
        resp.setContentLength(bytes.length);
        ServletOutputStream ouputStream = resp.getOutputStream();
        ouputStream.write(bytes, 0, bytes.length);
        ouputStream.flush();
        ouputStream.close();
    }

}
