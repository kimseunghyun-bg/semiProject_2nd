package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import com.util.DBConn;

public class AdminGoodsDAO {
	Connection conn=DBConn.getConnection();
	
	public int insertGoods(AdminGoodsDTO dto){
		//상품추가
		int result=0;
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("INSERT INTO PANMAE(panmae_num, kind_code, produce_code, name, introduce,");
			sb.append(" save_num, price, panmae_state, image) VALUES");
			sb.append(" (panmae_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?)");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getKindCode());
			pstmt.setString(2, dto.getProduceCode());
			pstmt.setString(3, dto.getName());
			pstmt.setString(4, dto.getIntroduce());
			pstmt.setString(5, dto.getSaveNum());
			pstmt.setString(6, dto.getPrice());
			pstmt.setString(7, dto.getPanmaeState());
			pstmt.setString(8, dto.getImage());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;		
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public Map<String,String> groupMajor(){
		Map<String, String> map=new LinkedHashMap<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql="SELECT kind_code, kind_name FROM kind WHERE kind_parent IS NULL ORDER BY kind_name";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next())
				map.put(rs.getString(2), rs.getString(1));
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return map;
	}
	
	public Map<String,String> groupMinor(int parent){
		Map<String, String> map=new LinkedHashMap<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql="SELECT kind_code, kind_name FROM kind WHERE kind_parent = ? ORDER BY kind_name";
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, parent);
			rs=pstmt.executeQuery();
			while(rs.next())
				map.put(rs.getString(2), rs.getString(1));
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return map;
	}
	
}

