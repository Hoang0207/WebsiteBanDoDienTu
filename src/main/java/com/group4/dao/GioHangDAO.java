package com.group4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.group4.entity.GioHang;

public interface GioHangDAO extends JpaRepository<GioHang, Integer>{
	
	@Query
	List<GioHang> findAllByNguoiDung_MaNguoiDung(String id);
	
	@Query @Transactional
	Integer deleteByNguoiDung_MaNguoiDung(String maNd);
	
}
