package com.microservices.faculty.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Person {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}