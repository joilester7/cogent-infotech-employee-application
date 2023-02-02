package com.gl.employee.dao.impl;

import java.util.ArrayList;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.gl.employee.dao.EmployeeDAO;
import com.gl.employee.model.Employee;

public class EmployeeDAOImpl implements EmployeeDAO
{

	private JdbcTemplate jdbcTemplate;
	Scanner sc = new Scanner(System.in);
	int choice = 0;

	public void setDataSource(DataSource dataSource)
	{
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void insert(Employee employee)
	{
		
		String sql = "INSERT INTO EMPLOYEE "
				+ "(EMPLOYEE_ID, EMP_NAME, AGE,SALARY) VALUES (?, ?, ?,?)";
		
		System.out.println("How many employees do you want to create?");
		choice = sc.nextInt();
		for(int i = 0; i < choice; i++) {
			System.out.println("Enter employee ID");
			employee.setEmployeeId(sc.nextInt());
			System.out.println("Enter employee name");
			employee.setName(sc.next());
			System.out.println("Enter employee age");
			employee.setAge(sc.nextInt());
			System.out.println("Enter employee salary");
			employee.setSalary(sc.nextInt());			

			jdbcTemplate.update(sql,
					new Object[] { employee.getEmployeeId(),
							employee.getName(), employee.getAge(),
							employee.getSalary() });
		}
		
		System.out.println("Employee record inserted successfully.");
	}
	
	public void update(int id) {
		
		String Update_EMPLOYEE_SQL_NAME = "update EMPLOYEE set EMP_NAME=? where EMPLOYEE_ID=?";
		String Update_EMPLOYEE_SQL_AGE = "update EMPLOYEE set AGE=? where EMPLOYEE_ID=?";
		String Update_EMPLOYEE_SQL_SALARY = "update EMPLOYEE set SALARY=? where EMPLOYEE_ID=?";
		
		System.out.println("1 - Update name");
		System.out.println("2 - Update age");
		System.out.println("3 - update salary");
		System.out.println("Please pick what you would like to update");
		choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			System.out.println("Please enter the updated employee name");
			String name = sc.next();
			jdbcTemplate.update(Update_EMPLOYEE_SQL_NAME,name, id);
			break;
		case 2:
			System.out.println("Please enter the updated employee age");
			int age = sc.nextInt();
			jdbcTemplate.update(Update_EMPLOYEE_SQL_AGE,age, id);
			break;
		case 3:
			System.out.println("Please enter the updated employee salary");
			int salary = sc.nextInt();
			jdbcTemplate.update(Update_EMPLOYEE_SQL_SALARY,salary, id);
			break;
		}
		
	}
	
	public void delete(int id) {
		String Delete_EMPLOYEE_BY_ID = "delete from EMPLOYEE where EMPLOYEE_ID=?";
		jdbcTemplate.update(Delete_EMPLOYEE_BY_ID,id);
	}
	
	public void showAllEmployees() {
		String SHOW_ALL = "select * from EMPLOYEE";
		ArrayList display = (ArrayList)jdbcTemplate.queryForList(SHOW_ALL);
		
		for(Object s: display) {
			System.out.println(s);
		}
	}
}
