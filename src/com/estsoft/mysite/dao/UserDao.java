package com.estsoft.mysite.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.estsoft.db.DBConnection;
import com.estsoft.mysite.vo.UserVo;

public class UserDao 
{
	private DBConnection dbConnection;
	
	public UserDao(DBConnection dbConnection)
	{
		this.dbConnection = dbConnection;
	}
	
	public UserVo get(String email)
	{
		UserVo vo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{	
			conn = dbConnection.getConnection();
			
			String sql = "select no, email From user Where email=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, email);
			rs = pstmt.executeQuery();
			
			if(rs.next())
			{
				vo = new UserVo();
				vo.setNo(rs.getLong(1));
				vo.setEmail(rs.getString(2));
			}

			return vo;
		}
		catch(SQLException ex)
		{	
			System.out.println("error:" + ex);
			return null;
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	// 보안 = 인증 + 권한
	
	// 인증(Auth)
	public UserVo get(UserVo vo)
	{
		UserVo userVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbConnection.getConnection();
			
			String sql = "select no, name, email from user where email = ? and passwd=password(?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getEmail()); // 바인딩 처리
			pstmt.setString(2, vo.getPassword()); // 바인딩 처리
			
			rs = pstmt.executeQuery();
			
			if ( rs.next())
			{
				Long no = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				
				userVo = new UserVo();
				
				userVo.setNo(no);
				userVo.setName(name);
				userVo.setEmail(email);
				
			}
			
			return userVo;
		}
		catch (SQLException e) 
		{
		 System.out.println("error" + e);
			return null;
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public UserVo plusGender(Long no)
	{
		UserVo userVo = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			conn = dbConnection.getConnection();
			
			String sql = "select no, name, email, gender  from user where no = ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no); // 바인딩 처리
			
			rs = pstmt.executeQuery();
			
			if ( rs.next())
			{
				Long no2 = rs.getLong(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				String gender = rs.getString(4);
				
				userVo = new UserVo();
				
				userVo.setNo(no2);
				userVo.setName(name);
				userVo.setEmail(email);
				userVo.setGender(gender);	
			}
			
			return userVo;
		}
		catch (SQLException e) 
		{
		 System.out.println("error" + e);
			return null;
		}
		finally
		{
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void getModify(UserVo vo)
	{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try
		{
			conn = dbConnection.getConnection();
			
			String sql = "update user set name = ?, passwd = password(?), gender = ? where no = ? ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName()); // 바인딩 처리
			pstmt.setString(2, vo.getPassword()); // 바인딩 처리
			pstmt.setString(3, vo.getGender()); // 바인딩 처리
			pstmt.setLong(4, vo.getNo()); // 바인딩 처리
			
			pstmt.executeUpdate();

		}
		catch (SQLException e) 
		{
		 System.out.println("error" + e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.close();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void insert(UserVo vo)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try 
		{
			conn = dbConnection.getConnection();
			
			String sql = "insert into user values(null,?, ?, password(?), ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getEmail());
			pstmt.setString(3, vo.getPassword());
			pstmt.setString(4, vo.getGender());
			
			pstmt.executeUpdate();
			
			
		} 
		catch (SQLException e) 
		{
		 System.out.println("error" + e);
		}
		finally
		{
			try
			{
				if (pstmt != null)
				{
					pstmt.cancel();
				}
				if (conn != null)
				{
					conn.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
}
