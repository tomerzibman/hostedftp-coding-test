package com.tomer.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tomer.demo.repository.UserRepository;
import com.tomer.demo.entity.User;

@Service
public class UserServiceImpl implements UserService {
	private UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean authenticate(String username, String password) {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return false;
		}

		String storedPasswordHash = user.getPasswordHash();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, storedPasswordHash);
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

}
