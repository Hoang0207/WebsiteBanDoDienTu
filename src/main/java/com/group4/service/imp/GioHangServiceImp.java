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
    GioHangDAO ghDao;

    @Override
    public GioHang saveGioHang(GioHang gioHang) {
        return ghDao.save(gioHang);
    }

    @Override
    public void deleteGioHangById(int id) {
        ghDao.deleteById(id);
    }

	@Override
	public List<GioHang> getGioHangByMaNguoiDung(String id) {
		return ghDao.findAllByNguoiDung_MaNguoiDung(id);
	}

	@Override
	public Boolean existById(int id) {
		return ghDao.existsById(id);
	}

	
}

