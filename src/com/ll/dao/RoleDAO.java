package com.ll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ll.entity.Limit;
import com.ll.utils.DBUtil;

public class RoleDAO {

	/**
	 * 添加角色
	 * @param roleName
	 * @param power
	 * @return
	 */
	public boolean insert(String roleName,String power){
		Connection conn =  DBUtil.getConn();
		String sql ="insert into role (role,power) values (?,?)";
		
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1,roleName);
			pcmd.setString(2, power);
			pcmd.executeUpdate();
		} catch (SQLException e) {
//			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * 根据角色名  查看权限
	 * @param roleName
	 * @return
	 */
	 public String queryPower(String roleName){
			Connection conn =  DBUtil.getConn();
			String sql ="select * from role where role = ?";
			try {
				PreparedStatement pcmd = conn.prepareStatement(sql);
				pcmd.setString(1, roleName);
				ResultSet rs = pcmd.executeQuery();
				if(rs.next()){
					
					return rs.getString("power");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
				
			}
			return null;
		}
}
