package com.microservices.faculty.dto;

public record TeacherRecord(Integer id, String firstName, String lastName, String title, byte[] picture) {
}