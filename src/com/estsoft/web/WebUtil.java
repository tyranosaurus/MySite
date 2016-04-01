package com.estsoft.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WebUtil 
{
	public static void redirect(HttpServletRequest request, HttpServletResponse response, String url) throws IOException  // 여기서는 path가 아닌 url을 사용
	{
		response.sendRedirect(url);
	}
	
	public static void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException // path는 뷰의 패스
	{
		//forwarding (다른말로는 -> request 확장, request dispatcher)
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
