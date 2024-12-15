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
import org.springframework.web.bind.annotation.RestController;

import com.group4.dao.NhaCungCapDAO;
import com.group4.entity.NhaCungCap;
import com.group4.entity.SanPham;
import com.group4.service.NhaCungCapService;
import com.group4.service.SanPhamService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/NhaCungCap")
public class NhaCungCapRestController {
	
	@Autowired
	NhaCungCapService nccService;
	
	@Autowired
	SanPhamService spService;
	
	@GetMapping()
	public ResponseEntity<Collection<NhaCungCap>> restGetAllNcc(){
		List<NhaCungCap> listNcc = nccService.findAll();
		if(listNcc.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listNcc);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<NhaCungCap> restGetNccById(@PathVariable("id") String id){
		Optional<NhaCungCap> ncc = nccService.findById(id);
		if(ncc.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ncc.get());
	}
	
	@PostMapping()
	public ResponseEntity<NhaCungCap> restPostNcc(@RequestBody NhaCungCap ncc){
		if(nccService.existsById(ncc.getMaNhaCungCap())) {
			return ResponseEntity.badRequest().build();
		}
		nccService.save(ncc);
		return ResponseEntity.ok(ncc);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<NhaCungCap> restPutNcc(@RequestBody NhaCungCap ncc, @PathVariable("id") String id){
		if(!nccService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		nccService.save(ncc);
		return ResponseEntity.ok(ncc);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> restDeleteNcc(@PathVariable("id") String id){
		if(!nccService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		List<SanPham> listSp = spService.findAll();
		listSp.stream().filter(sp -> sp.getMaNcc().equalsIgnoreCase(id)).forEach(sp -> sp.setMaNcc("NCC000"));
		nccService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
