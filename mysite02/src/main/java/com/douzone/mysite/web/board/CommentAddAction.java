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

public class CommentAddAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession(true);	 
		// "USER"로 바인딩된 객체를 돌려준다. ("USER"로 바인딩된 객체가 없다면 null)
		UserVo uservo = (UserVo) httpSession.getAttribute("authUser");
		 
		String stringno = request.getParameter("no");
		Long no = Long.parseLong(stringno);
		BoardVo vo = new BoardRepository().findByNo(no);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		Long userno = uservo.getNo();
		
		BoardVo newvo = new BoardVo();
		newvo.setTitle(title);
		newvo.setContents(content);
		newvo.setHit(0);
		newvo.setGroupNo(vo.getGroupNo());
		newvo.setOrderNo(vo.getOrderNo()+1);
		newvo.setDepth(vo.getDepth()+1);
		newvo.setUserNo(userno);

		new BoardRepository().update(newvo );
		new BoardRepository().insert(newvo);
		MvcUtils.redirect(request.getContextPath() + "/board", request, response);
	}

}
