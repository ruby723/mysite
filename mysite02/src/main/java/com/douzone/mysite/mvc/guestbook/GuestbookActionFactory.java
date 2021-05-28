package com.douzone.mysite.mvc.guestbook;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;

public class GuestbookActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action=null;
		
		if("add".equals(actionName)){
			action=new AddAction();
		}else if("delete".equals(actionName)) {
			action=new DeleteAction();
		}else if ("deleteform".equals(actionName)) {
			action=new DeleteformAction();
		}else {
			action = new ListAction();
		}
		
		return action;
	}

}