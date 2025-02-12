/*=================================================
   OracleDAO.java
   - DAO 역할
   - MssqlDAO 와 설정만 다르게 연결하여 실습 진행 
   - IDAO 인터페이스를 구현하는 클래스               ※ check 
==================================================*/

package com.test.spr;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OracleDAO implements IDAO
{
	@Override
	public List<MemberDTO> list() throws ClassNotFoundException, SQLException
	{
		List<MemberDTO> result = new ArrayList<MemberDTO>();
		
		// 데이터베이스 연결
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection conn = DriverManager.getConnection
							("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");
		
		//쿼리문 준비 및 실행
		String sql = "SELECT ID, NAME, TEL, EMAIL FROM TBL_MEMBERLIST";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// 결과 집합을 컬렉션에 추가
		while(rs.next())
		{
			MemberDTO dto = new MemberDTO();
			
			dto.setId(rs.getString("ID"));
			dto.setName(rs.getString("NAME"));
			dto.setTel(rs.getString("TEL"));
			dto.setEmail(rs.getString("EMAIL"));
			
			result.add(dto);
		}
		
		//리소스 반납
		rs.close();
		stmt.close();
		
		// 데이터베이스 연결 종료
		if (!conn.isClosed())
			conn.close();
		
		// 최종 결과값 반환
		return result;
	}

}