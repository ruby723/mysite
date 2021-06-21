package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	GuestbookRepository guestbookRepository; //	의존성 문제 해결
	
	public List<GuestbookVo> getMessageList(){
		return guestbookRepository.findAll();
	}
	
	public void deleteMessage(Long no, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		guestbookRepository.delete(vo);
	}
	
	public void insertMessage(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}
}
