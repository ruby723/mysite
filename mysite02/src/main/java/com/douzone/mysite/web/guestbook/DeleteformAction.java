package com.douzone.mysite.web.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class DeleteformAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MvcUtils.redirect(request.getContextPath() + "/guestbook/deleteform", request, response);
	}

}
