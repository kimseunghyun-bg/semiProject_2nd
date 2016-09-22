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
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT kind_code, kind_name, kind_parent FROM kind ORDER BY kind_name");
			pstmt=conn.prepareStatement(sb.toString());
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
			sb.append("			SELECT IMAGE, NAME, PANMAE_NUM, k1.KIND_NAME kindName, SAVE_NUM,");
			sb.append("				k2.kind_name kindParentName,");
			sb.append("				SAVE_NUM SELL_NUM,");
			sb.append("				PRICE, TO_CHAR(CREATED,'YYYY-MM-DD') CREATED, PANMAE_STATE, p.PRODUCE_CODE, PRODUCE_CORPOR_NAME, PRODUCE_CORPOR_NUM");
			sb.append("			FROM panmae p");
			sb.append("			JOIN kind k1 ON p.kind_code=k1.kind_code");
			sb.append("			LEFT OUTER JOIN kind k2 ON k1.kind_parent=k2.kind_code");
			sb.append("			JOIN producer g ON p.PRODUCE_CODE=g.PRODUCE_CODE");
			sb.append("			ORDER BY created DESC)tb");
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
				dto.setKindName(rs.getString("kindName"));
				dto.setKindParentName(rs.getString("kindParentName"));
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
			sb.append("	SELECT image, name, panmae_num,  introduce, save_num, k1.kind_code kindCode,");
			sb.append("		k1.kind_name kindName, k2.kind_name kindParentName,");
			sb.append("		save_num sell_num, TO_CHAR(CREATED,'YYYY-MM-DD') created, price, panmae_state, p.produce_code,");
			sb.append("		produce_corpor_name, produce_corpor_num, corpor_address");
			sb.append("	FROM panmae p");
			sb.append("	JOIN kind k1 ON p.kind_code=k1.kind_code");
			sb.append("	LEFT OUTER JOIN kind k2 ON k1.kind_parent=k2.kind_code");
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
				dto.setKindCode(rs.getString("kindCode"));
				dto.setKindName(rs.getString("kindName"));
				dto.setKindParentName(rs.getString("kindParentName"));
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
			sb.append("	UPDATE panmae SET");
			if(dto.getImage()!=null)
				sb.append("	image=?,");
			sb.append("	name=?, introduce=?, save_num=save_num+?, panmae_state=? WHERE panmae_num=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			int n=1;
			if(dto.getImage()!=null)
				pstmt.setString(n++, dto.getImage());
			pstmt.setString(n++, dto.getName());
			pstmt.setString(n++, dto.getIntroduce());
			pstmt.setString(n++, dto.getSaveNum());
			pstmt.setString(n++, dto.getPanmaeState());
			pstmt.setString(n++, dto.getPanmaeNum());
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public int dataCount(String panmaeState, String groupCode, String kindCode, String searchKey, String searchValue){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	SELECT COUNT(*) FROM panmae p ");
			sb.append("	JOIN kind k ON p.kind_code=k.kind_code ");
			sb.append("	JOIN producer pro ON pro.produce_code=p.produce_code ");
			if(panmaeState!=null || groupCode!=null || kindCode!=null || searchKey!=null || searchValue!=null)
				sb.append(" WHERE ");
			if(panmaeState!=null)
				sb.append("	panmae_state=?");
			if(panmaeState!=null && groupCode!=null)
				sb.append("	AND ");
			if(groupCode!=null)
				sb.append("	kind_parent=?");
			if((panmaeState!=null || groupCode!=null)&&kindCode!=null)
				sb.append("	AND ");
			if(kindCode!=null)
				sb.append("	k.kind_code=?");
			if((panmaeState!=null || groupCode!=null || kindCode!=null) && searchKey!=null && searchValue!=null)
				sb.append("	AND ");
			if(searchKey!=null && searchValue!=null)
				sb.append("	INSTR(" + searchKey + ", ?) >= 1 ");
			
			pstmt=conn.prepareStatement(sb.toString());
			int n=1;
			
			if(panmaeState!=null)
				pstmt.setString(n++, panmaeState);
			if(groupCode!=null)
				pstmt.setString(n++, groupCode);
			if(kindCode!=null)
				pstmt.setString(n++, kindCode);
			if(searchKey!=null && searchValue!=null)
				pstmt.setString(n++, searchValue);
			
			rs=pstmt.executeQuery();
			
			if(rs.next())
				result=rs.getInt(1);
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
}
