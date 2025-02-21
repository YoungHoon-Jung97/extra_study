/*=======================
 * DepartmentDAO.java
 	-데이터베이스 액션 처리 클래스
 	-부서데이터 출력, 입력, 수정, 삭제 액션
 		→ IDepartmentDAO 인터페이스 implements
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

public class DepartmentDAO implements IDepartmentDAO
{
	//주요 속성 구성
		private DataSource dataSource;
		
		public void setDataSource(DataSource dataSource)
		{
			this.dataSource = dataSource;
		}

		@Override
		public ArrayList<Department> list() throws SQLException
		{
			ArrayList<Department> result = new ArrayList<Department>();
			
			Connection conn = dataSource.getConnection();
			
			Statement stmt = conn.createStatement();
			
			String sql="SELECT DEPARTMENTID, DEPARTMENTNAME FROM DEPARTMENT";
			
			ResultSet rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Department department = new Department();
				
				department.setDepartmentId(rs.getString("DEPARTMENTID"));
				department.setDepartmentName(rs.getString("DEPARTMENTNAME"));
				
				result.add(department);
				
			}
			
			rs.close();
			stmt.close();
			return result;
		}

		@Override
		public int add(Department department) throws SQLException
		{
			
			Connection conn = dataSource.getConnection();
			
			String sql = "INSERT INTO DEPARTMENT(DEPARTMENTID, DEPARTMENTNAME)"
					+ " VALUES(DEPARTMENTSEQ.NEXTVAL, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,department.getDepartmentName());
			
			int result = pstmt.executeUpdate();
			
			return result;
		}

		@Override
		public int remove(String departmentId) throws SQLException
		{
			Connection conn = dataSource.getConnection();
			
			String sql = "DELETE FROM DEPARTMENT WHERE DEPARTMENTID=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,departmentId);
			
			int result = pstmt.executeUpdate();
			
			return result;
		}

		@Override
		public int modify(Department department) throws SQLException
		{
			Connection conn = dataSource.getConnection();
			
			String sql = "UPDATE DEPARTMENT SET DEPARTMENTNAME=? WHERE DEPARTMENTID=?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,department.getDepartmentName());
			pstmt.setString(2,department.getDepartmentId());
			
			int result = pstmt.executeUpdate();
			
			return result;
		}
}
