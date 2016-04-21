package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.GuestBookVo;

public class GuestBookDao 
{
	private DBConnection dbConnection;
	
	public GuestBookDao(DBConnection dbConnection)
	{
		this.dbConnection = dbConnection;
	}
	
	public GuestBookVo get(Long no)
	{
		GuestBookVo vo = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try
		{
			vo = new GuestBookVo();
			conn = dbConnection.getConnection();
			
			String sql = "select no, name, reg_date, message, passwd from guestbook where no= ?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				Long no1 = rs.getLong(1);
				String name = rs.getString(2);
				String reg_date = rs.getString(3);
				String message = rs.getString(4);
				String password = rs.getString(5);
				
				vo.setNo(no1);
				vo.setName(name);
				vo.setRegDate(reg_date);
				vo.setMessage(message);
				vo.setPassword(password);
			}
			
			return vo;
		}
		catch(SQLException ex)
		{
			System.out.println("error" + ex);
			return null;
		}
		finally
		{
			try
			{
				if( pstmt != null)
				{
					pstmt.close();
				}
				if( conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public Long add(GuestBookVo vo)
	{
		Long no = 0L;
		Connection conn = null;
		PreparedStatement pstmt = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbConnection.getConnection();
			
			String sql = "insert into guestbook values(null, ?, now(), ?, password(?))";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getMessage());
			pstmt.setString(3, vo.getPassword());
			pstmt.executeUpdate();
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select last_insert_id()");  // 프라이머리 키 가져옴
			if (rs.next())
			{
				no = rs.getLong(1);
			}
			
			return no;
		}
		catch(SQLException ex)
		{
			System.out.println("error" + ex);
			return 0L;
		}
		finally
		{
			try
			{
				if( rs != null)
				{
					rs.close();
				}
				if( stmt != null)
				{
					stmt.close();
				}
				if( pstmt != null)
				{
					pstmt.close();
				}
				if( conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public void delete(Long no, String password)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = dbConnection.getConnection();
			
			String sql = "delete from guestbook where no = ? and passwd = password (?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);
			pstmt.setString(2, password);
			
			pstmt.executeUpdate();
	
		}
		catch(SQLException ex)
		{
			System.out.println("error" + ex);
		}
		finally
		{
			try
			{
				if( pstmt != null)
				{
					pstmt.close();
				}
				if( conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	
	public List<GuestBookVo> getList()
	{
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select no, name, date_format(reg_date, '%Y-%m-%d %h:%i:%s') as '등록날짜', message from guestbook order by no desc";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String regDate = rs.getString(3);
				String message = rs.getString(4);
				
				GuestBookVo vo = new GuestBookVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setRegDate(regDate);
				vo.setMessage(message);
				
				list.add(vo);
			}
		}
		catch(SQLException ex)
		{
			System.out.println("error" + ex);
		}
		finally
		{
			try
			{
				if( rs != null)
				{
					rs.close();
				}
				if( stmt != null)
				{
					stmt.close();
				}
				if( conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return list;
	}
	
	public List<GuestBookVo> getList(int page)  // 페이지넘버를 받아 데이터를 가져오는 오버로딩
	{
		List<GuestBookVo> list = new ArrayList<GuestBookVo>();
		Connection conn = null;
		Statement stmt = null;  // 여기서는 preparestatement 써도 됨. 
		ResultSet rs = null;
		
		try
		{
			conn = dbConnection.getConnection();
			stmt = conn.createStatement();
			
			String sql = "select no, name, date_format(reg_date, '%Y-%m-%d %h:%i:%s') as '등록날짜', message from guestbook order by no desc limit " + (page-1)*5 + ",5";
			
			rs = stmt.executeQuery(sql);
			
			while(rs.next())
			{
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String regDate = rs.getString(3);
				String message = rs.getString(4);
				
				GuestBookVo vo = new GuestBookVo();
				
				vo.setNo(no);
				vo.setName(name);
				vo.setRegDate(regDate);
				vo.setMessage(message);
				
				list.add(vo);
			}
		}
		catch(SQLException ex)
		{
			System.out.println("error" + ex);
		}
		finally
		{
			try
			{
				if( rs != null)
				{
					rs.close();
				}
				if( stmt != null)
				{
					stmt.close();
				}
				if( conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException ex)
			{
				ex.printStackTrace();
			}
		}
		return list;
	}
}
