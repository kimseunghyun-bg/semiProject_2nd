package com.boardNotice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class BoardNoticeDAO {
	private Connection conn=DBConn.getConnection();
	
	//공지사항 추가
	public int insertBoardNotice(BoardNoticeDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="INSERT INTO BOARDNOTICE("
					+ "NUM, NOTICE, SUBJECT, CONTENT, "
					+ "SAVEFILENAME, ORIGINALFILENAME, FILESIZE, MEMBERID) "
					+ "VALUES(BOARDNOTICE_SEQ.NEXTVAL,?,?,?,?,?,?,?)";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getNotice());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getSaveFilename());
			pstmt.setString(5, dto.getOriginalFilename());
			pstmt.setLong(6, dto.getFilesize());
			pstmt.setString(7, dto.getMemberId());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//전체 게시물 수를 구한다.
	public int dataCount(){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			sql="SELECT NVL(COUNT(*), 0) FROM BOARDNOTICE";
			
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
	
	//검색된 상태에서의 게시글의 수를 구한다.
	public int dataCount(String searchKey, String searchValue){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String sql="";
		
		try {
			if(searchKey.equals("created")){
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDNOTICE N "
						+ "JOIN MEMBER M ON N.MEMBERID=M.MEMBERID "
						+ "WHERE TO_CHAR(N.CREATED, 'YYYY-MM-DD')=?";
			}else if(searchKey.equals("userName")){
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDNOTICE N "
						+ "JOIN MEMBER M ON N.MEMBERID=M.MEMBERID "
						+ "WHERE INSTR(NAME, ?)=1";
			}else{
				sql="SELECT NVL(COUNT(*), 0) FROM BOARDNOTICE N "
						+ "JOIN MEMBER M ON N.MEMBERID=M.MEMBERID "
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
	
	//맨 위에 따로 표시할 공지사항을 출력한다.
	public List<BoardNoticeDTO> listBoardNoticeTop(){
		List<BoardNoticeDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT NUM, N.MEMBERID, NAME, ");
			sb.append("SUBJECT, HITCOUNT, SAVEFILENAME, ");
			sb.append("TO_CHAR(N.CREATED, 'YYYY-MM-DD') CREATED ");
			sb.append("FROM BOARDNOTICE N ");
			sb.append("JOIN MEMBER M ON N.MEMBERID=M.MEMBERID ");
			sb.append("WHERE NOTICE=1 ");
			sb.append("ORDER BY NUM DESC");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardNoticeDTO dto=new BoardNoticeDTO();
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
				dto.setSaveFilename(rs.getString("SAVEFILENAME"));
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
	
	//검색이 안된 상태에서 게시글을 가져온다.
	public List<BoardNoticeDTO> listBoardNotice(int start, int end){
		List<BoardNoticeDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT * FROM ( ");
			sb.append("  SELECT ROWNUM RNUM, TB.* FROM ( ");
			sb.append("    SELECT NUM, N.MEMBERID, NAME, ");
			sb.append("    SUBJECT, HITCOUNT, SAVEFILENAME, ");
			sb.append("    TO_CHAR(N.CREATED, 'YYYY-MM-DD') CREATED ");
			sb.append("    FROM BOARDNOTICE N ");
			sb.append("    JOIN MEMBER M ON N.MEMBERID=M.MEMBERID ");
			sb.append("    ORDER BY NUM DESC ");
			sb.append("  ) TB WHERE ROWNUM<=? ");
			sb.append(") WHERE RNUM>=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				BoardNoticeDTO dto=new BoardNoticeDTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
				dto.setSaveFilename(rs.getString("SAVEFILENAME"));
				dto.setCreated(rs.getString("CREATED"));
				
				list.add(dto);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return list;
	}
	
	//검색된 상태에서 게시글을 가져온다.
	public List<BoardNoticeDTO> listBoardNotice(int start, int end, String searchKey, String searchValue){
		List<BoardNoticeDTO> list=new ArrayList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT * FROM ( ");
			sb.append("  SELECT ROWNUM RNUM, TB.* FROM( ");
			sb.append("    SELECT NUM, N.MEMBERID, NAME, ");
			sb.append("    SUBJECT, HITCOUNT, SAVEFILENAME, ");
			sb.append("    TO_CHAR(N.CREATED, 'YYYY-MM-DD') CREATED ");
			sb.append("    FROM BOARDNOTICE N ");
			sb.append("    JOIN MEMBER M ON N.MEMBERID=M.MEMBERID ");
			if(searchKey.equals("created"))
				sb.append("WHERE TO_CHAR(N.CREATED, 'YYYY-MM-DD')=? ");
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
				BoardNoticeDTO dto=new BoardNoticeDTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
				dto.setSaveFilename(rs.getString("SAVEFILENAME"));
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
	
	//해당 번호의 게시글을 불러온다.
	public BoardNoticeDTO readBoardNotice(int num){
		BoardNoticeDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT NUM, NOTICE, N.MEMBERID, NAME, SUBJECT, ");
			sb.append("CONTENT, N.CREATED, HITCOUNT, SAVEFILENAME, ");
			sb.append("ORIGINALFILENAME, FILESIZE ");
			sb.append("FROM BOARDNOTICE N ");
			sb.append("JOIN MEMBER M ON N.MEMBERID=M.MEMBERID ");
			sb.append("WHERE NUM=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardNoticeDTO();
				
				dto.setNum(rs.getInt("NUM"));
				dto.setNotice(rs.getInt("NOTICE"));
				dto.setMemberId(rs.getString("MEMBERID"));
				dto.setName(rs.getString("NAME"));
				dto.setSubject(rs.getString("SUBJECT"));
				dto.setContent(rs.getString("CONTENT"));
				dto.setCreated(rs.getString("CREATED"));
				dto.setHitCount(rs.getInt("HITCOUNT"));
				dto.setSaveFilename(rs.getString("SAVEFILENAME"));
				dto.setOriginalFilename(rs.getString("ORIGINALFILENAME"));
				dto.setFilesize(rs.getLong("FILESIZE"));		
			}
			
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return dto;
	}
	
	//이전글을 구한다.
	public BoardNoticeDTO preReadBoardNotice(int num, String searchKey, String searchValue){
		BoardNoticeDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(searchValue!=null && searchValue.length()!=0){
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDNOTICE ");
				if(searchKey.equalsIgnoreCase("created"))
					sb.append("  WHERE (TO_CHAR(CREATED, 'YYYY-MM-DD')=?) ");
				else if(searchKey.equalsIgnoreCase("name"))
					sb.append("  WHERE (INSTR(NAME, ?)=1) ");
				else
					sb.append("  WHERE (INSTR("+searchKey+", ?)>=1) ");
				sb.append("  AND (NUM>?) ");
				sb.append("  ORDER BY NUM ASC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, searchValue);
				pstmt.setInt(2, num);
			}else{
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDNOTICE ");
				sb.append("  WHERE NUM>? ");
				sb.append("  ORDER BY NUM ASC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardNoticeDTO();
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
	
	//다음글을 구한다
	public BoardNoticeDTO nextReadBoardNotice(int num, String searchKey, String searchValue){
		BoardNoticeDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			if(searchValue!=null && searchValue.length()!=0){
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDNOTICE ");
				if(searchKey.equalsIgnoreCase("created"))
					sb.append("  WHERE (TO_CHAR(CREATED, 'YYYY-MM-DD')=?) ");
				else if(searchKey.equalsIgnoreCase("name"))
					sb.append("  WHERE (INSTR(NAME, ?)=1) ");
				else
					sb.append("  WHERE (INSTR("+searchKey+", ?)>=1) ");
				sb.append("  AND (NUM<?) ");
				sb.append("  ORDER BY NUM DESC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setString(1, searchValue);
				pstmt.setInt(2, num);
			}else{
				sb.append("SELECT ROWNUM, TB.* FROM( ");
				sb.append("  SELECT NUM, SUBJECT FROM BOARDNOTICE ");
				sb.append("  WHERE NUM<? ");
				sb.append("  ORDER BY NUM DESC ");
				sb.append(") TB WHERE ROWNUM=1");
				
				pstmt=conn.prepareStatement(sb.toString());
				pstmt.setInt(1, num);
			}
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new BoardNoticeDTO();
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
	
	//공지사항 조회수 수정
	public int updateHitCount(int num){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="UPDATE BOARDNOTICE SET HITCOUNT=HITCOUNT+1 WHERE NUM=?";
			
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
	
	//제목과 내용만 수정가능하게 한다.
	public int updateBoardNotice(BoardNoticeDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="UPDATE BOARDNOTICE SET SUBJECT=?, CONTENT=?, NOTICE=?, "
					+ "SAVEFILENAME=?, ORIGINALFILENAME=?, FILESIZE=?, MEMBERID=? WHERE NUM=?";
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, dto.getSubject());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getNotice());
			pstmt.setString(4, dto.getSaveFilename());
			pstmt.setString(5, dto.getOriginalFilename());
			pstmt.setLong(6, dto.getFilesize());
			pstmt.setString(7, dto.getMemberId());
			pstmt.setInt(8, dto.getNum());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	//공지사항 삭제
	public int deleteBoardNotice(int num){
		int result=0;
		PreparedStatement pstmt=null;
		String sql="";
		
		try {
			sql="DELETE FROM BOARDNOTICE WHERE NUM=?";
			
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