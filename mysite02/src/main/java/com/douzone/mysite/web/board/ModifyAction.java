package com.douzone.mysite.web.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String stringno = request.getParameter("no");
		Long no = Long.parseLong(stringno);

		BoardVo vo = new BoardRepository().findByNo(no);
		
		request.setAttribute("vo", vo);
		MvcUtils.forward("board/modify", request, response);
	}

}