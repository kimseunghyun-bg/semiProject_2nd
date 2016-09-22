package com.member;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.util.MyServlet;

@WebServlet("/member/*")
public class MemberServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	   @Override
	   protected void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	      req.setCharacterEncoding("utf-8");
	      String uri = req.getRequestURI();
	      String cp = req.getContextPath();
	      
	      MemberDAO dao = new MemberDAO();
	      
	      HttpSession session=req.getSession();
	      
	      if(uri.indexOf("insert.do")!=-1) {
	    	  //회원가입 폼
		         req.setAttribute("mode", "created");
		         req.setAttribute("title", "회원 가입");

		         forward(req, resp, "/WEB-INF/views/member/join.jsp");
	    	 
	      } else if(uri.indexOf("insert_ok.do")!=-1) {
	    	  
	    	  //회원 저장
	    	  MemberDTO dto = new MemberDTO();
	          dto.setMemberId(req.getParameter("memberId"));
	          dto.setName(req.getParameter("name"));
	          dto.setPassword(req.getParameter("password"));
	          
	          dto.setBirth(req.getParameter("birth"));
	          dto.setEmail1(req.getParameter("email1"));
	          dto.setEmail2(req.getParameter("email2"));
	          dto.setHousephone1(req.getParameter("housephone1"));
	          dto.setHousephone2(req.getParameter("housephone2"));
	          dto.setHousephone3(req.getParameter("housephone3"));
	          dto.setTelephone1(req.getParameter("telephone1"));
	          dto.setTelephone2(req.getParameter("telephone2"));
	          dto.setTelephone3(req.getParameter("telephone3"));
	          dto.setZip(req.getParameter("zip"));
	          dto.setAddr1(req.getParameter("addr1"));
	          dto.setAddr2(req.getParameter("addr2"));
	    	  
	    	  int result = dao.insertMember(dto);
	            if(result != 1){
	               String message = "회원가입에 실패하셨습니다.";
	               
	               req.setAttribute("title", "회원 가입");
	               req.setAttribute("mode", "created");
	               req.setAttribute("message", message);
	               forward(req, resp, "/WEB-INF/views/member/join.jsp");
	               return;
	            }
	            
	            StringBuffer sb = new StringBuffer();
	            sb.append("<b>"+dto.getName()+"</b>님 회원가입이 완료되었습니다.<br>");
	            sb.append("메인화면으로 이동하여 로그인하시기 바랍니다.<br>");
	            
	            req.setAttribute("title", "회원 가입");
	            req.setAttribute("message", sb.toString());
	            
	            forward(req, resp, "/WEB-INF/views/member/complete.jsp");
	                
	      }else if(uri.indexOf("login.do") != -1){
	    	  
	    	  forward(req, resp, "/WEB-INF/views/member/login.jsp");
	    	  
	      }else if(uri.indexOf("login_ok.do") != -1){
	    	  
	    	  String memberId=req.getParameter("memberId");
	    	  String password=req.getParameter("password");
	    	  
	    	  MemberDTO dto=dao.readMember(memberId);
	    	  
	    	  if(dto!=null && dto.getPassword().equals(password) && dto.getEnabled()==1){
	    		  // 로그인 성공
	    		  SessionInfo info=new SessionInfo();
	    		  info.setMemberId(dto.getMemberId());
	    		  info.setName(dto.getName());
	    		  
	    		  session.setAttribute("member", info); // 세션에 저장
	    		  
	    		  resp.sendRedirect(cp+"/"); // 로그인시 메인페이지로
	    		  
	    		  return;
	    	  }
	    	  // 로그인 실패시
	    	  req.setAttribute("message", "아이디 또는 패스워드가 일치하지 않습니다.");
	    	  
	    	  forward(req, resp, "/WEB-INF/views/member/login.jsp");
	    		  
	      }else if(uri.indexOf("logout.do") != -1){
	    	  session.removeAttribute("member");
	    	  session.invalidate();
	    	  
	    	  resp.sendRedirect(cp+"/");
	      }
	      
	      
	   }
}
