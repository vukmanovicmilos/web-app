package com.microservices.faculty.jpa;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
@Getter 
@Setter 
@NoArgsConstructor
@ToString(includeFieldNames=true)
@NamedQuery(name="Teacher.findAll", query="SELECT t FROM Teacher t")
public class Teacher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="TEACHER_ID_GENERATOR", sequenceName="TEACHER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TEACHER_ID_GENERATOR")
	private Integer id;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	@Lob
    @Column(name="picture")
	//@JsonIgnore
	@Type(type="org.hibernate.type.BinaryType")
	private byte[] picture;

	private String title;

	//bi-directional many-to-one association to Course
	@JsonIgnore
	@OneToMany(mappedBy="teacher")
	private List<Course> courses;

}