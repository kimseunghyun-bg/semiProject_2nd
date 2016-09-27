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
<title>아이디 중복 확인</title>
</head>
<body>
<%
if(rst == 1){
%>
이미 사용중인 아이디입니다.

<%}else{ %>
아이디가 존재하지않습니다.

<%}%>
</body>
</html>

