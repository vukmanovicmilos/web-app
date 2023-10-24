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
public abstract class PersonDto {
    private String firstName;
    private String lastName;
    private Date birthday;
}