package com.group4.controller.rest;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group4.entity.VaiTro;
import com.group4.service.VaitroService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/VaiTro")
public class VaiTroRestController {

	@Autowired
	VaitroService vtService;
	
	@GetMapping()
	public ResponseEntity<Collection<VaiTro>> restGetAllVaiTro(){
		List<VaiTro> listVt = vtService.findAll();
		if(listVt.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listVt);
	}
	
}
