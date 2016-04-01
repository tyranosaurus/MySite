package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class JoinAction implements Action 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//Dao 처리
//		WebUtil.forward(request, response, "/WEB-INF/views/user/joinsuccess.jsp"); 이렇게 하면 사이트가 리프레시가 안되고 그냥 조인석세스에서 끝나버림.
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo vo = new UserVo();
		
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		
		UserDao dao = new UserDao( new MySQLWebDBConnection() );
		dao.insert(vo);
		
		WebUtil.redirect(request, response, "/mysite/user?a=joinsuccess");
		
		
	}

}
