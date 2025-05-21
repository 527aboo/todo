package com.example.demo.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserSerive {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserSerive(UserRepository userRepository, PasswordEncoder passwordEncoder ) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User register(String username, String email, String rawPassword) {
		String encodedPassword = passwordEncoder.encode(rawPassword);
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(encodedPassword);
		return userRepository.save(user);
	}
}
