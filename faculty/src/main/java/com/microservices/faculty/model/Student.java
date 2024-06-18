package com.microservices.faculty.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class Student extends Person {
    @Id
    @SequenceGenerator(name = "STUDENT_ID_GENERATOR", sequenceName = "STUDENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_ID_GENERATOR")
    private Integer id;

    @Column(name = "index_number")
    private String indexNumber;

    @Column(name = "grade")
    private BigDecimal grade;

    @Column(name = "description")
    private String description;

    //bi-directional many-to-many association to Course
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "enrollment",
            joinColumns = {
                    @JoinColumn(name = "student_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id")
            }
    )
    @JsonIgnore
    private List<Course> courses;
}
