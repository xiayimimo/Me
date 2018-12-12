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

public class AddAction extends HttpServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

//		System.out.println("进入用户新增后台");
		req.setCharacterEncoding("utf-8");
		String param = req.getParameter("param");
		UserService userService = new UserService();
		if(param.equals("userAdd")){
			String id = req.getParameter("userId");
			String name = req.getParameter("userName");
			String pwd = req.getParameter("userPwd");
			String power = req.getParameter("power");
			User user = new User();
			user.setAccount(id);
			user.setPassword(pwd);
			user.setName(name);
			user.setPower(power);
			boolean b = userService.regService(user);
			if(!b){
				resp.sendRedirect("useradd.jsp");
				return;
			}
			List<User> list = userService.getAllUser();
			req.setAttribute("users", list);
			req.getRequestDispatcher("users.jsp").forward(req, resp);
			return;
		}
		if(param.equals("roleAdd")){
			String roleName = req.getParameter("roleName");
			String[] power = req.getParameterValues("rights");
			String powerName = "";
			for (int i = 0; i < power.length; i++) {
				powerName += power[i]+(i==power.length-1?"":","); 
			}
			boolean b = userService.addRole(roleName, powerName);
			if(!b){
				resp.sendRedirect("roleedit.jsp");
				return;
			}
			List<Role> list = userService.getAllRole();
			req.setAttribute("userRole", list);
			req.getRequestDispatcher("role.jsp").forward(req, resp);
				return;
		}
			
		
		if(param.equals("powerAdd")){
			String powerName = req.getParameter("powerId");
			if(powerName.equals("")){
				resp.sendRedirect("privilegeadd.jsp");
				return;
			}
			boolean b = userService.addPower(powerName);
			if(!b){
				resp.sendRedirect("privilegeadd.jsp");
				return;
			}
			List<Limit> list = userService.getAllLimit();
			req.setAttribute("userLimit", list);
			req.getRequestDispatcher("privilege.jsp").forward(req, resp);
		}
		
		
		
	}

}
