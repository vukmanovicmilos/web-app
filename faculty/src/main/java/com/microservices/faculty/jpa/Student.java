package com.microservices.faculty.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the student database table.
 * 
 */
@Entity
@Getter 
@Setter 
@NoArgsConstructor
@ToString(includeFieldNames=true)
@NamedQuery(name="Student.findAll", query="SELECT s FROM Student s")
public class Student implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="STUDENT_ID_GENERATOR", sequenceName="STUDENT_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="STUDENT_ID_GENERATOR")
	private Integer id;
	
	@Column(name="index_number")
	private String indexNumber;
	
	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;

	//bi-directional many-to-many association to Course
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
		name="enrollment"
		, joinColumns={
			@JoinColumn(name="student_id")
			}
		, inverseJoinColumns={
			@JoinColumn(name="course_id")
			}
		)
	private List<Course> courses;

}