package com.douzone.mvc;

public abstract class ActionFactory {
	public abstract Action getAction(String actionName);
}