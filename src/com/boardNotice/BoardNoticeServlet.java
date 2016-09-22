package com.boardNotice;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Iterator;
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

@WebServlet("/boardNotice/*")
public class BoardNoticeServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		BoardNoticeDAO dao=new BoardNoticeDAO();
		MyUtil util=new MyUtil();
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		if(info==null){
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		String root=session.getServletContext().getRealPath("/");
		String pathname=root+File.separator+"uploads"+File.separator+"notice";
		File f=new File(pathname);
		
		if(!f.exists()) f.mkdirs();
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		if(uri.indexOf("list.do")!=-1){
			String page=req.getParameter("page");
			int current_page=1;
			
			if(page!=null)
				current_page=Integer.parseInt(page);
			
			String searchKey=req.getParameter("searchKey");
			String searchValue=req.getParameter("searchValue");
			if(searchKey==null){
				searchKey="subject";
				searchValue="";
			}
			
			if(req.getMethod().equalsIgnoreCase("GET"))
				searchValue=URLDecoder.decode(searchValue, "UTF-8");
			
			int dataCount;
			if(searchValue.length()==0)
				dataCount=dao.dataCount();
			else
				dataCount=dao.dataCount(searchKey, searchValue);
			
			int numPerPage=5;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			
			List<BoardNoticeDTO> listNotice=null;
			if(current_page==1)
				listNotice=dao.listBoardNoticeTop();
			
			List<BoardNoticeDTO> list=null;
			if(searchValue.length()==0)
				list=dao.listBoardNotice(start, end);
			else
				list=dao.listBoardNotice(start, end, searchKey, searchValue);
			
			int listNum, n=0;
			Iterator<BoardNoticeDTO> it=list.iterator();
			while(it.hasNext()){
				BoardNoticeDTO dto=it.next();
				listNum=dataCount-(start+n-1);
				dto.setListNum(listNum);
				n++;
			}
			
			String params="";
			if(searchValue.length()!=0){
				searchValue=URLEncoder.encode(searchValue, "UTF-8");
				params="searchKey="+searchKey+"&searchValue="+searchValue;
			}
			
			String listUrl=cp+"/boardNotice/list.do";
			String articleUrl=cp+"/boardNotice/article.do?page="+current_page;
			if(params.length()!=0){
				listUrl+="?"+params;
				articleUrl+="&"+params;
			}
			
			String paging=util.paging(current_page, total_page, listUrl);
			
			req.setAttribute("list", list);
			req.setAttribute("listNotice", listNotice);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("paging", paging);
			req.setAttribute("articleUrl", articleUrl);
			
			forward(req, resp, "/WEB-INF/views/boardNotice/list.jsp");
		}else if(uri.indexOf("created.do")!=-1){
			
			if (info==null || !info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/boardNotice/list.do");
				return;
			}
			
			req.setAttribute("mode", "created");
			
			forward(req, resp, "/WEB-INF/views/boardNotice/created.jsp");
		}else if(uri.indexOf("created_ok.do")!=-1){
			if (info==null || !info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/boardNotice/list.do");
				return;
			}
			
			BoardNoticeDTO dto=new BoardNoticeDTO();
			
			String encType="UTF-8";
			int maxFilesize=5*1024*1024;
			
			MultipartRequest mreq=new MultipartRequest
					(req, pathname, maxFilesize, encType, new DefaultFileRenamePolicy());
			
			dto.setMemberId(info.getMemberId());
			dto.setSubject(mreq.getParameter("subject"));
			dto.setContent(mreq.getParameter("content"));
			if(mreq.getParameter("notice")!=null)
				dto.setNotice(Integer.parseInt(mreq.getParameter("notice")));
			
			File file=mreq.getFile("upload");
			if(file!=null){
				dto.setSaveFilename(mreq.getFilesystemName("upload"));
				dto.setOriginalFilename(mreq.getOriginalFileName("upload"));
				dto.setFilesize(file.length());
			}
			
			dao.insertBoardNotice(dto);
			
			resp.sendRedirect(cp+"/boardNotice/list.do");
		}else if(uri.indexOf("download.do")!=-1){
			int num=Integer.parseInt(req.getParameter("num"));
			BoardNoticeDTO dto=dao.readBoardNotice(num);
			
			if(dto!=null){
				boolean flag=FileManager.doFiledownload
						(dto.getSaveFilename(), dto.getOriginalFilename(), pathname, resp);
				
				if(flag) return;
			}
			
			resp.setContentType("text/html;charset=utf-8");
			PrintWriter out=resp.getWriter();
			
			out.print("<script>");
			out.print("alert('파일을 다운로드하는데 실패했습니다.'); history.back();");
			out.print("</script>");
		}else if(uri.indexOf("article.do")!=-1){
			int num=Integer.parseInt(req.getParameter("num"));
			String page=req.getParameter("page");
			
			String searchKey=req.getParameter("searchKey");
			String searchValue=req.getParameter("searchValue");
			if(searchKey==null){
				searchKey="subject";
				searchValue="";
			}
			searchValue=URLDecoder.decode(searchValue, "UTF-8");
			
			dao.updateHitCount(num);
			
			BoardNoticeDTO dto=dao.readBoardNotice(num);
			if(dto==null){
				resp.sendRedirect(cp+"/boardNotice/list.do?page="+page);
				return;
			}
			
			int linesu=dto.getContent().split("\n").length;
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			BoardNoticeDTO preReadDTO=dao.preReadBoardNotice(num, searchKey, searchValue);
			BoardNoticeDTO nextReadDTO=dao.nextReadBoardNotice(num, searchKey, searchValue);
			
			String params="page="+page;
			if(searchValue.length()!=0){
				params+="&searchKey="+searchKey+
				"&searchValue="+URLEncoder.encode(searchValue, "UTF-8");
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("preReadDTO", preReadDTO);
			req.setAttribute("nextReadDTO", nextReadDTO);
			req.setAttribute("linesu", linesu);
			req.setAttribute("params", params);
			
			forward(req, resp, "/WEB-INF/views/boardNotice/article.jsp");
			
		}else if(uri.indexOf("update.do")!=-1){
			int num=Integer.parseInt(req.getParameter("num"));
			String page=req.getParameter("page");
			
			BoardNoticeDTO dto=dao.readBoardNotice(num);
			
			if(dto==null){
				resp.sendRedirect(cp+"/boardNotice/list.do?page="+page);
				return;
			}
			
			if(!dto.getMemberId().equals(info.getMemberId())) {
				resp.sendRedirect(cp+"/boardNotice/list.do?page="+page);
				return;
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("mode", "update");
			
			forward(req, resp, "/WEB-INF/views/boardNotice/created.jsp");
		}else if(uri.indexOf("update_ok.do")!=-1){
			if (info==null || !info.getMemberId().equals("admin")) {
				resp.sendRedirect(cp+"/boardNotice/list.do");
				return;
			}
			
			BoardNoticeDTO dto=new BoardNoticeDTO();
			
			String encType="UTF-8";
			int maxFilesize=5*1024*1024;
			
			MultipartRequest mreq=new MultipartRequest
					(req, pathname, maxFilesize, encType, new DefaultFileRenamePolicy());
			
			dto.setMemberId(info.getMemberId());
			dto.setNum(Integer.parseInt(mreq.getParameter("num")));
			if(mreq.getParameter("notice")!=null)
				dto.setNotice(Integer.parseInt(mreq.getParameter("notice")));
			dto.setSubject(mreq.getParameter("subject"));
			dto.setContent(mreq.getParameter("content"));
			
			File file=mreq.getFile("upload");
			if(file!=null){
				dto.setSaveFilename(mreq.getFilesystemName("upload"));
				dto.setOriginalFilename(mreq.getOriginalFileName("upload"));
				dto.setFilesize(file.length());
			}
			
			dao.updateBoardNotice(dto);
			
			resp.sendRedirect(cp+"/boardNotice/list.do");
		}else if(uri.indexOf("delete_ok.do")!=-1){
			String page=req.getParameter("page");
			int num=Integer.parseInt(req.getParameter("num"));
			
			BoardNoticeDTO dto=dao.readBoardNotice(num);
			
			if(dto==null){
				resp.sendRedirect(cp+"/boardNotice/list.do?page="+page);
				return;
			}
			
			if(!dto.getMemberId().equals(info.getMemberId()) && !info.getMemberId().equals("admin")){
				resp.sendRedirect(cp+"boardNotice/list.do?page="+page);
				return;
			}
			
			dao.deleteBoardNotice(num);
			
			resp.sendRedirect(cp+"/boardNotice/list.do?page="+page);
		}
	}
}