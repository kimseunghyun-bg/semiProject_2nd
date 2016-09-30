package com.panmai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.util.DBConn;

public class PanmaeDAO {
	private Connection conn = DBConn.getConnection();

	public int dataCount(String small_kind) {
		int result = 0;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {

			if (small_kind.equals("")) {

				sb.append("SELECT NVL(COUNT(*),0) FROM PANMAE");

				pstmt = conn.prepareStatement(sb.toString());

			} else {
				sb.append("SELECT NVL(COUNT(*),0)");
				sb.append("		FROM PANMAE P JOIN KIND K ON P.KIND_CODE = K.KIND_CODE");
				sb.append(" 			START WITH P.KIND_CODE = ? CONNECT BY PRIOR P.KIND_CODE = KIND_PARENT");

				pstmt = conn.prepareStatement(sb.toString());
				pstmt.setString(1, small_kind);
			}

			rs = pstmt.executeQuery();

			if (rs.next())
				result = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("데이터 전체 개수:" + result);
		return result;
	}

	public int basket_dataCount() {
		int result = 0;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {

			sb.append("SELECT NVL(COUNT(*),0) FROM BASKET");

			pstmt = conn.prepareStatement(sb.toString());
			rs = pstmt.executeQuery();

			if (rs.next())
				result = rs.getInt(1);

			rs.close();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		System.out.println("장바구니" + result);
		return result;

	}

	public List<PanmaeDTO> listPanmae(int start, int end, String small_kind) {
		List<PanmaeDTO> list = new ArrayList<PanmaeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			if (small_kind.equals("")) {
				sb.append("SELECT * FROM (");
				sb.append(" SELECT ROWNUM RNUM, TB.* FROM(");
				sb.append("	  SELECT PANMAE_NUM, NAME, SAVE_NUM, PRICE, PANMAE_STATE, IMAGE");
				sb.append("		FROM PANMAE");
				sb.append("	   ORDER BY created DESC");
				sb.append("	  )TB WHERE ROWNUM <= ?");
				sb.append("  )WHERE RNUM >=?");

				pstmt = conn.prepareStatement(sb.toString());

				pstmt.setInt(1, end);
				pstmt.setInt(2, start);
			} else {
				sb.append("SELECT * FROM (");
				sb.append(" SELECT ROWNUM RNUM, TB.* FROM(");
				sb.append("	  SELECT PANMAE_NUM, NAME, SAVE_NUM, PRICE, PANMAE_STATE, IMAGE");
				sb.append("		FROM PANMAE P JOIN KIND K ON P.KIND_CODE = K.KIND_CODE");
				sb.append(" 			START WITH P.KIND_CODE = ? CONNECT BY PRIOR P.KIND_CODE = KIND_PARENT");
				sb.append("	   ORDER BY created DESC");
				sb.append("	  )TB WHERE ROWNUM <= ?");
				sb.append("  )WHERE RNUM >=?");

				pstmt = conn.prepareStatement(sb.toString());

				pstmt.setString(1, small_kind);
				pstmt.setInt(2, end);
				pstmt.setInt(3, start);
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PanmaeDTO dto = new PanmaeDTO();
				dto.setPanmae_num(rs.getInt("panmae_num"));
				dto.setName(rs.getString("name"));
				dto.setSave_num(rs.getInt("save_num"));
				dto.setPrice(rs.getInt("price"));
				dto.setPanmae_state(rs.getString("panmae_state"));
				dto.setImage(rs.getString("image"));

				list.add(dto);
			}

			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public PanmaeDTO fromListToSangsae(int panmae_num) {
		PanmaeDTO dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			sb.append(
					"SELECT P.PANMAE_NUM, PRODUCE_CORPOR_NAME, NAME, PRICE, SAVE_NUM, INTRODUCE, PANMAE_STATE, IMAGE,");
			sb.append(" TO_CHAR(CREATED, 'YYYY-MM-DD') CREATED");
			sb.append(" FROM PANMAE P JOIN PRODUCER D ON P.PRODUCE_CODE = D.PRODUCE_CODE");
			sb.append(" WHERE P.PANMAE_NUM = ?");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, panmae_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				dto = new PanmaeDTO();
				dto.setPanmae_num(rs.getInt("panmae_num"));
				dto.setProduce_corpor_name(rs.getString("produce_corpor_name"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setSave_num(rs.getInt("save_num"));
				dto.setIntroduce(rs.getString("introduce"));
				dto.setPanmae_state(rs.getString("panmae_state"));
				dto.setImage(rs.getString("image"));
				dto.setCreated(rs.getString("created"));

				pstmt.close();
				rs.close();
			}

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}

	public PanmaeDTO fromSangsaeToPay(int panmae_num) {
		PanmaeDTO dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {

			sb.append("SELECT NAME, P.PANMAE_NUM, D.PRODUCE_CORPOR_NAME, PRICE,");
			sb.append(" TO_CHAR(CREATED, 'YYYY-MM-DD') CREATED");
			sb.append(" FROM PANMAE P JOIN PRODUCER D ON P.PRODUCE_CODE = D.PRODUCE_CODE");
			sb.append(" WHERE P.PANMAE_NUM = ?");
			sb.append(" GROUP BY P.PANMAE_NUM, NAME, D.PRODUCE_CORPOR_NAME, PRICE, CREATED");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, panmae_num);
			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new PanmaeDTO();
				dto.setPanmae_num(rs.getInt("panmae_num"));
				dto.setProduce_corpor_name(rs.getString("produce_corpor_name"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setCreated(rs.getString("created"));
			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}

	public int toBasketInsert(PanmaeDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		try {

			sb.append(
					"INSERT INTO BASKET (MEMBERID, BASKET_NUM, PANMAE_NUM, NAME, PRICE, PRODUCE_CORPOR_NAME,BUY_NUM)");
			sb.append(" VALUES (?,BASKET_NUM_SEQ.NEXTVAL,?,?,?,?,?)");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getMemberId());
			pstmt.setInt(2, dto.getPanmae_num());
			pstmt.setString(3, dto.getName());
			pstmt.setInt(4, dto.getPrice());
			pstmt.setString(5, dto.getProduce_corpor_name());
			pstmt.setInt(6, dto.getBuy_num());

			result = pstmt.executeUpdate();
			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;

	}

	public List<PanmaeDTO> listBasket() {
		List<PanmaeDTO> list = new ArrayList<PanmaeDTO>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {
			// 나중에 회원멤버도 가져와야댐.

			sb.append(" SELECT MEMBERID, BASKET_NUM,P.PANMAE_NUM, NAME, BUY_NUM, P.PRICE, B.PRODUCE_CORPOR_NAME");
			sb.append(" FROM BASKET B JOIN PANMAE P ON B.PANMAE_NUM = P.PANMAE_NUM");
			sb.append(" JOIN PRODUCER D ON P.PRODUCE_code = D.PRODUCE_CODE");
			sb.append(" JOIN PANMAE P ON B.NAME = P.NAME");
			sb.append(" ORDER BY p.created DESC");

			pstmt = conn.prepareStatement(sb.toString());

			rs = pstmt.executeQuery();

			while (rs.next()) {
				PanmaeDTO dto = new PanmaeDTO();
				dto.setMemberId(rs.getString("memberid"));
				dto.setBasket_num(rs.getInt("basket_num"));
				dto.setPanmae_num(rs.getInt("panmae_num"));
				dto.setName(rs.getString("name"));
				dto.setBuy_num(rs.getInt("buy_num"));
				dto.setPrice(rs.getInt("price"));
				dto.setProduce_corpor_name(rs.getString("produce_corpor_name"));

				list.add(dto);
			}

			pstmt.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;
	}

	public PanmaeDTO fromBasketToPay(int basket_num) {
		PanmaeDTO dto = null;

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();

		try {

			sb.append("SELECT P.NAME, P.PANMAE_NUM, D.PRODUCE_CORPOR_NAME, P.PRICE, BUY_NUM,");
			sb.append(" TO_CHAR(CREATED, 'YYYY-MM-DD') CREATED");
			sb.append(" FROM BASKET B JOIN PRODUCER D ON B.PRODUCE_CORPOR_NAME = D.PRODUCE_CORPOR_NAME");
			sb.append(" JOIN PANMAE P ON P.PANMAE_NUM = B.PANMAE_NUM");
			sb.append(" WHERE BASKET_NUM = ?");
			sb.append(" GROUP BY P.PANMAE_NUM, P.NAME, D.PRODUCE_CORPOR_NAME, P.PRICE, CREATED, BUY_NUM");

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, basket_num);

			rs = pstmt.executeQuery();

			if (rs.next()) {

				dto = new PanmaeDTO();
				dto.setPanmae_num(rs.getInt("panmae_num"));
				dto.setProduce_corpor_name(rs.getString("produce_corpor_name"));
				dto.setName(rs.getString("name"));
				dto.setPrice(rs.getInt("price"));
				dto.setBuy_num(rs.getInt("buy_num"));
				dto.setCreated(rs.getString("created"));
			}

			pstmt.close();
			rs.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return dto;
	}

	public int deleteBasket(String memberId) {

		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		sb.append("DELETE FROM BASKET WHERE MEMBERID = ?");

		try {

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memberId);
			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	public int deleteBasket2(String memberId, int basket_num) {

		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		sb.append("DELETE FROM BASKET WHERE MEMBERID = ? AND BASKET_NUM = ?");

		try {

			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, memberId);
			pstmt.setInt(2, basket_num);
			result = pstmt.executeUpdate();

			pstmt.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}
	
	public int jumunInsert(PanmaeDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		try {
			
			sb.append("INSERT INTO JUMUN (JUMUN_NUM,  MEMBERID)");
			sb.append(" VALUES (JUMUN_NUM_SEQ.NEXTVAL,?)");
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setString(1, dto.getMemberId());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
	}

	
	public int sangsaeInsert(PanmaeDTO dto) {

		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		try {
			
			sb.append("INSERT INTO SANGSAE (JUMUN_NUM, PANMAE_NUM, SELL_NUM, SELL_PRICE, DELIVERY_CODE, SEND_CODE)");
			sb.append(" VALUES (JUMUN_NUM_SEQ.CURRVAL,?,?,?,?,1)");
			pstmt = conn.prepareStatement(sb.toString());
			
			pstmt.setInt(1, dto.getPanmae_num());
			pstmt.setInt(2, dto.getSell_num());
			pstmt.setInt(3, dto.getSell_price());
			pstmt.setInt(4, dto.getDelivery_code());
			
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
		
		
	}
	
	public int payInsert(PanmaeDTO dto) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		try {
			
			sb.append("INSERT INTO PAY(JUMUN_NUM, PAY_STATE, PAY_ROOT)");
			sb.append(" VALUES (JUMUN_NUM_SEQ.CURRVAL,'입금대기','계좌이체')");
			pstmt = conn.prepareStatement(sb.toString());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
		
	}
	
	public int sendInsert(PanmaeDTO dto) {
		int result = 0;
		PreparedStatement pstmt = null;
		StringBuffer sb = new StringBuffer();

		try {
			
			sb.append("INSERT INTO SEND_ADDRESS(JUMUN_NUM,NAME,zip,ADDR1,ADDR2,PHONE_1,PHONE_2,PHONE_3,BANK,MEMO)");
			sb.append(" VALUES (JUMUN_NUM_SEQ.CURRVAL,?,?,?,?,?,?,?,?,?)");
			pstmt = conn.prepareStatement(sb.toString());
		
			pstmt.setString(1, dto.getGetname());
			pstmt.setString(2, dto.getZip());
			pstmt.setString(3, dto.getAddr1());
			pstmt.setString(4, dto.getAddr2());
			pstmt.setString(5, dto.getPhone_1());
			pstmt.setString(6, dto.getPhone_2());
			pstmt.setString(7, dto.getPhone_3());
			pstmt.setString(8, dto.getBank());
			pstmt.setString(9, dto.getMemo());
			
			result = pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			System.out.println(e.toString());
		}

		return result;
		
	}


}
