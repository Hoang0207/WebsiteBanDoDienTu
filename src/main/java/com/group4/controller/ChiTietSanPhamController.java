package com.group4.controller;


import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.dao.SanPhamDAO;
import com.group4.entity.DonHang;
import com.group4.entity.GioHang;
import com.group4.entity.NguoiDung;
import com.group4.entity.SanPham;
import com.group4.service.GioHangService;
import com.group4.service.NguoiDungService;
import com.group4.service.SanPhamService;

@Controller
public class ChiTietSanPhamController {
	
	@Autowired
	NguoiDungService ndService;
	
	@Autowired
	GioHangService ghService;
	
    /*@Autowired
    private SanPhamService productService;
    
    @GetMapping("/detail")
	public String detail(Model model) {
		model.addAttribute("content","/pages/detail");
		return "indexLayout";
	}*/
    
    /*@Autowired
    private SanPhamDAO sanPhamDAO;
    @GetMapping("/detail")
    public String getSanPham(@RequestParam("id") String id, Model model) {
        List<SanPham> detail = sanPhamDAO.findById_maSanPham(id); // Sử dụng repository phù hợp
        model.addAttribute("SanPham", detail);
        model.addAttribute("content","/pages/detail");
        return "indexLayout";
    }*/
    
    @Autowired
    private SanPhamService sanPhamService;
    
    @GetMapping("/detail/{maSp}")
    public String getSanPham(@PathVariable("maSp") String maSp, Model model) {
        // Lấy danh sách đơn hàng theo mã người dùng
        SanPham sp = sanPhamService.findById(maSp).get();
        model.addAttribute("detail", sp);
        model.addAttribute("content","/pages/detail");
        return "indexLayout"; // Trả về trang HTML
    }
    
    @PostMapping("/detail/{maSp}/addToCart")
    public String addToCart(Model model, @PathVariable("maSp") String maSp, @RequestParam("soLuong") int soLuong) {
    	NguoiDung nd = ndService.getInSession();
    	GioHang gh = new GioHang();
    	gh.setMaNd(nd.getMaNguoiDung());
    	gh.setMaSp(maSp);
    	gh.setSoLuong(soLuong);
    	ghService.saveGioHang(gh);
        return "redirect:/cart";
    }
}