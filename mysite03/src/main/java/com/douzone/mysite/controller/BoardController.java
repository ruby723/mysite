package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.security.AuthUser;
import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;
import com.douzone.mysite.vo.UserVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;
	
	@RequestMapping({"","/p/{no}"})
	public String index(@PathVariable(name="no",required=false)String pageno,Model model) {
		
		//paging
		List<BoardVo> list = boardService.getMessageList();
		int totalPage = boardService.count();
		
		int currentPageNo = 1;
		int firstPageNo=1;
		int lastPageNo=totalPage;
		
		int nextPageNo=currentPageNo;
		int prevPageNo=currentPageNo;
		
		
		if(pageno != null) {
			currentPageNo=Integer.parseInt(pageno);
		}
		
		if(currentPageNo!=firstPageNo) {
			prevPageNo=currentPageNo-1;
		}
		
		if(currentPageNo!=lastPageNo) {
			nextPageNo=currentPageNo+1;
		}
		
		int writeException = ((currentPageNo-1)*5+5);
		
		List<BoardVo> writelist = list.subList(0,5);
		
		if(writeException/5 >= totalPage) {
			writelist = list.subList((currentPageNo-1)*5,list.size()-1);
		}else {
			writelist = list.subList((currentPageNo-1)*5, writeException);
		}
		
		// System.out.println(currentPageNo);
		model.addAttribute("list",writelist);
		model.addAttribute("currentPageNo",currentPageNo);
		model.addAttribute("prevPageNo",prevPageNo);
		model.addAttribute("nextPageNo",nextPageNo);
		model.addAttribute("firstPageNo",firstPageNo);
		model.addAttribute("lastPageNo",lastPageNo);
		
		if(currentPageNo>3) {
			if(currentPageNo+2<totalPage) {
				lastPageNo=currentPageNo+2;
			}
			if(lastPageNo - currentPageNo >2) {
				firstPageNo = currentPageNo - 2;
			}
		}
		return "board/list";
	}
	
	@RequestMapping(value = "/delete/{no}",method = RequestMethod.GET)
	public String deleteform(@PathVariable(name="no") Long no,Model model) {
		
		boardService.delete(no);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/view/{no}",method = RequestMethod.GET)
	public String view(@PathVariable(name="no")Long no,Model model) {

		BoardVo vo = boardService.findbyNo(no);
		boardService.updateHit(vo);
		model.addAttribute("vo",vo);
		return "board/view";
	}
	
	@RequestMapping(value = "/write")
	public String writeform(Model model) {
		
		return "board/write";
	}
	
	@RequestMapping(value = "/add",method=RequestMethod.POST)
	public String write(@AuthUser UserVo user , BoardVo vo) {
		
		vo.setUserNo(user.getNo());
		vo.setUserName(user.getName());
		
		int groupno =boardService.maxGroup();
		vo.setGroupNo(groupno);
		boardService.write(vo);
		return "redirect:/board";
	}
	
	@RequestMapping(value="/modify/{no}",method=RequestMethod.GET)
	public String modify(@PathVariable(name="no")Long no,Model model) {
		
		BoardVo vo = boardService.findbyNo(no);
		//System.out.println(vo);
		model.addAttribute("vo",vo);
		return "board/modify";
	} 
	
	@RequestMapping(value="/update/{no}",method=RequestMethod.POST)
	public String update(@PathVariable(name="no")Long no,BoardVo vo) {

		boardService.modify(vo);
		return "redirect:/board/view/{no}";
	}
	
	@RequestMapping(value="/comment/{no}",method=RequestMethod.GET)
	public String comment(@PathVariable(name="no")Long no,Model model) {
		
		model.addAttribute("no",no);
		return "board/comment";
	}
	
	@RequestMapping(value="/commentadd/{no}",method=RequestMethod.POST)
	public String commentadd(@AuthUser UserVo user,@PathVariable(name="no")Long no,BoardVo commentvo) {
		
		BoardVo vo = boardService.findbyNo(no);
		
		commentvo.setGroupNo(vo.getGroupNo());
		commentvo.setOrderNo(vo.getOrderNo());
		commentvo.setUserNo(user.getNo());
		commentvo.setUserName(user.getName());
		commentvo.setDepth(vo.getDepth()+1);
		
		boardService.update(commentvo);
		boardService.write(commentvo);
		return "redirect:/board";
	}
}

