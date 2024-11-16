package com.group4.controller.rest;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.dao.GioHangDAO;
import com.group4.entity.GioHang;
import com.group4.service.GioHangService;
import com.group4.service.NguoiDungService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/GioHang")
public class GioHangRestController {

	@Autowired
	NguoiDungService ndService;
	
	@Autowired
	GioHangService ghService;
	
	@GetMapping("{maNd}")
	public ResponseEntity<Collection<GioHang>> restGetGioHangByMaNd(@PathVariable("maNd") String maNd){
		if(ndService.findById(maNd).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		List<GioHang> listGh = ghService.getGioHangByMaNguoiDung(maNd);
		return ResponseEntity.ok(listGh);
	}
	
	@PutMapping()
	public ResponseEntity<GioHang> restPostGioHang(@RequestBody GioHang gh){
		if(!ghService.existById(gh.getMaGioHang())) {
			return ResponseEntity.notFound().build();
		}
		GioHang gioHang = ghService.saveGioHang(gh);
		return ResponseEntity.ok(gioHang);
	}
	
}
