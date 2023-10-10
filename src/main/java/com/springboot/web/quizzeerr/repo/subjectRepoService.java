package com.springboot.web.quizzeerr.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.web.quizzeerr.entity.Subject;

public interface subjectRepoService extends JpaRepository<Subject, Integer> {
	public Subject findByName(String name);
}
