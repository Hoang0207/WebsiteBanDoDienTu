package com.group4.controller;

import com.group4.service.NguoiDungService;
import com.group4.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class DangNhapController {

    @Autowired
    NguoiDungService nguoiDungService;

    @Autowired
    CookieUtil cookieUtil;

    // Hiển thị form đăng nhập và điền tự động email và mật khẩu nếu có cookie
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        // Kiểm tra xem có cookie "email" và "password" không
        String email = cookieUtil.getValue("email");
        String password = cookieUtil.getValue("password");

        // Điền tự động vào form và đánh dấu ô 'remember me' nếu có cookie
        model.addAttribute("email", email);
        model.addAttribute("password", password);
        model.addAttribute("rememberMe", !email.isEmpty() && !password.isEmpty());

        return "account/login";  // Trả về trang login
    }

    // Xử lý đăng nhập, lưu hoặc xóa cookie theo trạng thái 'remember me'
    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, 
                        @RequestParam(required = false) boolean remember, Model model) {

        // Kiểm tra xem thông tin đăng nhập có hợp lệ không (ví dụ sử dụng một service để kiểm tra)
        boolean isValidCredentials = nguoiDungService.validateCredentials(email, password);
        if (isValidCredentials) {
            // Nếu 'remember me' được chọn, lưu email và password vào cookie
            if (remember) {
                cookieUtil.add("email", email, 24);  // Lưu email trong 24 giờ
                cookieUtil.add("password", password, 24);  // Lưu password trong 24 giờ
            } else {
                // Nếu không chọn 'remember me', xóa cookie
                cookieUtil.remove("email");
                cookieUtil.remove("password");
            }

            return "redirect:/";  // Điều hướng về trang chủ sau khi xử lý
        } else {
            model.addAttribute("error", "Sai email hoặc mật khẩu!");
            return "account/login";  // Trả lại trang login với thông báo lỗi
        }
    }

    @GetMapping("/loginError")
    public String loginError(Model model) {
        model.addAttribute("error", "Email hoặc mật khẩu sai!");
        return "account/loginError";
    }
}