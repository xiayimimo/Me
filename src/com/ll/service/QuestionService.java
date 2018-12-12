package com.ll.service;

import java.util.List;

import com.ll.dao.QuestionDAO;
import com.ll.entity.Question;

public class QuestionService {

	public List<Question> getQuestion(int currentPage){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.queryQuestion(currentPage);
	}
	
	public List<Question> getAllQuestion(String classify){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.queryAllQuestion(classify);
	}
	
	public List<Question> getAllQuestion(){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.queryAllQuestion();
	}
	
	public Question getQuestion(String title){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.queryQuestion(title);
	}
	
	public int updateQuestion(int id,int count){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.updateQuestion(id, count);
	}
	
	public boolean addQuestion(Question q){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.insertQuestion(q);
	}
	
	public boolean delQuestion(int id){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.delQuestion(id);
	}
	
	public boolean setAnswerCount(int id,int count){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.updateAnswerCount(id, count);
	}
	
	public boolean setBroswerCount(int id){
		QuestionDAO questionDAO = new QuestionDAO();
		return questionDAO.updateBroswerCount(id);
	}
}
