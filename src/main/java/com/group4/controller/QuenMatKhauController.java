package com.group4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class QuenMatKhauController {

	@GetMapping("/forgot-password")
	public String showForgotPasswordForm() {
		return "/account/forgotPassword";
	}
	
}
