package com.group4.service;

import java.util.List;
import java.util.Optional;

//import com.group4.controller.Product;
import com.group4.entity.SanPham;

public interface SanPhamService {
	public List<SanPham> findAll();
	
	public List<SanPham> findAllByTrangThai(Boolean trangThai);
	
	public Optional<SanPham> findById(String id);
	
	public List<SanPham> findByName(String name);
	
	public SanPham save(SanPham sp);
	
	public boolean existsById(String id);
	
	public int count();
	
	public void deleteById(String id);
	
	public List<SanPham> filterSanPham(String maSanPham,String tenSanPham, String maCl, String maNcc, String maTtdb, float minPrice, float maxPrice);

	public List<String> findSuggestionByKeyword(String keyword);
	
}
