package com.group4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group4.dto.DoanhThuTheoChungLoaiDTO;
import com.group4.dto.DoanhThuTheoNhaCungCap;
import com.group4.dto.DoanhThuTheoThangDTO;
import com.group4.entity.DonHang;

public interface DonHangDAO extends JpaRepository<DonHang, Integer>{
	
	//Lấy tất cả đơn hàng theo trang thái
	@Query
	List<DonHang> findAllByTrangThaiLike(String trangThai);
	
	//Thống kê doanh thu theo tháng
	@Query("SELECT new com.group4.dto.DoanhThuTheoThangDTO(MONTH(dh.ngayLapDon), YEAR(dh.ngayLapDon), SUM(dhct.soLuong*dhct.giaTien)) "
			+ "FROM DonHang dh JOIN DonHangChiTiet dhct ON dh.maDonHang = dhct.maDh "
			+ "WHERE dh.trangThai LIKE '%giao%' "
			+ "GROUP BY YEAR(dh.ngayLapDon), MONTH(dh.ngayLapDon) "
			+ "ORDER BY YEAR(dh.ngayLapDon), MONTH(dh.ngayLapDon) ")
	List<DoanhThuTheoThangDTO> getDoanhThuTheoThang();
	
	//Thống kê doanh thu theo chủng loại
	@Query("SELECT new com.group4.dto.DoanhThuTheoChungLoaiDTO(sp.chungLoai.tenChungLoai, SUM(dhct.soLuong*dhct.giaTien)) "
			+ "FROM DonHangChiTiet dhct "
			+ "JOIN SanPham sp ON dhct.sanPham.maSanPham = sp.maSanPham "
			//+ "JOIN ChungLoai cl ON cl.maChungLoai = sp.maCl"
			//+ "WHERE dh.trangThai LIKE '%giao%' "
			+ "GROUP BY sp.chungLoai.tenChungLoai ")
	List<DoanhThuTheoChungLoaiDTO> getDoanhThuTheoChungLoai();
	
	//Thống kê doanh thu theo nhà cung cấp
	@Query("SELECT new com.group4.dto.DoanhThuTheoNhaCungCap(sp.nhaCungCap.tenNhaCungCap, sum(dhct.giaTien*dhct.soLuong))"
			+ " FROM DonHangChiTiet dhct JOIN dhct.sanPham sp"
			//+ " WHERE dh.trangThai LIKE '%giao%'"
			+ " GROUP BY sp.nhaCungCap.tenNhaCungCap")
	List<DoanhThuTheoNhaCungCap> getDoanhThuTheoNhaCungCap();
	
	//Lấy danh sách đơn hàng theo mã người dùng
	@Query
	List<DonHang> findByMaNd(String maNd);
}
