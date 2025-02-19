/*=======================
 * EmployeeDAO.java
 	-데이터베이스 액션 처리 클래스
 	-직원데이터 출력, 입력, 수정, 삭제 액션
 		→ IEmployeeDAO 인터페이스 implements
 	-Connection 객체에 대한 의존성 주입을 위한 준비
 	→ 인터페이스 형태의 속성 구성(DataSource)
 	→ setter 메소드 형태
=======================*/

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

public class EmployeeDAO implements IEmployeeDAO
{
	//주요 속성 구성
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}

	@Override
	public ArrayList<Employee> list() throws SQLException
	{
		ArrayList<Employee> result = new ArrayList<Employee>();
		
		Connection conn = dataSource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String sql="SELECT EMPLOYEEID, NAME"
				+ ", SSN, BIRTHDAY, LUNAR"
				+ ", LUNARNAME, TELEPHONE"
				+ ", DEPARTMENTID, DEPARTMENTNAME"
				+ ", POSITIONID, POSITIONNAME, REGIONID"
				+ ", REGIONNAME, BASICPAY, EXTRAPAY"
				+ ", PAY, GRADE"
				+ " FROM EMPLOYEEVIEW"
				+"	ORDER BY EMPLOYEEID";
			
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			Employee employee =new Employee();
			
			employee.setEmployeeId(rs.getString("EMPLOYEEID"));
			employee.setName(rs.getString("NAME"));
			employee.setSsn(rs.getString("SSN"));
			employee.setBirthday(rs.getString("BIRTHDAY"));
			employee.setLunar(rs.getInt("LUNAR"));
			employee.setLunarName(rs.getString("LUNARNAME"));
			employee.setTelephone(rs.getString("TELEPHONE"));
			employee.setDepartmentId(rs.getString("DEPARTMENTID"));
			employee.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			employee.setPositionId(rs.getString("POSITIONID"));
			employee.setPositionName(rs.getString("POSITIONNAME"));
			employee.setRegionId(rs.getString("REGIONID"));
			employee.setRegionName(rs.getString("REGIONNAME"));
			employee.setBasicPay(rs.getInt("BASICPAY"));
			employee.setExtraPay(rs.getInt("EXTRAPAY"));
			employee.setPay(rs.getInt("PAY"));
			employee.setGrade(rs.getInt("GRADE"));
			
			result.add(employee);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}

	@Override
	public ArrayList<Region> regionList() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		Connection conn = dataSource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String sql="SELECT REGIONID, REGIONNAME, DELCHECK FROM REGIONVIEW";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			Region region = new Region();
			
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			region.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(region);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
	}

	@Override
	public ArrayList<Department> departmentList() throws SQLException
	{
		ArrayList<Department> result = new ArrayList<Department>();
		
		Connection conn = dataSource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String sql="SELECT DEPARTMENTID, DEPARTMENTNAME, DELCHECK FROM DEPARTMENTVIEW";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			Department department = new Department();
			
			department.setDepartmentId(rs.getString("DEPARTMENTID"));
			department.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			department.setDelCheck(rs.getInt("DELCHECK"));
			
			result.add(department);
			
		}
		
		rs.close();
		stmt.close();
		return result;
	}

	@Override
	public ArrayList<Position> positionList() throws SQLException
	{
		ArrayList<Position> result = new ArrayList<Position>();
		
		Connection conn = dataSource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String sql="SELECT POSITIONID, POSITIONNAME, MINBASICPAY,DELCHECK FROM POSITIONVIEW";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			Position position = new Position();
			
			position.setPositionId(rs.getString("POSITIONID"));
			position.setPositionName(rs.getString("POSITIONNAME"));
			position.setDelCheck(rs.getInt("DELCHECK"));
			position.setMinBasicPay(rs.getInt("MINBASICPAY"));
			result.add(position);
			
		}
		
		rs.close();
		stmt.close();
		
		return null;
	}

	@Override
	public int getMinBasicpay(String positionId) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		
		
		String sql="SELECT MINBASICPAY FROM POSITION WHERE POSITIONID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, positionId);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
		{
			
			result = rs.getInt("MINBASICPAY");
		}
		
		return result;
	}

	@Override
	public int add(Employee employee) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		
		
		String sql="INSERT INTO EMPLOYEE"
				+ "(EMPLOYEEID, NAME, SSN"
				+ ", BIRTHDAY, LUNAR, TELEPHONE"
				+ ", DEPARTMENTID, POSITIONID"
				+ ", REGIONID, BASICPAY, EXTRAPAY)"
				+ " VALUES(EMPLOYEESEQ.NEXTVAL"
				+ ", ?, CRYPTPACK.ENCRYPT(?,?)"
				+ ", TO_DATE(?, 'YYYY-MM-DD')"
				+ ", ?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		pstmt.setString(1, employee.getName());
		pstmt.setString(2, employee.getSsn1());
		pstmt.setString(3, employee.getSsn2());
		pstmt.setString(4, employee.getSsn2());
		pstmt.setString(5, employee.getBirthday());
		pstmt.setInt(6, employee.getLunar());
		pstmt.setString(7, employee.getTelephone());
		pstmt.setString(8, employee.getDepartmentId());
		pstmt.setString(9, employee.getPositionId());
		pstmt.setString(10, employee.getRegionId());
		pstmt.setInt(11, employee.getBasicPay());
		pstmt.setInt(12, employee.getExtraPay());
		
		result =pstmt.executeUpdate();
		
		return result;
	}

	@Override
	public int remove(String employeeId) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		String sql= "DELETE" + 
				"FROM EMPLOYEE" + 
				"WHERE EMPLOYEEID= ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,employeeId);
		
		result = pstmt.executeUpdate();
		
		return result;
	}

	@Override
	public int modify(Employee employee) throws SQLException
	{
		int result = 0;
		Connection conn = dataSource.getConnection();
		String sql= "UPDATE EMPLOYEE SET NAME=?"
				+ ", BIRTHDAY=TO_DATE(?, 'YYYY-MM-DD')"
				+ ", LUNAR=?, TELEPHONE=?"
				+ ", DEPARTMENTID=?, POSITIONID=?"
				+ ", REGIONID=?, BASICPAY=?"
				+ ", EXTRAPAY=?, SSN1=?"
				+ ", SSN2=CRYPTPACK.ENCRYPT(?, ?)"
				+ ", WHERE EMPLOYEEID=?" ;
		
		PreparedStatement pstmt = 	conn.prepareStatement(sql);
		

		pstmt.setString(1,employee.getName());
		pstmt.setString(2,employee.getBirthday());
		pstmt.setInt(3,employee.getLunar());
		pstmt.setString(4,employee.getTelephone());
		pstmt.setString(5,employee.getDepartmentId());
		pstmt.setString(6,employee.getPositionId());
		pstmt.setString(7,employee.getRegionId());
		pstmt.setInt(8,employee.getBasicPay());
		pstmt.setInt(9,employee.getExtraPay());
		pstmt.setString(10,employee.getSsn1());
		pstmt.setString(11,employee.getSsn2());
		pstmt.setString(12,employee.getSsn2());
		pstmt.setString(13,employee.getEmployeeId());
		
		
		result = pstmt.executeUpdate();
		
		
		return result;
	}

	@Override
	public Employee searchId(String employeeId) throws SQLException
	{	
		Employee result = new Employee();
		
		Connection conn = dataSource.getConnection();
		String sql= "SELEC" + 
				"FROM EMPLOYEE" + 
				"WHERE EMPLOYEEID= ?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,employeeId);
		
		ResultSet rs = pstmt.executeQuery();
		
		if(rs.next())
		{
			
			result.setEmployeeId(rs.getString("EMPLOYEEID"));
			result.setName(rs.getString("NAME"));
			result.setSsn(rs.getString("SSN1"));
			result.setSsn(rs.getString("SSN2"));
			result.setBirthday(rs.getString("BIRTHDAY"));
			result.setLunar(rs.getInt("LUNAR"));
			result.setTelephone(rs.getString("TELEPHONE"));
			result.setDepartmentId(rs.getString("DEPARTMENTID"));
			result.setPositionId(rs.getString("POSITIONID"));
			result.setRegionId(rs.getString("REGIONID"));
			result.setBasicPay(rs.getInt("BASICPAY"));
			result.setExtraPay(rs.getInt("EXTRAPAY"));
			result.setPay(rs.getInt("PAY"));
			result.setGrade(rs.getInt("GRADE"));
		}
		
		
		return result;
	}

	@Override
	public String login(String id, String pw) throws SQLException
	{
		String result = null;
		Connection conn = dataSource.getConnection();
		String sql= "SELECT NAME" + 
				"FROM EMPLOYEE" + 
				"WHERE EMPLOYEEID = ?" + 
				"  AND SSN2 = CRYPTPACK.ENCRYPT(?, ?)" + 
				"  AND GRADE =1";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,id);
		pstmt.setString(2,pw);
		pstmt.setString(3,pw);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
		{
			result = rs.getString("NAME");
		}
		
		return result;
	}

	@Override
	public String loginAdmin(String id, String pw) throws SQLException
	{
		String result = null;
		Connection conn = dataSource.getConnection();
		String sql= "SELECT NAME" + 
				"FROM EMPLOYEE" + 
				"WHERE EMPLOYEEID = ?" + 
				"  AND SSN2 = CRYPTPACK.ENCRYPT(?, ?)" + 
				"  AND GRADE =0";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,id);
		pstmt.setString(2,pw);
		pstmt.setString(3,pw);
		
		ResultSet rs = pstmt.executeQuery();
		
		if (rs.next())
		{
			result = rs.getString("NAME");
		}
		
		return result;
	}
	
	
}
