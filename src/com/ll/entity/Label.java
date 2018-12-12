package com.ll.entity;

import java.io.Serializable;

public class Label implements Serializable{

	/**
	 * 实现序列化
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	public Label(){}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
