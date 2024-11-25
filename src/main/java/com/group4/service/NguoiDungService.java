package com.group4.service;

import java.util.List;
import java.util.Optional;

import com.group4.dto.SoLuongNguoiDungMoiTheoThangDTO;
import com.group4.entity.NguoiDung;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface NguoiDungService extends UserDetailsService  {
	
	public List<NguoiDung> findAll();
	
	public List<NguoiDung> findAllByTrangThai(Boolean trangThai);
	boolean validateCredentials(String email, String password);

	public Optional<NguoiDung> findById(String id);
	
	public NguoiDung getInSession();
	
	public boolean existsById(String id);
	
	public NguoiDung save(NguoiDung nd);
	
	public void deleteById(String id);
	
	public int getSoLuongNguoiDung();
	
	public List<SoLuongNguoiDungMoiTheoThangDTO> getTkSoLuongNguoiDungMoiTheoThang();
	
}
