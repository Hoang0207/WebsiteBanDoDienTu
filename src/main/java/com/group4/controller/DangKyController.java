package com.group4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DangKyController {

	@GetMapping("/register")
	public String showRegistrationForm() {
		return "/account/registration";
	}
	
}
