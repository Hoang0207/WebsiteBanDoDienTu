package com.group4.service;

import java.util.List;

import com.group4.entity.GioHang;

public interface GioHangService {
	List<GioHang> getAllGioHangs();
	 GioHang getGioHangById(Long id);
	 GioHang saveGioHang(GioHang gioHang);
	 void deleteGioHang(Long id);
}
