package com.group4.controller;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrangChuController {

	@GetMapping("/admin")
	public String quanLyIndex() {
		//Trả về trang quản lý chính thức từ static
		return "redirect:/admin/html/QuanLyLayout.html";
	}
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
	
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   
	    String email = null;
	    if (authentication != null && authentication.isAuthenticated() && !(authentication.getName().equals("anonymousUser"))) {
	        email = authentication.getName();  // Get the authenticated username (email)
			session.setAttribute("email", email);
	    }
	    model.addAttribute("email", email);

	   
	    model.addAttribute("content", "/pages/home");
	    
	    return "indexLayout";  // Return the layout template
	}
	
	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("content","/pages/shop");
		return "indexLayout";
	}

	@GetMapping("/logout")
	public String logoutPage() {
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/?logout";
	}

}
