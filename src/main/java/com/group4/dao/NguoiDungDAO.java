package com.group4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group4.dto.SoLuongNguoiDungMoiTheoThangDTO;
import com.group4.entity.NguoiDung;

public interface NguoiDungDAO extends JpaRepository<NguoiDung, String>{
	
	//Thống kê số lượng người dùng đăng ký theo tháng
	@Query("SELECT new com.group4.dto.SoLuongNguoiDungMoiTheoThangDTO(MONTH(nd.ngayDangKy), YEAR(nd.ngayDangKy), COUNT(nd.maNguoiDung))"
			+ " FROM NguoiDung nd"
			//+ " WHERE nd.vaiTro LIKE 'Khách hàng' "
			+ " GROUP BY YEAR(nd.ngayDangKy), MONTH(nd.ngayDangKy)")
	List<SoLuongNguoiDungMoiTheoThangDTO> getThongKeNguoiDungMoiTheoThang();
	
	//Lấy danh sách người dùng theo trạng thái
	@Query
	List<NguoiDung> findAllByTrangThaiIs(Boolean trangThai);
}
