
package com.group4.controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.dao.DonHangChiTietDAO;
import com.group4.entity.DonHang;
import com.group4.entity.DonHangChiTiet;
import com.group4.entity.NguoiDung;
import com.group4.service.DonHangChiTietService;
import com.group4.service.DonHangService;
import com.group4.service.NguoiDungService;

@Controller
public class LichSuDatHangController {

    @Autowired
    private DonHangService donHangService;
    
    @Autowired
    NguoiDungService ndService;

    @GetMapping("/lichsu")
    public String getLichSuDatHang(Model model) {
    	NguoiDung nd = ndService.getInSession();
        // Lấy danh sách đơn hàng theo mã người dùng
        List<DonHang> ListDh = donHangService.findByMaNd(nd.getMaNguoiDung()); // Hoặc phương thức khác nếu cần
        List<DonHang> donHangs = ListDh.stream().filter(dh -> dh.getIsActive()==true)
        		.sorted((dh1,dh2) -> dh2.getNgayLapDon().compareTo(dh1.getNgayLapDon())).collect(Collectors.toList());
        model.addAttribute("donHangs", donHangs);
        model.addAttribute("content","/pages/lichsudathang");
        return "indexLayout"; // Trả về trang HTML
    }
    @Autowired
    private DonHangChiTietDAO donHangChiTietDAO;
    @GetMapping("/don_hang_chi_tiet")
    public String getChiTietDonHang(@RequestParam("id") int id, Model model) {
        List<DonHangChiTiet> chiTiet = donHangChiTietDAO.findAllByDonHang_MaDonHang(id); // Sử dụng repository phù hợp
        model.addAttribute("chiTietDonHang", chiTiet);
        model.addAttribute("content","/pages/donhangchitiet");
        return "indexLayout";
    }
}


