package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.UserDao;
import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class ModifyAction implements Action 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		UserVo authUser = (UserVo)session.getAttribute("authUserVo");
		
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		authUser.setName(name);
		authUser.setPassword(password);
		authUser.setGender(gender);
		
		UserDao dao = new UserDao( new MySQLWebDBConnection() );
		
		dao.getModify(authUser);
		
		session.setAttribute("authUser", authUser);
		
		WebUtil.redirect(request, response, "/mysite/main");
		
	}

}
