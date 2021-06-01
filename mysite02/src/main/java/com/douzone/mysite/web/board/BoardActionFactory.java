package com.douzone.mysite.web.board;

import com.douzone.web.Action;
import com.douzone.web.ActionFactory;

public class BoardActionFactory extends ActionFactory {

	@Override
	public Action getAction(String actionName) {
		Action action = null;
		
		if("view".equals(actionName)) {
			action = new ViewAction();
		}else if("write".equals(actionName)) {
			action =  new WriteAction();
		}else if("add".equals(actionName)){
			action = new AddAction();			
		}else if("modify".equals(actionName)){
			action = new ModifyAction();
		}else if("update".equals(actionName)){
			action = new UpdateAction();
		}else if("comment".equals(actionName)){
			action = new CommentAction();
		}else if("commentadd".equals(actionName)){
			action = new CommentAddAction();
		}else if("delete".equals(actionName)){
			action = new DeleteAction();
		}else if("p".equals(actionName)){
			action = new DeleteAction();
		}else {// default Action
			action = new ListAction();
		}
		
		return action;
	}
}
