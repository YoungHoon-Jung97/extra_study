/*=======================
 * RegionDAO.java
 	-데이터베이스 액션 처리 클래스
 	-지역데이터 출력, 입력, 수정, 삭제 액션
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

public class RegionDAO implements IRegionDAO
{

	//주요 속성 구성
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
	}
	
	@Override
	public ArrayList<Region> list() throws SQLException
	{
		ArrayList<Region> result = new ArrayList<Region>();
		
		Connection conn = dataSource.getConnection();
		
		Statement stmt = conn.createStatement();
		
		String sql="SELECT REGIONID, REGIONNAME FROM REGIONVIEW";
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next())
		{
			Region region = new Region();
			
			region.setRegionId(rs.getString("REGIONID"));
			region.setRegionName(rs.getString("REGIONNAME"));
			
			result.add(region);
			
		}
		
		rs.close();
		stmt.close();
		
		return result;
		
	}

	@Override
	public int add(Region region) throws SQLException
	{
		Connection conn = dataSource.getConnection();
		
		String sql = "INSERT INTO REGION(REGIONID, REGIONNAME)"
				+ " VALUES(REGIONSEQ.NEXTVAL, ?)";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,region.getRegionName());
		
		int result = pstmt.executeUpdate();
		
		return result;
	}

	@Override
	public int remove(String regionId) throws SQLException
	{
		Connection conn = dataSource.getConnection();
		
		String sql = "DELETE FROM REGION WHERE REGIONID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,regionId);
		
		int result = pstmt.executeUpdate();
		
		return result;
	}

	@Override
	public int modify(Region region) throws SQLException
	{
		Connection conn = dataSource.getConnection();
		
		String sql = "UPDATE REGION SET REGIONNAME=? WHERE REGIONID=?";
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1,region.getRegionName());
		pstmt.setString(2,region.getRegionId());
		
		int result = pstmt.executeUpdate();
		
		return result;
	}
	
}
