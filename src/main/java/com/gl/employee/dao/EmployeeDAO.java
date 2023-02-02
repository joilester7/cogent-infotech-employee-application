package com.gl.employee.dao;

import com.gl.employee.model.Employee;

public interface EmployeeDAO 
{
	public void insert(Employee employee);
	
	public void update(int id);
	
	public void delete(int id);
	
	public void showAllEmployees();
}