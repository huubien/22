package com.example.training.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.training.model.Employees;

@Mapper
public interface EmployeeMapper {
	
	void save(Employees emp);
	
	void delete(Long id);
	
	void update(Employees emp);
	
	Employees find(Long empId);
	
	List<Employees> findAll();
	
}