package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;


import com.ll.entity.Question;
import com.ll.service.AnswerService;
import com.ll.service.QuestionService;

public class SortAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("进入后台");
		resp.setCharacterEncoding("utf-8");
		String operation = req.getParameter("operation");
//		System.out.println(operation);
		QuestionService questionService = new QuestionService();
		HttpSession session = req.getSession();
		List<Question> list = (List<Question>)session.getAttribute("questionList");
//		System.out.println(list.get(0).getDate());
		if(list != null){
			if(operation.equals("最新提问")){
				Collections.sort(list,new Comparator<Question>() {
					public int compare(Question o1, Question o2) {
						// TODO Auto-generated method stub
						int flag = o1.getDate().compareTo(o2.getDate());
						return -flag;
					}
					
				});
			}else if(operation.equals("尚未回答")){
				AnswerService answerService = new AnswerService();
				Iterator<Question> it = list.iterator();
					while(it.hasNext()){
						Question question = it.next();
//						System.out.print(question.getId());
//						System.out.print(answerService.getAllAnswer(question.getId()));
						if(answerService.getAllAnswer(question.getId()) != null && 
								answerService.getAllAnswer(question.getId()).size()!=0){
							it.remove();
						}
					}
			}else{
				Collections.sort(list,new Comparator<Question>() {
					public int compare(Question o1, Question o2) {
						int a= o1.getAnswerCount()*3+o1.getBroswerCount()+o1.getTicketCount()*5;
						int b= o2.getAnswerCount()*3+o2.getBroswerCount()+o2.getTicketCount()*5;
						if(a == b){
							return 0;
						}
						return a>b?-1:1;
					}
					
				});
				
			}
			
			session.setAttribute("questionList",list);
		}
		
//		PrintWriter out = resp.getWriter();
//		JSONArray json = JSONArray.fromObject(list);
//		System.out.println(json.toString());
//		out.print(json.toString());
//		out.flush();
//		out.close();
	}

}
