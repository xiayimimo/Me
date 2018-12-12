package com.ll.action;

import java.io.IOException;

import java.util.Collections;
import java.util.Comparator;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.ll.entity.Answer;

public class AnswerOrderAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
//		System.out.println("排序");
		resp.setCharacterEncoding("utf-8");
		String operation = req.getParameter("operation");
//		System.out.println(operation);
		HttpSession session = req.getSession();
		List<Answer> list = (List<Answer>)session.getAttribute("answerList");
		if(list != null){
			if(operation.equals("按票数排序")){
				Collections.sort(list,new Comparator<Answer>() {

					public int compare(Answer o1, Answer o2) {
						// TODO Auto-generated method stub
						int flag = o1.getTicketCount() - o2.getTicketCount();
						return -flag;
					}
					
					
				});
			
			}else{
				Collections.sort(list,new Comparator<Answer>() {

					public int compare(Answer o1, Answer o2) {
						// TODO Auto-generated method stub
						int flag = o1.getAnswerTime().compareTo(o2.getAnswerTime());
						return -flag;
					}
					
					
				});
				
			}
			
			session.setAttribute("answerList",list);
		}
	}

}
