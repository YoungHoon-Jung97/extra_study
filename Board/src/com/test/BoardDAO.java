package com.test;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.REUtil;

public class BoardDAO
{
   private Connection conn;
   
   public BoardDAO(Connection conn)
   {
      this.conn =conn;
   }
   
   public int getMaxNum() throws SQLException
   {
      int result = 0;
      String sql = "SELECT NVL(MAX(NUM),0) AS MAXNUM FROM TBL_BOARD";
      
      PreparedStatement ptmt = conn.prepareStatement(sql);
      
      ResultSet rs = ptmt.executeQuery();
      
      while(rs.next())
      {
         result = rs.getInt("MAXNUM");
      }
      
      rs.close();
      ptmt.close();
      
      return result;
   }
   
   public int insertData(BoardDTO dto) throws SQLException
   {
      int result = 0;
      
      String sql = "INSERT INTO TBL_BOARD(NUM, NAME, PWD, EMAIL, SUBJECT, CONTENT, IPADDR, HITCOUNT, CREATED)"
            + " VALUES(?, ?, ?, ?, ?, ?, ?, 0, SYSDATE)";
      
      PreparedStatement ptmt = conn.prepareStatement(sql);
      
      ptmt.setInt(1, dto.getNum());
      ptmt.setString(2, dto.getName());
      ptmt.setString(3, dto.getPwd());
      ptmt.setString(4, dto.getEmail());
      ptmt.setString(5, dto.getSubject());
      ptmt.setString(6, dto.getContent());
      ptmt.setString(7, dto.getIPAddr());
      
      result = ptmt.executeUpdate();   
      
      ptmt.close();
      
      return result;
   }
   
	/*
	 * public int getDataCount() throws SQLException { int result = 0;
	 * 
	 * String sql = "SELECT COUNT(*) AS COUNT FROM TBL_BOARD";
	 * 
	 * PreparedStatement ptmt = conn.prepareStatement(sql);
	 * 
	 * ResultSet rs = ptmt.executeQuery();
	 * 
	 * if (rs.next()) result = rs.getInt("COUNT");
	 * 
	 * rs.close(); ptmt.close();
	 * 
	 * return result;
	 * 
	 * }
	 */
   
   
  //검색기능을 추가하며 메소드 수정 
   public int getDataCount(String searchKey ,String searchValue) throws SQLException
   {
      int result = 0;
      
      String sql = "SELECT COUNT(*) AS COUNT"
      		+ " FROM TBL_BOARD"
      		+ " WHERE "+searchKey+ " LIKE ?";
      
      searchValue = "%" + searchValue + "%";
      
      PreparedStatement ptmt = conn.prepareStatement(sql);
      
      ptmt.setString(1, searchValue);
      
      ResultSet rs = ptmt.executeQuery();
      
      if (rs.next())
         result = rs.getInt("COUNT");
      
      rs.close();
      ptmt.close();
      
      return result;
      
   }
   
   
   
   
	/*
	 * public List<BoardDTO> getLists(int start, int end) throws SQLException {
	 * List<BoardDTO> result = new ArrayList<BoardDTO>();
	 * 
	 * String sql = "SELECT NUM, NAME, SUBJECT, HITCOUNT, CREATED" +
	 * " FROM ( SELECT ROWNUM RNUM, DATA.*" +
	 * " FROM (SELECT NUM, NAME, SUBJECT, HITCOUNT, TO_CHAR(CREATED, 'YYYY-MM-DD') AS CREATED"
	 * + " FROM TBL_BOARD ORDER BY NUM DESC) DATA)" +
	 * " WHERE RNUM>=? AND RNUM <= ?";
	 * 
	 * PreparedStatement ptmt = conn.prepareStatement(sql);
	 * 
	 * ptmt.setInt(1, start); ptmt.setInt(2, end);
	 * 
	 * ResultSet rs = ptmt.executeQuery();
	 * 
	 * while (rs.next()) { BoardDTO dto = new BoardDTO();
	 * 
	 * dto.setNum(rs.getInt("NUM")); dto.setName(rs.getString("NAME"));
	 * dto.setSubject(rs.getString("SUBJECT"));
	 * dto.setHitcount(rs.getInt("HITCOUNT"));
	 * dto.setCreated(rs.getString("CREATED"));
	 * 
	 * result.add(dto); }
	 * 
	 * rs.close(); ptmt.close();
	 * 
	 * return result; }
	 */
   
   public List<BoardDTO> getLists(int start, int end ,String searchKey, String searchValue) throws SQLException
   {
      List<BoardDTO> result = new ArrayList<BoardDTO>();
      
      String sql = "SELECT NUM, NAME, SUBJECT, HITCOUNT, CREATED"
            + " FROM ( SELECT ROWNUM RNUM, DATA.*"
            + " FROM "
            + " ("
            + " SELECT NUM, NAME, SUBJECT, HITCOUNT, TO_CHAR(CREATED, 'YYYY-MM-DD') AS CREATED"
            + " FROM TBL_BOARD "
            + "	WHERE "+searchKey+ " LIKE ?"
            + "	ORDER BY NUM DESC"
            + " ) DATA)"
            + " WHERE RNUM>=? AND RNUM <= ?";
      
      PreparedStatement ptmt = conn.prepareStatement(sql);
      
      searchValue = "%" + searchValue + "%";
      
      ptmt.setString(1,searchValue);
      ptmt.setInt(2, start);
      ptmt.setInt(3, end);

      ResultSet rs = ptmt.executeQuery();
      
      while (rs.next())
      {
         BoardDTO dto = new BoardDTO();
         
         dto.setNum(rs.getInt("NUM"));
         dto.setName(rs.getString("NAME"));
         dto.setSubject(rs.getString("SUBJECT"));
         dto.setHitcount(rs.getInt("HITCOUNT"));
         dto.setCreated(rs.getString("CREATED"));
         
         result.add(dto);
      }
      
      rs.close();
      ptmt.close();
      
      return result;
   }
   
   public int updateHitCount(int num) throws SQLException
   {
      int result = 0;
      
      String sql = "UPDATE TBL_BOARD SET HITCOUNT= HITCOUNT + 1 WHERE NUM=?";
      
      PreparedStatement ptmt = conn.prepareStatement(sql);
      
      ptmt.setInt(1, num);
      
      result = ptmt.executeUpdate();
      
      ptmt.close();
      
      return result;
   }
   
   public BoardDTO getReadData(int num)
   {
      BoardDTO dto = null;
      
      PreparedStatement ptmt = null;
      ResultSet rs = null;
      String sql = "";
      
      try
      {
         sql = "SELECT NUM, NAME, PWD, EMAIL,SUBJECT, CONTENT, IPADDR,"
               + " HITCOUNT,TO_CHAR(CREATED, 'YYYY-MM-DD') AS CREATED"
               + " FROM TBL_BOARD"
               + " WHERE NUM = ?";
         
         ptmt = conn.prepareStatement(sql);
         
         ptmt.setInt(1, num);
         
         rs = ptmt.executeQuery();
         
         if(rs.next())
         {
            dto = new BoardDTO();
            
            dto.setNum(rs.getInt("NUM"));
            dto.setName(rs.getString("NAME"));
            dto.setPwd(rs.getString("PWD"));
            dto.setEmail(rs.getString("EMAIL"));
            dto.setSubject(rs.getString("SUBJECT"));
            dto.setContent(rs.getString("CONTENT"));
            dto.setIPAddr(rs.getString("IPADDR"));
            dto.setHitcount(rs.getInt("HITCOUNT"));
            dto.setCreated(rs.getString("CREATED"));
         }
         
         rs.close();
         ptmt.close();
         
      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      
      return dto;
   }
   
   
   public int deleteData(int num)
   {
      int result = 0;
      
      PreparedStatement pstmt = null;
      String sql= "";
      
      try
      {
         sql = "DELETE FROM TBL_BOARD WHERE NUM = ?";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, num);
         
         result = pstmt.executeUpdate();
         
         pstmt.close();
         
      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      return result;
   }
   
   public int updateData(BoardDTO dto)
   {
      int result = 0;
      
      PreparedStatement pstmt = null;
      String sql = "";
      
      try
      {   
         sql = "UPDATE TBL_BOARD SET NAME = ?, PWD= ? ,"
               + " EMAIL= ? , SUBJECT = ?,"
               + " CONTENT = ? "
               + " WHERE NUM=?";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setString(1, dto.getName());
         pstmt.setString(2, dto.getPwd());
         pstmt.setString(3, dto.getEmail());
         pstmt.setString(4, dto.getSubject());
         pstmt.setString(5, dto.getContent());
         pstmt.setInt(6, dto.getNum());
         
         result = pstmt.executeUpdate();
         
         pstmt.close();
         
      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      
      return result;
   }
   
   public int getBeforeNum(int num)
   {
      int result = 0;
      
      PreparedStatement pstmt = null;
      String sql ="";
      ResultSet rs =null;
      
      try
      {
         sql = "SELECT NVL(MAX(NUM),-1) BEFORENUM FROM TBL_BOARD WHERE NUM<?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, num);
         rs = pstmt.executeQuery();
         
         while(rs.next())
         {
            result = rs.getInt("BEFORENUM");
         }

      } catch (Exception e)
      {
         System.out.println(e.toString());
      }
      
      return result;
   }
   
   public int getNextNum(int num)
   {
      int result = 0;
      
      PreparedStatement pstmt = null;
      ResultSet rs = null;
      String sql = "";
      
      try
      {
         sql = "SELECT NVL(MIN(NUM), -1) AS NEXTNUM"
               + " FROM TBL_BOARD"
               + " WHERE NUM>?";
         
         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, num);
         
         rs = pstmt.executeQuery();
         
         if (rs.next())
            result = rs.getInt("NEXTNUM");
         
         
      }
      catch(Exception e)
      {
         System.out.println(e.toString());
      }
      
      return result;
   
   }
}
