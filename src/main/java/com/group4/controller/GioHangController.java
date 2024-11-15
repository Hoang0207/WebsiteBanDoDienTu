package com.group4.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group4.entity.NguoiDung;
import com.group4.entity.GioHang;
import com.group4.service.GioHangService;
import com.group4.service.NguoiDungService;

@Controller
public class GioHangController {
	
	@Autowired
	NguoiDungService nguoiDungService;
	
	@Autowired
    GioHangService gioHangService;
    
    @GetMapping("/cart")
	public String cart(Model model) {
		model.addAttribute("content","/pages/cart");
		return "indexLayout";
	}
    
    
    /*
    @GetMapping("/GioHang/{maNd}")
    public String getGioHang(Model model, @PathVariable("maNd") String maNd) {
        List<GioHang> gioHangs = gioHangService.getGioHangByMaNguoiDung(maNd);
        NguoiDung nguoiDung = nguoiDungService.findById(maNd).get(); 
        System.out.println(gioHangs);
        System.out.println(nguoiDung);
        model.addAttribute("gioHangs", gioHangs);
        model.addAttribute("nguoiDung", nguoiDung);
        model.addAttribute("content","/layout/giohang");
        return "index";
    }
    
    
    
    public String deleteGioHangItem(@PathVariable("maNd") int maNd) {
        gioHangService.deleteGioHang(maNd); // Implement this method in GioHangService
        return "redirect:/GioHang/{maNd}"; // Redirect to cart page after deletion
    }*/
}
