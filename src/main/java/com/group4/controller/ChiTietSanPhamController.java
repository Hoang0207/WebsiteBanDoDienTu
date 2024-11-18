package com.group4.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group4.entity.SanPham;
import com.group4.service.SanPhamService;

@Controller
public class ChiTietSanPhamController {
    @Autowired
    private SanPhamService productService;
    
    /*@GetMapping("/detail")
	public String detail(Model model) {
		model.addAttribute("content","/pages/detail");
		return "indexLayout";
	}*/
    

    @GetMapping({"/detail/{maSanPham}"})
    public String detail(@PathVariable("maSanPham") String maSanPham, Model model) {
        SanPham product = productService.findById("maSanPham").orElse(null);
        model.addAttribute("product", product);
        model.addAttribute("content","/pages/detail");
        return "indexLayout";
    }
    
}