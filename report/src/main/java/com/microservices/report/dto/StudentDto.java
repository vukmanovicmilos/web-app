package com.microservices.report.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class StudentDto extends PersonDto{
	private Integer id;
	private String indexNumber;
	private List<CourseDto> courses;
}