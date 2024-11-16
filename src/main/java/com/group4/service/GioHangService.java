package com.group4.service;

import java.util.List;
import java.util.Optional;

import com.group4.entity.GioHang;

public interface GioHangService {
	
	public GioHang saveGioHang(GioHang gioHang);
	
	public void deleteGioHangById(int id);
	
	public Boolean existById(int id);
	
	public List<GioHang> getGioHangByMaNguoiDung(String id);

}
