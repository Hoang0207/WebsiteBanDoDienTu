package com.group4.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "vai_tro")
public class VaiTro {
	
	@Id
	@Column(name = "ma_vai_tro", nullable = false, length = 10)
	private String maVaiTro;
	
	@Column(name = "ten_vai_tro", nullable = false, length = 40)
	private String tenVaiTro;
	
	@JsonIgnore
	@OneToMany(mappedBy = "vaiTro", cascade = CascadeType.ALL)
	private Set<PhanQuyen> phanQuyens;
}
