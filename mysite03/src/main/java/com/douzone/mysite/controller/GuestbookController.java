package com.douzone.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {
	@Autowired
	GuestbookService guestbookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.getMessageList();
			model.addAttribute("list",list);
		return "guestbook/index";
	}
	
	@RequestMapping(value = "/deleteform/{no}",method = RequestMethod.GET)
	public String deleteform(@PathVariable(name="no") Long no,Model model) {
		
		model.addAttribute("no",no);
		return "guestbook/deleteform";
	}
	
	@RequestMapping(value="/delete/{no}", method=RequestMethod.POST)
	public String delete(@PathVariable(name="no")Long no,@PathVariable(name="password")String password) {
		guestbookService.deleteMessage(no, password);
		return "redirect:/guestbook";
	}
	
	@RequestMapping(value = "/insert" ,method = RequestMethod.POST)
	public String add(GuestbookVo vo) {
		
		guestbookService.insertMessage(vo);
		return "redirect:/guestbook";
	}
	
//	@ExceptionHandler(Exception.class)
//	public String handlerException() {
//		// 1. logging
//		return "error/exception"; // 2. 사과 페이지
//	}
}
