package com.douzone.mysite.vo;

public class GalleryVo {

	private int no;
	private String comment;
	private String url;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", comment=" + comment + ", url=" + url + "]";
	}
	
	
}
