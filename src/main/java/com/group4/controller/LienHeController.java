package com.group4.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.service.MailerService;

@Controller
public class LienHeController {

    @Autowired
    private JavaMailSender mailSender;
    
    @Autowired
    MailerService mailService;

    // Trang liên hệ
    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("content", "/pages/contact");
        return "indexLayout";
    }

    // Gửi email
    @PostMapping("/sendMail")
    public String sendMail(
            @RequestParam("Hoten") String Hoten,
            @RequestParam("Didong") String Didong,
            @RequestParam("Ghichu") String Ghichu,
            @RequestParam("recipientEmail") String recipientEmail,
            Model model) {

        try {
            // Dùng định dạng HTML để hỗ trợ xuống dòng
            String body = "Họ tên: " + Hoten 
                        + "<br>Email: " + recipientEmail 
                        + "<br>Di động: " + Didong 
                        + "<br><br>Ghi chú:<br>" + Ghichu;

            // Gửi email qua MailerService
            mailService.push(recipientEmail, "Yêu cầu hỗ trợ từ người dùng", body);

            model.addAttribute("successMessage", "Gửi email thành công!");
        } catch (Exception e) {
            model.addAttribute("errorMessage", "Đã xảy ra lỗi khi gửi email: " + e.getMessage());
        }

        model.addAttribute("content", "/pages/contact");
        return "indexLayout";
    }
}
