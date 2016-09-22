package com.boardFAQ;

import java.io.IOException;
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
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/boardFAQ/*")
public class BoardFAQServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		BoardFAQDAO dao=new BoardFAQDAO();
		MyUtil util=new MyUtil();
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
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
			
			int numPerPage=4;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			
			List<BoardFAQDTO> list=null;
			if(searchValue.length()==0)
				list=dao.listBoardFAQ(start, end);
			else
				list=dao.listBoardFAQ(start, end, searchKey, searchValue);
			
			int listNum, n=0;
			Iterator<BoardFAQDTO> it=list.iterator();
			while(it.hasNext()){
				BoardFAQDTO dto=it.next();
				listNum=dataCount-(start+n-1);
				dto.setListNum(listNum);
				n++;
			}
			
			String params="";
			if(searchValue.length()!=0){
				searchValue=URLEncoder.encode(searchValue, "UTF-8");
				params="searchKey="+searchKey+"&searchValue="+searchValue;
			}
			
			String listUrl=cp+"/boardFAQ/list.do";
			String articleUrl=cp+"/boardFAQ/article.do?page="+current_page;
			if(params.length()!=0){
				listUrl+="?"+params;
				articleUrl+="&"+params;
			}
			
			String paging=util.paging(current_page, total_page, listUrl);
			
			req.setAttribute("list", list);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("paging", paging);
			req.setAttribute("articleUrl", articleUrl);
			
			forward(req, resp, "/WEB-INF/views/boardFAQ/list.jsp");
		}else if(uri.indexOf("created.do")!=-1){
			req.setAttribute("mode", "created");
			
			forward(req, resp, "/WEB-INF/views/boardFAQ/created.jsp");
		}else if(uri.indexOf("created_ok.do")!=-1){
			BoardFAQDTO dto=new BoardFAQDTO();
			
			dto.setMemberId(info.getMemberId());
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			dao.insertBoardFAQ(dto);
			
			resp.sendRedirect(cp+"/boardFAQ/list.do");
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
			
			BoardFAQDTO dto=dao.readBoardFAQ(num);
			if(dto==null){
				resp.sendRedirect(cp+"/boardFAQ/list.do?page="+page);
				return;
			}
			
			int linesu=dto.getContent().split("\n").length;
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			BoardFAQDTO preReadDTO=dao.preReadBoardFAQ(num, searchKey, searchValue);
			BoardFAQDTO nextReadDTO=dao.nextReadBoardFAQ(num, searchKey, searchValue);
			
			String params="page="+page;
			if(searchValue.length()!=0)
				params+="&searchKey="+searchKey+"&searchValue="+URLEncoder.encode(searchValue, "UTF-8");
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("preReadDTO", preReadDTO);
			req.setAttribute("nextReadDTO", nextReadDTO);
			req.setAttribute("linesu", linesu);
			req.setAttribute("params", params);
			
			forward(req, resp, "/WEB-INF/views/boardFAQ/article.jsp");
		}else if(uri.indexOf("update.do")!=-1){
			int num=Integer.parseInt(req.getParameter("num"));
			String page=req.getParameter("page");
			
			BoardFAQDTO dto=dao.readBoardFAQ(num);
			if(dto==null){
				resp.sendRedirect(cp+"/boardFAQ/list.do?page="+page);
				return;
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("mode", "update");
			
			forward(req, resp, "/WEB-INF/views/boardFAQ/created.jsp");
		}else if(uri.indexOf("update_ok.do")!=-1){
			BoardFAQDTO dto=new BoardFAQDTO();
			int num=Integer.parseInt(req.getParameter("num"));
			
			dto.setNum(num);
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			String page=req.getParameter("page");
			
			dao.updateBoardFAQ(dto);
			
			resp.sendRedirect(cp+"/boardFAQ/article.do?num="+num+"&page="+page);
		}else if(uri.indexOf("delete.do")!=-1){
			int num=Integer.parseInt(req.getParameter("num"));
			String page=req.getParameter("page");
			
			dao.deleteBoard(num);
			
			resp.sendRedirect(cp+"/boardFAQ/list.do?page="+page);
		}
	}
}