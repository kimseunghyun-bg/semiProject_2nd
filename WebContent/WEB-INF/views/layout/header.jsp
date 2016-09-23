<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
%>
<!-- header-top -->
<div class="top-header">
	<div class="container">
			<ul class="header-in">
			<c:choose>
				<c:when test="${empty sessionScope.member}">
					<li><a href="<%=cp%>/member/login.do">로그인</a>|</li>
					<li><a href="<%=cp%>/member/insert.do">회원가입</a>|</li>
				</c:when>
				<c:otherwise>
					<li><span style="color:blue;">${sessionScope.member.name}</span>님 |
					<a href="<%=cp%>/member/logout.do">로그아웃</a>|</li>
				</c:otherwise>
			</c:choose>
				<li><a href="#">마이페이지</a>|</li>
				<li><a href="#" >공지사항</a>|</li>
				<li><a href="#" >고객센터</a></li>
			</ul>
	</div>
</div>
<!--  -->

<div class="header" >
			<div class="header-top">
		<div class="container">
		<div class="head-top">
			<div class="logo">
				<a href="index.html"><img src="images/logo.png" alt="" ></a>
			</div>
		<div class="top-nav">		
			  <ul class="megamenu skyblue">
				      <li class="active grid"><a  href="#"><img src="images/rice.png" alt="" > 곡류</a>
					    <div class="megapanel">
						<div class="row">
							<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">쌀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">보리</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">조</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">귀리</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">호밀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">메밀</a></li>
							</ul>
						  </div>
						</div>
					</li>
					<li class="grid"><a href="#"><img src="images/vegetable.png" alt="" > 채소</a>
					    <div class="megapanel">
						<div class="row">
							<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">상추</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">배추</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">당근</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">무</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">호밀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">메밀</a></li>
							</ul>	
						  </div>
						</div>
						</li>
				    <li class="grid"><a  href="#"><img src="images/apple.png" alt="" > 과일</a>
					    <div class="megapanel">
						<div class="row">
							<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">사과</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">배</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">조</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">귀리</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">호밀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="products.html">메밀</a></li>
							</ul>	
						  </div>
						</div>
			    </li>			
				
			  <!-- </ul> --> 
				</div>
					<div class="cart box_1">
						<a href="checkout.html">
						<h3> <div class="total">
							<span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)</div>
							<img src="images/cart.png" alt=""/></h3>
						</a>
						<p><a href="javascript:;" class="simpleCart_empty"><img src="images/cart-c.png"  alt=""></a></p>
						<div class="clearfix"> </div>
					</div>
		
				<div class="clearfix"> </div>
		</div>
		</div>
	</div>
</div>

