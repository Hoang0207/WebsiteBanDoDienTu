package com.group4.controller;

import com.group4.service.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LienHeController {
   @Autowired
   private JavaMailSender mailSender;
   @Autowired
   MailerService mailService;

   public LienHeController() {
   }

   @GetMapping({"/contact"})
   public String contact(Model model) {
      model.addAttribute("content", "/pages/contact");
      return "indexLayout";
   }

   @PostMapping({"/sendMail"})
   public String sendMail(@RequestParam("Hoten") String Hoten, @RequestParam("Didong") String Didong, @RequestParam("Ghichu") String Ghichu, @RequestParam("recipientEmail") String recipientEmail, Model model) {
      try {
         String body = "H\u1ecd t\u00ean: " + Hoten + "<br>Email: " + recipientEmail + "<br>Di \u0111\u1ed9ng: " + Didong + "<br><br>Ghi ch\u00fa:<br>" + Ghichu;
         this.mailService.push(recipientEmail, "Y\u00eau c\u1ea7u h\u1ed7 tr\u1ee3 t\u1eeb ng\u01b0\u1eddi d\u00f9ng", body);
         model.addAttribute("successMessage", "G\u1eedi email th\u00e0nh c\u00f4ng!");
      } catch (Exception var7) {
         model.addAttribute("errorMessage", "\u0110\u00e3 x\u1ea3y ra l\u1ed7i khi g\u1eedi email: " + var7.getMessage());
      }

      model.addAttribute("content", "/pages/contact");
      return "indexLayout";
   }
}
