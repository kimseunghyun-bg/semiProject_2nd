package com.panmai;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/sale/*")
public class PanmaeServlet extends MyServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String uri = req.getRequestURI();

		String cp = req.getContextPath();

		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		

		String root = session.getServletContext().getRealPath("/");
		String pathname = File.separator + "images";
		File f = new File(pathname);
		if (!f.exists()) {
			f.mkdirs();
		}
		
		PanmaeDAO dao = new PanmaeDAO();
		int BasketdataCount = dao.basket_dataCount();

		MyUtil util = new MyUtil();

		if (uri.indexOf("panmaeList.do") != -1) {
			// 상품 리스트 보기
			String page = req.getParameter("page");
			String small_kind = req.getParameter("small_kind");

			if (small_kind == null)
				small_kind = "";

			int current_page = 1;
			if (page != null)
				current_page = Integer.parseInt(page);

			int dataCount = dao.dataCount(small_kind);

			int numPerPage = 9;
			int total_page = util.pageCount(numPerPage, dataCount);
			if (current_page > total_page)
				current_page = total_page;

			int start = (current_page - 1) * numPerPage + 1;
			int end = current_page * numPerPage;

			List<PanmaeDTO> list = dao.listPanmae(start, end, small_kind);

			String basketUrl = cp + "/sale/basketlist.do?";
			String listUrl = cp + "/sale/panmaeList.do?";
			String sangsaeUrl = cp + "/sale/sangsae.do?page=" + current_page;
			String paging = util.paging(current_page, total_page, listUrl);

			req.setAttribute("list", list);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("sangsaeUrl", sangsaeUrl);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("paging", paging);
			req.setAttribute("basketUrl", basketUrl);
			String path = "/WEB-INF/semi/sale/panmaeList.jsp";
			forward(req, resp, path);

		} else if (uri.indexOf("sangsae.do") != -1) {
			// 상품상세정보 보기
			PanmaeDTO dto = null;
			int num = Integer.parseInt(req.getParameter("panmae_num"));

			String payUrl = cp + "/sale/pay.do";

			dto = dao.fromListToSangsae(num);

			if (dto == null) {
				resp.sendRedirect(cp + "/sale/panmaeList.do");
				return;
			}

			dto.setIntroduce(dto.getIntroduce().replaceAll("\n", "<br>"));

			req.setAttribute("dto", dto);
			req.setAttribute("payUrl", payUrl);

			String path = "/WEB-INF/semi/sale/sangsae.jsp";
			forward(req, resp, path);

		}

		else if (uri.indexOf("pay_ok.do") != -1) {
			// 결제 완료하기
			if(info==null) { // 로그인되지 않은 경우
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
			PanmaeDTO dto = new PanmaeDTO();
			
			dto.setMemberId(info.getMemberId());
			dto.setPanmae_num(Integer.parseInt(req.getParameter("panmae_num")));
			dto.setSell_num(Integer.parseInt(req.getParameter("sell_num")));
			dto.setSell_price(Integer.parseInt(req.getParameter("sell_price")));
			
			Map<String, String[]> map = req.getParameterMap();
			String send[] = new String[12];
			int count = 0;
			Iterator<String> it = map.keySet().iterator();
			while(it.hasNext()) {
				String key = it.next();
				String[] ss = map.get(key);
				for(String s:ss) {
					send[count] = s;
				}
				count++;
			}
			
			dto.setGetname(send[0]);
			dto.setPhone_1(send[1]);
			dto.setPhone_2(send[2]);
			dto.setPhone_3(send[3]);
			dto.setZip(send[4]);
			dto.setAddr1(send[5]);
			dto.setAddr2(send[6]);
			dto.setBank(send[7]);
			dto.setMemo(send[8]);
			
			
			dao.jumunInsert(dto);
			dao.sangsaeInsert(dto);
			dao.payInsert(dto);
			dao.sendInsert(dto);
			
			String delete2Url = cp + "/sale/delete2.do";
			req.setAttribute("delete2Url", delete2Url);
			req.setAttribute("dto", dto);
			String path = "/WEB-INF/semi/sale/pay_ok.jsp";
			forward(req, resp, path);

		} else if(uri.indexOf("delete2.do")!=-1) {
			// 장바구니 삭제(자동)
			if(info==null) { // 로그인되지 않은 경우
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
			
		String id =info.getMemberId();
		
		dao.deleteBasket(id);
		BasketdataCount = dao.basket_dataCount();
		List<PanmaeDTO> list = dao.listBasket();
		
		req.setAttribute("list", list);
		req.setAttribute("dataCount", BasketdataCount);
		resp.sendRedirect(cp + "/sale/panmaeList.do");
		
		} else if (uri.indexOf("basket.do") != -1) {
			// 장바구니 담기
			
			if(info==null) { // 로그인되지 않은 경우
				resp.sendRedirect(cp+"/member/login.do");
				return;
			}
			PanmaeDTO dto = new PanmaeDTO();
			
			dto.setMemberId(info.getMemberId());
			dto.setBasket_num(Integer.parseInt(req.getParameter("basket_num")));
			dto.setPanmae_num(Integer.parseInt(req.getParameter("panmae_num")));
			dto.setName(req.getParameter("name"));
			dto.setBuy_num(Integer.parseInt(req.getParameter("buynum")));
			dto.setPrice(Integer.parseInt(req.getParameter("price")));
			dto.setProduce_corpor_name(req.getParameter("produce_corpor_name"));

			dao.toBasketInsert(dto);

			resp.sendRedirect(cp + "/sale/panmaeList.do");

		} else if (uri.indexOf("basketlist.do") != -1) {
			// 장바구니 보기
			
			if(info==null) { // 로그인되지 않은 경우
				resp.sendRedirect(cp+"/member/login.do");
				return;
			}
			BasketdataCount = dao.basket_dataCount();
			List<PanmaeDTO> list = dao.listBasket();
			String basket_payUrl = cp + "/sale/pay.do";
			String deleteUrl = cp + "/sale/delete.do";
			
			req.setAttribute("list", list);
			req.setAttribute("basket_payUrl", basket_payUrl);
			req.setAttribute("deleteUrl", deleteUrl);
			req.setAttribute("dataCount", BasketdataCount);

			String path = "/WEB-INF/semi/sale/basket.jsp";
			forward(req, resp, path);
		} else if (uri.indexOf("sangsae_pay.do") != -1) {
			// 상세창에서 결제하기
			
			if(info==null) { // 로그인되지 않은 경우
				resp.sendRedirect(cp+"/member/login.do");
				return;
			}
			PanmaeDTO dto = new PanmaeDTO();
			int num = Integer.parseInt((req.getParameter("panmae_num")));
			int number =  Integer.parseInt((req.getParameter("buynum")));
			List<PanmaeDTO> list = new ArrayList<>();

			String pay_okUrl = cp + "/sale/pay_ok.do";

			dto = dao.fromSangsaeToPay(num);
			dto.setBuy_num(number);
			list.add(dto);
			
			
			req.setAttribute("list", list);
			req.setAttribute("pay_okUrl", pay_okUrl);

			String path = "/WEB-INF/semi/sale/pay.jsp";
			forward(req, resp, path);
		} else if (uri.indexOf("basket_pay.do") != -1) {
			// 장바구니에서 결제하기
			if(info==null) { // 로그인되지 않은 경우
			resp.sendRedirect(cp+"/member/login.do");
			return;
		}
			
			PanmaeDTO dto = new PanmaeDTO();
			String number[] = req.getParameterValues("panmae");
			List<PanmaeDTO> list = new ArrayList<>();

			String pay_okUrl = cp + "/sale/pay_ok.do";
			String id = info.getMemberId();
			for (int i = 0; i < number.length; i++) {
				dto = dao.fromBasketToPay(Integer.parseInt(number[i]));
				list.add(dto);
			}
			req.setAttribute("dto", dto);
			req.setAttribute("list", list);
			req.setAttribute("pay_okUrl", pay_okUrl);
			req.setAttribute("memberId", id);

			String path = "/WEB-INF/semi/sale/pay.jsp";
			forward(req, resp, path);

		} 
			else if(uri.indexOf("delete.do")!=-1) {
			// 장바구니 삭제(수동)
				if(info==null) { // 로그인되지 않은 경우
				resp.sendRedirect(cp+"/member/login.do");
				return;
			}
				
			int num = Integer.parseInt(req.getParameter("basket_num"));
			String id = info.getMemberId();
			
			dao.deleteBasket2(id,num);
			
			BasketdataCount = dao.basket_dataCount();
			List<PanmaeDTO> list = dao.listBasket();
			String basket_payUrl = cp + "/sale/pay.do";
			String deleteUrl = cp + "/sale/delete.do";
			
			req.setAttribute("list", list);
			req.setAttribute("basket_payUrl", basket_payUrl);
			req.setAttribute("deleteUrl", deleteUrl);
			req.setAttribute("dataCount", BasketdataCount);

			String path = "/WEB-INF/semi/sale/basket.jsp";
			forward(req, resp, path);
		}
	}
}
