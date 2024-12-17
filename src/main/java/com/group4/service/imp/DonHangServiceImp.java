package com.group4.service.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.dao.DonHangChiTietDAO;
import com.group4.dao.DonHangDAO;
import com.group4.dao.GioHangDAO;
import com.group4.dto.DoanhThuTheoChungLoaiDTO;
import com.group4.dto.DoanhThuTheoNhaCungCap;
import com.group4.dto.DoanhThuTheoThangDTO;
import com.group4.entity.DonHang;
import com.group4.entity.DonHangChiTiet;
import com.group4.entity.GioHang;
import com.group4.entity.NguoiDung;
import com.group4.service.DonHangService;
import com.group4.service.GioHangService;
import com.group4.service.NguoiDungService;

@Service
public class DonHangServiceImp implements DonHangService{

	@Autowired
	DonHangDAO dhDao;
	
	@Autowired
	DonHangChiTietDAO dhctDao;
	
	@Autowired
	NguoiDungService ndService;
	
	@Autowired
	GioHangService ghService;
	
	@Override
	public List<DonHang> findAll() {
		return dhDao.findAll();
	}

	@Override
	public Optional<DonHang> findById(int id) {
		return dhDao.findById(id);
	}
	
	@Override
	public boolean existsById(int id) {
		return dhDao.existsById(id);
	}

	@Override
	public DonHang save(DonHang dh) {
		return dhDao.save(dh);
	}

	@Override
	public void deleteById(int id) {
		dhDao.deleteById(id);
		
	}

	@Override
	public List<DonHang> findAllByTrangThai(String trangThai) {
		return dhDao.findAllByTrangThaiLike(trangThai);
	}

	@Override
	public int count() {
		List<DonHang> listDh = this.findAllByIsActive(true);
		return listDh.size();
	}
	
	@Override
	public List<DoanhThuTheoThangDTO> getTkDoanhThuTheoThang() {
		return dhDao.getDoanhThuTheoThang();
	}

	@Override
	public List<DoanhThuTheoChungLoaiDTO> getTkDoanhThuTheoCl() {
		return dhDao.getDoanhThuTheoChungLoai();
	}

	@Override
	public List<DoanhThuTheoNhaCungCap> getTkDoanhThuTheoNcc() {
		return dhDao.getDoanhThuTheoNhaCungCap();
	}
	
	@Override
	public List<DonHang> findByMaNd(String maNd) {
		return dhDao.findByMaNd(maNd); 
	}

	@Override
	public DonHang order(String diaChi) {
		Date date = new Date();
		NguoiDung nd = ndService.getInSession();
		if(nd==null) {
			return null;
		}
		nd.setDiaChi(diaChi);
		ndService.save(nd);
		
		//Thiết lập đơn hàng
		DonHang dh = new DonHang();
		dh.setDiaChiGiao(nd.getDiaChi());
		dh.setMaNd(nd.getMaNguoiDung());
		dh.setNgayLapDon(date);
		dh.setTrangThai("Chờ xác nhận");
		dh.setNguoiDung(nd);
		this.save(dh);
		
		//Thiết lập đơn hàng chi tiết
		List<GioHang> listGh = ghService.getGioHangByMaNguoiDung(nd.getMaNguoiDung());
		for(GioHang gh: listGh) {
			DonHangChiTiet dhct = new DonHangChiTiet();
			dhct.setDonHang(dh);
			dhct.setGiaTien(gh.getSanPham().getGiaTien());
			dhct.setMaDh(dh.getMaDonHang());
			dhct.setMaSp(gh.getMaSp());
			dhct.setSanPham(gh.getSanPham());
			dhct.setSoLuong(gh.getSoLuong());
			dhctDao.save(dhct);
			gh.getSanPham().setSoLuong(gh.getSanPham().getSoLuong()-gh.getSoLuong());
			ghService.saveGioHang(gh);
		}
		
		return dh;
	}

	@Override
	public List<DonHang> findAllByIsActive(Boolean status) {
		return dhDao.findAllByIsActiveIs(status);
	}
}
