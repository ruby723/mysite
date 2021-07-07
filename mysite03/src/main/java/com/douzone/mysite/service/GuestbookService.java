package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GuestbookRepository;
import com.douzone.mysite.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookRepository guestbookRepository; //	의존성 문제 해결
	
	public List<GuestbookVo> getMessageList(){
		return guestbookRepository.findAll();
	}
	
	public List<GuestbookVo> getMessageList(Long no/*기준*/){
		return guestbookRepository.findAll(no);
	}
	
	public boolean deleteMessage(Long no, String password) {
		GuestbookVo vo = new GuestbookVo();
		vo.setNo(no);
		vo.setPassword(password);
		
		return guestbookRepository.delete(vo);
	}
	
	public void insertMessage(GuestbookVo vo) {
		guestbookRepository.insert(vo);
	}

	public List<GuestbookVo> getSpaMessageList(Long no) {
		
		List<GuestbookVo> result = null;
		
		if (no == 0) {
			result = guestbookRepository.spafindAll();
		} else {
			result = guestbookRepository.spafindAll(no);
		}
		return result;
	}
}
