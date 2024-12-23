package com.group4.entity;

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
import lombok.EqualsAndHashCode;
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
	
	@Column(name="ma_nd", nullable = false)
	private String maNd;
	
	@Column(name="ma_vt", nullable = false)
	private String maVt;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "ma_nd", insertable = false, updatable = false)
	private NguoiDung nguoiDung;
	
	@ManyToOne
	@EqualsAndHashCode.Exclude
	@JoinColumn(name = "ma_vt", insertable = false, updatable = false)
	private VaiTro vaiTro;
	
}
