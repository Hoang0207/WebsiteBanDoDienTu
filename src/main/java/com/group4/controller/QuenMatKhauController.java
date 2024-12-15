package com.group4.controller;

import com.group4.config.Constants;
import com.group4.service.MailerService;
import com.group4.service.NguoiDungService;
import jakarta.servlet.http.HttpSession;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuenMatKhauController {

	@Autowired
	private MailerService mailerService;

	@Autowired
	private NguoiDungService nguoiDungService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	Constants constants = new Constants();


	@GetMapping("/forgot-password")
	public String showForgotPasswordForm() {
		return "/account/forgotPassword";
	}

	@RequestMapping(value = "send-otp-recover", method = RequestMethod.POST)
	public String getMail(@RequestParam("email") String email, HttpSession session) {
		session.setAttribute("emailToReset",email);
		String otpCode = constants.otpCode();
		String subject = "Mã OTP Xác Minh Tài Khoản";
		String mess = "Chào Bạn" + " " + "Đây Là Mã Của Bạn: " + otpCode + " Vui Lòng Nhập Mã" + "\n Để Xác Minh";
		this.mailerService.push(email, subject, mess);
		session.setAttribute("recoverOtp",otpCode);
		session.setMaxInactiveInterval(360);
		return "account/confirmOtpRecover";
	}

	@RequestMapping(value = "confirm-otp-recover", method = RequestMethod.POST)
	public String recover(@RequestParam("otp") String otp, Model model, HttpSession session) {
		if (session.getAttribute("recoverOtp").equals(otp)){
			return "account/resetPassword";
		}
		model.addAttribute("mess","Mã OTP Không Đúng,Vui Lòng Kiểm Tra Lại.");
		return "account/confirmOtpRecover";
	}

	@RequestMapping(value = "save-new-password", method = RequestMethod.POST)
	public String saveNewPassword(@RequestParam("password") String password, HttpSession session) {
		String email = (String) session.getAttribute("emailToReset");
		if (nguoiDungService.recoverPassword(passwordEncoder.encode(password),email)==1){
			return "redirect:/login";
		}
		return "account/resetPassword";
	}

}
