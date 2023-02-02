package com.gl.core;

import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.gl.employee.dao.EmployeeDAO;
import com.gl.employee.model.Employee;

public class App
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"applicationContext.xml");

		EmployeeDAO employeeDAO = (EmployeeDAO) context
				.getBean("employeeDAO");
		
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		do {
			System.out.println("1 - Create an employee");
			System.out.println("2 - Update an employee");
			System.out.println("3 - Delete an employee");
			System.out.println("4 - Show all employees");
			System.out.println("5 - Exit");
			System.out.println("Please enter a choice");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
				Employee employee = new Employee();
				employeeDAO.insert(employee);
				break;
			case 2:
				System.out.println("Please enter employee's ID");
				employeeDAO.update(sc.nextInt());
				break;
			case 3:
				System.out.println("Please enter employee's ID");
				employeeDAO.delete(sc.nextInt());
				break;
			case 4:
				employeeDAO.showAllEmployees();
				break;
			}
		} while(choice != 5);
		
	}
}
