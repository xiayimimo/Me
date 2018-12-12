package com.ll.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ll.entity.Question;
import com.ll.service.QuestionService;

public class IndexAction extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		resp.setCharacterEncoding("utf-8");
		QuestionService questionService = new QuestionService();
		List<Question> listAllQuestion = questionService.getAllQuestion();
		HttpSession session = req.getSession();
		session.setAttribute("questionList",listAllQuestion);
	}

	
}
