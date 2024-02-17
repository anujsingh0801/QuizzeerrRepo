package com.springboot.web.quizzeerr.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springboot.web.quizzeerr.entity.User;
import com.springboot.web.quizzeerr.repo.UserRepository;


@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public List<User> getUser() {
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		// Updated code to store encrpyted password in db. 
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
}
