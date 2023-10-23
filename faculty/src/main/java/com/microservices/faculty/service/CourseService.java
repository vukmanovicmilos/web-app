package com.microservices.faculty.service;

import com.microservices.faculty.dto.CourseRecord;
import com.microservices.faculty.mapper.CourseMapper;
import com.microservices.faculty.model.Course;
import com.microservices.faculty.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseMapper courseMapper;
    private final CourseRepository courseRepository;

    public PageImpl<CourseRecord> getAll(String filter, Pageable page) {
        Page<Course> courses = filter == null ?
                courseRepository.findAll(page) :
                courseRepository.findByNameContainingIgnoreCaseOrLabelContainingIgnoreCaseOrTeacher_FirstNameContainingIgnoreCaseOrTeacher_LastNameContainingIgnoreCase(
                        filter, filter, filter, filter, page);
        return new PageImpl<>(courseMapper.entitiesToDTOs(courses.getContent()), page, courses.getTotalElements());
    }

    public CourseRecord getOne(int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.map(courseMapper::entityToDTO).orElse(null);
    }

    public CourseRecord save(CourseRecord courseRecord) {
        Course savedCourse = courseRepository.save(courseMapper.dtoToEntity(courseRecord));
        return courseMapper.entityToDTO(savedCourse);
    }

    public CourseRecord update(CourseRecord courseRecord, int id) {
        Course Course = courseMapper.dtoToEntity(courseRecord);
        Course.setId(id);
        Course savedCourse = courseRepository.save(Course);
        return courseMapper.entityToDTO(savedCourse);
    }

    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }
}
