package com.microservices.faculty.service;

import com.microservices.faculty.dto.TeacherRecord;
import com.microservices.faculty.mapper.TeacherMapper;
import com.microservices.faculty.model.Teacher;
import com.microservices.faculty.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherMapper teacherMapper;
    private final TeacherRepository teacherRepository;

    public PageImpl<TeacherRecord> getAll(String filter, Pageable page) {
        Page<Teacher> teachers = filter == null ?
                teacherRepository.findAll(page) :
                teacherRepository.findAllByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCaseOrTitleContainingIgnoreCase(filter, filter, filter, page);
        return new PageImpl<>(teacherMapper.entitiesToDTOs(teachers.getContent()), page, teachers.getTotalElements());
    }

    public TeacherRecord getOne(int id) {
        Optional<Teacher> teacher = teacherRepository.findById(id);
        return teacher.map(teacherMapper::entityToDTO).orElse(null);
    }

    public TeacherRecord save(TeacherRecord teacherRecord) {
        Teacher savedTeacher = teacherRepository.save(teacherMapper.dtoToEntity(teacherRecord));
        return teacherMapper.entityToDTO(savedTeacher);
    }

    public TeacherRecord update(TeacherRecord teacherRecord, int id) {
        Teacher teacher = teacherMapper.dtoToEntity(teacherRecord);
        teacher.setId(id);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.entityToDTO(savedTeacher);
    }

    public void deleteById(Integer id) {
        teacherRepository.deleteById(id);
    }
}
