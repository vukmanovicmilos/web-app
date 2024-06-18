package com.microservices.faculty.dto;

import java.math.BigDecimal;

public record StudentRecord(Integer id, String indexNumber, String firstName, String lastName, BigDecimal grade, String description) {
}
