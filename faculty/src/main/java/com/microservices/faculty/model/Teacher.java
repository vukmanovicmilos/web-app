package com.microservices.faculty.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Teacher extends Person {
    @Id
    @SequenceGenerator(name = "TEACHER_ID_GENERATOR", sequenceName = "TEACHER_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TEACHER_ID_GENERATOR")
    private Integer id;

    @Column(name = "picture", columnDefinition = "varbinary")
    private byte[] picture;

    private String title;

    //bi-directional many-to-one association to Course
    @JsonIgnore
    @OneToMany(mappedBy = "teacher")
    private List<Course> courses;

}