package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GuestbookVo;

@Repository
public class GuestbookRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<GuestbookVo> findAll() {
		
		return sqlSession.selectList("guestbook.findAll");
	}
	
	public List<GuestbookVo> findAll(Long no) {
		
		return sqlSession.selectList("guestbook.findAllByNo",no);
	}
	
	public boolean delete(GuestbookVo vo) {
		int count = sqlSession.delete("guestbook.delete",vo);
		return count ==1;		
	}
	
	public boolean insert(GuestbookVo vo) {
		System.out.println(vo);
		int count=sqlSession.insert("guestbook.insert",vo);
		System.out.println(vo);
		return count ==1;
	}

	public List<GuestbookVo> spafindAll() {

		return sqlSession.selectList("guestbook.spafindAll");
	}

	public List<GuestbookVo> spafindAll(Long no) {
		
		return sqlSession.selectList("guestbook.spafindAll2",no);
	}
}