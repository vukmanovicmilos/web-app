package com.microservices.faculty.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(name = "COURSE_ID_GENERATOR", sequenceName = "COURSE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_ID_GENERATOR")
    private Integer id;

    private String label;

    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    //bi-directional many-to-one association to Teacher
    @ManyToOne
    private Teacher teacher;

    //bi-directional many-to-many association to Student
    @JsonIgnore
    @ManyToMany(mappedBy = "courses")
    private List<Student> students;

}