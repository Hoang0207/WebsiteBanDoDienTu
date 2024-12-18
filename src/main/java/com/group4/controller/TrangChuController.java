package com.group4.controller;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group4.entity.NguoiDung;
import com.group4.entity.PhanQuyen;
import com.group4.entity.VaiTro;
import com.group4.service.NguoiDungService;
import com.group4.util.SessionUtil;

@Controller
public class TrangChuController {

	@Autowired
	SessionUtil sessionUtil;
	
	@Autowired
	NguoiDungService ndService;
	
	@GetMapping("/admin")
	public String quanLyIndex() {
		NguoiDung nd = ndService.getInSession();
		Set<PhanQuyen> dsPhanQuyen = nd.getPhanQuyens();
		List<String> dsVaiTro = new ArrayList<String>();
		for(PhanQuyen pq: dsPhanQuyen) {
			dsVaiTro.add(pq.getMaVt());
		}
		if(dsVaiTro.contains("DIRE")) {
			return "redirect:/admin/html/QuanLyLayout.html";
		}else {
			return "redirect:/admin/html/NhanVienLayout.html";
		}
		//Trả về trang quản lý chính thức từ static
		//return "redirect:/admin/html/QuanLyLayout.html";
	}
	
	@GetMapping("")
	public String home(Model model, HttpSession session) {
	
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   
	    String email = null;
	    if (authentication != null && authentication.isAuthenticated() && !(authentication.getName().equals("anonymousUser"))) {
	        email = authentication.getName();  // Get the authenticated username (email)
			session.setAttribute("email", email);
	        sessionUtil.set("user", ndService.findByEmail(email).get());
			model.addAttribute("success", "Đăng nhập thành công!");
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
		sessionUtil.remove("user");
		SecurityContextHolder.getContext().setAuthentication(null);
		return "redirect:/?logout";
	}

}
