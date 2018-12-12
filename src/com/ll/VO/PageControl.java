package com.ll.VO;

import java.util.List;

public class PageControl {

	private int currentPage;
	private int pageCount;
	private List currentContent;
	private int questionId;
	
	public int getQuestionId() {
		return questionId;
	}
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public List getCurrentContent() {
		return currentContent;
	}
	public void setCurrentContent(List currentContent) {
		this.currentContent = currentContent;
	}
	
	
	
	
	
}
