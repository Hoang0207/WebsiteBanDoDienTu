package com.group4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {
	
	//test confilg file 222
	
	@GetMapping("/admin")
	public String quanLyIndex() {
		//Trả về trang quản lý chính thức từ static
		return "redirect:/admin/html/QuanLyIndex.html";
	}
	
	@GetMapping()
	public String index(Model model) {
		model.addAttribute("content","/layout/TrangChu");
		return "index";
	}
	
	//************************
	@GetMapping("/home")
	public String home(Model model) {
		model.addAttribute("content","/pages/home");
		return "indexLayout";
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("content","/pages/shop");
		return "indexLayout";
	}
	
	@GetMapping("/contact")
	public String contact(Model model) {
		model.addAttribute("content","/pages/contact");
		return "indexLayout";
	}
	
	@GetMapping("/detail")
	public String detail(Model model) {
		model.addAttribute("content","/pages/detail");
		return "indexLayout";
	}
	
	@GetMapping("/cart")
	public String cart(Model model) {
		model.addAttribute("content","/pages/cart");
		return "indexLayout";
	}
	
}
