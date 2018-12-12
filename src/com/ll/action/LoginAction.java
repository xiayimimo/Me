package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.omg.CORBA.Current;

import net.sf.json.JSONArray;

import com.ll.entity.Label;
import com.ll.entity.Question;
import com.ll.entity.User;
import com.ll.service.LabelService;
import com.ll.service.QuestionService;
import com.ll.service.UserService;

public class LoginAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("进入登录后台了没？");
		resp.setCharacterEncoding("utf-8");
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		//调用登录业务
		UserService userService = new UserService();
		User user= userService.loginService(account, password);
		String flag = "登录失败";
		if(user == null){
			PrintWriter out = resp.getWriter();
			out.print(flag);
			out.flush();
			out.close();
			return;
		}
		if(user.getPower().equals("普通用户")){
			flag="登录成功";
			req.getSession().setAttribute("user", user);
			QuestionService questionService = new QuestionService();
			List<Question> list = questionService.getAllQuestion("技术问答");
			req.getSession().setAttribute("questionList",list);
			LabelService label = new LabelService();
			long start = System.currentTimeMillis();
			List<Label> labelList = label.getAllLabels();
			long end = System.currentTimeMillis();
			req.getSession().setAttribute("labelList", labelList);
			req.getSession().setAttribute("time", end-start);
			req.getRequestDispatcher("index.jsp");
			return;
		}else{
			//判断权限 显示内容
			String powers = userService.getPower(user.getPower());
			flag = "登录成功";
			req.getSession().setAttribute("manage", powers);
			req.getSession().setAttribute("user", user);
//			System.out.println(powers);
			PrintWriter out = resp.getWriter();
			out.print(flag);
			out.flush();
			out.close();
			
		}
	
	}

}
