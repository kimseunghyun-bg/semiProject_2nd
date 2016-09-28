package com.myPage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.member.SessionInfo;

import com.util.MyServlet;



@WebServlet("/myPage/myMember/*")
public class MyPageServlet extends MyServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void process(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		
		
		HttpSession session=req.getSession();
		SessionInfo info=(SessionInfo)session.getAttribute("member");
		String uri=req.getRequestURI();
		String cp=req.getContextPath();		

		MemberDAO dao=new MemberDAO();
		
		if(info==null){
			forward(req, resp, "/WEB-INF/views/member/login.jsp");
			return;
			}else if(uri.indexOf("login_ok.do")!=-1) {
		// 로그인 처리
			String memberId=req.getParameter("memberId");
			String password=req.getParameter("password");		
			MemberDTO dto=dao.readMember(memberId);
			if(dto!=null) {
				if(password.equals(dto.getPassword()) && dto.getEnabled()==1) {
					// 로그인 성공 : 로그인정보를 서버에 저장
					// 세션의 유지시간을 20분설정(기본 30분)
					session.setMaxInactiveInterval(20*60);
					
					// 세션에 저장할 내용				
					info.setMemberId(dto.getMemberId());
					info.setName(dto.getName());
					
					// 세션에 member이라는 이름으로 저장
					session.setAttribute("member", info);
					
					// 마이페이지화면으로 리다이렉트
					String path="/WEB-INF/views/myPage/myMember/myPage.jsp";
					forward(req, resp, path);
					return;
				} 
			}
			// 로그인 실패인 경우(다시 로그인 폼으로)
						String msg="아이디 또는 패스워드가 일치하지 않습니다.";
						req.setAttribute("message", msg);
						
						String path="/WEB-INF/views/myPage/myMember/myPage.jsp";
						forward(req, resp, path);
			}else if(uri.indexOf("myPage.do")!=-1){
				forward(req, resp, "/WEB-INF/views/myPage/myMember/myPage.jsp");
			} else if(uri.indexOf("pwd.do")!=-1) {
				// 패스워드 확인 폼
				info=(SessionInfo)session.getAttribute("member");
				if(info==null) {
					// 로그아웃상태이면
					resp.sendRedirect(cp+"/member/login.do");
					return;
				}
				
				String mode=req.getParameter("mode");
				if(mode.equals("update"))
					req.setAttribute("title", "회원 정보 수정");
				else
					req.setAttribute("title", "회원 탈퇴");
				
				req.setAttribute("mode", mode);
				forward(req, resp, "/WEB-INF/views/myPage/myMember/pwd.jsp");
				
			} else if(uri.indexOf("pwd_ok.do")!=-1) {
				// 패스워드 확인
				info=(SessionInfo)session.getAttribute("member");
				if(info==null) { //로그아웃 된 경우
					resp.sendRedirect(cp+"/member/login.do");
					return;
				}
				
				// DB에서 해당 회원 정보 가져오기
				MemberDTO dto=dao.readMember(info.getMemberId());
				if(dto==null) {
					session.invalidate();
					resp.sendRedirect(cp+"/myPage/myPage.do");
					return;
				}
				
				String userPwd=req.getParameter("password");
				String mode=req.getParameter("mode");
				if(! dto.getPassword().equals(userPwd)) {
					if(mode.equals("update"))
						req.setAttribute("title", "회원 정보 수정");
					else
						req.setAttribute("title", "회원 탈퇴");
			
					req.setAttribute("mode", mode);
					req.setAttribute("message", "<span style='color:red;'>패스워드가 일치하지 않습니다.</span>");
					forward(req, resp, "/WEB-INF/views/myPage/myMember/pwd.jsp");
					return;
				}
				
				if(mode.equals("dropout")) {
					// 회원탈퇴
					dao.deleteMember(info.getMemberId());
					
					session.removeAttribute("member");
					session.invalidate();
					
					StringBuffer sb=new StringBuffer();
					sb.append("<b>"+dto.getName()+ "</b>님 회원탈퇴가 정상처리되었습니다.<br>");
					sb.append("그동안 이용해 주셔서 감사합니다.<br>");
					
					
					req.setAttribute("message", sb.toString());
					
					forward(req, resp, "/WEB-INF/views/myPage/myMember/complete.jsp");
					
					return;
				}
				
				// 회원정보수정
				if(dto.getTelephone()!=null) {
					String []s=dto.getTelephone().split("-");
					if(s.length==3) {
						dto.setTelephone1(s[0]);
						dto.setTelephone2(s[1]);
						dto.setTelephone3(s[2]);
					}
				}
				
				// 회원수정폼으로 이동
				req.setAttribute("title", "회원 정보 수정");
				req.setAttribute("dto", dto);
				req.setAttribute("mode", "update");
				forward(req, resp, "/WEB-INF/views/myPage/myMember/update.jsp");
				
			} else if(uri.indexOf("update_ok.do")!=-1) {
				// 회원정보 수정 완료
				info=(SessionInfo)session.getAttribute("member");
				if(info==null) { //로그아웃 된 경우
					resp.sendRedirect(cp+"/member/login.do");
					return;
				}
				
				MemberDTO dto = new MemberDTO();

				dto.setMemberId(req.getParameter("memberId"));
				dto.setPassword(req.getParameter("password"));
				dto.setName(req.getParameter("name"));
				dto.setBirth(req.getParameter("birth"));
				dto.setEmail(req.getParameter("email"));
				String telephone1 = req.getParameter("telephone1");
				String telephone2 = req.getParameter("telephone2");
				String telephone3 = req.getParameter("telephone3");
				if (telephone1 != null && telephone1.length() != 0 && telephone2 != null
						&& telephone2.length() != 0 && telephone3 != null && telephone3.length() != 0) {
					dto.setTelephone(telephone1 + "-" + telephone2 + "-" + telephone3);
				}
				String housephone1 = req.getParameter("housephone1");
				String housephone2 = req.getParameter("housephone2");
				String housephone3 = req.getParameter("housephone3");
				if (housephone1 != null && housephone1.length() != 0 && housephone2 != null
						&& housephone2.length() != 0 && housephone3 != null && housephone3.length() != 0) {
					dto.setHousephone(housephone1 + "-" + housephone2 + "-" + housephone3);
				}
				dto.setZip(req.getParameter("zip"));
				dto.setAddr1(req.getParameter("addr1"));
				dto.setAddr2(req.getParameter("addr2"));
				
				dao.updateMember(dto);
				
				StringBuffer sb=new StringBuffer();
				sb.append("<b>"+dto.getName()+ "</b>님 회원 정보가 수정 되었습니다.<br>");
								
				req.setAttribute("title", "회원 정보 수정");
				req.setAttribute("message", sb.toString());
				
				forward(req, resp, "/WEB-INF/views/myPage/myMember/complete.jsp");
			}
		}	
}

	