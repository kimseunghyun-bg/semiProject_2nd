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
	    	  //ȸ������ ��
		         req.setAttribute("mode", "created");
		         req.setAttribute("title", "ȸ�� ����");

		         forward(req, resp, "/WEB-INF/views/member/join.jsp");
	    	 
	      } else if(uri.indexOf("insert_ok.do")!=-1) {
	    	  
	    	  //ȸ�� ����
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
	               String message = "ȸ�����Կ� �����ϼ̽��ϴ�.";
	               
	               req.setAttribute("title", "ȸ�� ����");
	               req.setAttribute("mode", "created");
	               req.setAttribute("message", message);
	               forward(req, resp, "/WEB-INF/views/member/join.jsp");
	               return;
	            }
	            
	            StringBuffer sb = new StringBuffer();
	            sb.append("<b>"+dto.getName()+"</b>�� ȸ�������� �Ϸ�Ǿ����ϴ�.<br>");
	            sb.append("����ȭ������ �̵��Ͽ� �α����Ͻñ� �ٶ��ϴ�.<br>");
	            
	            req.setAttribute("title", "ȸ�� ����");
	            req.setAttribute("message", sb.toString());
	            
	            forward(req, resp, "/WEB-INF/views/member/complete.jsp");
	                
	      }else if(uri.indexOf("login.do") != -1){
	    	  
	    	  forward(req, resp, "/WEB-INF/views/member/login.jsp");
	    	  
	      }else if(uri.indexOf("login_ok.do") != -1){
	    	  
	    	  String memberId=req.getParameter("memberId");
	    	  String password=req.getParameter("password");
	    	  
	    	  MemberDTO dto=dao.readMember(memberId);
	    	  
	    	  if(dto!=null && dto.getPassword().equals(password) && dto.getEnabled()==1){
	    		  // �α��� ����
	    		  SessionInfo info=new SessionInfo();
	    		  info.setMemberId(dto.getMemberId());
	    		  info.setName(dto.getName());
	    		  
	    		  session.setAttribute("member", info); // ���ǿ� ����
	    		  
	    		  resp.sendRedirect(cp+"/"); // �α��ν� ������������
	    		  
	    		  return;
	    	  }
	    	  // �α��� ���н�
	    	  req.setAttribute("message", "���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�.");
	    	  
	    	  forward(req, resp, "/WEB-INF/views/member/login.jsp");
	    		  
	      }else if(uri.indexOf("logout.do") != -1){
	    	  session.removeAttribute("member");
	    	  session.invalidate();
	    	  
	    	  resp.sendRedirect(cp+"/");
	      }
	      
	      
	   }
}
