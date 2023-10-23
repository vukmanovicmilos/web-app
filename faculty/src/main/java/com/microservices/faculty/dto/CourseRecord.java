package com.microservices.faculty.dto;

import java.util.Date;

public record CourseRecord(Integer id, String label, String name, Date startDate, TeacherRecord teacher) {
}
