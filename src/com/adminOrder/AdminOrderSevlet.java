package com.adminOrder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.MyServlet;

@WebServlet("/admin/ordermgmt/*")
public class AdminOrderSevlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		if(uri.indexOf("list.do")!=-1){
			//주문리스트
			
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
