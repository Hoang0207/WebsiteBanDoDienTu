package com.group4.controller.rest;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.group4.dao.DonHangDAO;
import com.group4.entity.DonHang;
import com.group4.entity.NguoiDung;
import com.group4.service.DonHangService;
import com.group4.service.MailerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/DonHang")
public class DonHangRestController {
	
	@Autowired
	DonHangService dhService;
	
	@Autowired
	MailerService mailService;
	
	@GetMapping()
	public ResponseEntity<Collection<DonHang>> restGetAllDh(){
		List<DonHang> listDh = dhService.findAll();
		if(listDh.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listDh);
	}
	
	@GetMapping("/IsActive/{status}")
	public ResponseEntity<Collection<DonHang>> restGetAllDonHangByIsActive(@PathVariable("status") Boolean status){
		List<DonHang> listDh = dhService.findAllByIsActive(status);
		return ResponseEntity.ok(listDh);
	}
	
	@GetMapping("SoLuong")
	public ResponseEntity<Integer> restGetSoLuongDh(){
		int soLuong = dhService.count();
		return ResponseEntity.ok(soLuong);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<DonHang> restGetDhById(@PathVariable("id") Integer id){
		Optional<DonHang> dh =dhService.findById(id);
		if(dh.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(dh.get());
	}
	
	@GetMapping("/TrangThai/{x}")
	public ResponseEntity<Collection<DonHang>> restGetDhByTrangThai(@PathVariable("x") String x){
		List<DonHang> listDh = dhService.findAllByTrangThai(x);
		if(listDh.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		//Lọc ra những đơn hàng không bị xóa
		List<DonHang> activeListDh = listDh.stream().filter(dh -> dh.getIsActive()==true).collect(Collectors.toList());
		return ResponseEntity.ok(activeListDh);
	}
	
	@GetMapping("/Order")
	public ResponseEntity<DonHang> restOrder(@RequestParam String diaChi){
		DonHang dh = dhService.order(diaChi);
		if(dh == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(dh);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<DonHang> restPutDh(@PathVariable("id") Integer id, @RequestBody DonHang dh){
		if(!dhService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		dhService.save(dh);
		//Gửi mail
		String body = "<div style='font-size: 13px; color: #000000;' !important>"
						+ "<p style='color: #000000;' !important>Kính chào khách hàng <strong>"+dh.getNguoiDung().getHoTen()+"</strong>.</p>"
						+ "<p style='color: #000000;' !important>Quý khách có đơn hàng có mã là <strong>"+dh.getMaDonHang()+"</strong>, địa chỉ nhận hàng là <strong>"+dh.getDiaChiGiao()+"</strong>.</p>"
						+ "<p style='color: #000000;' !important>Hiện đơn hàng đang trong trạng thái: <strong>"+dh.getTrangThai()+"</strong>.</p>"
						+ "<p style='color: #000000;' !important>Cảm ơn quý khách đã tin tưởng cửa hàng điện máy HTV.</p>"
						+ "</div>";
		mailService.push(dh.getNguoiDung().getEmail(),"Cập nhật trạng thái đơn hàng",body);
		return ResponseEntity.ok(dh);
	}
	
	@PutMapping("/Restore/{id}")
	public ResponseEntity<DonHang> restRestoreDh(@PathVariable("id") Integer id){
		if(!dhService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		DonHang dh = dhService.findById(id).get();
		dh.setIsActive(true);
		dhService.save(dh);
		return ResponseEntity.ok(dh);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> restDeleteDh(@PathVariable("id") Integer id){
		if(!dhService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		DonHang dh = dhService.findById(id).get();
		dh.setIsActive(false);
		dhService.save(dh);
		return ResponseEntity.ok().build();
	}
	
}
