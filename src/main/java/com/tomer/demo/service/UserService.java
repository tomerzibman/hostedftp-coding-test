package com.tomer.demo.service;

import com.tomer.demo.entity.User;

public interface UserService {
	public boolean authenticate(String username, String password);
	public User findByUsername(String username);
}
