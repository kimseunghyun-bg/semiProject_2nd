<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div style="margin: 10px auto; margin-top:50px; width:500px; height:400px;">
<h3>■ 비밀번호찾기</h3>
	<form name="searchidForm" method="post" action="">
		<table style="margin: 10px auto; width:500px; border-spacing: 0px;">
		<tr>
			<td>아이디</td>
			<td><input type="text" size="30" maxlength="10"></td>
			<td rowspan="3"><button>확인</button></td>
		</tr>
		<tr>
			<td>이름</td>
			<td><input type="text" size="30" maxlength="10"></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="text" size="30" maxlength="10"></td>
		</tr>
		</table>
	</form>
</div>
</body>
</html>