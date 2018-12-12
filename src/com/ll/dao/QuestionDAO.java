package com.ll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ll.entity.Question;
import com.ll.utils.DBUtil;

public class QuestionDAO {

	/**
	 * 添加问题
	 * @param q
	 * @return
	 */
	
	public boolean insertQuestion(Question q){
		Connection conn =  DBUtil.getConn();
		String sql ="insert into question (title,content,labels,classify,userName)values(?,?,?,?,?)";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1, q.getTitle());
			pcmd.setString(2, q.getContent());
			pcmd.setString(3, q.getLabels());
			pcmd.setString(4, q.getClassify());
			pcmd.setString(5, q.getUserName());
			pcmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
 * 根据类型返回问题
 * @param classify 类型
 * @return
 */
	public List<Question> queryAllQuestion(String classify){
		Connection conn =  DBUtil.getConn();
		List<Question> list = new ArrayList<Question>(); 
		String sql ="select * from question where classify = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1, classify);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setTicketCount(rs.getInt("ticketCount"));
				question.setAnswerCount(rs.getInt("answerCount"));
				question.setBroswerCount(rs.getInt("broswerCount"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				//转JSON日期需要格式化	
//				question.setDate(new java.util.Date(rs.getDate("date").getTime()));
				
				question.setDate(rs.getTimestamp("date"));
				question.setLabels(rs.getString("labels"));
				question.setUserName(rs.getString("userName"));
				question.setClassify(rs.getString("classify"));
				list.add(question);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}
	/**
	 * 查询所有问题
	 * @return 返回list集合
	 */
	public List<Question> queryAllQuestion(){
		Connection conn =  DBUtil.getConn();
		List<Question> list = new ArrayList<Question>(); 
		String sql ="select * from question where 1 = 1";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setTicketCount(rs.getInt("ticketCount"));
				question.setAnswerCount(rs.getInt("answerCount"));
				question.setBroswerCount(rs.getInt("broswerCount"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				//转JSON日期需要格式化	
//				question.setDate(new java.util.Date(rs.getDate("date").getTime()));
				question.setDate(rs.getTimestamp("date"));
				question.setLabels(rs.getString("labels"));
				question.setUserName(rs.getString("userName"));
				question.setClassify(rs.getString("classify"));
				list.add(question);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 根据title查询帖子
	 * @param title
	 * @return
	 */
	public Question queryQuestion(String title){
		Connection conn =  DBUtil.getConn();
		List<Question> list = new ArrayList<Question>(); 
		String sql ="select * from question where title = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1, title);
			ResultSet rs = pcmd.executeQuery();
			if(rs.next()){
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setTicketCount(rs.getInt("ticketCount"));
				question.setAnswerCount(rs.getInt("answerCount"));
				question.setBroswerCount(rs.getInt("broswerCount"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				question.setDate(rs.getTimestamp("date"));
				question.setLabels(rs.getString("labels"));
				question.setUserName(rs.getString("userName"));
				question.setClassify(rs.getString("classify"));
				return question;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 投票
	 * @param id 问题id
	 * @param count 票数+1，-1
	 * @return
	 */
	public int updateQuestion(int id,int count){
		Connection conn =  DBUtil.getConn();
		String sql ="UPDATE question SET ticketCount = ticketCount + ? WHERE id = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, count);
			pcmd.setInt(2, id);
			pcmd.executeUpdate();
			sql ="select ticketCount from question WHERE id = ?";
			pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, id);
			ResultSet rs = pcmd.executeQuery();
			rs.next();
			return rs.getInt("ticketCount");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	/**
	 * 根据id删除问题
	 * @param id
	 * @return
	 */
	public boolean delQuestion(int id){
		Connection conn =  DBUtil.getConn();
		String sql ="delete from question where id = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, id);
			pcmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 设置答案数量
	 * @param id
	 * @param count
	 * @return
	 */
	public boolean updateAnswerCount(int id,int count){
		Connection conn =  DBUtil.getConn();
		String sql ="UPDATE question SET answerCount = ? WHERE id = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, count);
			pcmd.setInt(2, id);
			pcmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 设置浏览数
	 * @param id
	 * @return
	 */
	public boolean updateBroswerCount(int id){
		Connection conn =  DBUtil.getConn();
		String sql ="UPDATE question SET broswerCount = broswerCount + 1 WHERE id = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, id);
			pcmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	/**
	 * 分页
	 * @param currentPage
	 * @return
	 */
	
	public List<Question> queryQuestion(int currentPage){
		Connection conn =  DBUtil.getConn();
		List<Question> list = new ArrayList<Question>(); 
		int count = 2;
		int startIndex = (currentPage-1)*count;
		String sql ="select * from question limit ? , ? ";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, startIndex);
			pcmd.setInt(2, count);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				Question question = new Question();
				question.setId(rs.getInt("id"));
				question.setTicketCount(rs.getInt("ticketCount"));
				question.setAnswerCount(rs.getInt("answerCount"));
				question.setBroswerCount(rs.getInt("broswerCount"));
				question.setTitle(rs.getString("title"));
				question.setContent(rs.getString("content"));
				question.setDate(rs.getTimestamp("date"));
				question.setLabels(rs.getString("labels"));
				question.setUserName(rs.getString("userName"));
				question.setClassify(rs.getString("classify"));
				list.add(question);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
