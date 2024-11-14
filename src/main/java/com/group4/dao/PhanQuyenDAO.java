package com.group4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.group4.entity.PhanQuyen;

public interface PhanQuyenDAO extends JpaRepository<PhanQuyen, Integer>{
	
	@Query
	public List<PhanQuyen> findAllByNguoiDung_MaNguoiDung(String id);
	
}
