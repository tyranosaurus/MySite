package com.estsoft.mysite.web.action.guestbook;

import com.estsoft.web.action.Action;
import com.estsoft.web.action.ActionFactory;

public class GuestBookActionFactory extends ActionFactory 
{
	@Override
	public Action getAction(String actionName) 
	{
		Action action = null;
		
		if ("list".equals(actionName))
		{
			action = new ListAction();
		}
		else if ("add".equals(actionName))
		{
			action = new AddAction();
		}
		else if ("deleteform".equals(actionName))
		{
			action = new DeleteFormAction();
		}
		else if ("delete".equals(actionName))
		{
			action = new DeleteAction();
		}
		else
		{
			action = new DefaultAction();
		}
			return action;
	}

}
