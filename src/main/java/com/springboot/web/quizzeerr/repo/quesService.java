package com.springboot.web.quizzeerr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.web.quizzeerr.entity.Question;

public interface quesService extends JpaRepository<Question, Integer> {

}
