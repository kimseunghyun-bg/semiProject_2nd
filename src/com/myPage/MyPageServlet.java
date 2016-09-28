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
		// �α��� ó��
			String memberId=req.getParameter("memberId");
			String password=req.getParameter("password");		
			MemberDTO dto=dao.readMember(memberId);
			if(dto!=null) {
				if(password.equals(dto.getPassword()) && dto.getEnabled()==1) {
					// �α��� ���� : �α��������� ������ ����
					// ������ �����ð��� 20�м���(�⺻ 30��)
					session.setMaxInactiveInterval(20*60);
					
					// ���ǿ� ������ ����				
					info.setMemberId(dto.getMemberId());
					info.setName(dto.getName());
					
					// ���ǿ� member�̶�� �̸����� ����
					session.setAttribute("member", info);
					
					// ����������ȭ������ �����̷�Ʈ
					String path="/WEB-INF/views/myPage/myMember/myPage.jsp";
					forward(req, resp, path);
					return;
				} 
			}
			// �α��� ������ ���(�ٽ� �α��� ������)
						String msg="���̵� �Ǵ� �н����尡 ��ġ���� �ʽ��ϴ�.";
						req.setAttribute("message", msg);
						
						String path="/WEB-INF/views/myPage/myMember/myPage.jsp";
						forward(req, resp, path);
			}else if(uri.indexOf("myPage.do")!=-1){
				forward(req, resp, "/WEB-INF/views/myPage/myMember/myPage.jsp");
			} else if(uri.indexOf("pwd.do")!=-1) {
				// �н����� Ȯ�� ��
				info=(SessionInfo)session.getAttribute("member");
				if(info==null) {
					// �α׾ƿ������̸�
					resp.sendRedirect(cp+"/member/login.do");
					return;
				}
				
				String mode=req.getParameter("mode");
				if(mode.equals("update"))
					req.setAttribute("title", "ȸ�� ���� ����");
				else
					req.setAttribute("title", "ȸ�� Ż��");
				
				req.setAttribute("mode", mode);
				forward(req, resp, "/WEB-INF/views/myPage/myMember/pwd.jsp");
				
			} else if(uri.indexOf("pwd_ok.do")!=-1) {
				// �н����� Ȯ��
				info=(SessionInfo)session.getAttribute("member");
				if(info==null) { //�α׾ƿ� �� ���
					resp.sendRedirect(cp+"/member/login.do");
					return;
				}
				
				// DB���� �ش� ȸ�� ���� ��������
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
						req.setAttribute("title", "ȸ�� ���� ����");
					else
						req.setAttribute("title", "ȸ�� Ż��");
			
					req.setAttribute("mode", mode);
					req.setAttribute("message", "<span style='color:red;'>�н����尡 ��ġ���� �ʽ��ϴ�.</span>");
					forward(req, resp, "/WEB-INF/views/myPage/myMember/pwd.jsp");
					return;
				}
				
				if(mode.equals("dropout")) {
					// ȸ��Ż��
					dao.deleteMember(info.getMemberId());
					
					session.removeAttribute("member");
					session.invalidate();
					
					StringBuffer sb=new StringBuffer();
					sb.append("<b>"+dto.getName()+ "</b>�� ȸ��Ż�� ����ó���Ǿ����ϴ�.<br>");
					sb.append("�׵��� �̿��� �ּż� �����մϴ�.<br>");
					
					
					req.setAttribute("message", sb.toString());
					
					forward(req, resp, "/WEB-INF/views/myPage/myMember/complete.jsp");
					
					return;
				}
				
				// ȸ����������
				if(dto.getTelephone()!=null) {
					String []s=dto.getTelephone().split("-");
					if(s.length==3) {
						dto.setTelephone1(s[0]);
						dto.setTelephone2(s[1]);
						dto.setTelephone3(s[2]);
					}
				}
				
				// ȸ������������ �̵�
				req.setAttribute("title", "ȸ�� ���� ����");
				req.setAttribute("dto", dto);
				req.setAttribute("mode", "update");
				forward(req, resp, "/WEB-INF/views/myPage/myMember/update.jsp");
				
			} else if(uri.indexOf("update_ok.do")!=-1) {
				// ȸ������ ���� �Ϸ�
				info=(SessionInfo)session.getAttribute("member");
				if(info==null) { //�α׾ƿ� �� ���
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
				sb.append("<b>"+dto.getName()+ "</b>�� ȸ�� ������ ���� �Ǿ����ϴ�.<br>");
								
				req.setAttribute("title", "ȸ�� ���� ����");
				req.setAttribute("message", sb.toString());
				
				forward(req, resp, "/WEB-INF/views/myPage/myMember/complete.jsp");
			}
		}	
}

	