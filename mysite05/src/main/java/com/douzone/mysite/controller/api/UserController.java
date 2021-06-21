package com.douzone.mysite.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.douzone.mysite.dto.JsonResult;
import com.douzone.mysite.service.UserService;
import com.douzone.mysite.vo.UserVo;

@Controller("userControllerApi")
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@GetMapping("/checkemail")
	public JsonResult checkEmail(@RequestParam(value="email", required=true, defaultValue="") String email) {
		
		UserVo userVo = userService.getUser(email);
		return JsonResult.success(userVo !=null);
	}
}
