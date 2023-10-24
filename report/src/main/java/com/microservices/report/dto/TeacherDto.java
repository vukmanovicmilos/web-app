package com.microservices.report.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class TeacherDto extends PersonDto {
    private Integer id;
    private byte[] picture;
    private String title;
}