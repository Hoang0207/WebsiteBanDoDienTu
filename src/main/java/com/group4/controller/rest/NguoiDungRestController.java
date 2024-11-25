package com.group4.controller.rest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.dao.NguoiDungDAO;
import com.group4.entity.NguoiDung;
import com.group4.service.NguoiDungService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/NguoiDung")
public class NguoiDungRestController {

	@Autowired
	NguoiDungService ndService;
	
	@GetMapping()
	public ResponseEntity<Collection<NguoiDung>> restGetAllNd(){
		List<NguoiDung> listNd = ndService.findAll();
		if(listNd.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listNd);
	}
	
	@GetMapping("/TrangThai/{status}")
	public ResponseEntity<Collection<NguoiDung>> restGetAllNdByTrangThai(@PathVariable("status") Boolean trangThai){
		List<NguoiDung> listNd = ndService.findAllByTrangThai(trangThai);
		return ResponseEntity.ok(listNd);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<NguoiDung> restGetNdById(@PathVariable("id") String id){
		Optional<NguoiDung> nd = ndService.findById(id);
		if(nd.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(nd.get());
	}
	
	@GetMapping("Session")
	public ResponseEntity<NguoiDung> restGetNdInSession(){
		NguoiDung nd = ndService.getInSession();
		return ResponseEntity.ok(nd);
	}
	
	//Thống kê số lượng khách hàng
	@GetMapping("SoLuong")
	public ResponseEntity<Integer> restGetSoLuongKhachHang(){	
		int soLuong = ndService.getSoLuongNguoiDung();
		return ResponseEntity.ok(soLuong);
	}
	
	@PostMapping()
	public ResponseEntity<NguoiDung> restPostNguoiDung(@RequestBody NguoiDung nd ){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(ndService.existsById(nd.getMaNguoiDung())) {
			return ResponseEntity.badRequest().build();
		}
		nd.setMatKhau(encoder.encode(nd.getMatKhau()));
		ndService.save(nd);
		return ResponseEntity.ok(nd);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<NguoiDung> restPutNguoiDung(@RequestBody NguoiDung nd, @PathVariable("id") String id){
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		Optional<NguoiDung> nguoiDung = ndService.findById(id);
		if(nguoiDung.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		nd.setMatKhau(encoder.encode(nd.getMatKhau()));
		ndService.save(nd);
		return ResponseEntity.ok(nd);
	}
	
	@PutMapping("/Restore/{id}")
	public ResponseEntity<NguoiDung> restRestoreNguoiDung(@PathVariable("id") String maNd){
		Optional<NguoiDung> nd = ndService.findById(maNd);
		if(nd.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		NguoiDung nguoiDung = nd.get();
		nguoiDung.setTrangThai(true);
		ndService.save(nguoiDung);
		return ResponseEntity.ok(nguoiDung);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> restDeleteNguoiDung(@PathVariable("id") String id){
		Optional<NguoiDung> nd = ndService.findById(id);
		if(nd.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		NguoiDung nguoiDung = nd.get();
		nguoiDung.setTrangThai(false);
		ndService.save(nguoiDung);
		return ResponseEntity.ok().build();
	}
	
}
