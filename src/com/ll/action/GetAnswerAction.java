package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ll.VO.PageControl;
import com.ll.entity.Answer;
import com.ll.service.AnswerService;

public class GetAnswerAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("获取所有回答");
		String questionId = req.getParameter("id");
		String answerId = req.getParameter("answerId");
		String currentPage = req.getParameter("currentPage");
		AnswerService answerService = new AnswerService();
		if(answerId != null){
			boolean b =answerService.delAnswer(Integer.parseInt(answerId));
			PrintWriter out = resp.getWriter();
			out.print(b);
			out.flush();
			out.close();
			return;
		}
		if(currentPage != null){
			PageControl pc = (PageControl) req.getSession().getAttribute("pageControl");
			if(pc.getPageCount() < Integer.parseInt(currentPage)){
				return;
			}
			pc.setCurrentPage(Integer.parseInt(currentPage));
			pc.setCurrentContent(answerService.getAnswer(pc.getQuestionId(), Integer.parseInt(currentPage)));
			pc.setPageCount((int)(Math.ceil(answerService.getAllAnswer(pc.getQuestionId()).size()/3.0)));
			req.getSession().setAttribute("pageControl", pc);
			req.getRequestDispatcher("huida.jsp").forward(req, resp);
			return;
		}
		int pageCount = (int)Math.ceil(answerService.getAllAnswer(Integer.parseInt(questionId)).size()/3.0);
		//初始化回答页面内容
		if(currentPage == null){
			currentPage = "1";
		}
		List<Answer> list = answerService.getAnswer(Integer.parseInt(questionId), Integer.parseInt(currentPage));
		//封装页数对应的内容
		PageControl pc = new PageControl();
		pc.setCurrentContent(list);
		if(list.size() == 0){
			pc.setCurrentPage(0);
		}else{
			pc.setCurrentPage(Integer.parseInt(currentPage));
		}
		pc.setPageCount(pageCount);
		pc.setQuestionId(Integer.parseInt(questionId));
		req.getSession().setAttribute("pageControl", pc);
		req.getRequestDispatcher("huida.jsp").forward(req, resp);
	}

}
