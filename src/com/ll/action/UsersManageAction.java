package com.ll.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ll.entity.Limit;
import com.ll.entity.Role;
import com.ll.entity.User;
import com.ll.service.UserService;

public class UsersManageAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("进入用户管理后台");
		req.setCharacterEncoding("utf-8");
		String param = req.getParameter("param");
		UserService userService = new UserService();
		if(param.equals("addlimit")){
			int size= userService.getAllRole().size()+1;
			List<Limit> list = userService.getAllLimit();
			req.setAttribute("userLimit", list);
			req.setAttribute("size", size);
			req.getRequestDispatcher("roleedit.jsp").forward(req, resp);
			return;
		}
		if(param.equals("add")){
			List<Role> list = userService.getAllRole();
			req.setAttribute("userRole", list);
			req.getRequestDispatcher("useradd.jsp").forward(req, resp);
			return;
		}
		if(param.equals("人员管理")){
			List<User> list = userService.getAllUser();
			req.setAttribute("users", list);
			req.getRequestDispatcher("users.jsp").forward(req, resp);
			return;
		}
		if(param.equals("角色管理")){
			List<Role> list = userService.getAllRole();
			req.setAttribute("userRole", list);
			req.getRequestDispatcher("role.jsp").forward(req, resp);
				return;
		}
			
		
		if(param.equals("权限管理")){
			List<Limit> list = userService.getAllLimit();
			req.setAttribute("userLimit", list);
			req.getRequestDispatcher("privilege.jsp").forward(req, resp);
		}
		
		
		
	}

}
