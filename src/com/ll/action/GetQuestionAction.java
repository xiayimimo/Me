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

public class GetQuestionAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("获取所有问题");
		resp.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
//		System.out.println(id);
		QuestionService questionService = new QuestionService();
		if(id != null){
			boolean b =questionService.delQuestion(Integer.parseInt(id));
			PrintWriter out = resp.getWriter();
			out.print(b);
			out.flush();
			out.close();
			return;
		}
		List<Question> list = questionService.getQuestion(1);
		HttpSession session = req.getSession();
		session.setAttribute("questionList",list);
		List<Question> list1 = questionService.getAllQuestion();
		int pageCount = (int) Math.round(list1.size() / 2.0);
//		System.out.println(Math.round(1.5));
		session.setAttribute("pageCount",pageCount);
		session.setAttribute("currentPage", 1);

	}

}
