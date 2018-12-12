package com.ll.service;

import java.util.List;

import com.ll.dao.LabelDAO;
import com.ll.entity.Label;

public class LabelService {

	public List<Label> getAllLabels(){
		LabelDAO label = new LabelDAO();
		return label.queryAll();
	}
}
