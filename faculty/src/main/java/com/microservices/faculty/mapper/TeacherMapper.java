package com.microservices.faculty.mapper;

import com.microservices.faculty.dto.TeacherRecord;
import com.microservices.faculty.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class TeacherMapper {
    public TeacherRecord entityToDTO(Teacher teacher) {
        return new TeacherRecord(teacher.getId(), teacher.getFirstName(), teacher.getLastName(), teacher.getTitle(), teacher.getPicture());
    }

    public List<TeacherRecord> entitiesToDTOs(Collection<Teacher> teachers) {
        return teachers.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public Teacher dtoToEntity(TeacherRecord teacherRecord) {
        Teacher teacher = Teacher.builder().build();
        teacher.setId(teacherRecord.id());
        teacher.setFirstName(teacherRecord.firstName());
        teacher.setLastName(teacherRecord.lastName());
        teacher.setPicture(teacherRecord.picture());
        return teacher;
    }

    public List<Teacher> dtosToEntities(Collection<TeacherRecord> teacherRecords) {
        return teacherRecords.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}