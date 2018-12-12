package com.ll.service;

import java.util.List;

import com.ll.dao.AnswerDAO;
import com.ll.entity.Answer;

public class AnswerService {

	
	public boolean delAnswer(int answerId){
		AnswerDAO answerDAO = new AnswerDAO();
		return answerDAO.delAnswer(answerId);
	}
	
	public List<Answer> getAnswer(int questionId,int currentPage){
		AnswerDAO answerDAO = new AnswerDAO();
		return answerDAO.queryAnswer(questionId, currentPage);
	}
	
	public List<Answer> getAllAnswer(int id){
		AnswerDAO answerDAO = new AnswerDAO();
		return answerDAO.queryAllAnswer(id);
	}
	
	public int getAnswerTicket(int id,int count){
		AnswerDAO answerDAO = new AnswerDAO();
		
		return answerDAO.updateAnswerTicket(id,count);
	}
	
	public boolean addAnswer(Answer a){
		AnswerDAO answerDAO = new AnswerDAO();
		
		return answerDAO.insertAnswer(a);
	}
}
