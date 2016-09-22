package com.admin;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
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
import com.util.MyUtil;

@WebServlet("/admin/goodsmgmt/*")
public class AdminGoodsServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String pathname="C:\\web\\work\\semiProject_2nd\\WebContent\\images\\admin";
		File f=new File(pathname);
		if(! f.exists())
			f.mkdirs();
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		AdminGoodsDAO dao=new AdminGoodsDAO();
		MyUtil util=new MyUtil();
		
		//HttpSession session=req.getSession();
		
		if(uri.indexOf("list.do")!=-1){
			
			//�ʱ� ������
			String page=req.getParameter("page");
			int current_page=1;
			if(page!=null)
				current_page=Integer.parseInt(page);
			
			//�˻�
			String panmaeState=req.getParameter("panmaeState");
			String groupCode=req.getParameter("groupCode");
			String kindCode=req.getParameter("kindCode");
			String searchKey=req.getParameter("searchKey");
			String searchValue=req.getParameter("searchValue");
			if(searchKey==null){
				searchKey="panmaeNum";
				searchValue="";
			}
			
			//GET��� ���ڵ�
			if(req.getMethod().equalsIgnoreCase("GET")){
				searchValue=URLDecoder.decode("searchValue","UTF-8");
			}
			
			//��ü ������ ����
			int dataCount;
			dataCount=dao.dataCount(panmaeState, groupCode, kindCode, searchKey, searchValue);
				
			//��ü ������ ��
			int numPerPage=5;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			
			//�Խù� ������ ���۰� ��
			int start=1;
			int end=10;
			
			//�Խù� ��������
			List<AdminGoodsDTO> panmaeList=dao.listPanmae(start, end);
			//����¡ó��
			
			//�������� JSP�� �ѱ� �Ӽ�
			
			
			List<AdminGoodsDTO> groupList=dao.group();
			
			req.setAttribute("panmaeList", panmaeList);
			req.setAttribute("groupList", groupList);
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/list.jsp");
		}else if(uri.indexOf("create.do")!=-1){
			
			
			List<AdminGoodsDTO> groupList=dao.group();
			List<AdminGoodsDTO> producerList=dao.producer();
			req.setAttribute("mode", "create");
			req.setAttribute("groupList", groupList);
			req.setAttribute("producerList", producerList);
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/create.jsp");
		}else if(uri.indexOf("create_ok.do")!=-1){
			AdminGoodsDTO dto=new AdminGoodsDTO();
			String enctype="UTF-8";
			int maxFilesize=5*1024*1024;
			
			MultipartRequest mreq=null;
			mreq=new MultipartRequest(req, pathname, maxFilesize, enctype, new DefaultFileRenamePolicy());
			
			dto.setKindCode(mreq.getParameter("kindCode"));
			dto.setProduceCode(mreq.getParameter("producer"));
			dto.setName(mreq.getParameter("subject"));
			dto.setIntroduce(mreq.getParameter("introduce"));
			dto.setSaveNum(mreq.getParameter("amount"));
			dto.setPrice(mreq.getParameter("price"));
			dto.setPanmaeState(mreq.getParameter("state"));
			File file=mreq.getFile("picture");
			if(file!=null){
				String newname=FileManager.doFilerename(pathname, mreq.getFilesystemName("picture"));
				dto.setImage(newname);
			}
			
			dao.insertGoods(dto);
			
			resp.sendRedirect(cp+"/admin/goodsmgmt/list.do");
		}else if(uri.indexOf("update.do")!=-1){
			
			int panmaeNum=Integer.parseInt(req.getParameter("panmaeNum"));
			
			AdminGoodsDTO dto=dao.readPanmae(panmaeNum);
			
			req.setAttribute("dto", dto);
			req.setAttribute("mode", "update");
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/create.jsp");
		}else if(uri.indexOf("update_ok.do")!=-1){
			AdminGoodsDTO dto=new AdminGoodsDTO();
			String enctype="UTF-8";
			int maxFilesize=5*1024*1024;
			
			MultipartRequest mreq=null;
			mreq=new MultipartRequest(req, pathname, maxFilesize, enctype, new DefaultFileRenamePolicy());
			
			dto.setPanmaeNum(mreq.getParameter("panmaeNum"));
			dto.setName(mreq.getParameter("subject"));
			dto.setIntroduce(mreq.getParameter("introduce"));
			dto.setSaveNum(mreq.getParameter("amount"));
			dto.setPanmaeState(mreq.getParameter("state"));
			File file=mreq.getFile("picture");
			if(file!=null){
				String newname=FileManager.doFilerename(pathname, mreq.getFilesystemName("picture"));
				dto.setImage(newname);
			}
			
			dao.updatePanmae(dto);
			
			resp.sendRedirect(cp+"/admin/goodsmgmt/list.do");
		}else if(uri.indexOf("detail.do")!=-1){
			
		}
	}

}
