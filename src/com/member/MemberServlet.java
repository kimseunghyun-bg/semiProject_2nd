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
	      
			if(uri.indexOf("login.do")!=-1) {
				// 로그인 폼
				String path="/WEB-INF/views/member/login.jsp";
				forward(req, resp, path);
				
			} else if(uri.indexOf("login_ok.do")!=-1) {
				// 로그인 처리
				String memberId=req.getParameter("memberId");
				String password=req.getParameter("password");
				
				MemberDTO dto=dao.readMember(memberId);
				if(dto!=null) {
					if(password.equals(dto.getPassword()) && dto.getEnabled()==1) {
						session.setMaxInactiveInterval(20*60);
						
						SessionInfo info=new SessionInfo();
						info.setMemberId(dto.getMemberId());
						info.setName(dto.getName());
						
						session.setAttribute("member", info);
						
						// 메인화면으로 리다이렉트
						resp.sendRedirect(cp);
						return;
					} 
				}
				
				// 로그인 실패인 경우(다시 로그인 폼으로)
				String msg="아이디 또는 패스워드가 일치하지 않습니다.";
				req.setAttribute("message", msg);
				
				String path="/WEB-INF/views/member/login.jsp";
				forward(req, resp, path);
				
			} else if(uri.indexOf("logout.do")!=-1) {
				// 로그아웃
				// 세션에 저장된 정보를 지운다
				session.removeAttribute("member");
				
				// 세션에 저장된 모든 정보를 지우고 세션을 초기화 한다.
				session.invalidate();
				
				// 루트로 리다이렉트
				resp.sendRedirect(cp);
				
			} else if(uri.indexOf("insert.do")!=-1) {
				// 회원가입폼
				req.setAttribute("title", "회원 가입");
				req.setAttribute("mode", "created");
				String path="/WEB-INF/views/member/join.jsp";
				forward(req, resp, path);
				
			} else if(uri.indexOf("insert_ok.do")!=-1) {
				// 회원가입 처리
				MemberDTO dto = new MemberDTO();

				dto.setMemberId(req.getParameter("memberId"));
				dto.setPassword(req.getParameter("password"));
				dto.setName(req.getParameter("name"));
				dto.setBirth(req.getParameter("birth"));
				dto.setEmail(req.getParameter("email"));
				String housephone1 = req.getParameter("housephone1");
				String housephone2 = req.getParameter("housephone2");
				String housephone3 = req.getParameter("housephone3");
				if (housephone1 != null && housephone1.length() != 0 && housephone2 != null
						&& housephone2.length() != 0 && housephone3 != null && housephone3.length() != 0) {
					dto.setHousephone(housephone1 + "-" + housephone2 + "-" + housephone3);
				}
				String telephone1 = req.getParameter("telephone1");
				String telephone2 = req.getParameter("telephone2");
				String telephone3 = req.getParameter("telephone3");
				if (telephone1 != null && telephone1.length() != 0 && telephone2 != null
						&& telephone2.length() != 0 && telephone3 != null && telephone3.length() != 0) {
					dto.setHousephone(telephone1 + "-" + telephone2 + "-" + telephone3);
				}
				dto.setZip(req.getParameter("zip"));
				dto.setAddr1(req.getParameter("addr1"));
				dto.setAddr2(req.getParameter("addr2"));

				int result = dao.insertMember(dto);
				if (result != 1) {
					String message = "회원 가입이 실패 했습니다.";

					req.setAttribute("title", "회원 가입");
					req.setAttribute("mode", "created");
					req.setAttribute("message", message);
					forward(req, resp, "/WEB-INF/views/member/join.jsp");
					return;
				}

				StringBuffer sb=new StringBuffer();
				sb.append("<b>"+dto.getName()+ "</b>님 회원가입이 되었습니다.<br>");
				sb.append("메인화면으로 이동하여 로그인 하시기 바랍니다.<br>");
				
				req.setAttribute("title", "회원 가입");
				req.setAttribute("message", sb.toString());
				
				forward(req, resp, "/WEB-INF/views/member/complete.jsp");
				
			}/* else if(uri.indexOf("pwd.do")!=-1) {
				// 패스워드 확인 폼
				SessionInfo info=(SessionInfo)session.getAttribute("member");
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
				forward(req, resp, "/WEB-INF/views/member/pwd.jsp");
				
			} else if(uri.indexOf("pwd_ok.do")!=-1) {
				// 패스워드 확인
				SessionInfo info=(SessionInfo)session.getAttribute("member");
				if(info==null) { //로그아웃 된 경우
					resp.sendRedirect(cp+"/member/login.do");
					return;
				}
				
				// DB에서 해당 회원 정보 가져오기
				MemberDTO dto=dao.readMember(info.getMemberId());
				if(dto==null) {
					session.invalidate();
					resp.sendRedirect(cp);
					return;
				}
				
				String password=req.getParameter("password");
				String mode=req.getParameter("mode");
				if(! dto.getPassword().equals(password)) {
					if(mode.equals("update"))
						req.setAttribute("title", "회원 정보 수정");
					else
						req.setAttribute("title", "회원 탈퇴");
			
					req.setAttribute("mode", mode);
					req.setAttribute("message", 	"<span style='color:red;'>패스워드가 일치하지 않습니다.</span>");
					forward(req, resp, "/WEB-INF/views/member/pwd.jsp");
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
					
					req.setAttribute("title", "회원 탈퇴");
					req.setAttribute("message", sb.toString());
					
					forward(req, resp, "/WEB-INF/views/member/complete.jsp");
					
					return;
				}
				
				// 회원정보수정
				if(dto.getTel()!=null) {
					String []s=dto.getTel().split("-");
					if(s.length==3) {
						dto.setTel1(s[0]);
						dto.setTel2(s[1]);
						dto.setTel3(s[2]);
					}
				}
				
				// 회원수정폼으로 이동
				req.setAttribute("title", "회원 정보 수정");
				req.setAttribute("dto", dto);
				req.setAttribute("mode", "update");
				forward(req, resp, "/WEB-INF/views/member/member.jsp");
				
			} else if(uri.indexOf("update_ok.do")!=-1) {
				// 회원정보 수정 완료
				SessionInfo info=(SessionInfo)session.getAttribute("member");
				if(info==null) { //로그아웃 된 경우
					resp.sendRedirect(cp+"/member/login.do");
					return;
				}
				
				MemberDTO dto = new MemberDTO();

				dto.setUserId(req.getParameter("userId"));
				dto.setUserPwd(req.getParameter("userPwd"));
				dto.setUserName(req.getParameter("userName"));
				dto.setBirth(req.getParameter("birth"));
				dto.setEmail(req.getParameter("email"));
				String tel1 = req.getParameter("tel1");
				String tel2 = req.getParameter("tel2");
				String tel3 = req.getParameter("tel3");
				if (tel1 != null && tel1.length() != 0 && tel2 != null
						&& tel2.length() != 0 && tel3 != null && tel3.length() != 0) {
					dto.setTel(tel1 + "-" + tel2 + "-" + tel3);
				}
				dto.setJob(req.getParameter("job"));
				dto.setZip(req.getParameter("zip"));
				dto.setAddr1(req.getParameter("addr1"));
				dto.setAddr2(req.getParameter("addr2"));
				
				dao.updateMember(dto);
				
				StringBuffer sb=new StringBuffer();
				sb.append("<b>"+dto.getUserName()+ "</b>님 회원 정보가 수정 되었습니다.<br>");
				sb.append("메인화면으로 이동 하시기 바랍니다.<br>");
				
				req.setAttribute("title", "회원 정보 수정");
				req.setAttribute("message", sb.toString());
				
				forward(req, resp, "/WEB-INF/views/member/complete.jsp");
			}*/
	   }
   }
