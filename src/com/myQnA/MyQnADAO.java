package com.myQnA;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;


public class MyQnADAO  {
	private Connection conn=DBConn.getConnection();	
	
	//게시글을 추가한다.
	public int insertMyQnA(MyQnADTO dto, String mode){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		//등록되어 있는 게시글의 최대값에 1을 더한 값이 새로운 게시글의 번호가 된다.
		int maxNum=maxMyQnANum()+1;
		dto.setNum(maxNum);
		
		if(mode.equals("created")){
			dto.setGroupNum(maxNum);
			dto.setOrderNo(0);
			dto.setDepth(0);
			dto.setParent(0);
		}else{//reply : 답글을 다는 경우라면...
			updateOrderNo(dto.getGroupNum(), dto.getOrderNo());
			
			dto.setDepth(dto.getDepth()+1);
			dto.setOrderNo(dto.getOrderNo()+1);
		}
		
		try {
			sql="INSERT INTO MyQnA(NUM, MEMBERID, SUBJECT, CONTENT, "
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
	
	//해당하는 글번호의 데이터를 가져온다.
	public MyQnADTO readMyQnA(int num){
		
		MyQnADTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT NUM, B.MEMBERID, NAME, SUBJECT, CONTENT, ");
			sb.append("B.CREATED, HITCOUNT, GROUPNUM, DEPTH, ORDERNO, PARENT ");
			sb.append("FROM boardQnA B ");
			sb.append("JOIN MEMBER M ON B.MEMBERID=M.MEMBERID ");
			sb.append("WHERE B.NUM=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new MyQnADTO();
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
	
	//전체 데이터 중, 게시판 번호가 가장 큰 수를 구한다.
	public int maxMyQnANum(){
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
	
	//전체 데이터 수를 구한다.
	public int dataCount(String memberId){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA WHERE memberID=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
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
	
	//검색된 결과의 전체 데이터 수를 구한다.
	public int dataCount(String searchKey, String searchValue, String memberId){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			//주의 : MEMBER와 BOARDQNA의 CREATED 컬럼의 중복으로 오류가 발생할 수 있다.
			if(searchKey.equals("created"))
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE B.MEMBERID=? AND TO_CHAR(CREATED, 'YYYY-MM-DD')=?";
			else if(searchKey.equals("userName"))
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE B.MEMBERID=? AND INSTR(NAME, ?)=1";
			else
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDQNA B "
						+ "JOIN MEMBER M ON B.MEMBERID=M.MEMBERID "
						+ "WHERE B.MEMBERID=? AND INSTR("+searchKey+", ?)>=1";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, searchValue);
			
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
	
	//검색을 안한 상태의 리스트를 구한다.
	public List<MyQnADTO> listMyQnA(int start, int end, String memberId){
		List<MyQnADTO> list=new ArrayList<>();
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
					+ "     WHERE B.MEMBERID=? "
					+ "     ORDER BY GROUPNUM DESC, ORDERNO ASC "
					+ ") TB WHERE ROWNUM<=?"
				+ ") WHERE RNUM>=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setInt(2, end);
			pstmt.setInt(3, start);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MyQnADTO dto=new MyQnADTO();
				
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
	
	//검색한 상태에서의 리스트를 구한다.
	public List<MyQnADTO> listMyQnA(int start, int end, String searchKey, String searchValue, String memberId){
		List<MyQnADTO> list=new ArrayList<>();
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
			sb.append("    WHERE B.MEMBERID=?");
			if(searchKey.equals("created"))
				sb.append("  AND TO_CHAR(CREATED, 'YYYY-MM-DD')=? ");
			else if(searchKey.equals("userName"))
				sb.append("  AND INSTR(NAME, ?)=1 ");
			else
				sb.append("  AND INSTR("+searchKey+", ?)>=1 ");
			sb.append("    ORDER BY GROUPNUM DESC, ORDERNO ASC ");
			sb.append("  ) TB WHERE ROWNUM<=? ");
			sb.append(") WHERE RNUM>=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, memberId);
			pstmt.setString(2, searchValue);
			pstmt.setInt(3, end);
			pstmt.setInt(4, start);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				MyQnADTO dto=new MyQnADTO();
				
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
	
	//등록하기 이전에 존재한 글들의 orderNo를 수정한다.
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
	
	//조회수를 수정한다.
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
	
	//이전글
	public MyQnADTO preReadMyQnA(int groupNum, int orderNo, String searchKey, String searchValue){
		MyQnADTO dto=null;
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
				dto=new MyQnADTO();
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
	
	//다음글
	public MyQnADTO nextReadMyQnA(int groupNum, int orderNo, String searchKey, String searchValue){
		MyQnADTO dto=null;
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
				dto=new MyQnADTO();
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
	
	public int updateMyQnA(MyQnADTO dto){
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
	
	public int deleteMyQnA(int num){
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