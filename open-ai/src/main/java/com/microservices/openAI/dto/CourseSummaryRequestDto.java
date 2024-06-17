package com.microservices.openAI.dto;

import lombok.Data;

@Data
public class CourseSummaryRequestDto {
    private String courseName;
    private String courseDate;
    private String teacherTitle;
    private String teacherLastName;
}
