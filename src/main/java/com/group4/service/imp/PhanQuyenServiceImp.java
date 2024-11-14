package com.group4.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.dao.PhanQuyenDAO;
import com.group4.entity.PhanQuyen;
import com.group4.service.PhanQuyenService;

@Service
public class PhanQuyenServiceImp implements PhanQuyenService{

	@Autowired
	PhanQuyenDAO pqDao;
	
	@Override
	public List<PhanQuyen> findAllByMaNguoiDung(String maNd) {
		return pqDao.findAllByNguoiDung_MaNguoiDung(maNd);
	}
	
}
