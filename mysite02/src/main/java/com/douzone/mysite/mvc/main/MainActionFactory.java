package com.douzone.mysite.mvc.main;

import com.douzone.mvc.Action;
import com.douzone.mvc.ActionFactory;

public class MainActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		return new MainAction();
	}

}