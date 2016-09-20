package com.admin;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.FileManager;
import com.util.MyServlet;

@WebServlet("/admin/goodsmgmt/*")
public class AdminGoodsServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String pathname="C:\\image";
		File f=new File(pathname);
		if(! f.exists())
			f.mkdirs();
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		//HttpSession session=req.getSession();
		
		AdminGoodsDAO dao=new AdminGoodsDAO();
		
		if(uri.indexOf("list.do")!=-1){
			
			//Map<String, String> map=dao.groupMajor();
			
			//req.setAttribute("groupMajor", map);
			
			List<AdminGoodsDTO> list=dao.group();
			
			req.setAttribute("group", list);
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/list.jsp");
		}else if(uri.indexOf("create.do")!=-1){
			
			
			List<AdminGoodsDTO> groupList=dao.group();
			List<AdminGoodsDTO> producerList=dao.producer();
			req.setAttribute("mode", "create");
			req.setAttribute("group", groupList);
			req.setAttribute("producer", producerList);
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/create.jsp");
		}else if(uri.indexOf("create_ok.do")!=-1){
			AdminGoodsDTO dto=new AdminGoodsDTO();
			String enctype="UTF-8";
			int maxFilesize=5*1024*1024;
			
			MultipartRequest mreq=null;
			mreq=new MultipartRequest(req, pathname, maxFilesize, enctype, new DefaultFileRenamePolicy());
			
			dto.setKindCode(mreq.getParameter("major"));
			dto.setProduceCode(mreq.getParameter("producer"));
			dto.setName(mreq.getParameter("subject"));
			dto.setIntroduce(mreq.getParameter("introduce"));
			dto.setSaveNum(mreq.getParameter("amount"));
			dto.setPrice(mreq.getParameter("price"));
			dto.setPanmaeState(mreq.getParameter("status"));
			File file=mreq.getFile("picture");
			if(file!=null){
				String newname=FileManager.doFilerename(pathname, mreq.getFilesystemName("picture"));
				dto.setImage(newname);
			}
			
			dao.insertGoods(dto);
			
			resp.sendRedirect(cp+"/admin/goodsmgmt/list.do");
		}else if(uri.indexOf("update.do")!=-1){
			
			forward(req, resp, "/cp/WEB-INF/views/admin/goodsmgmt/created.jsp");
		}else if(uri.indexOf("update_ok")!=-1){
			
			forward(req, resp, "/cp/WEB-INF/views/admin/goodsmgmt/detail.jsp");
		}else if(uri.indexOf("detail.do")!=-1){
			
		}
	}

}
