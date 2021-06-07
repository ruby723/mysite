package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.BoardService;
import com.douzone.mysite.vo.BoardVo;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	BoardService boardService;

	@RequestMapping("")
	public String index(Model model) {
		List<BoardVo> list = boardService.getMessageList();
			model.addAttribute("list",list);
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
		model.addAttribute("vo",vo);
		return "board/view/{no}";
	}
	
	@RequestMapping(value = "/write")
	public String writeform(Model model) {
		
		return "board/write";
	}
	
	@RequestMapping(value = "/add",method = RequestMethod.POST)
	public String write(BoardVo vo) {
		
		boardService.write(vo);
		return "redirect:/guestbook";
	}
}

