package com.springboot.web.quizzeerr.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Answer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String correctAnswer;
	@OneToOne
	private Question ques;
	public Answer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Answer(int id, String correctAnswer, Question ques) {
		super();
		Id = id;
		this.correctAnswer = correctAnswer;
		this.ques = ques;
	}
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getCorrectAnswer() {
		return correctAnswer;
	}
	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}
	public Question getQues() {
		return ques;
	}
	public void setQues(Question ques) {
		this.ques = ques;
	}
}
