package com.group4.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

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
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@OneToMany(mappedBy = "vaiTro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<PhanQuyen> phanQuyens;
}
