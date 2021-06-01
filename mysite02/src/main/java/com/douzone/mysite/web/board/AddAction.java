package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class AddAction implements Action {
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderno = 0;
		int depth = 0;
		HttpSession httpSession = request.getSession(true);
		 
		// "USER"로 바인딩된 객체를 돌려준다. ("USER"로 바인딩된 객체가 없다면 null)
		UserVo uservo = (UserVo) httpSession.getAttribute("authUser");
		 
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long userno = uservo.getNo();
		
		BoardVo vo = new BoardVo();
		vo.setTitle(title);
		vo.setContents(content);
		vo.setHit(0);
		vo.setGroupNo(new BoardRepository().maxGroup()+1);
		vo.setOrderNo(orderno);
		vo.setDepth(depth);
		vo.setUserNo(userno);

		new BoardRepository().insert(vo);
		MvcUtils.redirect(request.getContextPath() + "/board", request, response);
	}

}