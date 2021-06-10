package com.douzone.mysite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.mysite.repository.GalleryRepository;
import com.douzone.mysite.vo.GalleryVo;

@Service
public class GalleryService {

	@Autowired
	GalleryRepository galleryRepository;
	
	public void upload(GalleryVo vo) {
		
		galleryRepository.upload(vo);
	}
	
	public GalleryVo findByNo(int no) {
		
		return galleryRepository.findByNo(no); 
	}

	public void delete(GalleryVo vo) {
		
		galleryRepository.delete(vo);
		
	}

	public List<GalleryVo> findAll() {
		
		return galleryRepository.findAll();
		
	}
}
