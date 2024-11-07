package com.group4.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group4.entity.GioHang;
import com.group4.service.GioHangService;

@Controller
public class GioHangController {
	@Autowired
    private GioHangService gioHangService;

    @GetMapping("/Giohang")
    public String getGioHang(Model model) {
        List<GioHang> gioHangs = gioHangService.getAllGioHangs(); 
        model.addAttribute("gioHangs", gioHangs);
        return "index";
    }
}
