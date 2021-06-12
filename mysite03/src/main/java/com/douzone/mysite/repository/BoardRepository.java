package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.BoardVo;

@Repository
public class BoardRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(BoardVo vo) {
		
		int count= sqlSession.insert("board.insert",vo);
		return count==1;
	}
	
	public boolean modify(BoardVo vo) {
		//System.out.println(vo);
		int count = sqlSession.update("board.modify",vo);
		return count ==1;
	}
	
	public boolean update(BoardVo vo) {

		int count = sqlSession.update("board.update");
		return count==1;
	}
	
	public int maxGroup() {
		return sqlSession.selectOne("board.maxGroup");
	}
	
	public int count() {

		return sqlSession.selectOne("board.count");
	}
	
	public boolean updateHit(BoardVo vo) {

		int count=sqlSession.update("board.updateHit",vo);
		return count ==1 ;
	}
	
	public BoardVo findbyNo(Long no) {
		
		return sqlSession.selectOne("board.findbyNo",no);
	}	
	
	public List<BoardVo> findAll() {
		
		List<BoardVo> list = sqlSession.selectList("board.findAll");
		//System.out.println(list);
		return list;
	}
	
	public boolean delete(BoardVo vo) {

		int count=sqlSession.delete("board.delete",vo);
		// System.out.println(vo);
		return count==1;
	}

	public List<BoardVo> search(String keyword) {
		
		List<BoardVo> list = sqlSession.selectList("board.search",keyword);
		return list;
	}
}
