package com.adminGoods;

import java.io.File;
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
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.util.FileManager;
import com.util.MyServlet;
import com.util.MyUtil;

//session, session에 따른 login redirect 추가 필요.
@WebServlet("/admin/goodsmgmt/*")
public class AdminGoodsServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		HttpSession session = req.getSession();
		SessionInfo info = (SessionInfo) session.getAttribute("member");
		
	    String pathname="C:\\web\\work\\semiProject_2nd\\WebContent\\images\\panmaeImg";
		File f=new File(pathname);
		if(! f.exists())
			f.mkdirs();
		
		AdminGoodsDAO dao=new AdminGoodsDAO();
		MyUtil util=new MyUtil();
		
		if(uri.indexOf("list.do")!=-1){
			if(! info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/semiProject_2nd/main.do");
				return;
			}
			
			//초기 페이지
			String page=req.getParameter("page");
			int current_page=1;
			if(page!=null)
				current_page=Integer.parseInt(page);
			
			//검색
			String panmaeState=req.getParameter("panmaeState");
			String groupCode=req.getParameter("groupCode");
			String kindCode=req.getParameter("kindCode");
			String searchKey=req.getParameter("searchKey");
			String searchValue=req.getParameter("searchValue");

			//GET방식 디코딩
			if(req.getMethod().equalsIgnoreCase("GET") && searchValue!=null){
				searchValue=URLDecoder.decode(searchValue,"UTF-8");
			}
			
			if(panmaeState==null)
				panmaeState="";
			if(groupCode==null)
				groupCode="";
			if(kindCode==null)
				kindCode="";
			if(searchKey==null)
				searchKey="";
			if(searchValue==null)
				searchValue="";
			
			
			//전체 데이터 갯수
			int dataCount;
			if(panmaeState.length()!=0 || groupCode.length()!=0 || kindCode.length()!=0 || searchKey.length()!=0 || searchValue.length()!=0)
				dataCount=dao.dataCount(panmaeState, groupCode, kindCode, searchKey, searchValue);
			else
				dataCount=dao.dataCount();
				
			//전체 페이지 수
			int numPerPage=5;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			
			//게시물 가져올 시작과 끝
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			
			//게시물 가져오기
			List<AdminGoodsDTO> panmaeList=null;
			if(panmaeState.length()!=0 || groupCode.length()!=0 || kindCode.length()!=0 || searchKey.length()!=0 || searchValue.length()!=0)
				panmaeList=dao.listPanmae(start, end, panmaeState, groupCode, kindCode, searchKey, searchValue);
			else
				panmaeList=dao.listPanmae(start, end);
				
			// 검색인 경우 검색값 인코딩
			StringBuffer params=new StringBuffer();
			
			if(panmaeState.length()!=0) 
				params.append("&panmaeState="+panmaeState);
			if(groupCode.length()!=0) 
				params.append("&groupCode="+groupCode);
			if(kindCode.length()!=0) 
				params.append("&kindCode="+kindCode);
			if(searchKey.length()!=0 || searchValue.length()!=0) {
				searchValue=URLEncoder.encode(searchValue, "utf-8");
				params.append("&searchKey="+searchKey+"&searchValue="+searchValue);
			}
			
			if(params.toString().indexOf("&")==0){
				params.deleteCharAt(0);
			}
			
			//페이징처리
			String listUrl=cp+"/admin/goodsmgmt/list.do";
			String articleUrl=cp+"/admin/goodsmgmt/update.do?page="+current_page;
			if(params.length()!=0) {
				listUrl+="?"+params;
				articleUrl+="&"+params;
			}
			
			String paging=util.paging(current_page, total_page, listUrl);
			
			//포워딩할 JSP로 넘길 속성
			List<AdminGoodsDTO> groupList=dao.group();
			
			req.setAttribute("panmaeList", panmaeList);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("paging", paging);
			req.setAttribute("articleUrl", articleUrl);
			req.setAttribute("groupList", groupList);
			req.setAttribute("pathname", pathname);
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/list.jsp");
		}else if(uri.indexOf("create.do")!=-1){
			if(! info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/semiProject_2nd/main.do");
				return;
			}
			
			List<AdminGoodsDTO> groupList=dao.group();
			List<AdminGoodsDTO> producerList=dao.producer();
			req.setAttribute("mode", "create");
			req.setAttribute("groupList", groupList);
			req.setAttribute("producerList", producerList);
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/create.jsp");
			
		}else if(uri.indexOf("create_ok.do")!=-1){
			if(! info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/semiProject_2nd/main.do");
				return;
			}
			
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
			if(! info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/semiProject_2nd/main.do");
				return;
			}
			
			int panmaeNum=Integer.parseInt(req.getParameter("panmaeNum"));
			String page=req.getParameter("page");
			
			AdminGoodsDTO dto=dao.readPanmae(panmaeNum);
			if(dto==null){
				resp.sendRedirect(cp+"/admin/list.do?page="+page);
			}
			
			req.setAttribute("pathname", pathname);
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("mode", "update");
			
			forward(req, resp, "/WEB-INF/views/admin/goodsmgmt/create.jsp");
			
		}else if(uri.indexOf("update_ok.do")!=-1){
			if(! info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/semiProject_2nd/main.do");
				return;
			}
			
			AdminGoodsDTO dto=new AdminGoodsDTO();
			String enctype="UTF-8";
			int maxFilesize=5*1024*1024;
			
			MultipartRequest mreq=null;
			mreq=new MultipartRequest(req, pathname, maxFilesize, enctype, new DefaultFileRenamePolicy());
			
			String page=mreq.getParameter("page");
			String panmaeState=mreq.getParameter("panmaeState");
			String groupCode=mreq.getParameter("groupCode");
			String kindCode=mreq.getParameter("kindCode");
			String searchKey=mreq.getParameter("searchKey");
			String searchValue=mreq.getParameter("searchValue");
			
			StringBuffer params=new StringBuffer();
			
			if(panmaeState.length()!=0) 
				params.append("&panmaeState="+panmaeState);
			if(groupCode.length()!=0) 
				params.append("&groupCode="+groupCode);
			if(kindCode.length()!=0) 
				params.append("&kindCode="+kindCode);
			if(searchKey.length()!=0 || searchValue.length()!=0) {
				searchValue=URLEncoder.encode(searchValue, "utf-8");
				params.append("&searchKey="+searchKey+"&searchValue="+searchValue);
			}
			
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
			
			resp.sendRedirect(cp+"/admin/goodsmgmt/list.do?page="+page+params);
			
		}else if(uri.indexOf("detail.do")!=-1){
			
		}
	}

}