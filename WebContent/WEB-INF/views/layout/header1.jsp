<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");	
%>

	
	
		
		<div class="head-bottom">			
			<ul class="megamenu skyblue">
				
				<li class="active grid"><a href="<%=cp%>/myPage/myOrder/orderList.do">주문 내역</a></li> 
	            <li class="active grid"><a href="<%=cp%>/myPage/cancle/cancleList.do">취소/반품 내역</a></li>            
				<li class="active grid"><a href="<%=cp%>/myPage/bank/bankList.do">입금/환불 내역</a></li>
				<li class="active grid"><a href="<%=cp%>/myPage/myQnA/list.do">내가한 질문</a></li>
				<li class="active grid"><a href="<%=cp%>/myPage/myMember/pwd.do?mode=update">회원정보 수정</a></li>
				<li class="active grid"><a href="<%=cp%>/myPage/myMember/pwd.do?mode=dropout">회원 탈퇴</a></li>
			</ul>		
		</div>							
		<div class="clearfix"> </div>
