package com.ll.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.ll.entity.User;
import com.ll.service.UserService;

public class RegAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setCharacterEncoding("utf-8");
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String passwordCk = req.getParameter("passwordCk");
		String name = req.getParameter("name");
		String msg = "注册成功";
		User u = new User();
		u.setAccount(account);
		u.setPassword(password);
		u.setName(name);
		//调用注册业务
		UserService userService = new UserService();
		boolean flag = userService.regService(u);
		if(!flag){
			msg="注册失败";
		}
		PrintWriter out = resp.getWriter();
		out.print(msg);
		out.flush();
		out.close();
	}

}
