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
    private GioHangDAO gioHangRepository;

    @Override
    public List<GioHang> getAllGioHangs() {
        return gioHangRepository.findAll();
    }

    @Override
    public GioHang getGioHangById(Long id) {
    	Optional<GioHang> gioHang = gioHangRepository.findById(id);
        return gioHang.orElse(null);
    }

    @Override
    public GioHang saveGioHang(GioHang gioHang) {
        return gioHangRepository.save(gioHang);
    }

    @Override
    public void deleteGioHang(Long id) {
        gioHangRepository.deleteById(id);
    }

	
}

