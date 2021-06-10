package com.douzone.mysite.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.mysite.vo.GalleryVo;

@Repository
public class GalleryRepository {

	@Autowired
	private SqlSession sqlSession;

	public void upload(GalleryVo vo) {
		sqlSession.insert("gallery.upload",vo);
		
	}

	public GalleryVo findByNo(int no) {
		
		return sqlSession.selectOne("gallery.findByNo",no);
	}

	public void delete(GalleryVo vo) {
		
		sqlSession.delete("gallery.delete",vo);
		
	}

	public List<GalleryVo> findAll() {
		
		return sqlSession.selectList("gallery.findAll");
	}

}
