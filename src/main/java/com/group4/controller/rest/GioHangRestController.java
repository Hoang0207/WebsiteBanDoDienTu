package com.group4.controller.rest;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	
}
