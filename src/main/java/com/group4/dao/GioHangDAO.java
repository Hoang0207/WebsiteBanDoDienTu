package com.group4.dao;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group4.entity.GioHang;

public interface GioHangDAO extends JpaRepository<GioHang, Integer>{

	Optional<GioHang> findById(Long id);

	void deleteById(Long id);


}
