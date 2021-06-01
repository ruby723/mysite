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



public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession httpSession = request.getSession(true);
		 
		// "USER"로 바인딩된 객체를 돌려준다. ("USER"로 바인딩된 객체가 없다면 null)
		UserVo uservo = (UserVo) httpSession.getAttribute("authUser");
		
		String no = request.getParameter("no");
		
		BoardVo vo = new BoardVo();
		vo.setNo(Long.parseLong(no));

		new BoardRepository().delete(vo);
		MvcUtils.redirect(request.getContextPath() + "/board", request, response);

	}

}
