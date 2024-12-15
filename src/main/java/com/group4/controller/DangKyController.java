package com.group4.controller;
import com.group4.entity.NguoiDung;
import com.group4.entity.PhanQuyen;
import com.group4.service.NguoiDungService;
import com.group4.service.PhanQuyenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;
@Controller
public class DangKyController {
   @Autowired
   private NguoiDungService nguoiDungService;
   @Autowired
   private PasswordEncoder passwordEncoder;
   @Autowired
   private PhanQuyenService pqService;
   // Hiển thị form đăng ký
   @GetMapping("/dangky")
   public String showRegisterForm() {
       return "/account/register"; // Trả về trang HTML đăng ký
   }
   // Xử lý đăng ký người dùng
   @PostMapping("/savedangky")
   public String registerUser(
           @RequestParam String email,
           @RequestParam String matKhau,
           @RequestParam String matKhauNhapLai,
           @RequestParam String hoTen,
           @RequestParam Boolean gioiTinh,
           @RequestParam String dienthoai,
           @RequestParam String ngaySinh,
           @RequestParam(required = false, defaultValue = "USER") String role,
           Model model) {
       // Kiểm tra xem email đã được đăng ký chưa
       if (nguoiDungService.emailExists(email)) {
           model.addAttribute("emailError", "Email này đã được đăng ký vui lòng nhập mail khác!");
           return "/account/register"; // Trả lại trang đăng ký với thông báo lỗi
       }
       // Kiểm tra xem mật khẩu và "Nhập lại mật khẩu" có khớp không
       if (!matKhau.equals(matKhauNhapLai)) {
           model.addAttribute("error", "Mật khẩu và Nhập lại mật khẩu không khớp!");
           return "/account/register";
       }
       String maNguoiDung = email.substring(0, email.indexOf("@"));
       NguoiDung nguoiDung = new NguoiDung();
       nguoiDung.setMaNguoiDung(maNguoiDung);
       nguoiDung.setMatKhau(passwordEncoder.encode(matKhau));
       nguoiDung.setHoTen(hoTen);
       nguoiDung.setGioiTinh(gioiTinh);
       nguoiDung.setEmail(email);
       nguoiDung.setSoDienThoai(dienthoai);
       nguoiDung.setNgaySinh(Date.valueOf(ngaySinh));
       nguoiDung.setNgayDangKy(Date.valueOf(LocalDate.now()));
       nguoiDung.setTrangThai(true);
       nguoiDungService.save(nguoiDung);
       PhanQuyen pq = new PhanQuyen();
       pq.setMaNd(maNguoiDung);
       pq.setMaVt("CUST");
       pqService.save(pq);
       model.addAttribute("success", "Đăng ký thành công!");
       return "/account/login";
   }
}
