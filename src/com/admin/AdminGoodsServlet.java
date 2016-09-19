package com.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.MyServlet;

@WebServlet("/admin/goodsmgmt/*")
public class AdminGoodsServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		HttpSession session=req.getSession();
		
		if(uri.indexOf("list.do")!=-1){
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/list.jsp");
		}else if(uri.indexOf("create.do")!=-1){
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/create.jsp");
		}else if(uri.indexOf("create_ok.do")!=-1){
			
		}else if(uri.indexOf("update.do")!=-1){
			
			forward(req, resp, "/cp/WEB-INF/views/admin/goodsmgmt/created.jsp");
		}else if(uri.indexOf("update_ok")!=-1){
			
			forward(req, resp, "/cp/WEB-INF/views/admin/goodsmgmt/detail.jsp");
		}else if(uri.indexOf("detail.do")!=-1){
			
		}
	}

}
