package com.ll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ll.entity.Answer;
import com.ll.entity.Question;
import com.ll.utils.DBUtil;

public class AnswerDAO {
	/**
	 * 删除答案
	 * @param answerId
	 * @return
	 */
	public boolean delAnswer(int answerId){
		Connection conn =  DBUtil.getConn();
		String sql ="delete from answer where id = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, answerId);
			pcmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	/**
	 * 根据当前页 显示答案
	 * @param questionId
	 * @param currentPage
	 * @return
	 */
	public List<Answer> queryAnswer(int questionId,int currentPage){
		Connection conn =  DBUtil.getConn();
		List<Answer> list = new ArrayList<Answer>(); 
		int count = 3;
		int startIndex = (currentPage-1)*count;
		String sql ="select * from answer where questionId = ? limit ?,?";
		
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, questionId);
			pcmd.setInt(2, startIndex);
			pcmd.setInt(3, count);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				Answer Answer = new Answer();
				Answer.setId(rs.getInt("id"));
				Answer.setQuestionId(rs.getInt("questionId"));
				Answer.setTicketCount(rs.getInt("ticketCount"));
				Answer.setUserName(rs.getString("userName"));
				Answer.setAnswerTime(rs.getDate("answerTime"));
				Answer.setContent(rs.getString("content"));
				list.add(Answer);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
		
		
	}
	/**
 *添加回答 
 * @param a
 * @return
 */
	public boolean insertAnswer(Answer a){
		Connection conn =  DBUtil.getConn();
		String sql ="insert into answer (content,userName,questionId)values(?,?,?)";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setString(1, a.getContent());
			pcmd.setString(2, a.getUserName());
			pcmd.setInt(3, a.getQuestionId());
			pcmd.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
 * 根据问题id查找回答
 * @param questionId
 * @return
 */
	public List<Answer> queryAllAnswer(int questionId){
		Connection conn =  DBUtil.getConn();
		List<Answer> list = new ArrayList<Answer>(); 
		String sql ="select * from answer where questionId = ?";
		
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, questionId);
			ResultSet rs = pcmd.executeQuery();
			while(rs.next()){
				Answer Answer = new Answer();
				Answer.setId(rs.getInt("id"));
				Answer.setQuestionId(rs.getInt("questionId"));
				Answer.setTicketCount(rs.getInt("ticketCount"));
				Answer.setUserName(rs.getString("userName"));
				Answer.setAnswerTime(rs.getDate("answerTime"));
				Answer.setContent(rs.getString("content"));
				list.add(Answer);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
		
		
	}
	/**
	 * 投票
	 * @param id
	 * @param count
	 * @return
	 */
	public int updateAnswerTicket(int id,int count){
		Connection conn =  DBUtil.getConn();
		String sql ="UPDATE answer SET ticketCount = ticketCount + ? WHERE id = ?";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			pcmd.setInt(1, count);
			pcmd.setInt(2, id);
			pcmd.executeUpdate();
			sql ="select ticketCount from answer WHERE id = ?";
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
	
}
