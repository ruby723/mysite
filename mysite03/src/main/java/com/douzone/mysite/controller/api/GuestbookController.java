package com.douzone.mysite.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.GuestbookService;
import com.douzone.mysite.vo.GuestbookVo;

@Controller("guestbookControllerApi")
@RequestMapping("/guestbook/api")
public class GuestbookController {
	
	@Autowired
	private GuestbookService guestbookService;
	
	@ResponseBody
	@RequestMapping("/list/{no}")
	public JsonResult list(@PathVariable Long no) {
		List<GuestbookVo> vo = guestbookService.getSpaMessageList(no);
		System.out.println(vo);
		return JsonResult.success(vo);
	}
	
	@ResponseBody
	@RequestMapping("/delete/{no}")
	public JsonResult ex3(@PathVariable("no") Long no,  @RequestParam(value="password", required=true, defaultValue="") String password) {
		// delete 작업 (GuestbookService)
		Long data = null;
		boolean result = guestbookService.deleteMessage(no, password);
		
		if(result == false) {
			// 1. 삭제가 안 된 경우
			System.out.println("false");
			data = -1L;
		} else {
			// 2. 삭제 성공한 경우
			data = no;
		}
		return JsonResult.success(data);
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public JsonResult add(@RequestBody GuestbookVo vo) {
		// 등록 작업(GuestbookService)
		guestbookService.insertMessage(vo);	
		return JsonResult.success(vo);
	}
}
