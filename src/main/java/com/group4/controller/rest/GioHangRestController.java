package com.group4.controller.rest;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.dao.GioHangDAO;
import com.group4.entity.GioHang;
import com.group4.entity.NguoiDung;
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
		for (GioHang gh : listGh) {
			if(gh.getSanPham().getTrangThai()==false) {
				ghService.deleteGioHangById(gh.getMaGioHang());
			}
		}
		List<GioHang> listGhFinal = ghService.getGioHangByMaNguoiDung(maNd);
		return ResponseEntity.ok(listGhFinal);
	}
	
	@PutMapping()
	public ResponseEntity<GioHang> restPutGioHang(@RequestBody GioHang gh){
		if(!ghService.existById(gh.getMaGioHang())) {
			return ResponseEntity.notFound().build();
		}
		GioHang gioHang = ghService.saveGioHang(gh);
		return ResponseEntity.ok(gioHang);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> restDeleteGioHang(@PathVariable("id") Integer id){
		if(!ghService.existById(id)) {
			return ResponseEntity.notFound().build();
		}
		ghService.deleteGioHangById(id);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/DeleteByMaNd/{maNd}")
	public ResponseEntity<Integer> restDeleteGioHangByMaNd(@PathVariable("maNd") String maNd){
		//NguoiDung nd = ndService.getInSession();
		if(ghService.getGioHangByMaNguoiDung(maNd).isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		Integer rowDelete = ghService.deleteByMaNd(maNd);
		return ResponseEntity.ok(rowDelete);
	}
}
