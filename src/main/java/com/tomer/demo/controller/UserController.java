package com.tomer.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.tomer.demo.entity.User;
import com.tomer.demo.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}

    @GetMapping("/user")
    public String user(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        if (username == null) {
        	System.out.println("username null - redirecting to login");
            return "redirect:/login";
        }
        
        User user = userService.findByUsername(username);
        if (user == null) {
        	System.out.println("user null - redirecting to login");
            return "redirect:/error";
        }
        
        model.addAttribute("user", user);
        return "user";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
