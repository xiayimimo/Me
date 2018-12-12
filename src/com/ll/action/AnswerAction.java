package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ll.entity.Answer;
import com.ll.entity.Question;
import com.ll.entity.User;
import com.ll.service.AnswerService;
import com.ll.service.QuestionService;

public class AnswerAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("回答");
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		Question question = (Question) session.getAttribute("question");
		if(user == null || question == null){
			PrintWriter out = resp.getWriter();
			out.print("请登录后再回答！");
			out.flush();
			out.close();
			return;
		}
		String userName = user.getName();
		int questionId = question.getId();
		String content = req.getParameter("content");
		Answer  answer = new Answer();
		answer.setQuestionId(questionId);
		answer.setContent(content);
		answer.setUserName(userName);
		AnswerService answerService = new AnswerService();
		answerService.addAnswer(answer);
		//回答成功并显示在页面上
		List<Answer> list = answerService.getAllAnswer(questionId);
		QuestionService questionService = new QuestionService();
		questionService.setAnswerCount(questionId, list.size());
		session.setAttribute("answerList", list);
		
		
	}

}
