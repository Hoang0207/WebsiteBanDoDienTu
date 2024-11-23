package com.group4.controller.rest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.group4.dao.SanPhamDAO;
import com.group4.entity.SanPham;
import com.group4.service.SanPhamService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/SanPham")
public class SanPhamRestController {

	@Autowired
	SanPhamService spService;
		
	@GetMapping()
	public ResponseEntity<Collection<SanPham>> restGetAllSp(){
		List<SanPham> listSp = spService.findAll();
		if(listSp.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listSp);
	}
	
	@GetMapping("/TrangThai/{status}")
	public ResponseEntity<Collection<SanPham>> restGetAllSpByStatus(@PathVariable("status") Boolean status){
		List<SanPham> listSp = spService.findAllByTrangThai(status);
		return ResponseEntity.ok(listSp);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<SanPham> restGetSpById(@PathVariable("id") String id){
		Optional<SanPham> sp = spService.findById(id);
		if(sp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(sp.get());
	}
	
	@GetMapping("SoLuong")
	public ResponseEntity<Integer> restGetSoLuongSp(){
		int soLuong = (int)spService.count();
		return ResponseEntity.ok(soLuong);
	}
	
	@GetMapping("Suggestion")
	public ResponseEntity<Collection<String>> restGetSuggestionSp(String keyword){
		List<String> suggestion = spService.findSuggestionByKeyword(keyword);
		return ResponseEntity.ok(suggestion);
	}
	
	@GetMapping("filter")
	public ResponseEntity<Collection<SanPham>> restFilterSp(
			@RequestParam(required = false) String maSanPham,
			@RequestParam(required = false) String tenSanPham, 
			@RequestParam(required = false) String maCl,
			@RequestParam(required = false) String maNcc,
			@RequestParam(required = false) String maTtdb,
			@RequestParam Optional<Float> minPrice,
			@RequestParam Optional<Float> maxPrice,
			@RequestParam(required = false) Boolean trangThai){
		List<SanPham> listSp = spService.filterSanPham(maSanPham,tenSanPham,maCl,maNcc,maTtdb,minPrice.orElse(Float.MIN_VALUE),maxPrice.orElse(Float.MAX_VALUE),trangThai);
		return ResponseEntity.ok(listSp);
	}
	
	@PostMapping()
	public ResponseEntity<SanPham> restPostSanPham(@RequestBody SanPham sp){
		if(spService.existsById(sp.getMaSanPham())){
			return ResponseEntity.badRequest().build();
		}
		spService.save(sp);
		return ResponseEntity.ok(sp);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<SanPham> restPutSanPham(@RequestBody SanPham sp, @PathVariable("id") String id){
		Optional<SanPham> sanPham = spService.findById(id);
		if(sanPham.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		spService.save(sp);
		return ResponseEntity.ok(sp);
	}
	
	@PutMapping("/Restore/{id}")
	public ResponseEntity<SanPham> restRestoreSp(@PathVariable("id") String id){
		Optional<SanPham> sp = spService.findById(id);
		if(sp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		SanPham sanPham = sp.get();
		sanPham.setTrangThai(true);
		spService.save(sanPham);
		return ResponseEntity.ok(sanPham);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> restDeleteSanPham(@PathVariable("id") String id){
		Optional<SanPham> sp = spService.findById(id);
		if(sp.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		SanPham sanPham = sp.get();
		sanPham.setTrangThai(false);
		spService.save(sanPham);
		return ResponseEntity.ok().build();
	}
	
}
