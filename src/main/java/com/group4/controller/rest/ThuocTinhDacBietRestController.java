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

import com.group4.dao.ThuocTinhDacBietDAO;
import com.group4.entity.SanPham;
import com.group4.entity.ThuocTinhDacBiet;
import com.group4.service.SanPhamService;
import com.group4.service.ThuocTinhDacBietService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ThuocTinhDacBiet")
public class ThuocTinhDacBietRestController {

	@Autowired
	ThuocTinhDacBietService ttdbService;
	
	@Autowired
	SanPhamService spService;
	
	@GetMapping()
	public ResponseEntity<Collection<ThuocTinhDacBiet>> restGetAllTtdb(){
		List<ThuocTinhDacBiet> listTtdb = ttdbService.findAll();
		if(listTtdb.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listTtdb);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ThuocTinhDacBiet> restGetTtdbById(@PathVariable("id") String id){
		Optional<ThuocTinhDacBiet> ttdb = ttdbService.findById(id);
		if(ttdb.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(ttdb.get());
	}
	
	@PostMapping()
	public ResponseEntity<ThuocTinhDacBiet> restPostTtdb(@RequestBody ThuocTinhDacBiet ttdb){
		if(ttdbService.existsById(ttdb.getMaThuocTinhDacBiet())) {
			return ResponseEntity.badRequest().build();
		}
		ttdbService.save(ttdb);
		return ResponseEntity.ok(ttdb);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<ThuocTinhDacBiet> restPutTtdb(@RequestBody ThuocTinhDacBiet ttdb, @PathVariable("id") String id){
		if(!ttdbService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		ttdbService.save(ttdb);
		return ResponseEntity.ok(ttdb);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> restDeleteTtdb(@PathVariable("id") String id){
		if(!ttdbService.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		List<SanPham> listSp = spService.findAll();
		listSp.stream().filter(sp -> sp.getMaTtdb().equalsIgnoreCase(id)).forEach(sp -> sp.setMaTtdb("TTDB000"));
		ttdbService.deleteById(id);
		return ResponseEntity.ok().build();
	}
	
}
