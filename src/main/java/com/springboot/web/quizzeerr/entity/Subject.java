package com.springboot.web.quizzeerr.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sub")
	private List<Question> question;
	@Override
	public String toString() {
		return "Subject [id=" + id + ", name=" + name + ", question=" + question + "]";
	}
	public Subject(int id, String name, List<Question> question) {
		super();
		this.id = id;
		this.name = name;
		this.question = question;
	}
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Question> getQuestion() {
		return question;
	}
	public void setQuestion(List<Question> question) {
		this.question = question;
	}
	
}
