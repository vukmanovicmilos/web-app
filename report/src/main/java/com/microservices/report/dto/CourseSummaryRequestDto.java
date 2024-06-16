package com.microservices.report.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CourseSummaryRequestDto {
    private String courseName;
    private Date courseDate;
    private String teacherTitle;
    private String teacherLastName;
}
