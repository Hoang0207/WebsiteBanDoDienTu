package com.group4.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class GioHangChiTiet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long maChiTiet;
    private String tenSanPham;
    private int soLuong;
    private double gia;

    @ManyToOne
    @JoinColumn(name = "maGioHang")
    private GioHang gioHang;
    // Getters và setters
}
