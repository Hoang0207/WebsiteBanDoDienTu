package com.group4.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GioHangChiTietDAO extends JpaRepository<GioHangChiTietDAO, Long> {
	
}