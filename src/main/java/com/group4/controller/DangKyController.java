package com.group4.controller;

import com.group4.entity.NguoiDung;
import com.group4.service.NguoiDungService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.time.LocalDate;

@Controller
public class DangKyController {

    @Autowired
    private NguoiDungService nguoiDungService;

    // Hiển thị form đăng ký
    @GetMapping("/dangky")
    public String showRegisterForm() {
        return "/account/register"; // Trả về trang HTML đăng ký
    }

    // Xử lý đăng ký người dùng
    @PostMapping("/dangky")
    public String registerUser(
            @RequestParam String email,
            @RequestParam String matKhau,
            @RequestParam String matKhauNhapLai, // Nhận giá trị từ trường "Nhập lại mật khẩu"
            @RequestParam String hoTen,
            @RequestParam Boolean gioiTinh,
            @RequestParam String dienthoai,
            @RequestParam String ngaySinh,
            Model model) {

        // Kiểm tra xem mật khẩu và "Nhập lại mật khẩu" có khớp không
        if (!matKhau.equals(matKhauNhapLai)) {
            model.addAttribute("error", "Mật khẩu và Nhập lại mật khẩu không khớp!");
            return "/account/register";  // Trả lại trang đăng ký với thông báo lỗi
        }

        try {
            // Lấy mã người dùng từ email (phần trước dấu '@')
            String maNguoiDung = email.substring(0, email.indexOf("@"));

            // Tạo đối tượng người dùng mới
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setMaNguoiDung(maNguoiDung);  // Thiết lập mã người dùng lấy từ email
            nguoiDung.setMatKhau(matKhau); // Đảm bảo mã hóa mật khẩu trước khi lưu vào DB
            nguoiDung.setHoTen(hoTen);
            nguoiDung.setGioiTinh(gioiTinh);
            nguoiDung.setEmail(email);
            nguoiDung.setSoDienThoai(dienthoai);
            nguoiDung.setNgaySinh(Date.valueOf(ngaySinh)); // Chuyển đổi từ String sang Date
            nguoiDung.setNgayDangKy(Date.valueOf(LocalDate.now())); // Ngày hiện tại
            nguoiDung.setTrangThai(true); // Mặc định người dùng được kích hoạt

            // Lưu người dùng vào cơ sở dữ liệu
            nguoiDungService.save(nguoiDung);

            model.addAttribute("success", "Đăng ký thành công!");
            return "/account/register"; // Quay lại trang đăng ký với thông báo thành công
        } catch (Exception e) {
            model.addAttribute("error", "Đã xảy ra lỗi khi đăng ký: " + e.getMessage());
            return "/account/register"; // Quay lại trang đăng ký nếu có lỗi
        }
    }


}
