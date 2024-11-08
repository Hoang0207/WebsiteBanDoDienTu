package com.group4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.group4.entity.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, String>, JpaSpecificationExecutor<SanPham>{
	
	@Query
	List<SanPham> findAllByTenSanPhamLike(String tenSp);
	
}
