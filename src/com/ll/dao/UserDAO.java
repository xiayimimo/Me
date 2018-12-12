package com.ll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ll.entity.Limit;
import com.ll.entity.Role;
import com.ll.entity.User;
import com.ll.utils.DBUtil;


public class UserDAO {

	/**
	 * 用户注册
	 * @param u 用户
	 */
	public boolean insert(User u){
		Connection conn =  DBUtil.getConn();
		String sql = "";
		if(u.getPower() != null){
			sql ="insert into user (account,password,name,power) values (?,?,?,?)";
		}else{
			sql ="insert into user (account,password,name) values (?,?,?)";
		}
		
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1, u.getAccount());
			pcmd.setString(2, u.getPassword());
			pcmd.setString(3, u.getName());
			if(u.getPower() != null){
				pcmd.setString(4, u.getPower());
			}
			pcmd.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
/**
 * 用户是否存在
 * @param account 账号
 * @return 存在返回true
 */
	public boolean ck(String account){
		Connection conn =  DBUtil.getConn();
		String sql ="select * from user where account = ?";
		
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1, account);
			ResultSet rs = pcmd.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return false;
	}

	public User query(String account,String password){
		Connection conn =  DBUtil.getConn();
		String sql ="select * from user where account = ? and password =?";
		
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1, account);
			pcmd.setString(2, password);
			ResultSet rs = pcmd.executeQuery();
			if(rs.next()){
				User u = new User();
				u.setAccount(account);
				u.setPassword(password);
				u.setName(rs.getString("name"));
				u.setPower(rs.getString("power"));
				return u;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 查询所有用户
	 * @return
	 */
	public List<User> queryAllUser(){
		Connection conn =  DBUtil.getConn();
		String sql ="select * from user where 1 = 1";
		List<User> list = new ArrayList<User>();
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setAccount(rs.getString("account"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setPower(rs.getString("power"));
				list.add(user);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 获取所有角色
	 * @return
	 */
	public List<Role> queryAllRole(){
		Connection conn =  DBUtil.getConn();
		String sql ="select * from role where 1 = 1";
		List<Role> list = new ArrayList<Role>();
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				Role role = new Role();
				role.setId(rs.getString("id"));
				role.setRole(rs.getString("role"));
				role.setPower(rs.getString("power"));
				list.add(role);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	/**
	 * 获取所有权限
	 * @return
	 */
	    public List<Limit> queryAllLimit(){
		Connection conn =  DBUtil.getConn();
		String sql ="select * from power where 1 = 1";
		List<Limit> list = new ArrayList<Limit>();
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				Limit limit = new Limit();
				limit.setId(rs.getInt("id"));
				limit.setName(rs.getString("name"));
				list.add(limit);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return null;
	}
	    /**
	     * 
	     * @param powerName
	     * @return
	     */
	    public boolean addPower(String powerName){
			Connection conn =  DBUtil.getConn();
			String sql ="insert into power(name) values (?)";
			try {
				PreparedStatement pcmd = conn.prepareStatement(sql);
				pcmd.setString(1,powerName);
				pcmd.executeUpdate();
			} catch (SQLException e) {
//				e.printStackTrace();
				return false;
			}
			return true;
		}
}

