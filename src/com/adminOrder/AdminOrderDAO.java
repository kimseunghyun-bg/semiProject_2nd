package com.adminOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;
import com.util.DBConn;

public class AdminOrderDAO {
	Connection conn=DBConn.getConnection();
	
	public List<AdminOrderDTO> orderList(int start, int end){
		List<AdminOrderDTO> list=new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();

		try {
			sb.append("	SELECT * FROM(");
			sb.append("		SELECT ROWNUM rnum, tb.* FROM(");
			sb.append("			SELECT tb2.name panmaeName, TO_CHAR(jumun_created,'YYYY-MM-DD') jumun_created, m.name memberName, m.memberid, rankname, p.pay_state, jumun_state, tb.*");
			sb.append("			FROM(SELECT COUNT(totalpay)-1 extra, TO_CHAR(SUM(totalpay),'999,999,999') orderTotalpay, jumun_num, SUM(not_send) not_send, SUM(sending) sending, SUM(arrived) arrived, sum(return_product) return_product");
			sb.append("				FROM(SELECT sell_num*sell_price totalpay, s.jumun_num, ");
			sb.append("						decode(decode(send_state,null,1,0)-NVL2(r.jumun_num,1,0),-1,0,decode(send_state,null,1,0)-NVL2(r.jumun_num,1,0)) not_send, ");
			sb.append("						decode(decode(send_state,'배송중',1,0)-NVL2(r.jumun_num,1,0),-1,0,decode(send_state,'배송중',1,0)-NVL2(r.jumun_num,1,0)) sending, ");
			sb.append("						decode(decode(send_state,'배송완료',1,0)-NVL2(r.jumun_num,1,0),-1,0,decode(send_state,'배송완료',1,0)-NVL2(r.jumun_num,1,0)) arrived, ");
			sb.append("						NVL2(r.jumun_num,1,0) return_product");
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
			sb.append("			ORDER BY jumun_created DESC, tb.jumun_num DESC) tb");
			sb.append("		WHERE ROWNUM <=?");
			sb.append("	)WHERE rnum >=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				AdminOrderDTO dto=new AdminOrderDTO();
				
				dto.setJumunNum(rs.getString("jumun_num"));
				dto.setPanmaeName(rs.getString("panmaeName"));
				dto.setExtra(rs.getString("extra"));
				dto.setCreated(rs.getString("jumun_created"));
				dto.setMemberName(rs.getString("memberName"));
				dto.setMemberId(rs.getString("memberid"));
				dto.setRankName(rs.getString("rankname"));
				dto.setOrderTotalpay(rs.getString("orderTotalpay"));
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
	
	public List<AdminOrderDTO> orderList(int start, int end, String jumunState, String payState, String searchKey, String searchValue){
		List<AdminOrderDTO> list=new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	SELECT * FROM(");
			sb.append("		SELECT ROWNUM rnum, tb.* FROM(");
			sb.append("			SELECT tb2.name panmaeName, TO_CHAR(jumun_created,'YYYY-MM-DD') jumun_created, m.name memberName, m.memberid, rankname, p.pay_state, jumun_state, tb.*");
			sb.append("			FROM(SELECT COUNT(totalpay)-1 extra, TO_CHAR(SUM(totalpay),'999,999,999') orderTotalpay, jumun_num, SUM(not_send) not_send, SUM(sending) sending, SUM(arrived) arrived, sum(return_product) return_product");
			sb.append("				FROM(SELECT sell_num*sell_price totalpay, s.jumun_num, decode(send_state,null,1,0) not_send, ");
			sb.append("						decode(decode(send_state,'배송중',1,0)-NVL2(r.jumun_num,1,0),-1,0,decode(send_state,'배송중',1,0)-NVL2(r.jumun_num,1,0)) sending, ");
			sb.append("						decode(decode(send_state,'배송완료',1,0)-NVL2(r.jumun_num,1,0),-1,0,decode(send_state,'배송완료',1,0)-NVL2(r.jumun_num,1,0)) arrived, ");
			sb.append("						NVL2(r.jumun_num,1,0) return_product");
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
				sb.append("		p.pay_state=?");
			if((jumunState.length()!=0 || payState.length()!=0) && searchKey.length()!=0 && searchValue.length()!=0)
				sb.append("		AND ");
			if(searchKey.length()!=0 && searchValue.length()!=0)
				sb.append("		INSTR(" + searchKey + ", ?) >= 1 ");
			
			sb.append("			ORDER BY jumun_created DESC, tb.jumun_num DESC)tb");
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
				AdminOrderDTO dto=new AdminOrderDTO();
				
				dto.setJumunNum(rs.getString("jumun_num"));
				dto.setPanmaeName(rs.getString("panmaeName"));
				dto.setExtra(rs.getString("extra"));
				dto.setCreated(rs.getString("jumun_created"));
				dto.setMemberName(rs.getString("memberName"));
				dto.setMemberId(rs.getString("memberid"));
				dto.setRankName(rs.getString("rankname"));
				dto.setOrderTotalpay(rs.getString("orderTotalpay"));
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
	
	public int dataCount(){
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
	
	
	public int dataCount(String jumunState, String payState, String searchKey, String searchValue){
		int result=0;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append(" SELECT NVL(COUNT(*),0) ");
			sb.append("	FROM jumun j");
			sb.append(" JOIN member m ON j.memberid=m.memberid");
			sb.append(" JOIN pay p ON j.jumun_num=p.jumun_num");
			sb.append(" WHERE ");
			if(jumunState.length()!=0)
				sb.append("	jumun_state=?");
			if(jumunState.length()!=0 && payState.length()!=0)
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
	
	public AdminOrderDTO readJumunDetail(int jumunNum){
		AdminOrderDTO dto=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	SELECT j.jumun_num, m.name meber_name, m.memberid, rankname, TO_CHAR(jumun_created,'YYYY-MM-DD') jumun_created, TO_CHAR(NVL(orderTotalpay,0),'999,999,999') orderTotalpay, telephone, email,");
			sb.append("			pay_state, TO_CHAR(pay_total,'999,999,999') pay_total, pay_created, pay_root,");
			sb.append("			send.name send_name, send.phone_1, send.phone_2, send.phone_3, send.addr1, send.addr2, send.zip,");
			sb.append("			return_money, return_pay_created, bankname, banknumber");
			sb.append("	FROM jumun j");
			sb.append("	JOIN member m ON j.memberid=m.memberid");
			sb.append("	JOIN memberdetails md ON m.memberid=md.memberid");
			sb.append("	JOIN rankcode r ON m.rank_code=r.rank_code");
			sb.append("	JOIN pay ON pay.jumun_num=j.jumun_num");
			sb.append("	JOIN send_address send ON send.jumun_num=j.jumun_num");
			sb.append("	LEFT JOIN (SELECT SUM(sell_num*sell_price) orderTotalpay, s.jumun_num");
			sb.append("			FROM sangsae s LEFT JOIN return_product rp ON s.jumun_num=rp.jumun_num AND s.panmae_num=rp.panmae_num");
			sb.append("			WHERE rp.jumun_num IS NULL");
			sb.append("			GROUP BY s.jumun_num) tb");
			sb.append("		ON j.jumun_num=tb.jumun_num");
			sb.append("	LEFT JOIN RETURN_PAY rp ON rp.jumun_num=j.jumun_num");
			sb.append("	WHERE j.jumun_num=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, jumunNum);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				dto=new AdminOrderDTO();
				
				dto.setJumunNum(rs.getString("jumun_num"));
				dto.setMemberName(rs.getString("meber_name"));
				dto.setMemberId(rs.getString("memberid"));
				dto.setRankName(rs.getString("rankname"));
				dto.setCreated(rs.getString("jumun_created"));
				dto.setOrderTotalpay(rs.getString("orderTotalpay"));
				dto.setTelePhone(rs.getString("telephone"));
				dto.setEmail(rs.getString("email"));
				dto.setPayState(rs.getString("pay_state"));
				dto.setPayTotal(rs.getString("pay_total"));
				dto.setPayCreated(rs.getString("pay_created"));
				dto.setPayRoot(rs.getString("pay_root"));
				dto.setSendName(rs.getString("send_name"));
				dto.setPhone_1(rs.getString("phone_1"));
				dto.setPhone_2(rs.getString("phone_2"));
				dto.setPhone_3(rs.getString("phone_3"));
				dto.setZip(rs.getString("zip"));
				dto.setAddr1(rs.getString("addr1"));
				dto.setAddr2(rs.getString("addr2"));
				dto.setReturnMoney(rs.getString("return_money"));
				dto.setReturnPayCreated(rs.getString("return_pay_created"));
				dto.setBankName(rs.getString("bankname"));
				dto.setBankNumber(rs.getString("banknumber"));
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
	
	public List<AdminOrderSubDTO> readJumunSubDetail(int jumunNum){
		List<AdminOrderSubDTO> list=new LinkedList<>();
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("	SELECT s.panmae_num, p.name panmaeName, ");
			sb.append("			k1.KIND_NAME kindName, k2.kind_name kindParentName,");
			sb.append("			s.send_state, s.sell_num, s.sell_price, sell_num*sell_price totalPay,");
			sb.append("			send.send_corpor_name, send.send_phonenum, rp.reason, TO_CHAR(rp.return_created,'YYYY-MM-DD') return_created");
			sb.append("	FROM sangsae s");
			sb.append("	JOIN panmae p ON p.panmae_num=s.panmae_num");
			sb.append("	JOIN KIND k1 ON p.kind_code=k1.kind_code");
			sb.append("	JOIN kind k2 ON k1.kind_parent=k2.kind_code");
			sb.append("	JOIN send ON send.send_code=s.send_code");
			sb.append("	LEFT JOIN return_product rp ON rp.panmae_num=s.panmae_num AND rp.jumun_num=s.jumun_num");
			sb.append("	WHERE s.jumun_num=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, jumunNum);
			rs=pstmt.executeQuery();
			
			while(rs.next()){
				AdminOrderSubDTO dto=new AdminOrderSubDTO();
				
				dto.setPanmaeNum(rs.getString("panmae_num"));
				dto.setPanmaeName(rs.getString("panmaeName"));
				dto.setKindName(rs.getString("kindName"));
				dto.setKindParentName(rs.getString("kindParentName"));
				dto.setSendState(rs.getString("send_state"));
				dto.setSellNum(rs.getString("sell_num"));
				dto.setSellPrice(rs.getString("sell_price"));
				dto.setTotalPay(rs.getString("totalPay"));
				dto.setSendCorporName(rs.getString("send_corpor_name"));
				dto.setSendPhoneNum(rs.getString("send_phonenum"));
				dto.setReason(rs.getString("reason"));
				dto.setReturnCreated(rs.getString("return_created"));
				
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
	
	public int updatePayment(AdminOrderDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append("UPDATE PAY SET pay_state=?, pay_root=? ");
			if(dto.getPayTotal()!=null){
				sb.append(" ,pay_total=?, pay_created=SYSDATE");
			}else {
				sb.append(" ,pay_total=null, pay_created=null");
			}
			sb.append("  WHERE jumun_num=?");
			
			pstmt=conn.prepareStatement(sb.toString());
			int n=1;
			
			pstmt.setString(n++, dto.getPayState());
			pstmt.setString(n++, dto.getPayRoot());
			if(dto.getPayTotal()!=null)
				pstmt.setString(n++, dto.getPayTotal());
			pstmt.setString(n++, dto.getJumunNum());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	public int updateArrive(AdminOrderDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append(" UPDATE send_address SET name=?, addr1=?, addr2=?, zip=?, phone_1=?, phone_2=?, phone_3=? WHERE jumun_num=?");
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getSendName());
			pstmt.setString(2, dto.getAddr1());
			pstmt.setString(3, dto.getAddr2());
			pstmt.setString(4, dto.getZip());
			pstmt.setString(5, dto.getPhone_1());
			pstmt.setString(6, dto.getPhone_2());
			pstmt.setString(7, dto.getPhone_3());
			pstmt.setString(8, dto.getJumunNum());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
	public int updateDelivery(AdminOrderDTO dto){
		int result=0;
		PreparedStatement pstmt=null;
		StringBuffer sb=new StringBuffer();
		
		try {
			sb.append(" UPDATE sangsae SET send_state=? WHERE jumun_num=? AND panmae_num=?" );
			pstmt=conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getSendState());
			pstmt.setString(2, dto.getJumunNum());
			pstmt.setString(3, dto.getPanmaeNum());
			
			result=pstmt.executeUpdate();
			
			pstmt.close();
			pstmt=null;
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
		return result;
	}
	
}
