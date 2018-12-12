package com.ll.entity;

import java.util.Date;

public class Question{

	private int id;
	private int ticketCount;
	private int answerCount;
	private int broswerCount;
	private String title;
	private String content ;
	private String labels;
	private String userName;
	private Date date;
	private String classify; 
	
	public Question(){
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTicketCount() {
		return ticketCount;
	}
	public void setTicketCount(int ticketCount) {
		this.ticketCount = ticketCount;
	}
	public int getAnswerCount() {
		return answerCount;
	}
	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public int getBroswerCount() {
		return broswerCount;
	}
	public void setBroswerCount(int broswerCount) {
		this.broswerCount = broswerCount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public void setClassify(String classify) {
		this.classify = classify;
	}

	public String getClassify() {
		return classify;
	}

	
	
	
}
