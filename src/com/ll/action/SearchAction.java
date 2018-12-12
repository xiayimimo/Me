package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.ll.entity.Question;
import com.ll.service.QuestionService;

public class SearchAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("进入搜索后台");
		resp.setCharacterEncoding("utf-8");
		String param = req.getParameter("param");
		QuestionService questionService = new QuestionService();
		List<Question> listAllQuestion = questionService.getAllQuestion();
		HttpSession session = req.getSession();
		List<Question> list = (List<Question>)session.getAttribute("questionList");
		list.clear();
			for (Question question : listAllQuestion) {
				if(question.getTitle().contains(param) && !param.equals("")){
					list.add(question);
				}
		}
			session.setAttribute("questionList",list);
			
			
			
	}

	
	
	
}
