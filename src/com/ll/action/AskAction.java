package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ll.entity.Question;
import com.ll.entity.User;
import com.ll.service.QuestionService;

public class AskAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("提问");
		resp.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		User user = (User) session.getAttribute("user");
		/*if(user == null){
			return;
		}*/
		String userName = user.getName();
		String title = req.getParameter("title");
		String classify = req.getParameter("classify");
		String labels = req.getParameter("labels");
		String content = req.getParameter("content");
		Question question = new Question();
		question.setTitle(title);
		question.setClassify(classify);
		question.setLabels(labels);
		question.setContent(content);
		question.setUserName(userName);
		QuestionService questionService = new QuestionService();
		boolean b = questionService.addQuestion(question);
		String flag = "添加成功";
		if(!b){
			flag="添加失败";
		}
		
		PrintWriter out = resp.getWriter();
		out.print(flag);
		out.flush();
		out.close();
		
	}

}
