package com.adminOrder;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.member.SessionInfo;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/admin/ordermgmt/*")
public class AdminOrderSevlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		AdminOrderDAO dao=new AdminOrderDAO();
		MyUtil util=new MyUtil();
		
		if(uri.indexOf("list.do")!=-1){
			//주문리스트
			if(info==null || !info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/semiProject_2nd/main.do");
				return;
			}
			
			String page=req.getParameter("page");
			int current_page=1;
			if(page!=null)
				current_page=Integer.parseInt(page);
			
			String jumunState=req.getParameter("jumunState");
			String payState=req.getParameter("payState");
			String searchKey=req.getParameter("searchKey");
			String searchValue=req.getParameter("searchValue");
			
			if(req.getMethod().equalsIgnoreCase("GET") && searchValue!=null){
				searchValue=URLDecoder.decode(searchValue,"UTF-8");
			}
			
			if(jumunState==null)
				jumunState="";
			if(payState==null)
				payState="";
			if(searchKey==null)
				searchKey="";
			if(searchValue==null)
				searchValue="";
			
			int dataCount;
			if(jumunState.length()!=0 || payState.length()!=0 || searchKey.length()!=0 || searchValue.length()!=0)
				dataCount=dao.dataCount(jumunState, payState, searchKey, searchValue);
			else
				dataCount=dao.dataCount();
			
			int numPerPage=10;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			
			List<AdminOrderDTO> orderList=null;
			if(jumunState.length()!=0 || payState.length()!=0 || searchKey.length()!=0 || searchValue.length()!=0)
				orderList=dao.orderList(start, end, jumunState, payState, searchKey, searchValue);
			else
				orderList=dao.orderList(start, end);
			
			StringBuffer params=new StringBuffer();
			
			if(jumunState.length()!=0) 
				params.append("&jumunState="+jumunState);
			if(payState.length()!=0) 
				params.append("&payState="+payState);
			if(searchKey.length()!=0 || searchValue.length()!=0) {
				searchValue=URLEncoder.encode(searchValue, "utf-8");
				params.append("&searchKey="+searchKey+"&searchValue="+searchValue);
			}
			
			if(params.toString().indexOf("&")==0){
				params.deleteCharAt(0);
			}
			
			String listUrl=cp+"/admin/ordermgmt/list.do";
			String articleUrl=cp+"/admin/ordermgmt/update.do?page="+current_page;
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
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/list.jsp");
			
		}else if(uri.indexOf("detail.do")!=-1){
			//주문상세보기
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
			
		}else if(uri.indexOf("updateOrder.do")!=-1){
			//주문상세수정
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/update.jsp");
			
		}else if(uri.indexOf("updateOrder_ok.do")!=-1){
			//주문상세수정완료
			resp.sendRedirect(cp+"/admin/ordermgmt/detail.do");
			
		}else if(uri.indexOf("updaetPayment")!=-1){
			//결제수정
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/updatePayment.jsp");
			
		}else if(uri.indexOf("updatePayment_ok")!=-1){
			//결제수정완료
			
			resp.sendRedirect(cp+"/admin/ordermgmt/detail.do");
			
		}else if(uri.indexOf("updateDelivery")!=-1){
			//배송수정
		}
	}

}
