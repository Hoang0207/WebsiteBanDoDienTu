package com.group4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group4.entity.NguoiDung;
import com.group4.service.NguoiDungService;
import com.group4.util.SessionUtil;

@Controller
public class ThongTinCaNhan {

	@Autowired
	NguoiDungService ndService;
	
	@Autowired
	SessionUtil session;

	@GetMapping("/userform")
	public String userform(Model model) {
		model.addAttribute("content", "/pages/thongtinnguoidung");
		NguoiDung nd = ndService.getInSession();
		model.addAttribute("nd", nd);
		return "indexLayout";
	}

	@PostMapping("/userform/ChangeInfomation")
	public String changeInfomation(Model model, @ModelAttribute("nd") NguoiDung nd) {
		System.out.println(nd);
		NguoiDung ndSession = ndService.getInSession();
		ndSession.setHoTen(nd.getHoTen());
		ndSession.setSoDienThoai(nd.getSoDienThoai());
		ndSession.setNgaySinh(nd.getNgaySinh());
		ndSession.setDiaChi(nd.getDiaChi());
		ndSession.setGioiTinh(nd.getGioiTinh());
		ndService.save(ndSession);
		return "redirect:/userform";
	}
	
}
