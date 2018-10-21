package com.example.training.mapper.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.training.mapper.EmployeeMapper;
import com.example.training.model.Employees;


@Service
public class EmployeeServiceImpl implements EmployeeMapper {

	@Autowired
	private EmployeeMapper empmapper;

	@Override
	public void save(Employees emp) {
		this.empmapper.save(emp);
		
	}

	@Override
	public void delete(Long id) {
		this.empmapper.delete(id);
		
	}

	@Override
	public void update(Employees emp) {
		this.update(emp);;
	
	}

	@Override
	public Employees find(Long id) {
		return this.empmapper.find(id);
		
	}

	@Override
	public List<Employees> findAll() {
		return empmapper.findAll();
	}

}
