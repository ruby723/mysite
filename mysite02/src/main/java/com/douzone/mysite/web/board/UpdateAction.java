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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stringno = request.getParameter("no");
		Long no = Long.parseLong(stringno);
		String title = request.getParameter("title");
		String content = request.getParameter("content");

		BoardVo vo = new BoardVo();
		
		vo.setNo(no);
		vo.setTitle(title);
		vo.setContents(content);

		new BoardRepository().modify(vo);
		MvcUtils.redirect(request.getContextPath() + "/board?a=view&no="+no, request, response);
	}

}
