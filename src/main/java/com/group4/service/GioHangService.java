package com.group4.service;

import java.util.List;
import java.util.Optional;

import com.group4.entity.GioHang;

public interface GioHangService {
	
	GioHang saveGioHang(GioHang gioHang);
	
	void deleteGioHang(int id);
	
	List<GioHang> getGioHangByMaNguoiDung(String id);

}
