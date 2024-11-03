package com.group4.service.imp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.group4.dao.SanPhamDAO;
import com.group4.entity.SanPham;
import com.group4.service.SanPhamService;
import com.group4.specification.SanPhamSpecification;

@Service
public class SanPhamServiceImp implements SanPhamService{

	@Autowired
	SanPhamDAO spDao;
	
	@Autowired
	SanPhamSpecification spSpec;
	
	@Override
	public List<SanPham> findAll() {
		return spDao.findAll();
	}

	@Override
	public Optional<SanPham> findById(String id) {
		return spDao.findById(id);
	}
	
	@Override
	public SanPham save(SanPham sp) {
		return spDao.save(sp);
	}

	@Override
	public void deleteById(String id) {
		spDao.deleteById(id);
	}

	@Override
	public List<SanPham> filterSanPham(String tenSanPham, String maCl) {
		Specification<SanPham> spec = Specification.where(null);  //Bắt đầu với điều kiện null 
		spec = spec.and(spSpec.hasTenSanPham(tenSanPham));
		spec = spec.and(spSpec.hasChungLoai(maCl));
		return spDao.findAll(spec);
	}

	@Override
	public boolean existsById(String id) {
		return spDao.existsById(id);
	}

	@Override
	public int count() {
		return (int)spDao.count();
	}

}
