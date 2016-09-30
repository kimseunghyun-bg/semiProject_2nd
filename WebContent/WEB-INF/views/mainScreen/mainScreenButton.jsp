<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="<%=cp%>/jquery/css/smoothness/jquery-ui.min.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/bootstrap/css/bootstrap.min.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/bootstrap/css/bootstrap-theme.min.css" type="text/css"/>

<link rel="stylesheet" href="<%=cp%>/css/style1.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/css/layout/layout.css" type="text/css"/>

<script type="text/javascript" src="<%=cp%>/res/jquery/js/jquery-1.12.3.min.js"></script>

</head>
<body>
	
	<div style="height: 40px;"></div>
	<div style="margin: 0 auto;" align="center">
		<a class="btn btn-default" href="<%=cp%>/boardNotice/list.do" role="button">
			<img src="<%=cp%>/images/msb_paper.png"><br>
			공지사항
		</a>
		
		<a class="btn btn-default" href="<%=cp%>/member/login.do" role="button">
			<img src="<%=cp%>/images/msb_a.png"><br>
			로그인
		</a>
		<a class="btn btn-default" href="<%=cp%>/member/insert.do" role="button">
			<img src="<%=cp%>/images/msb_member.png"><br>
			회원가입
		</a>
		<a class="btn btn-default" href="<%=cp%>/myPage/myOrder/orderList.do" role="button">
			<img src="<%=cp%>/images/msb_b.png"><br>
			마이페이지
		</a>
	</div>

	<script type="text/javascript" src="<%=cp%>/jquery/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=cp%>/jquery/js/jquery.ui.datepicker-ko.js"></script>
	<script type="text/javascript" src="<%=cp%>/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>