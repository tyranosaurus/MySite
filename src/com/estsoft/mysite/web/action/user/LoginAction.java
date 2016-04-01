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

public class LoginAction implements Action 
{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo vo = new UserVo();
		
		vo.setEmail(email);
		vo.setPassword(password);
		
		UserDao dao = new UserDao( new MySQLWebDBConnection() );
		
		UserVo authUser = dao.get(vo); // 인증된 사용자
		
		
//		System.out.println(authUser);
		
		if ( authUser == null)
		{
			// 비밀번호나 이메일이 틀림
			WebUtil.redirect(request, response, "/mysite/user?a=loginform&result=fail"); // 1번 째 방식
			
//			WebUtil.forward(request, response, "WEB-INF/views/user/loginformfail.jsp"); //2번째 방식 포워딩
			
			return; // 로그인 화면으로 보내고 거기서 끝내야함. 끝내주지 않으면 아래 문장까지 다 실행함.
		}
		
		// 인증 성공(로그인 처리)
		HttpSession session = request.getSession(true);// 제이세션아이디 쿠키만 다루는 메소드이므로 제이세션쿠키의 값을 매핑한다. 처음 세션을 만들때는 true라고 써야 새로 세션을 만들어줌. ();이렇게 쓰면 기존에 있는것을 찾고 있으면 그 세션을 반환하고 없으면 null반환
		session.setAttribute("authUser", authUser); // 여기까지 해야 로그인 처리 끝
		
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
