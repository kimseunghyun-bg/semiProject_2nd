package com.boardQnA;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class BoardQnADAO {
	private Connection conn=DBConn.getConnection();
	
	//�Խñ��� �߰��Ѵ�.
	public int insertBoardQnA(BoardQnADTO dto, String mode){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		//��ϵǾ� �ִ� �Խñ��� �ִ밪�� 1�� ���� ���� ���ο� �Խñ��� ��ȣ�� �ȴ�.
		int maxNum=maxBoardQnANum()+1;
		dto.setNum(maxNum);
		
		if(mode.equals("created")){
			dto.setGroupNum(maxNum);
			dto.setOrderNo(0);
			dto.setDepth(0);
			dto.setParent(0);
		}else{//reply : ����� �ٴ� �����...
			updateOrderNo(dto.getGroupNum(), dto.getOrderNo());
			
			dto.setDepth(dto.getDepth()+1);
			dto.setOrderNo(dto.getOrderNo()+1);
		}
		
		try {
			sql="INSERT INTO BOARDQNA(NUM, MEMBERID, SUBJECT, CONTENT, "
					+ "GROUPNUM, DEPTH, ORDERNO, PARENT) "
					+ "VALUES(?,?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, dto.getNum());
			pstmt.setString(2, dto.getMemberId());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setInt(5, dto.getGroupNum());
			pstmt.setInt(6, dto.getDepth());
			pstmt.setInt(7, dto.getOrderNo());
			pstmt.setInt(8, dto.getParent());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//�ش��ϴ� �۹�ȣ�� �����͸� �����´�.
	public BoardQnADTO readBoardQnA(int num){
		BoardQnADTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT NUM, B.MEMBERID, NAME, SUBJECT, CONTENT, ");
			sb.append("B.CREATED, HITCOUNT, GROUPNUM, DEPTH, ORDERNO, PARENT ");
			sb.append("FROM BOARDQNA B ");
			sb.append("JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
			sb.append("WHERE NUM=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardQnADTO();
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setGroupNum(rs.getInt("GROUPNUM"));
				dto.setOrderNo(rs.getInt("ORDERNO"));
				dto.setDepth(rs.getInt("DEPTH"));
				dto.setParent(rs.getInt("PARENT"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
				dto.setCreated(rs.getString("CREATED"));
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	//��ü ������ ��, �Խ��� ��ȣ�� ���� ū ���� ���Ѵ�.
	public int maxBoardQnANum(){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			sql="SELECT NVL(MAX(NUM), 0) FROM BOARDQNA";
			
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
			sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA";
			
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
			//���� : MEMBER�� BOARDQNA�� CREATED �÷��� �ߺ����� ������ �߻��� �� �ִ�.
			if(searchKey.equals("created"))
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE TO_CHAR(CREATED, 'YYYY-MM-DD')=?";
			else if(searchKey.equals("userName"))
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE INSTR(NAME, ?)=1";
			else
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE INSTR("+searchKey+", ?)>=1";
			
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
	public List<BoardQnADTO> listBoardQnA(int start, int end){
		List<BoardQnADTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			sql="SELECT * FROM( "
					+ "SELECT ROWNUM RNUM, TB.* FROM( "
					+ "     SELECT NUM, B.MEMBERID, NAME, "
					+ "     SUBJECT, GROUPNUM, ORDERNO, DEPTH, HITCOUNT, B.CREATED "
					+ "     FROM BOARDQNA B "
					+ "     JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
					+ "     ORDER BY GROUPNUM DESC, ORDERNO ASC "
					+ ") TB WHERE ROWNUM<=?"
				+ ") WHERE RNUM>=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardQnADTO dto=new BoardQnADTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setGroupNum(rs.getInt("GROUPNUM"));
				dto.setOrderNo(rs.getInt("ORDERNO"));
				dto.setDepth(rs.getInt("DEPTH"));
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
	public List<BoardQnADTO> listBoardQnA(int start, int end, String searchKey, String searchValue){
		List<BoardQnADTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT * FROM ( ");
			sb.append("  SELECT ROWNUM RNUM, TB.* FROM( ");
			sb.append("    SELECT NUM, B.MEMBERID, NAME, ");
			sb.append("    SUBJECT, GROUPNUM, ORDERNO, DEPTH, HITCOUNT, B.CREATED ");
			sb.append("    FROM BOARDQNA B ");
			sb.append("    JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
			if(searchKey.equals("created"))
				sb.append("WHERE TO_CHAR(CREATED, 'YYYY-MM-DD')=? ");
			else if(searchKey.equals("userName"))
				sb.append("WHERE INSTR(NAME, ?)=1 ");
			else
				sb.append("WHERE INSTR("+searchKey+", ?)>=1 ");
			sb.append("    ORDER BY GROUPNUM DESC, ORDERNO ASC ");
			sb.append("  ) TB WHERE ROWNUM<=? ");
			sb.append(") WHERE RNUM>=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, searchValue);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardQnADTO dto=new BoardQnADTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setGroupNum(rs.getInt("GROUPNUM"));
				dto.setOrderNo(rs.getInt("ORDERNO"));
				dto.setDepth(rs.getInt("DEPTH"));
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
	
	//����ϱ� ������ ������ �۵��� orderNo�� �����Ѵ�.
	public int updateOrderNo(int groupNum, int orderNo){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="UPDATE BOARDQNA SET ORDERNO=ORDERNO+1 "
					+ "WHERE GROUPNUM=? AND ORDERNO>?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, groupNum);
			pstmt.setInt(2, orderNo);
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//��ȸ���� �����Ѵ�.
	public int updateHitCount(int num){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="UPDATE BOARDQNA SET HITCOUNT=HITCOUNT+1 WHERE NUM=?";
		
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
	
	//������
	public BoardQnADTO preReadBoardQnA(int groupNum, int orderNo, String searchKey, String searchValue){
		BoardQnADTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(searchValue!=null && searchValue.length()!=0){
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("SELECT NUM, SUBJECT ");
				sb.append("FROM BOARDQNA B ");
				sb.append("JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
				if(searchKey.equals("created"))
					sb.append("WHERE (TO_CHAR(CREATED, 'YYYY-MM-DD')=?) AND ");
				else if(searchKey.equals("userName"))
					sb.append("WHERE (INSTR(NAME, ?)=1) AND ");
				else
					sb.append("WHERE (INSTR("+searchKey+", ?)>=1 ) AND ");
				sb.append("((GROUPNUM=? AND ORDERNO<?) ");
				sb.append("OR (GROUPNUM>?)) ");
				sb.append("ORDER BY GROUPNUM ASC, ORDERNO DESC) TB WHERE ROWNUM=1");
			
				pstmt=conn.prepareStatement(sb.toString());
				
				pstmt.setString(1, searchValue);
				pstmt.setInt(2, groupNum);
				pstmt.setInt(3, orderNo);
				pstmt.setInt(4, groupNum);
			}else{
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("SELECT NUM, SUBJECT ");
				sb.append("FROM BOARDQNA B ");
				sb.append("JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
				sb.append("WHERE(GROUPNUM=? AND ORDERNO<?) ");
				sb.append("OR (GROUPNUM>?) ");
				sb.append("ORDER BY GROUPNUM ASC, ORDERNO DESC) TB WHERE ROWNUM=1");
			
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setInt(1, groupNum);
				pstmt.setInt(2, orderNo);
				pstmt.setInt(3, groupNum);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardQnADTO();
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
	
	//������
	public BoardQnADTO nextReadBoardQnA(int groupNum, int orderNo, String searchKey, String searchValue){
		BoardQnADTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(searchValue!=null && searchValue.length()!=0){
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("SELECT NUM, SUBJECT ");
				sb.append("FROM BOARDQNA B ");
				sb.append("JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
				if(searchKey.equals("created"))
					sb.append("WHERE (TO_CHAR(CREATED, 'YYYY-MM-DD')=?) AND ");
				else if(searchKey.equals("userName"))
					sb.append("WHERE (INSTR(NAME, ?)=1) AND ");
				else
					sb.append("WHERE (INSTR("+searchKey+", ?)>=1 ) AND ");
				sb.append("((GROUPNUM=? AND ORDERNO>?) ");
				sb.append("OR (GROUPNUM<?)) ");
				sb.append("ORDER BY GROUPNUM DESC, ORDERNO ASC) TB WHERE ROWNUM=1");
			
				pstmt=conn.prepareStatement(sb.toString());
				
				pstmt.setString(1, searchValue);
				pstmt.setInt(2, groupNum);
				pstmt.setInt(3, orderNo);
				pstmt.setInt(4, groupNum);
			}else{
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("SELECT NUM, SUBJECT ");
				sb.append("FROM BOARDQNA B ");
				sb.append("JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
				sb.append("WHERE(GROUPNUM=? AND ORDERNO>?) ");
				sb.append("OR (GROUPNUM<?) ");
				sb.append("ORDER BY GROUPNUM DESC, ORDERNO ASC) TB WHERE ROWNUM=1");
			
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setInt(1, groupNum);
				pstmt.setInt(2, orderNo);
				pstmt.setInt(3, groupNum);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardQnADTO();
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
	
	public int updateBoardQnA(BoardQnADTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="UPDATE BOARDQNA SET SUBJECT=?, CONTENT=? "
					+ "WHERE NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNum());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public int deleteBoardQnA(int num){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="DELETE FROM BOARDQNA WHERE NUM IN "
					+ "(SELECT NUM FROM BOARDQNA START WITH NUM=? "
					+ "CONNECT BY PRIOR NUM=PARENT)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
}