package com.order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.util.DBConn;

public class OrderDAO {
	Connection conn=DBConn.getConnection();
	
	public List<OrderDTO> orderList(int start, int end, String memberId){
		List<OrderDTO> list= new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		try {
			sb.append("	SELECT * FROM(");
			sb.append("		SELECT ROWNUM rnum, tb.* FROM(");
			sb.append("			SELECT tb2.name panmaeName, TO_CHAR(jumun_created,'YYYY-MM-DD') jumun_created, m.name memberName, m.memberid, rankname, p.pay_state, jumun_state, tb.*");
			sb.append("			FROM(SELECT COUNT(total)-1 extra, TO_CHAR(SUM(total),'999,999,999') total, jumun_num, SUM(not_send) not_send, SUM(sending) sending, SUM(arrived) arrived, sum(return_product) return_product, send_state");
			sb.append("				FROM(SELECT sell_num*sell_price total, s.jumun_num, decode(send_state,null,1,0) not_send, decode(send_state,'배송중',1,0) sending, decode(send_state,'배송완료',1,0) arrived, NVL2(r.jumun_num,1,0) return_product, send_state");
			sb.append("					FROM sangsae s LEFT JOIN return_product r ON s.jumun_num=r.jumun_num AND s.panmae_num=r.panmae_num)");
			sb.append("				GROUP BY jumun_num, send_state) tb");
			sb.append("			JOIN jumun j ON tb.jumun_num=j.jumun_num");
			sb.append("			JOIN member m ON j.memberid=m.memberid");
			sb.append("			JOIN rankcode r ON m.rank_code=r.rank_code");
			sb.append("			JOIN pay p ON j.jumun_num=p.jumun_num");
			sb.append("			JOIN (SELECT *");
			sb.append("				FROM(SELECT p.name, s.jumun_num, row_number() OVER ( PARTITION BY s.jumun_num ORDER BY  s.panmae_num ) rnum");
			sb.append("					FROM sangsae s");
			sb.append("					JOIN panmae p ON s.panmae_num=p.panmae_num)");
			sb.append("				WHERE rnum = 1) tb2 ON tb.jumun_num=tb2.jumun_num");
			sb.append("			ORDER BY jumun_created DESC) tb");
			sb.append("		WHERE ROWNUM <=?");
			sb.append("	)WHERE rnum >=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				OrderDTO dto=new OrderDTO();
				
				dto.setJumunNum(rs.getString("jumun_num"));
				dto.setPanmaeName(rs.getString("panmaeName"));
				dto.setExtra(rs.getString("extra"));
				dto.setCreated(rs.getString("jumun_created"));
				dto.setMemberName(rs.getString("memberName"));
				dto.setMemberId(rs.getString("memberid"));
				dto.setRankName(rs.getString("rankname"));
				dto.setPayTotal(rs.getString("total"));
				dto.setPayState(rs.getString("pay_state"));
				dto.setJumunState(rs.getString("jumun_state"));
				dto.setNotSend(rs.getString("not_send"));
				dto.setSending(rs.getString("sending"));
				dto.setArrived(rs.getString("arrived"));
				dto.setSendState(rs.getString("send_state"));
				dto.setReturnProduct(rs.getString("return_product"));
				
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
	
	public List<OrderDTO> orderList(int start, int end,  String jumunState, String payState, String searchKey, String searchValue, String memberId){
		List<OrderDTO> list=new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	SELECT * FROM(");
			sb.append("		SELECT ROWNUM rnum, * FROM(");
			sb.append("			SELECT tb2.name panmaeName, TO_CHAR(jumun_created,'YYYY-MM-DD') jumun_created, m.name memberName, m.memberid, rankname, p.pay_state, jumun_state, tb.*");
			sb.append("			FROM(SELECT COUNT(total)-1 extra, TO_CHAR(SUM(total),'999,999,999') total, jumun_num, SUM(not_send) not_send, SUM(sending) sending, SUM(arrived) arrived, sum(return_product) return_product");
			sb.append("				FROM(SELECT sell_num*sell_price total, s.jumun_num, decode(send_state,null,1,0) not_send, decode(send_state,'배송중',1,0) sending, decode(send_state,'배송완료',1,0) arrived, NVL2(r.jumun_num,1,0) return_product");
			sb.append("					FROM sangsae s LEFT JOIN return_product r ON s.jumun_num=r.jumun_num AND s.panmae_num=r.panmae_num)");
			sb.append("				GROUP BY jumun_num) tb");
			sb.append("			JOIN jumun j ON tb.jumun_num=j.jumun_num");
			sb.append("			JOIN member m ON j.memberid=m.memberid");
			sb.append("			JOIN rankcode r ON m.rank_code=r.rank_code");
			sb.append("			JOIN pay p ON j.jumun_num=p.jumun_num");
			sb.append("			JOIN (SELECT *");
			sb.append("				FROM(SELECT p.name, s.jumun_num, row_number() OVER ( PARTITION BY s.jumun_num ORDER BY  s.panmae_num ) rnum");
			sb.append("					FROM sangsae s");
			sb.append("					JOIN panmae p ON s.panmae_num=p.panmae_num)");
			sb.append("				WHERE rnum = 1) tb2 ON tb.jumun_num=tb2.jumun_num");
			sb.append(" 		WHERE ");

			if(jumunState.length()!=0)
				sb.append("		jumun_state=?");
			if(jumunState.length()!=0 && payState.length()!=0)
				sb.append("		AND ");
			if(payState.length()!=0)
				sb.append("		pay_state=?");
			if((jumunState.length()!=0 || payState.length()!=0) && searchKey.length()!=0 && searchValue.length()!=0)
				sb.append("		AND ");
			if(searchKey.length()!=0 && searchValue.length()!=0)
				sb.append("		INSTR(" + searchKey + ", ?) >= 1 ");
			
			sb.append("			ORDER BY jumun_created DESC)tb");
			sb.append("		WHERE ROWNUM <=?");
			sb.append("	)WHERE rnum >=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			
			int n=1;
			
			if(jumunState.length()!=0)
				pstmt.setString(n++, jumunState);
			if(payState.length()!=0)
				pstmt.setString(n++, payState);
			if(searchKey.length()!=0 && searchValue.length()!=0)
				pstmt.setString(n++, searchValue);
			pstmt.setInt(n++, end);
			pstmt.setInt(n++, start);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				OrderDTO dto=new OrderDTO();
				
				dto.setJumunNum(rs.getString("jumun_num"));
				dto.setPanmaeName(rs.getString("panmaeName"));
				dto.setExtra(rs.getString("extra"));
				dto.setCreated(rs.getString("jumun_created"));
				dto.setMemberName(rs.getString("memberName"));
				dto.setMemberId(rs.getString("memberid"));
				dto.setRankName(rs.getString("rankname"));
				dto.setPayTotal(rs.getString("total"));
				dto.setPayState(rs.getString("pay_state"));
				dto.setJumunState(rs.getString("jumun_state"));
				dto.setNotSend(rs.getString("not_send"));
				dto.setSending(rs.getString("sending"));
				dto.setArrived(rs.getString("arrived"));
				dto.setReturnProduct(rs.getString("return_product"));
				
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
	
	public int dataCount(String memberId){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("SELECT NVL(COUNT(*),0) FROM jumun"); 
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			
			if(rs.next())
				result=rs.getInt(1);
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	
	public int dataCount(String jumunState, String payState, String searchKey, String searchValue, String memberId){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append(" SELECT NVL(COUNT(*),0) ");
			sb.append("	FROM jumun j");
			sb.append(" JOIN member m ON j.memberid=m.memberid");
			sb.append(" JOIN pay p ON j.jumun_num=p.jumun_num");
			sb.append(" WHERE j.memberid");
			if(jumunState.length()!=0)
				sb.append("	AND jumun_state=?");
			if(payState.length()!=0 && payState.length()!=0)
				sb.append("	AND ");
			if(payState.length()!=0)
				sb.append("	pay_state=?");
			if((jumunState.length()!=0 || payState.length()!=0) && searchKey.length()!=0 && searchValue.length()!=0)
				sb.append("	AND ");
			if(searchKey.length()!=0 && searchValue.length()!=0)
				sb.append("	INSTR(" + searchKey + ", ?) >= 1 ");
			
			pstmt=conn.prepareStatement(sb.toString());
			int n=1;
			
			if(jumunState.length()!=0)
				pstmt.setString(n++, jumunState);
			if(payState.length()!=0)
				pstmt.setString(n++, payState);
			if(searchKey.length()!=0 && searchValue.length()!=0)
				pstmt.setString(n++, searchValue);
			
			rs=pstmt.executeQuery();
			
			if(rs.next())
				result=rs.getInt(1);
			
			pstmt.close();
			rs.close();
			pstmt=null;
			rs=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
}
