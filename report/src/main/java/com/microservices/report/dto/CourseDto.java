package com.microservices.report.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode()
public class CourseDto {
    private Integer id;
    private String label;
    private String name;
    private Date startDate;
    private TeacherDto teacher;
    private String summary;
}