package com.group4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DangNhapController {

	@GetMapping("/login")
	public String showLoginForm() {
		return "/account/login";
	}
	
}
