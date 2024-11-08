package com.group4.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group4.dao.GioHangDAO;
import com.group4.entity.GioHang;
import com.group4.service.GioHangService;

@Controller
public class GioHangController {
	
	@Autowired
    GioHangService gioHangService;

    @GetMapping("/GioHang/{maNd}")
    public String getGioHang(Model model, @PathVariable("maNd") String maNd) {
        List<GioHang> gioHangs = gioHangService.getGioHangByMaNguoiDung(maNd); 
        System.out.println(gioHangs);
        model.addAttribute("gioHangs", gioHangs);
        model.addAttribute("content","/layout/giohang");
        return "index";
    }
    
}
