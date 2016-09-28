package com.myQnA;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
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

@WebServlet("/myPage/myQnA/*")
public class MyQnAServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		
		if(info==null){
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
		}
		
		String memberId=info.getMemberId();
		
		String  uri=req.getRequestURI();
		String cp=req.getContextPath();
		MyQnADAO dao=new MyQnADAO();
		MyUtil util=new MyUtil();
		
		if(uri.indexOf("list.do")!=-1){
			String page=req.getParameter("page");
			int current_page=1;
			if(page!=null)
				current_page=Integer.parseInt(page);
			
			//검색 처리영역
			String searchKey=req.getParameter("searchKey");
			String searchValue=req.getParameter("searchValue");
			if(searchKey==null){
				searchKey="subject";
				searchValue="";
			}
			
			//GET 방식의 데이터 디코딩
			if(req.getMethod().equalsIgnoreCase("GET"))
				searchValue=URLDecoder.decode(searchValue, "UTF-8");
			
			//전체 데이터 개수
			int dataCount;
			if(searchValue.length()==0)
				dataCount=dao.dataCount(memberId);
			else
				dataCount=dao.dataCount(searchKey, searchValue, memberId);
			
			//전체 페이지 수
			int numPerPage=2;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			//게시물의 시작과 끝
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			
			//게시물 가져오기
			List<MyQnADTO> list=null;
			if(searchValue.length()==0)
				list=dao.listMyQnA(start, end, memberId);
			else
				list=dao.listMyQnA(start, end, searchKey, searchValue, memberId);
			
			//일정 시간동안 새로 작성한 글의 제목 옆에 이미지를 추가한다.
			Date endDate=new Date();
			long gap;
			
			//글번호 만들기
			int listNum, n=0;
			Iterator<MyQnADTO> it=list.iterator();
			while(it.hasNext()){
				MyQnADTO dto=it.next();
				listNum=dataCount-(start+n-1);
				dto.setListNum(listNum);
				
				try {
					SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					Date beginDate=formatter.parse(dto.getCreated());
					
					gap=(endDate.getTime()-beginDate.getTime())/(60*60*1000);
					dto.setGap(gap);
				} catch (Exception e) {}
				
				dto.setCreated(dto.getCreated().substring(0, 10));
				n++;
			}
			
			//페이징 파라미터
			String params="";
			
			if(searchValue.length()!=0){
				searchValue=URLEncoder.encode(searchValue, "UTF-8");
				params="searchKey="+searchKey+"&searchValue="+searchValue;
			}
			
			//페이징 처리
			String listUrl=cp+"/myPage/myQnA/list.do";
			String articleUrl=cp+"/myPage/myQnA/article.do?page="+current_page;
			if(params.length()!=0){
				listUrl+="?"+params;
				articleUrl+="&"+params;
			}
			
			String paging=util.paging(current_page, total_page, listUrl);
			
			//포워딩할 jsp로 넘길 속성
			req.setAttribute("list", list);
			req.setAttribute("page", current_page);
			req.setAttribute("total_page", total_page);
			req.setAttribute("dataCount", dataCount);
			req.setAttribute("paging", paging);
			req.setAttribute("articleUrl", articleUrl);
			
			forward(req, resp, "/WEB-INF/views/myPage/myQnA/list.jsp");
		}else if(uri.indexOf("created.do")!=-1){
			req.setAttribute("mode", "created");
			
			forward(req, resp, "/WEB-INF/views/myPage/myQnA/created.jsp");
		}else if(uri.indexOf("created_ok.do")!=-1){
			MyQnADTO dto=new MyQnADTO();
			
			dto.setMemberId(info.getMemberId());
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			dao.insertMyQnA(dto, "created");
			
			resp.sendRedirect(cp+"/myPage/myQnA/list.do");
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
			
			MyQnADTO dto=dao.readMyQnA(num);
			if(dto==null){
				resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
				return;
			}
			
			int linesu=dto.getContent().split("\n").length;
			dto.setContent(dto.getContent().replaceAll("\n", "<br>"));
			
			int groupNum=dto.getGroupNum();
			int orderNo=dto.getOrderNo();
			MyQnADTO preReadDTO=dao.preReadMyQnA(groupNum, orderNo, searchKey, searchValue);
			MyQnADTO nextReadDTO=dao.nextReadMyQnA(groupNum, orderNo, searchKey, searchValue);
			
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
			
			forward(req, resp, "/WEB-INF/views/myPage/myQnA/article.jsp");
		}else if(uri.indexOf("update.do")!=-1){
			String page=req.getParameter("page");
			int num=Integer.parseInt(req.getParameter("num"));
			MyQnADTO dto=dao.readMyQnA(num);
			
			if(dto==null){
				resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
				return;
			}
			
			if(!dto.getMemberId().equals(info.getMemberId())){
				resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
				return;
			}
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("mode", "update");
			
			forward(req, resp, "/WEB-INF/views/myPage/myQnA/created.jsp");
		}else if(uri.indexOf("update_ok.do")!=-1){
			String page=req.getParameter("page");
			
			if(req.getMethod().equalsIgnoreCase("GET")){
				resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
				return;
			}
			
			MyQnADTO dto=new MyQnADTO();
			dto.setNum(Integer.parseInt(req.getParameter("num")));
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			
			dao.updateMyQnA(dto);
			
			resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
		}else if(uri.indexOf("reply.do")!=-1){
			int num=Integer.parseInt(req.getParameter("num"));
			String page=req.getParameter("page");
			
			MyQnADTO dto=dao.readMyQnA(num);
			if(dto==null){
				resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
				return;
			}
			
			dto.setContent("");
			
			req.setAttribute("dto", dto);
			req.setAttribute("page", page);
			req.setAttribute("mode", "reply");
			
			forward(req, resp, "/WEB-INF/views/myPage/myQnA/created.jsp");
		}else if(uri.indexOf("reply_ok.do")!=-1){
			MyQnADTO dto=new MyQnADTO();
			
			dto.setMemberId(info.getMemberId());
			dto.setSubject(req.getParameter("subject"));
			dto.setContent(req.getParameter("content"));
			dto.setGroupNum(Integer.parseInt(req.getParameter("groupNum")));
			dto.setDepth(Integer.parseInt(req.getParameter("depth")));
			dto.setOrderNo(Integer.parseInt(req.getParameter("orderNo")));
			dto.setParent(Integer.parseInt(req.getParameter("parent")));
		
			String page=req.getParameter("page");
			
			dao.insertMyQnA(dto, "reply");
			
			resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
		}else if(uri.indexOf("delete_ok.do")!=-1){
			String page=req.getParameter("page");
			int num=Integer.parseInt(req.getParameter("num"));
			
			MyQnADTO dto=dao.readMyQnA(num);
			if(dto==null){
				resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
				return;
			}
			
			if(!dto.getMemberId().equals(info.getMemberId()) && !info.getMemberId().equals("admin")){
				resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
				return;
			}
			
			dao.deleteMyQnA(num);
			
			resp.sendRedirect(cp+"/myPage/myQnA/list.do?page="+page);
		}
	}
}