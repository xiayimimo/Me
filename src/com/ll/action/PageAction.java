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

public class PageAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("分页操作");
		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		String currentPage =req.getParameter("currentPage");
//		System.out.println(currentPage);
		if(currentPage.equals("")){
			return;
		}
		QuestionService questionService = new QuestionService();
		List<Question> list = questionService.getQuestion(Integer.parseInt(currentPage));
		HttpSession session = req.getSession();
		session.setAttribute("questionList",list);
		session.setAttribute("currentPage", currentPage);

	}

}
