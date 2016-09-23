package com.adminOrder;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adminGoods.AdminGoodsDAO;
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
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
		AdminGoodsDAO dao=new AdminGoodsDAO();
		MyUtil util=new MyUtil();
		
		if(uri.indexOf("list.do")!=-1){
			
			forward(req, resp, "/WEB-INF/views/admin/ordermgmt/list.jsp");
		}
	}

}
