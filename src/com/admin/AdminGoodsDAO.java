package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

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
	
	public List<AdminGoodsDTO> group(){
		List<AdminGoodsDTO> list=new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql;
		
		try {
			sql="SELECT kind_code, kind_name, kind_parent FROM kind ORDER BY kind_name";
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				AdminGoodsDTO dto=new AdminGoodsDTO();
				dto.setKindCode(rs.getString("kind_code"));
				dto.setKindName(rs.getString("kind_name"));
				dto.setKindParent(rs.getString("kind_parent"));
				
				list.add(dto);
			}
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	/*
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
	*/
	
	public List<AdminGoodsDTO> producer(){
		List<AdminGoodsDTO> list=new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT produce_code, produce_corpor_name, produce_corpor_num, corpor_address ");
			sb.append(" FROM producer ORDER BY produce_corpor_name");
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				AdminGoodsDTO dto=new AdminGoodsDTO();
				dto.setProduceCode(rs.getString("produce_code"));
				dto.setProduceCorporName(rs.getString("produce_corpor_name"));
				dto.setProduceCorporNum(rs.getString("produce_corpor_num"));
				dto.setCorporAddress(rs.getString("corpor_address"));
				
				list.add(dto);
			}
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	
	public List<AdminGoodsDTO> listPanmae(int start, int end){
		List<AdminGoodsDTO> list=new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	SELECT * FROM(");
			sb.append("		SELECT ROWNUM rnum, tb.* FROM(");
			sb.append("			SELECT IMAGE, NAME, PANMAE_NUM, KIND_NAME, SAVE_NUM,");
			sb.append("				SAVE_NUM SELL_NUM,");
			sb.append("				PRICE, TO_CHAR(CREATED,'YYYY-MM-DD') CREATED, PANMAE_STATE, p.PRODUCE_CODE, PRODUCE_CORPOR_NAME, PRODUCE_CORPOR_NUM");
			sb.append("			FROM panmae p");
			sb.append("			JOIN kind k ON p.kind_code=k.kind_code");
			sb.append("			JOIN producer g ON p.PRODUCE_CODE=g.PRODUCE_CODE");
			sb.append("			ORDER BY created)tb");
			sb.append("		WHERE ROWNUM <=?");
			sb.append("	)WHERE rnum >=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				AdminGoodsDTO dto=new AdminGoodsDTO();
				
				dto.setImage(rs.getString("IMAGE"));
				dto.setName(rs.getString("NAME"));
				dto.setPanmaeNum(rs.getString("PANMAE_NUM"));
				dto.setKindName(rs.getString("KIND_NAME"));
				dto.setSaveNum(rs.getString("SAVE_NUM"));
				dto.setSellNum(rs.getString("SELL_NUM"));
				dto.setPrice(rs.getString("PRICE"));
				dto.setCreated(rs.getString("CREATED"));
				dto.setPanmaeState(rs.getString("PANMAE_STATE"));
				dto.setProduceCode(rs.getString("PRODUCE_CODE"));
				dto.setProduceCorporName(rs.getString("PRODUCE_CORPOR_NAME"));
				dto.setProduceCorporNum(rs.getString("PRODUCE_CORPOR_NUM"));
				
				list.add(dto);
			}
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	
	public AdminGoodsDTO readPanmae(int panmaeNum){
		AdminGoodsDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	SELECT image, name, panmae_num, kind_name, introduce, save_num,");
			sb.append("		save_num sell_num, TO_CHAR(CREATED,'YYYY-MM-DD') created, price, panmae_state, p.produce_code,");
			sb.append("		produce_corpor_name, produce_corpor_num, corpor_address");
			sb.append("	FROM panmae p");
			sb.append("	JOIN kind k ON p.kind_code=k.kind_code");
			sb.append("	JOIN producer g ON p.PRODUCE_CODE=g.PRODUCE_CODE");
			sb.append("	WHERE panmae_num=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, panmaeNum);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new AdminGoodsDTO();
				
				dto.setImage(rs.getString("image"));
				dto.setName(rs.getString("name"));
				dto.setPanmaeNum(rs.getString("panmae_num"));
				dto.setKindName(rs.getString("kind_name"));
				dto.setIntroduce(rs.getString("introduce"));
				dto.setSaveNum(rs.getString("save_num"));
				dto.setSellNum(rs.getString("sell_num"));
				dto.setCreated(rs.getString("created"));
				dto.setPrice(rs.getString("price"));
				dto.setPanmaeState(rs.getString("panmae_state"));
				dto.setProduceCode(rs.getString("produce_code"));
				dto.setProduceCorporName(rs.getString("produce_corpor_name"));
				dto.setProduceCorporNum(rs.getString("produce_corpor_num"));
				dto.setCorporAddress(rs.getString("corpor_address"));
			}
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	public int updatePanmae(AdminGoodsDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	UPDATE panmae SET image=?, name=?, introduce=?, save_num=save_num+?, panmae_state=? WHERE panmae_num=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getImage());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getIntroduce());
			pstmt.setString(4, dto.getSaveNum());
			pstmt.setString(5, dto.getPanmaeState());
			pstmt.setString(6, dto.getPanmaeNum());
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
}

