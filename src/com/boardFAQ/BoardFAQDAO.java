package com.boardFAQ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class BoardFAQDAO {
	private Connection conn=DBConn.getConnection();
	
	//FAQ �߰�
	public int insertBoardFAQ(BoardFAQDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		int maxNum=maxBoardFAQNum()+1;
		dto.setNum(maxNum);
		
		try {
			sql="INSERT INTO BOARDFAQ(NUM, SUBJECT, CONTENT, MEMBERID) "
					+ "VALUES(?,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getMemberId());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//��ü ������ ��, �Խ��� ��ȣ�� ���� ū ���� ���Ѵ�.
	public int maxBoardFAQNum(){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			sql="SELECT NVL(MAX(NUM), 0) FROM BOARDFAQ";
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next())
				result=rs.getInt(1);
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//��ü ������ ���� ���Ѵ�.
	public int dataCount(){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			sql="SELECT NVL(COUNT(*), 0) FROM BOARDFAQ";
			
			pstmt=conn.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next())
				result=rs.getInt(1);
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//�˻��� ����� ��ü ������ ���� ���Ѵ�.
	public int dataCount(String searchKey, String searchValue){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			if(searchKey.equals("created")){
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDFAQ B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE TO_CHAR(CREATED, 'YYYY-MM-DD')=?";
			}else if(searchKey.equals("userName")){
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDFAQ B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE INSTR(NAME, ?)=1";
			}else{
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDFAQ B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE INSTR("+searchKey+", ?)>=1";
			}
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, searchValue);
			
			rs=pstmt.executeQuery();
			
			if(rs.next())
				result=rs.getInt(1);
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//�˻��� ���� ������ ����Ʈ�� ���Ѵ�.
	public List<BoardFAQDTO> listBoardFAQ(int start, int end){
		List<BoardFAQDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT * FROM( ");
			sb.append("  SELECT ROWNUM RNUM, TB.* FROM( ");
			sb.append("    SELECT NUM, B.MEMBERID, NAME, ");
			sb.append("    SUBJECT, HITCOUNT, ");
			sb.append("    TO_CHAR(B.CREATED, 'YYYY-MM-DD') CREATED ");
			sb.append("    FROM BOARDFAQ B ");
			sb.append("    JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
			sb.append("    ORDER BY NUM DESC ");
			sb.append("  ) TB WHERE ROWNUM<=? ");
			sb.append(") WHERE RNUM>=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardFAQDTO dto=new BoardFAQDTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
				dto.setCreated(rs.getString("CREATED"));
				
				list.add(dto);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	
	//�˻��� ���¿����� ����Ʈ�� ���Ѵ�.
	public List<BoardFAQDTO> listBoardFAQ(int start, int end, String searchKey, String searchValue){
		List<BoardFAQDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT * FROM( ");
			sb.append("  SELECT ROWNUM RNUM, TB.* FROM( ");
			sb.append("    SELECT NUM, B.MEMBERID, NAME, ");
			sb.append("    SUBJECT, HITCOUNT, ");
			sb.append("    TO_CHAR(B.CREATED, 'YYYY-MM-DD') CREATED ");
			sb.append("    FROM BOARDFAQ B ");
			sb.append("    JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
			if(searchKey.equals("created"))
				sb.append("WHERE TO_CHAR(CREATED, 'YYYY-MM-DD')=? ");
			else if(searchKey.equals("userName"))
				sb.append("WHERE INSTR(NAME, ?)=1 ");
			else
				sb.append("WHERE INSTR("+searchKey+", ?)>=1 ");
			sb.append("    ORDER BY NUM DESC ");
			sb.append("  ) TB WHERE ROWNUM<=? ");
			sb.append(") WHERE RNUM>=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardFAQDTO dto=new BoardFAQDTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
				dto.setCreated(rs.getString("CREATED"));
				
				list.add(dto);
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	
	//��ȸ���� �����Ѵ�.
	public int updateHitCount(int num){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="UPDATE BOARDFAQ SET HITCOUNT=HITCOUNT+1 WHERE NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//������ ��������
	public BoardFAQDTO preReadBoardFAQ(int num, String searchKey, String searchValue){
		BoardFAQDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(searchValue!=null && searchValue.length()!=0){
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDFAQ ");
				if(searchKey.equalsIgnoreCase("created"))
					sb.append("WHERE (TO_CHAR(CREATED, 'YYYY-MM-DD')=?) ");
				else if(searchKey.equalsIgnoreCase("name"))
					sb.append("WHERE (INSTR(NAME, ?)=1) ");
				else
					sb.append("WHERE (INSTR("+searchKey+", ?)>=1) ");
				sb.append("AND (NUM>?) ");
				sb.append("ORDER BY NUM ASC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, searchValue);
				pstmt.setInt(2, num);
			}else{
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDFAQ ");
				sb.append("  WHERE NUM>? ");
				sb.append("  ORDER BY NUM ASC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardFAQDTO();
				dto.setNum(rs.getInt("NUM"));
				dto.setSubject(rs.getString("SUBJECT"));
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	//������ ��������
	public BoardFAQDTO nextReadBoardFAQ(int num, String searchKey, String searchValue){
		BoardFAQDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(searchValue!=null && searchValue.length()!=0){
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDFAQ ");
				if(searchKey.equalsIgnoreCase("created"))
					sb.append("WHERE (TO_CHAR(CREATED, 'YYYY-MM-DD')=?) ");
				else if(searchKey.equalsIgnoreCase("name"))
					sb.append("WHERE (INSTR(NAME, ?)=1) ");
				else
					sb.append("WHERE (INSTR("+searchKey+", ?)>=1) ");
				sb.append("AND (NUM<?) ");
				sb.append("ORDER BY NUM DESC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, searchValue);
				pstmt.setInt(2, num);
			}else{
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDFAQ ");
				sb.append("  WHERE NUM<? ");
				sb.append("  ORDER BY NUM DESC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardFAQDTO();
				dto.setNum(rs.getInt("NUM"));
				dto.setSubject(rs.getString("SUBJECT"));
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	//�ش� ��ȣ�� �Խñ��� �ҷ��´�.
	public BoardFAQDTO readBoardFAQ(int num){
		BoardFAQDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT NUM, F.MEMBERID, NAME, SUBJECT, ");
			sb.append("CONTENT, F.CREATED, HITCOUNT ");
			sb.append("FROM BOARDFAQ F ");
			sb.append("JOIN MEMBER M ON F.MEMBERID=M.MEMBERID ");
			sb.append("WHERE NUM=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardFAQDTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setCreated(rs.getString("CREATED"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	//FAQ ����
	public int updateBoardFAQ(BoardFAQDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="UPDATE BOARDFAQ SET SUBJECT=?, CONTENT=? "
					+ "WHERE NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//FAQ ����
	public int deleteBoard(int num){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="DELETE FROM BOARDFAQ WHERE NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
}