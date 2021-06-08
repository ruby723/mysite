package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.BoardRepository;
import com.douzone.mysite.vo.BoardVo;


@Service
public class BoardService {
	@Autowired
	BoardRepository boardRepository; //	의존성 문제 해결
	
	public List<BoardVo> getMessageList(){
		List<BoardVo> list = boardRepository.findAll();
		//System.out.println(list);
		return list;
	}
	
	public void delete(Long no) {
		BoardVo vo = new BoardVo();
		vo.setNo(no);
		
		boardRepository.delete(vo);
	}
	
	public void write(BoardVo vo) {
		boardRepository.insert(vo);
	}
	
	public void modify(BoardVo vo) {
		//System.out.println(vo);
		boardRepository.modify(vo);
	}
	
	public void update(BoardVo vo) {
		boardRepository.update(vo);
	}
	
	public int maxGroup() {
		return boardRepository.maxGroup();
	}
	
	public int count() {
		return boardRepository.count();
	}
	
	public BoardVo findbyNo(Long no) {
		return boardRepository.findbyNo(no);
	}
	
	public void updateHit(BoardVo vo) {
		boardRepository.updateHit(vo);
	}
}
