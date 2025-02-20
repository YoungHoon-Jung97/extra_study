/*
 ============================
 * IEmployeeDAO.java
 	-인터페이스
 ============================*/

package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.aop.ThrowsAdvice;

public interface IEmployeeDAO
{
	public ArrayList<Employee> list() throws SQLException;
	
	public ArrayList<Region> regionList() throws SQLException;
	
	public ArrayList<Department> departmentList() throws SQLException;
	
	public ArrayList<Position> positionList() throws SQLException;
	
	public int getMinBasicpay(String positionId) throws SQLException;
	
	public int add(Employee employee) throws SQLException;
	public int remove(String employeeId) throws SQLException;
	public int modify(Employee employee) throws SQLException;
	
	public Employee searchId(String employeeId) throws SQLException;
	
	public String login(String id, String pw)throws SQLException;
	public String loginAdmin(String id, String pw)throws SQLException;
	
	
	
	
}
