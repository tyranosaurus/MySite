package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class JoinFormAction implements Action  // 회원가입 페이지를 보여주는 녀석. 포워딩.
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		WebUtil.forward(request, response, "/WEB-INF/views/user/joinform.jsp");
	}
}