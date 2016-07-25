package com.example.core;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Lesson {
private long id;
private String title;
private int number;
private Course course;

@Id
@GeneratedValue
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public int getNumber() {
	return number;
}
public void setNumber(int number) {
	this.number = number;
}

@ManyToOne
public Course getCourse() {
	return course;
}
public void setCourse(Course course) {
	this.course = course;
}
	

}
