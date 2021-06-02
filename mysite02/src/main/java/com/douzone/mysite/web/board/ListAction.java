package com.douzone.mysite.web.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.web.Action;
import com.douzone.web.util.MvcUtils;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<BoardVo> list = new BoardRepository().findAll();
		String pageno = request.getParameter("p");
		int totalPage = new BoardRepository().count();
		
		int currentPageNo = 1;
		int firstPageNo = 1;
		int lastPageNo = totalPage;
		
		if(pageno!= null) {
			currentPageNo = Integer.parseInt(pageno);
		}
		
		if(currentPageNo>3) {
			if(currentPageNo+2<totalPage) {
				lastPageNo=currentPageNo+2;
			}
			if(lastPageNo - currentPageNo > 2) {
				firstPageNo = currentPageNo-2;
			}
		}
		
		//int nextPageNo = currentPageNo+1;
		//int prevPageNo = currentPageNo-1;
		
		request.setAttribute("currentPageNo", currentPageNo);
		request.setAttribute("firstPageNo", firstPageNo);
		request.setAttribute("lastPageNo", lastPageNo);
		request.setAttribute("list", list);
		MvcUtils.forward("board/list", request, response);
	}

}