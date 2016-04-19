package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class AddAction implements Action 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		String password = request.getParameter("pass");
		String message = request.getParameter("content");
		
		GuestBookVo vo = new GuestBookVo();
		GuestBookDao dao = new GuestBookDao( new MySQLWebDBConnection() );
		
		vo.setName(name);
		vo.setPassword(password);
		vo.setMessage(message);
		
		dao.add(vo);
		
		WebUtil.redirect(request, response, "/mysite/gb?a=list");
	}

}
