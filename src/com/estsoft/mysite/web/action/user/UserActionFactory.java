package com.estsoft.mysite.web.action.user;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class UserActionFactory extends ActionFactory 
{
	@Override
	public Action getAction(String actionName) 
	{
		Action action = null;
		
		if ( "joinform".equals(actionName))
		{
			action = new JoinFormAction();
		}
		else if ("join".equals(actionName))
		{
			action = new JoinAction();
		}
		else if ("joinsuccess".equals(actionName))
		{
			action = new JoinSuccessAction();
		}
		else if ("loginform".equals(actionName))
		{
			action = new LoginFormAction();
		}
		else if ("login".equals(actionName))
		{
			action = new LoginAction();
		}
		else if ("logout".equals(actionName))
		{
			action = new LogoutAction();
		}
		else if ("modifyform".equals(actionName))
		{
			action = new ModifyFormAction();
		}
		else if ("modify".equals(actionName))
		{
			action = new ModifyAction();
		}
		else
		{
			action = new DefaultAction(); // 또라이 사용자를 위한 부분
		}
		
		return action;
	}
}
