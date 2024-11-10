package com.group4.entity;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "phan_quyen")
public class PhanQuyen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ma_phan_quyen", nullable = false)
	private int maPhanQuyen;
	
	@ManyToOne
	@JoinColumn(name = "ma_nd",insertable = false, updatable = false)
	private NguoiDung nguoiDung;
	
	@ManyToOne
	@JoinColumn(name = "ma_vt",insertable = false, updatable = false)
	private VaiTro vaiTro;
	
}
