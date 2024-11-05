
package com.group4.controller.NguoiDung;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.dao.DonHangChiTietDAO;
import com.group4.entity.DonHang;
import com.group4.entity.DonHangChiTiet;
import com.group4.service.DonHangChiTietService;
import com.group4.service.DonHangService;

@Controller
public class LichSuDatHang {

    @Autowired
    private DonHangService donHangService;

    @GetMapping("/lichsu/{maNd}")
    public String getLichSuDatHang(@PathVariable("maNd") String maNd, Model model) {
        // Lấy danh sách đơn hàng theo mã người dùng
        List<DonHang> donHangs = donHangService.findByMaNd(maNd); // Hoặc phương thức khác nếu cần
        model.addAttribute("donHangs", donHangs);
        return "lichsudathang"; // Trả về trang HTML
    }
    @Autowired
    private DonHangChiTietDAO donHangChiTietDAO;
    @GetMapping("/don_hang_chi_tiet")
    public String getChiTietDonHang(@RequestParam("id") int id, Model model) {
        List<DonHangChiTiet> chiTiet = donHangChiTietDAO.findAllByDonHang_MaDonHang(id); // Sử dụng repository phù hợp
        model.addAttribute("chiTietDonHang", chiTiet);
        return "donhangchitiet";
    }
}


