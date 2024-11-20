package com.group4.controller.rest;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.dao.DonHangDAO;
import com.group4.dao.NguoiDungDAO;
import com.group4.dto.DoanhThuTheoChungLoaiDTO;
import com.group4.dto.DoanhThuTheoNhaCungCap;
import com.group4.dto.DoanhThuTheoThangDTO;
import com.group4.dto.SoLuongNguoiDungMoiTheoThangDTO;
import com.group4.service.DonHangService;
import com.group4.service.NguoiDungService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/ThongKe")
public class ThongKeRestController {
	
	@Autowired
	DonHangService dhService;
	
	@Autowired
	NguoiDungService ndService;
	
	@GetMapping("/DoanhThu/TheoThang")
	public ResponseEntity<Collection<DoanhThuTheoThangDTO>> restGetDoanhThuTheoThang(){
		List<DoanhThuTheoThangDTO> listDttt = dhService.getTkDoanhThuTheoThang();
		if(listDttt.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listDttt);
	}
	
	@GetMapping("/DoanhThu/TheoChungLoai")
	public ResponseEntity<Collection<DoanhThuTheoChungLoaiDTO>> restGetDoanhThuTheoCl(){
		List<DoanhThuTheoChungLoaiDTO> listDttcl =dhService.getTkDoanhThuTheoCl();
		if(listDttcl.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listDttcl);
	}
	
	@GetMapping("/DoanhThu/TheoNhaCungCap")
	public ResponseEntity<Collection<DoanhThuTheoNhaCungCap>> restGetDoanhThuTheoNcc(){
		List<DoanhThuTheoNhaCungCap> listDttncc = dhService.getTkDoanhThuTheoNcc();
		if(listDttncc.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listDttncc);
	}
	
	@GetMapping("/NguoiDung/SoLuongMoiTheoThang")
	public ResponseEntity<Collection<SoLuongNguoiDungMoiTheoThangDTO>> restGetSoLuongNdMoiTheoThang(){
		List<SoLuongNguoiDungMoiTheoThangDTO> listTknd = ndService.getTkSoLuongNguoiDungMoiTheoThang();
		if(listTknd.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listTknd);
	}
	
}
