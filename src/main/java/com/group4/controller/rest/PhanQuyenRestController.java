package com.group4.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.entity.PhanQuyen;
import com.group4.service.PhanQuyenService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/PhanQuyen")
public class PhanQuyenRestController {

	@Autowired
	PhanQuyenService pqService;
	
	@GetMapping("{maNd}")
	public List<PhanQuyen> restGetPhanQuyenByMaNd(@PathVariable("maNd") String maNd){
		return pqService.findAllByMaNguoiDung(maNd);
	}
	
	@PostMapping()
	public PhanQuyen restPostPhanQuyen(@RequestBody PhanQuyen pq) {
		return pqService.save(pq);
	}
	
	@DeleteMapping("{id}")
	public void restDeletePhanQuyen(@PathVariable("id") Integer id) {
		pqService.deleteById(id);
	}
}
