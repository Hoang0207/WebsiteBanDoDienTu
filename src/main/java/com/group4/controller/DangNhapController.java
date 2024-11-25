package com.group4.controller;

import com.group4.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DangNhapController {


	@Autowired
	NguoiDungService nguoiDungService;
	@GetMapping("/login")
	public String showLoginForm() {
		return "account/login";
	}

//	@PostMapping("/login")
//	public String login(@RequestParam String email, @RequestParam String password, Model model) {
//		boolean isValidCredentials = nguoiDungService.validateCredentials(email, password);
//		if (isValidCredentials) {
//			return "redirect:/";
//		} else {
//			model.addAttribute("error", "Sai email hoặc mật khẩu!");
//			return "account/login";
//		}
//	}
	
}
