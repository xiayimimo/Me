package com.ll.service;

import java.util.List;

import com.ll.dao.RoleDAO;
import com.ll.dao.UserDAO;
import com.ll.entity.Limit;
import com.ll.entity.Role;
import com.ll.entity.User;

public class UserService {

	public List<Limit> getAllLimit(){
		UserDAO user = new UserDAO();
		return user.queryAllLimit();
	}
	public boolean addPower(String powerName){
		UserDAO user = new UserDAO();
		return user.addPower(powerName);
	}
	/**
	 * 角色操作
	 * @return
	 */
	public List<Role> getAllRole(){
		UserDAO user = new UserDAO();
		return user.queryAllRole();
	}
	public boolean addRole(String roleName,String power){
		RoleDAO roleDAO = new RoleDAO();
		return roleDAO.insert(roleName, power);
	}
	public String getPower(String roleName){
		RoleDAO role = new RoleDAO();
		return role.queryPower(roleName);
	}
	
	
	
	
	public List<User> getAllUser(){
		UserDAO user = new UserDAO();
		return user.queryAllUser();
	}
	/**
	 * 注册业务
	 * @param u
	 */
	public boolean regService(User u){
		UserDAO user = new UserDAO();
		return user.insert(u);
	}
	/**
	 * 注册验证账号是否存在
	 * @param account 账号
	 * @return
	 */
	public boolean ckService(String account){
		UserDAO user = new UserDAO();
		return user.ck(account);
	}
	/**
	 * 登录验证
	 * @param account 账号
	 * @param password 密码
	 * @return 返回用户
	 */
	public User loginService(String account,String password){
		UserDAO user = new UserDAO();
		return user.query(account, password);
		
	}
	
}
