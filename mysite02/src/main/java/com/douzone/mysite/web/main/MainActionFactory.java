package com.douzone.mysite.web.main;

import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}