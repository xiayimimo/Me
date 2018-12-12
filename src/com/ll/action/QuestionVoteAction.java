package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ll.service.QuestionService;

public class QuestionVoteAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(req.getParameter("questionId"));
		int count = Integer.parseInt(req.getParameter("a"));
		QuestionService questionService = new QuestionService();
		int ticketCount = questionService.updateQuestion(id, count);
		PrintWriter out = resp.getWriter();
		out.print(ticketCount);
		out.flush();
		out.close();
	}

}
