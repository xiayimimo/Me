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

public class CkAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
		String account = req.getParameter("account");
		String ck = "yes";
		UserService userService = new UserService();
		boolean flag = userService.ckService(account);
		if(flag){
			ck="NO";
		}
		PrintWriter out = resp.getWriter();
		out.print(ck);
		out.flush();
		out.close();
	}

}
