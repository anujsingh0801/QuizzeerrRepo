package com.springboot.web.quizzeerr.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Question {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String ques;
	@ManyToOne
	private Subject sub;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "ques")
	private Choice choice;
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "ques")
	private Answer answer;
	@Override
	public String toString() {
		return "Question [id=" + id + ", ques=" + ques + ", sub=" + sub + ", choice=" + choice + ", answer=" + answer
				+ "]";
	}
	public Question(int id, String ques, Subject sub, Choice choice, Answer answer) {
		super();
		this.id = id;
		this.ques = ques;
		this.sub = sub;
		this.choice = choice;
		this.answer = answer;
	}
	public Question() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQues() {
		return ques;
	}
	public void setQues(String ques) {
		this.ques = ques;
	}
	public Subject getSub() {
		return sub;
	}
	public void setSub(Subject sub) {
		this.sub = sub;
	}
	public Choice getChoice() {
		return choice;
	}
	public void setChoice(Choice choice) {
		this.choice = choice;
	}
	public Answer getAnswer() {
		return answer;
	}
	public void setAnswer(Answer answer) {
		this.answer = answer;
	}
}
