package com.springboot.web.quizzeerr.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.web.quizzeerr.entity.User;


public interface UserRepository extends JpaRepository<User, String> {
	public Optional<User> findByUsername(String email);
}
