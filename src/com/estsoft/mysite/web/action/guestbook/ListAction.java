package com.estsoft.mysite.web.action.guestbook;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.mysite.dao.GuestBookDao;
import com.estsoft.mysite.vo.GuestBookVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class ListAction implements Action 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
		GuestBookDao dao = new GuestBookDao( new MySQLWebDBConnection() );
		List<GuestBookVo> list = dao.getList();
		
		request.setAttribute("list", list);
		
		WebUtil.forward(request, response, "/WEB-INF/views/guestbook/list.jsp");
	}

}
