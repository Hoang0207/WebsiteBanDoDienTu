package com.group4.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group4.entity.SanPham;

public interface SanPhamDAO extends JpaRepository<SanPham, String>, JpaSpecificationExecutor<SanPham>{
	
	@Query
	List<SanPham> findAllByTenSanPhamLike(String tenSp);
	
	@Query("SELECT sp.tenSanPham FROM SanPham sp WHERE sp.tenSanPham LIKE %:keyword%")
	List<String> findSuggestionsByKeyword(@Param("keyword") String keyword);
	
	@Query
	List<SanPham> findAllByTrangThaiIs(Boolean trangThai);
}
