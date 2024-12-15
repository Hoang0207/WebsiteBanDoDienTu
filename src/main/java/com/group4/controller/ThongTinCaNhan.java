package com.group4.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ThongTinCaNhan {
	  @GetMapping("/userform")
	    public String contact(Model model) {
	        model.addAttribute("content", "/pages/thongtinnguoidung");
	        return "indexLayout";
	    }

}
