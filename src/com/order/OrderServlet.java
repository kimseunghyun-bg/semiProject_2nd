package com.order;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/myPage/myOrder/*")
public class OrderServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");		
		
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		if(info==null){
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
				return;
			}
		String memberId=info.getMemberId();
		/*String uri=req.getRequestURI();*/
		String cp=req.getContextPath();
		
		
		OrderDAO dao=new OrderDAO();
		MyUtil util=new MyUtil();
		
		
		//주문목록
		String page=req.getParameter("page");
		int current_page=1;
		if(page!=null)
			current_page=Integer.parseInt(page);
		
/*		String jumunState=null;
		String payState=null;*/
/*		String searchKey=req.getParameter("searchKey");
		String searchValue=req.getParameter("searchValue");*/
		
		//GET 방식의 데이터 디코딩
/*		if(req.getMethod().equalsIgnoreCase("GET")&&searchValue!=null){
			searchValue=URLDecoder.decode(searchValue,"UTF-8");			
		}
		if(payState==null)
			payState="";
		if(jumunState==null)
			jumunState="";
		if(searchKey==null)
			searchKey="";
		if(searchValue==null)			
			searchValue="";*/
		
		//전체 데이터 개수
		int dataCount;
		dataCount=dao.dataCount(memberId);
/*		if(searchValue.length()==0)
			dataCount=dao.dataCount(memberId);
		else
			dataCount=dao.dataCount(jumunState, payState, searchKey, searchValue, memberId);*/
			
		
		int numPerPage=10;
		int total_page=util.pageCount(numPerPage, dataCount);
		
		if(current_page>total_page)
			current_page=total_page;
		
		int start=(current_page-1)*numPerPage+1;
		int end=current_page*numPerPage;
		
		List<OrderDTO> orderList=null;
/*		if(searchValue.length()==0)*/
		orderList=dao.orderList(start, end, memberId);
/*			
		else
			orderList=dao.orderList(start, end, jumunState, payState, searchKey, searchValue, memberId);*/
		
		StringBuffer params=new StringBuffer();
		
/*		if(jumunState.length()!=0) 
			params.append("&jumunState="+jumunState);
		if(payState.length()!=0) 
			params.append("&payState="+payState);
		if(searchKey.length()!=0 || searchValue.length()!=0) {
			searchValue=URLEncoder.encode(searchValue, "utf-8");
			params.append("&searchKey="+searchKey+"&searchValue="+searchValue);
		}

		if(params.toString().indexOf("&")==0){
			params.deleteCharAt(0);
		}*/
		
		String listUrl=cp+"/myPage/myOrder/list.do";
		String articleUrl=cp+"/myPage/myOrder/detail.do?page="+current_page;
		if(params.length()!=0) {
			listUrl+="?"+params;
			articleUrl+="&"+params;
		}
		String paging=util.paging(current_page, total_page, listUrl);
		
		req.setAttribute("orderList", orderList);
		req.setAttribute("page", current_page);
		req.setAttribute("total_page", total_page);
		req.setAttribute("dataCount", dataCount);
		req.setAttribute("paging", paging);
		req.setAttribute("articleUrl", articleUrl);
		
		forward(req, resp, "/WEB-INF/views/myPage/myOrder/orderList.jsp");
		
		
		/*
		OrderDTO dto=new OrderDTO();
		dao.cancleOrder(dto);
		StringBuffer sb=new StringBuffer();
		sb.append("<b>"+dto.getJumunNum()+"</b> 주문이 취소 되었습니다.<br>");
						
		req.setAttribute("title", "주문취소");
		req.setAttribute("message", sb.toString());
		
		forward(req, resp, "/WEB-INF/views/myPage/cancle/complete.jsp");*/
	}
	
}


