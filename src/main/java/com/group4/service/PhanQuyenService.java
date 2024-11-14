package com.group4.service;

import java.util.List;

import com.group4.entity.PhanQuyen;

public interface PhanQuyenService {
	
	public List<PhanQuyen> findAllByMaNguoiDung(String maNd);
	
}
