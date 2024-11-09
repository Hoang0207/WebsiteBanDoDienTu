package com.group4.service;

import java.util.List;
import java.util.Optional;

//import com.group4.controller.Product;
import com.group4.entity.SanPham;

public interface SanPhamService {
	public List<SanPham> findAll();
	
	public Optional<SanPham> findById(String id);
	
	public List<SanPham> findByName(String name);
	
	public SanPham save(SanPham sp);
	
	public boolean existsById(String id);
	
	public int count();
	
	public void deleteById(String id);
	
	public List<SanPham> filterSanPham(String maSanPham,String tenSanPham, String maCl, String maNcc, String maTtdb, float minPrice, float maxPrice, Boolean trangThai);

	//public SanPham getProductById(String id);

}
