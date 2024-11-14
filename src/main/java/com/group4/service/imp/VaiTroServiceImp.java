package com.group4.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group4.dao.VaiTroDAO;
import com.group4.entity.VaiTro;
import com.group4.service.VaitroService;

@Service
public class VaiTroServiceImp implements VaitroService{

	@Autowired
	VaiTroDAO vtDAO;
	
	@Override
	public List<VaiTro> findAll() {
		return vtDAO.findAll();
	}

}
