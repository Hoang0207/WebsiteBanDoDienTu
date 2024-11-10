package com.group4.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.dao.NguoiDungDAO;
import com.group4.dto.SoLuongKhachDangKyTheoThangDTO;
import com.group4.entity.NguoiDung;
import com.group4.service.NguoiDungService;

@Service
public class NguoiDungServiceImp implements NguoiDungService {

	@Autowired
	NguoiDungDAO ndDao;

	@Override
	public List<NguoiDung> findAll() {
		return ndDao.findAll();
	}

	@Override
	public Optional<NguoiDung> findById(String id) {
		return ndDao.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		return ndDao.existsById(id);
	}
	
	@Override
	public NguoiDung save(NguoiDung nd) {
		return ndDao.save(nd);
	}
	
	@Override
	public void deleteById(String id) {
		ndDao.deleteById(id);
	}
	
	//Thống kê số lượng khách hàng
	@Override
	public int getSoLuongKhachHang() {
		return ndDao.findAll().size();
	}

	// Thống kê số lượng khách đăng ký theo tháng
	@Override
	public List<SoLuongKhachDangKyTheoThangDTO> getTkSoLuongKhachDangKyTheoThang() {
		return null;
	}

}
