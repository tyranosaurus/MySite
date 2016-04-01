package com.estsoft.mysite.web.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.estsoft.mysite.vo.UserVo;
import com.estsoft.web.WebUtil;
import com.estsoft.web.action.Action;

public class LogoutAction implements Action 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		HttpSession session = request.getSession();
		
		if (session == null)  // 또라이들이 로그인하지 않고 그냥 로그아웃으로 접글했을때
		{
			WebUtil.redirect(request, response, "/mysite/main");
			
			return; // 다음 코드가 있으므로 꼭 리턴 해서 끝내줘야함
		}
		
		//로그아웃 처리
		session.removeAttribute("authUser"); 
		session.invalidate();  //기존 세션에 매핑된 값을 삭제해주고 세션아이디를 새로 만들어줌.
		
		WebUtil.redirect(request, response, "/mysite/main");
		
	}

}
