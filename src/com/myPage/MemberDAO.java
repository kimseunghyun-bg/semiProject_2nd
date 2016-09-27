package com.myPage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.member.MemberDTO;
import com.util.DBConn;

public class MemberDAO {
	private Connection conn=DBConn.getConnection();
	
	public int insertMember(MemberDTO dto){
		int result=0;	
		PreparedStatement pstmt=null;
		StringBuffer sb= new StringBuffer();
		
		try {
			// member
	        sb.append("INSERT INTO member (memberId, name, password) ");
	        sb.append(" VALUES (?, ?, ?)");
			
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, dto.getMemberId());
            pstmt.setString(2, dto.getName());
            pstmt.setString(3, dto.getPassword());
            
            result = pstmt.executeUpdate();
            pstmt.close();
            pstmt=null;
            
            // memberdetails
            // 휴대폰번호 합치기
            if(dto.getTelephone1().length()!=0&&dto.getTelephone2().length()!=0&&dto.getTelephone3().length()!=0)
            	dto.setTelephone(dto.getTelephone1()+"-"+dto.getTelephone2()+"-"+dto.getTelephone3());
            
            // 전화번호 합치기
            if(dto.getHousephone1().length()!=0&&dto.getHousephone2().length()!=0&&dto.getHousephone3().length()!=0)
            	dto.setHousephone(dto.getHousephone1()+"-"+dto.getHousephone2()+"-"+dto.getHousephone3());
           
            // 이메일 합치기
            if(dto.getEmail1().length()!=0&&dto.getEmail2().length()!=0)
            	dto.setEmail(dto.getEmail1()+"@"+dto.getEmail2());
            
            sb= new StringBuffer();
            
            sb.append("INSERT INTO memberdetails (memberId, birth, email, housephone, telephone, zip, addr1, addr2) ");
            sb.append(" VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1, dto.getMemberId());
            pstmt.setString(2, dto.getBirth());
            pstmt.setString(3, dto.getEmail());
            pstmt.setString(4, dto.getHousephone());
            pstmt.setString(5, dto.getTelephone());
            pstmt.setString(6, dto.getZip());
            pstmt.setString(7, dto.getAddr1());
            pstmt.setString(8, dto.getAddr2());
            
            result = pstmt.executeUpdate();
            pstmt.close();
            pstmt=null;
            
			result=1;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	// left outer join으로 모든 정보 가져오기
	public MemberDTO readMember(String memberId){
		MemberDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
            sb.append("SELECT m1.memberId, name, password, m1.rank_code,rankname,created, enabled, birth, email, housephone, telephone, zip, addr1,addr2");
            sb.append(" FROM member m1");
            sb.append(" LEFT OUTER JOIN memberdetails m2 ON m1.memberId=m2.memberId");
            sb.append(" LEFT OUTER JOIN rankcode r ON m1.rank_code=r.rank_code");
            sb.append(" WHERE m1.memberId=?");
            
            pstmt=conn.prepareStatement(sb.toString());
            pstmt.setString(1, memberId);
            rs=pstmt.executeQuery();
            
            if(rs.next()){
            	dto=new MemberDTO();
            	dto.setMemberId(rs.getString("memberId"));
            	dto.setName(rs.getString("name"));
            	dto.setPassword(rs.getString("password"));
            	dto.setCreated(rs.getString("created"));
            	dto.setEnabled(rs.getInt("enabled"));
            	
            	dto.setBirth(rs.getString("birth"));
            	
            	dto.setEmail(rs.getString("email"));
            	if(dto.getEmail()!=null){
            		String []ee=dto.getEmail().split("@");
	            	if(ee.length==2){
	            		dto.setEmail1(ee[0]);
	            		dto.setEmail2(ee[1]);
	            	}
            	}
            	dto.setHousephone(rs.getString("housephone"));
            	if(dto.getHousephone()!=null){
            		String []hh=dto.getHousephone().split("-");
	            	if(hh.length==3){
	            		dto.setHousephone1(hh[0]);
	            		dto.setHousephone2(hh[1]);
	            		dto.setHousephone3(hh[2]);
	            	}
            	}
            	dto.setTelephone(rs.getString("telephone"));
            	if(dto.getTelephone()!=null){
            		String []tt=dto.getTelephone().split("-");
	            	if(tt.length==3){
	            		dto.setTelephone1(tt[0]);
	            		dto.setTelephone2(tt[1]);
	            		dto.setTelephone3(tt[2]);
	            	}
            	}
            	dto.setZip(rs.getString("zip"));
            	dto.setAddr1(rs.getString("addr1"));
            	dto.setAddr2(rs.getString("addr2"));
            	
            	dto.setRank_code(rs.getInt("rank_code"));
            	dto.setRankname(rs.getString("rankname")); 
            }
            
            rs.close();
            pstmt.close();
  
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return dto;
	}

	public int updateMember(MemberDTO dto) {
		int result=0;
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("UPDATE member SET password=?,  WHERE MemberId=?");
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getPassword());
			pstmt.setString(2, dto.getMemberId());
			
			result=pstmt.executeUpdate();
			pstmt.close();
			pstmt=null;
			
			sb.delete(0, sb.length());
			
			sb.append("UPDATE memberdetails SET birth=?, email=?, telephone=?, housephone=?, zip=?, addr1=?, addr2=? WHERE memberId=?");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getBirth());
			pstmt.setString(2, dto.getEmail());
			pstmt.setString(3, dto.getTelephone());
			pstmt.setString(4, dto.getHousephone());
			pstmt.setString(5, dto.getZip());
			pstmt.setString(6, dto.getAddr1());
			pstmt.setString(7, dto.getAddr2());
			pstmt.setString(8, dto.getMemberId());
			
			pstmt.executeUpdate();
			pstmt.close();
			pstmt=null;
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	public int deleteMember(String memberId) {
			int result=0;
		PreparedStatement pstmt=null;
		String sql;
		
		try {
			sql="UPDATE member SET enabled=0 WHERE memberId=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result=pstmt.executeUpdate();
			pstmt.close();
			pstmt=null;
			 
			sql="DELETE FROM  memberdetails WHERE memberId=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberId);
			result=pstmt.executeUpdate();
			pstmt.close();
			pstmt=null;
			
		} catch (Exception e) {
			System.out.println();
		}
		return result;
		
	}
}
