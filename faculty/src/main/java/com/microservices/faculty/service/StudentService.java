package com.microservices.faculty.service;

import com.microservices.faculty.dto.StudentRecord;
import com.microservices.faculty.mapper.StudentMapper;
import com.microservices.faculty.model.Student;
import com.microservices.faculty.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentMapper studentMapper;
    private final StudentRepository studentRepository;

    public PageImpl<StudentRecord> getAll(String filter, Pageable page) {
        Page<Student> students = filter == null ?
                studentRepository.findAllByOrderByLastNameAsc(page) :
                studentRepository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrIndexNumberContainingIgnoreCaseOrderByLastNameAsc(filter, filter, filter, page);
        return new PageImpl<>(studentMapper.entitiesToDTOs(students.getContent()), page, students.getTotalElements());
    }

    public List<Student> findAll() {
       return studentRepository.findAll();
    }

    public PageImpl<StudentRecord> findByCourse(Integer id, Pageable page) {
        Page<Student> students = studentRepository.findByCourses_IdOrderByLastName(id, page);
        return new PageImpl<>(studentMapper.entitiesToDTOs(students.getContent()), page, students.getTotalElements());
    }

    public List<StudentRecord> findByCourse(Integer id) {
        List<Student> students = studentRepository.findByCourses_IdOrderByLastName(id);
        return studentMapper.entitiesToDTOs(students);
    }

    public StudentRecord save(StudentRecord studentRecord) {
        Student savedStudent = studentRepository.save(studentMapper.dtoToEntity(studentRecord));
        return studentMapper.entityToDTO(savedStudent);
    }

    public StudentRecord update(StudentRecord studentRecord, int id) {
        Student Student = studentMapper.dtoToEntity(studentRecord);
        Student.setId(id);
        Student savedStudent = studentRepository.save(Student);
        return studentMapper.entityToDTO(savedStudent);
    }

    public void deleteById(Integer id) {
        studentRepository.deleteById(id);
    }
}
