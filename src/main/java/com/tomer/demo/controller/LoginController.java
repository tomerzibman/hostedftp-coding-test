package com.tomer.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
//import org.springframework.http.ResponseEntity;

import com.tomer.demo.dto.LoginRequest;
import com.tomer.demo.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	private UserService userService;

	@Autowired
	public LoginController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/login")
	public String login(LoginRequest body, HttpServletRequest request) {
		String username = body.getUsername();
		String password = body.getPassword();

		if (userService.authenticate(username, password)) {
			HttpSession session = request.getSession();
			session.setAttribute("authenticated", true);
			session.setAttribute("username", username);
			return "redirect:/user";
		}
		return "redirect:/fail";

	}
}
