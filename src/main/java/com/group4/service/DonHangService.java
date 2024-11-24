package com.group4.service;

import java.util.List;
import java.util.Optional;

import com.group4.dto.DoanhThuTheoChungLoaiDTO;
import com.group4.dto.DoanhThuTheoNhaCungCap;
import com.group4.dto.DoanhThuTheoThangDTO;
import com.group4.entity.DonHang;

public interface DonHangService {
	
	public List<DonHang> findAll();
	
	public Optional<DonHang> findById(int id);
	
	public boolean existsById(int id);
	
	public DonHang save(DonHang dh);
	
	public DonHang order();
	
	public void deleteById(int id);
	
	public List<DonHang> findAllByIsActive(Boolean status);
	
	public List<DonHang> findAllByTrangThai(String trangThai);
	
	//Thống kê số lượng đơn hàng
	public int count();
	
	//Thống kê doanh thu theo tháng
	public List<DoanhThuTheoThangDTO> getTkDoanhThuTheoThang();
	
	//Thống kê doanh thu theo chủng loại
	public List<DoanhThuTheoChungLoaiDTO> getTkDoanhThuTheoCl();
	
	//Thống kê doanh thu theo nhà cung cấp
	public List<DoanhThuTheoNhaCungCap> getTkDoanhThuTheoNcc();
	
	//Lấy đơn hàng theo mã người dùng
	public List<DonHang> findByMaNd(String maNd);
}
