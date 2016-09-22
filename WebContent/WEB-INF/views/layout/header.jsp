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
				<li><a href="<%=cp%>/member/login.do">로그인</a>|</li>
				<li><a href="<%=cp%>/member/insert.do">회원가입</a>|</li>
				<li><a href="<%=cp %>/member/myPage.do">마이페이지</a>|</li>
				<li><a href="<%=cp%>/boardQnA/list.do">질문과 답변</a>|</li>
				<li><a href="<%=cp%>/boardNotice/list.do">공지사항</a>|</li>
				<li><a href="<%=cp%>/boardFAQ/list.do" >자주하는 질문</a>|</li>
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
				<a href="<%=cp %>/index.jsp"><img src="<%=cp %>/images/logo.png" alt=""></a>
			</div>
		<div class="top-nav" >		
			  <ul class="megamenu skyblue" style="top: 30px;">
				      <li class="active grid"><a  href="<%=cp %>/#"><img src="<%=cp%>/images/rice.png" alt="" > 곡류</a>
					    <div class="megapanel">
						<div class="row">
							<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">쌀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">보리</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">조</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">귀리</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">호밀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">메밀</a></li>
							</ul>
						  </div>
						</div>
					</li>
					<li class="grid"><a href="#"><img src="<%=cp %>/images/vegetable.png" alt="" > 채소</a>
					    <div class="megapanel">
						<div class="row">
							<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">상추</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">배추</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">당근</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">무</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">호밀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">메밀</a></li>
							</ul>	
						  </div>
						</div>
						</li>
				    <li class="grid"><a  href="<%=cp %>/#"><img src="<%=cp %>/images/apple.png" alt="" > 과일</a>
					    <div class="megapanel">
						<div class="row">
							<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">사과</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">배</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">조</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">귀리</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">호밀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">메밀</a></li>
							</ul>	
						  </div>
						</div>
			    </li>			
				
			   </ul>  
				</div>
					<div class="cart box_1" style="padding-top: 30px;">
						<a href="<%=cp %>/checkout.html">
						<h3> <div class="total">
							<span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)</div>
							<img src="<%=cp %>/images/cart.png" alt=""/></h3>
						</a>
						<p><a href="javascript:;" class="simpleCart_empty"><img src="<%=cp %>/images/cart-c.png"  alt=""></a></p>
						<div class="clearfix"> </div>
					</div>
		
				<div class="clearfix"> </div>
		</div>
		</div>
	</div>
</div>

