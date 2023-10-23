package com.microservices.faculty.mapper;

import com.microservices.faculty.dto.CourseRecord;
import com.microservices.faculty.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class CourseMapper {
    @Autowired
    private TeacherMapper teacherMapper;

    public CourseRecord entityToDTO(Course course) {
        return new CourseRecord(course.getId(), course.getLabel(), course.getName(), course.getStartDate(), teacherMapper.entityToDTO(course.getTeacher()));
    }

    public List<CourseRecord> entitiesToDTOs(Collection<Course> courses) {
        return courses.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public Course dtoToEntity(CourseRecord courseRecord) {
        Course course = Course.builder().build();
        course.setId(courseRecord.id());
        course.setLabel(courseRecord.label());
        course.setName(courseRecord.name());
        course.setStartDate(courseRecord.startDate());
        course.setName(courseRecord.name());
        course.setTeacher(teacherMapper.dtoToEntity(courseRecord.teacher()));
        return course;
    }

    public List<Course> dtosToEntities(Collection<CourseRecord> courseRecords) {
        return courseRecords.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}