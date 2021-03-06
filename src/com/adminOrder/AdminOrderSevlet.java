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
			if(jumunState.length()!=0 || payState.length()!=0 || (searchKey.length()!=0 && searchValue.length()!=0)){
				dataCount=dao.dataCount(jumunState, payState, searchKey, searchValue);
			}else
				dataCount=dao.dataCount();
			
			int numPerPage=10;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			
			List<AdminOrderDTO> orderList=null;
			if(jumunState.length()!=0 || payState.length()!=0 || (searchKey.length()!=0 && searchValue.length()!=0))
				orderList=dao.orderList(start, end, jumunState, payState, searchKey, searchValue);
			else
				orderList=dao.orderList(start, end);
			
			StringBuffer params=new StringBuffer();
			
			if(jumunState.length()!=0) 
				params.append("&jumunState="+jumunState);
			if(payState.length()!=0) 
				params.append("&payState="+payState);
			if(searchKey.length()!=0 && searchValue.length()!=0) {
				searchValue=URLEncoder.encode(searchValue, "utf-8");
				params.append("&searchKey="+searchKey+"&searchValue="+searchValue);
			}
			
			if(params.toString().indexOf("&")==0){
				params.deleteCharAt(0);
			}
			
			String listUrl=cp+"/admin/ordermgmt/list.do";
			String articleUrl=cp+"/admin/ordermgmt/detail.do?page="+current_page;
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
			if(info==null || !info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/semiProject_2nd/main.do");
				return;
			}
			
			int jumunNum=Integer.parseInt(req.getParameter("jumunNum"));
			String page=req.getParameter("page");
			
			AdminOrderDTO dto=dao.readJumunDetail(jumunNum);
			List<AdminOrderSubDTO> list=dao.readJumunSubDetail(jumunNum);
			
			if(dto==null){
				resp.sendRedirect(cp+"/admin/ordermgmt/list.do?page="+page);
				return;
			}
			
			req.setAttribute("mode", "view");
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
			
		}else if(uri.indexOf("updatePayment.do")!=-1){
			//결제수정
			int jumunNum=Integer.parseInt(req.getParameter("jumunNum"));
			String page=req.getParameter("page");
			
			AdminOrderDTO dto=dao.readJumunDetail(jumunNum);
			List<AdminOrderSubDTO> list=dao.readJumunSubDetail(jumunNum);
			
			if(dto==null){
				resp.sendRedirect(cp+"/admin/ordermgmt/list.do?page="+page);
			}
			
			req.setAttribute("mode", "updatePayment");
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
		}else if(uri.indexOf("updatePayment_ok.do")!=-1){
			//결제수정완료
			int jumunNum=Integer.parseInt(req.getParameter("jumunNum"));
			String page=req.getParameter("page");
			
			AdminOrderDTO updatedto=new AdminOrderDTO();
			
			updatedto.setJumunNum(req.getParameter("jumunNum"));
			updatedto.setPayState(req.getParameter("payState"));
			updatedto.setPayTotal(req.getParameter("payTotal"));
			updatedto.setPayRoot(req.getParameter("payRoot"));
			
			dao.updatePayment(updatedto);
			
			AdminOrderDTO dto=dao.readJumunDetail(jumunNum);
			List<AdminOrderSubDTO> list=dao.readJumunSubDetail(jumunNum);
			
			req.setAttribute("mode", "view");
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
			
		}else if(uri.indexOf("updateArrive.do")!=-1){
			//배송지수정
			int jumunNum=Integer.parseInt(req.getParameter("jumunNum"));
			String page=req.getParameter("page");
			
			AdminOrderDTO dto=dao.readJumunDetail(jumunNum);
			List<AdminOrderSubDTO> list=dao.readJumunSubDetail(jumunNum);
			
			if(dto==null){
				resp.sendRedirect(cp+"/admin/ordermgmt/list.do?page="+page);
			}
			
			req.setAttribute("mode", "updateArrive");
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
			
		}else if(uri.indexOf("updateArrive_ok.do")!=-1){
			//배송지수정완료
			int jumunNum=Integer.parseInt(req.getParameter("jumunNum"));
			String page=req.getParameter("page");
			
			AdminOrderDTO updatedto=new AdminOrderDTO();
			
			updatedto.setJumunNum(req.getParameter("jumunNum"));
			updatedto.setSendName(req.getParameter("sendName"));
			updatedto.setPhone_1(req.getParameter("phone_1"));
			updatedto.setPhone_2(req.getParameter("phone_2"));
			updatedto.setPhone_3(req.getParameter("phone_3"));
			updatedto.setZip(req.getParameter("zip"));
			updatedto.setAddr1(req.getParameter("addr1"));
			updatedto.setAddr2(req.getParameter("addr2"));
			
			dao.updateArrive(updatedto);
			
			AdminOrderDTO dto=dao.readJumunDetail(jumunNum);
			List<AdminOrderSubDTO> list=dao.readJumunSubDetail(jumunNum);
			
			req.setAttribute("mode", "view");
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
			
		}else if(uri.indexOf("updateDelivery.do")!=-1){
			//배송상태수정
			int jumunNum=Integer.parseInt(req.getParameter("jumunNum"));
			String page=req.getParameter("page");
			
			AdminOrderDTO dto=dao.readJumunDetail(jumunNum);
			List<AdminOrderSubDTO> list=dao.readJumunSubDetail(jumunNum);
			
			if(dto==null){
				resp.sendRedirect(cp+"/admin/ordermgmt/list.do?page="+page);
			}
			
			req.setAttribute("mode", "updateDelivery");
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
			
		}else if(uri.indexOf("updateDelivery_ok.do")!=-1){
			//배송상태수정완료
			
			int jumunNum=Integer.parseInt(req.getParameter("jumunNum"));
			String page=req.getParameter("page");
			
			AdminOrderDTO updatedto=new AdminOrderDTO();
			
			updatedto.setJumunNum(req.getParameter("jumunNum"));
			updatedto.setSendState(req.getParameter("sendState"));
			updatedto.setPanmaeNum(req.getParameter("panmaeNum"));
			
			dao.updateDelivery(updatedto);
			
			AdminOrderDTO dto=dao.readJumunDetail(jumunNum);
			List<AdminOrderSubDTO> list=dao.readJumunSubDetail(jumunNum);
			
			req.setAttribute("mode", "view");
			req.setAttribute("list", list);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/detail.jsp");
			
		}else if(uri.indexOf("preparingDeliveryList.do")!=-1){
			//배송준비중 리스트
		}else if(uri.indexOf("finishDeliveryList.do")!=-1){
			//배송완료 리스트
		}else if(uri.indexOf("returnList.do")!=-1){
			//반품 리스트
		}
	}

}
