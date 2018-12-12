package com.ll.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {

	
	private static String url;
	private static String user;
	private static String password;

	
	private DBUtil(){}
	
	static{
		
		Properties pro=new Properties();
		try {
			pro.load(DBUtil.class.getResourceAsStream("db.properties"));
			String className=pro.getProperty("className");
			
			Class.forName(className);
			
			url=pro.getProperty("url");
			user=pro.getProperty("user");
			password=pro.getProperty("password");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static Connection getConn(){
		
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public static void closeConn(Connection conn){
		if(conn != null){
	try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
}
	
	
	
}
