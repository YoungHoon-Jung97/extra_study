/*
 * MemberDAO.java
 	-데이터베이스 액션 처리 클래스
 	-IMemeberDAO 인터페이스를 구현하는 클래스
 	-Connection 객체에 대한 의존성 주입 준비
 		→ 인터페이스 자료형 속성 구성
 		→ setter 메소드 추가
 */

package com.test.mvc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.SimpleDriverDataSource;



public class MemberDAO implements IMemberDAO
{
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource)
	{
		this.dataSource = dataSource;
		

	}

	@Override
	public int add(MemberDTO member) throws SQLException
	{
		String sql = "INSERT INTO TBL_MEMBERLIST(MID,NAME,TELEPHONE) VALUES(MEMBERLISTSEQ.NEXTVAL,?,?)";
		
		Connection conn = dataSource.getConnection();
	
		
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, member.getName());
		pstmt.setString(2, member.getTelephone());
		
		int result = pstmt.executeUpdate();
		
		
		return result;
	}

	@Override
	public int count() throws SQLException
	{
		Connection conn = dataSource.getConnection();
		int result = 0;
		
		String sql = "SELECT COUNT(*) AS COUNT FROM TBL_MEMBERLIST";
		
		Statement stmt =  conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		if (rs.next())
		{
			result = rs.getInt("COUNT");
		}
		
		return result;
	}

	@Override
	public ArrayList<MemberDTO> list() throws SQLException
	{
		Connection conn = dataSource.getConnection();
		ArrayList<MemberDTO> result = new ArrayList<MemberDTO>();
		
		String sql ="SELECT MID, NAME, TELEPHONE FROM TBL_MEMBERLIST ORDER BY MID";
		
		Statement stmt = conn.createStatement();
		
		ResultSet rs = stmt.executeQuery(sql);
		
		while (rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setMid(rs.getInt("MID"));
			dto.setName(rs.getString("NAME"));
			dto.setTelephone(rs.getString("TELEPHONE"));
			
			result.add(dto);
		}
		
		return result;
	}
	
}
