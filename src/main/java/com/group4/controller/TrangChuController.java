package com.group4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {

	@GetMapping("/admin")
	public String quanLyIndex() {
		//Trả về trang quản lý chính thức từ static
		return "redirect:/admin/html/QuanLyIndex.html";
	}
	
	@GetMapping()
	public String home(Model model) {
		model.addAttribute("content","/pages/home");
		return "indexLayout";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("content","/pages/shop");
		return "indexLayout";
	}
	
}
