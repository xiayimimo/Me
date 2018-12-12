package com.ll.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;

import com.ll.entity.Label;
import com.ll.utils.DBUtil;

public class LabelDAO {

	public List<Label> queryAll(){
		List<Label> list = new ArrayList<Label>(); 
		Jedis client= new Jedis("localhost", 6379);
		long size = client.llen("labelList".getBytes());
		if(size != 0){
			List<byte[]> labelListByte = client.lrange("labelList".getBytes(), 0, -1);
			for (byte[] bs : labelListByte) {
				ByteArrayInputStream bin = new ByteArrayInputStream(bs);
				ObjectInputStream obj = null;
				try {
					obj = new ObjectInputStream(bin);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Label label = (Label) obj.readObject();
					list.add(label);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list;
		}
		Connection conn =  DBUtil.getConn();
		String sql ="select * from label where 1 = 1 ";
		try {
			PreparedStatement pcmd = conn.prepareStatement(sql);
			ResultSet rs = pcmd.executeQuery();
			
			while(rs.next()){
				Label label = new Label();
				label.setId(rs.getInt("id"));
				label.setName(rs.getString("name"));
				list.add(label);
				//加入redis中
				ByteArrayOutputStream bout = new ByteArrayOutputStream();
				try {
					ObjectOutputStream oop = new ObjectOutputStream(bout);
					oop.writeObject(label);
					oop.flush();
					oop.close();
					byte[] b = bout.toByteArray();
					client.lpush("labelList".getBytes(), b);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
		return null;
	}
}
