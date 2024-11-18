package com.group4.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.group4.dao.SanPhamDAO;
import com.group4.entity.DonHang;
import com.group4.entity.SanPham;
import com.group4.service.SanPhamService;

@Controller
public class ChiTietSanPhamController {
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
        List<SanPham> detail = sanPhamService.findSanPhamById(maSp); 
        model.addAttribute("detail", detail);
        model.addAttribute("content","/pages/detail");
        return "indexLayout"; // Trả về trang HTML
    }
}