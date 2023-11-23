package com.microservices.report.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CourseSummaryDto {
    private Integer id;
    private String summary;
}