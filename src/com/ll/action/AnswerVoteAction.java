package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.ll.entity.User;
import com.ll.service.AnswerService;
import com.ll.service.UserService;

public class AnswerVoteAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		int id = Integer.parseInt(req.getParameter("answerId"));
		int count = Integer.parseInt(req.getParameter("a"));
		AnswerService answerService = new AnswerService();
		int ticketCount = answerService.getAnswerTicket(id, count);
		PrintWriter out = resp.getWriter();
		out.print(ticketCount);
		out.flush();
		out.close();
	}

}
