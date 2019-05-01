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


/**
 * The persistent class for the teacher database table.
 * 
 */
@Entity
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

	public Teacher() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<Course> getCourses() {
		return this.courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public Course addCours(Course cours) {
		getCourses().add(cours);
		cours.setTeacher(this);

		return cours;
	}

	public Course removeCours(Course cours) {
		getCourses().remove(cours);
		cours.setTeacher(null);

		return cours;
	}

}