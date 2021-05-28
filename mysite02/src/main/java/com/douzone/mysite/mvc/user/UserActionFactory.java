package com.douzone.mysite.mvc.user;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;
import com.douzone.mysite.mvc.main.MainAction;

public class UserActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("joinform".equals(actionName)) {
			action = new JoinformAction();
		} else if("joinsuccess".equals(actionName)) {
			action = new JoinSuccessAction();
		} else if("loginform".equals(actionName)) {
			action = new LoginFormAction();
		} else if("join".equals(actionName)) {
			action = new JoinAction();
		} else if("login".equals(actionName)) {
			action = new LoginAction();
		} else if("logout".equals(actionName)) {
			action = new LogoutAction();
		} else if("updateform".equals(actionName)) {
			action = new UpdateFormAction();
		} else {
			action = new MainAction();
		}
		
		return action;
	}

}
