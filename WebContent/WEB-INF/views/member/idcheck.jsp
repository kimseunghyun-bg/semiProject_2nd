<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<jsp:useBean id="dao" class="com.member.MemberDAO"/>
<%
 int rst = 0;
 String memberId = (String)request.getParameter("memberId");
 rst = dao.idCheck(memberId);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01

 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>���̵� �ߺ� Ȯ��</title>
</head>
<body>
<%
if(rst == 1){
%>
�̹� ������� ���̵��Դϴ�.

<%}else{ %>
���̵� ���������ʽ��ϴ�.

<%}%>
</body>
</html>

