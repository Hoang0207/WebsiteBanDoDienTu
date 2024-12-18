package com.group4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String changeInfomation(Model model, @ModelAttribute("nd") NguoiDung nd, RedirectAttributes redirectAttributes) {
		System.out.println(nd);
		NguoiDung ndSession = ndService.getInSession();
		ndSession.setHoTen(nd.getHoTen());
		ndSession.setSoDienThoai(nd.getSoDienThoai());
		ndSession.setNgaySinh(nd.getNgaySinh());
		ndSession.setDiaChi(nd.getDiaChi());
		ndSession.setGioiTinh(nd.getGioiTinh());
		ndService.save(ndSession);
		redirectAttributes.addFlashAttribute("SuccessUpdateInfomationMessage", "Cập nhật thông tin cá nhân thành công");
		return "redirect:/userform";
	}
	
	@PostMapping("/userform/ChangePassword")
	public String changePassword(Model model,RedirectAttributes redirectAttributes, @RequestParam("NewPass") String newPass, @RequestParam("Retype") String retype) {
		NguoiDung ndSession = ndService.getInSession();
		if(newPass.equalsIgnoreCase(retype)) {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			ndSession.setMatKhau(encoder.encode(newPass));
			ndService.save(ndSession);
			redirectAttributes.addFlashAttribute("SuccessUpdatePasswordMessage", "Cập nhật mật khẩu thành công");
		}else {
			redirectAttributes.addFlashAttribute("FailedUpdatePasswordMessage", "Mật khẩu nhập lại không khớp");
		}
		return "redirect:/userform";
	}
}
