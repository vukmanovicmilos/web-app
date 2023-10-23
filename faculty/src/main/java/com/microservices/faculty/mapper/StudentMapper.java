package com.microservices.faculty.mapper;

import com.microservices.faculty.dto.StudentRecord;
import com.microservices.faculty.model.Student;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class StudentMapper {
    public StudentRecord entityToDTO(Student student) {
        return new StudentRecord(student.getId(), student.getIndexNumber(), student.getFirstName(), student.getLastName());
    }

    public List<StudentRecord> entitiesToDTOs(Collection<Student> students) {
        return students.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    public Student dtoToEntity(StudentRecord studentRecord) {
        Student student = Student.builder().build();
        student.setId(studentRecord.id());
        student.setFirstName(studentRecord.firstName());
        student.setLastName(studentRecord.lastName());
        return student;
    }

    public List<Student> dtosToEntities(Collection<StudentRecord> studentRecords) {
        return studentRecords.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }
}