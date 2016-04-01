package com.estsoft.mysite.web.action.user;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/life")
public class LifeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Map map;

	
	public void init(ServletConfig config) throws ServletException {
		System.out.println("라이프서블릿 : init() : ");
	}


	public void destroy() {
		System.out.println("라이프서블릿 : destroy() : ");
	}

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("라이프서블릿 : service() : ");
		super.service(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("라이프서블릿 : doGET() : ");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("라이프서블릿 : POST : ");
	}

}
