package com.microservices.faculty.jpa;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@Getter 
@Setter 
@NoArgsConstructor
@ToString(includeFieldNames=true)
@NamedQuery(name="Course.findAll", query="SELECT c FROM Course c")
public class Course implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="COURSE_ID_GENERATOR", sequenceName="COURSE_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="COURSE_ID_GENERATOR")
	private Integer id;

	private String label;

	private String name;

	@Temporal(TemporalType.DATE)
	@Column(name="start_date")
	private Date startDate;

	//bi-directional many-to-one association to Teacher
	@ManyToOne
	private Teacher teacher;

	//bi-directional many-to-many association to Student
	@JsonIgnore
	@ManyToMany(mappedBy="courses")
	private List<Student> students;

}