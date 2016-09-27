<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
%>
<hr>
<div class="footer">
	<ul class="footer-in">
		<li ><a href="<%=cp %>/#" >회사소개</a>|</li>
		<li><a href="<%=cp %>/#">이용약관</a>|</li>
		<li><a href="<%=cp %>/#">이용안내</a>|</li>
		<li ><a href="<%=cp %>/#" >개인정보취급방침</a></li>
	</ul>
<br><br>
	<div class="flogo">
		<a href="<%=cp%>/index.jsp"><img src="<%=cp%>/images/flogo.png" alt=""></a>
	</div>
	<div class="fcontent" style="padding:30px 0px 0px 230px; margin-left: 200px;">
		<ul >
			<li> 서울시 마포구 노고산동</li>
			<li><strong>전화</strong> 070-1234-5678</li>
			<li><strong>핸드폰</strong> 010-1234-5678</li>
			<li><strong>업체명</strong> 쌍용회사법인 주식회사 녹색매장</li>
			<li><strong>사업자등록번호</strong> 123-81-00179 </li>
	        <li><strong>대표</strong> JSP</li>
    	</ul>
    	<ul><li> copyright (c) <strong>green.com</strong> all rights reserved.</li></ul>
    		   	
	</div>
</div>
