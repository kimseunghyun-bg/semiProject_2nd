<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");	
%>

	
	
		
		<div class="head-bottom">			
			<ul class="megamenu skyblue">
				
				<li class="active grid"><a href="<%=cp%>/order/orderList.do">주문 내역</a></li> 
	            <li class="active grid"><a href="<%=cp%>/order/cancle.do">취소/반품 내역</a></li>            
				<li class="active grid"><a href="<%=cp%>/order/bank.do">입금/환불 내역</a></li>
				<li class="active grid"><a href="<%=cp%>/board/myQuestion.do">내가한 질문</a></li>
				<li class="active grid"><a href="<%=cp%>/myPage/join.do">회원정보 수정</a></li>
				<li class="active grid"><a href="<%=cp%>/myPage/pwd.do">회원 탈퇴</a></li>
			</ul>		
		</div>							
		<div class="clearfix"> </div>
