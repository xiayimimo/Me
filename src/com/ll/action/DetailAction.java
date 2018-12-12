package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import com.ll.entity.Answer;
import com.ll.entity.Question;
import com.ll.service.AnswerService;
import com.ll.service.QuestionService;

public class DetailAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("进入detail后台");
		resp.setCharacterEncoding("utf-8");
		String title = req.getParameter("title");
//		System.out.println(title);
		QuestionService questionService = new QuestionService();
		Question question = questionService.getQuestion(title);
		int id = question.getId();
	    questionService.setBroswerCount(id);
		AnswerService answerService = new AnswerService();
		List<Answer> list = answerService.getAllAnswer(id);
		Collections.sort(list, new Comparator<Answer>() {
			public int compare(Answer o1, Answer o2) {
				// TODO Auto-generated method stub
				int flag = o1.getAnswerTime().compareTo(o2.getAnswerTime());
				return flag;
			}
		
		});
		req.getSession().setAttribute("answerList", list);
		req.getSession().setAttribute("question", question);
//		System.out.println(question);	
	}

	
	
	
}
