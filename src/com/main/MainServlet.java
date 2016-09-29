
package com.main;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.boardNotice.BoardNoticeDAO;
import com.boardNotice.BoardNoticeDTO;
import com.util.MyServlet;
import com.util.MyUtil;

@WebServlet("/main/*")
public class MainServlet extends MyServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		BoardNoticeDAO dao=new BoardNoticeDAO();
		MyUtil util=new MyUtil();
		
		String uri=req.getRequestURI();
		String cp=req.getContextPath();
		
		if(uri.indexOf("main.do")!=-1) {
			int current_page=1;
			
			int dataCount=dao.dataCount();
			
			int numPerPage=5;
			int total_page=util.pageCount(numPerPage, dataCount);
			
			if(current_page>total_page)
				current_page=total_page;
			
			int start=(current_page-1)*numPerPage+1;
			int end=current_page*numPerPage;
			
			List<BoardNoticeDTO> list=dao.listBoardNotice(start, end);
			
			String articleUrl=cp+"/boardNotice/article.do?page="+current_page;
			
			req.setAttribute("list", list);
			req.setAttribute("articleUrl", articleUrl);
			
			forward(req, resp, "/WEB-INF/views/main/main.jsp");
		}else if(uri.indexOf("companyIntroduce.do")!=-1){
			forward(req, resp, "/WEB-INF/views/mainScreen/companyIntroduce.jsp");
		}
	}
}

























