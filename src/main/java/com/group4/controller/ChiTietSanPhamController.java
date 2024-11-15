package com.group4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.group4.entity.SanPham;
import com.group4.service.SanPhamService;

@Controller
public class ChiTietSanPhamController {
    @Autowired
    private SanPhamService productService;
    
    @GetMapping("/detail")
	public String detail(Model model) {
		model.addAttribute("content","/pages/detail");
		return "indexLayout";
	}
    
    /*
    @GetMapping("/{id}")
    public String getProductDetail(@PathVariable("id") String id, Model model) {
        SanPham product = productService.findById(id).get();
        model.addAttribute("product", product);
        model.addAttribute("content","/layout/chitietsanpham");
        return "index";
    }*/
}