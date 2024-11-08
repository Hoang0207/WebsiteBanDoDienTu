package com.group4.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.dao.GioHangDAO;
import com.group4.entity.GioHang;
import com.group4.service.GioHangService;

@Service
public class GioHangServiceImp implements GioHangService{
	@Autowired
    GioHangDAO gioHangRepository;

    @Override
    public GioHang saveGioHang(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    @Override
    public void deleteGioHang(int id) {
        gioHangRepository.deleteById(id);
    }

	@Override
	public List<GioHang> getGioHangByMaNguoiDung(String id) {
		return gioHangRepository.findAllByNguoiDung_MaNguoiDung(id);
	}

	
}

