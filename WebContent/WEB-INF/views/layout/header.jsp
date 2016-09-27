<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
%>

<script src="<%=cp%>/js/jquery.jclock-1.2.0.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function($) {
		$('.jclock').jclock();
	});
</script>

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
				<li><a href="<%=cp%>/myPage/myPage.do">마이페이지</a>|</li>
				<li><a href="<%=cp%>/boardQnA/list.do">질문과 답변</a>|</li>
				<li><a href="<%=cp%>/boardNotice/list.do">공지사항</a>|</li>
				<li><a href="<%=cp%>/boardFAQ/list.do" >자주하는 질문</a>|</li>
				<li><a href="#" >고객센터</a></li>
			<c:if test="${sessionScope.member.memberId=='admin'}"></c:if>
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
						<c:choose>
							<c:when test="${sessionScope.member.memberId=='admin'}">
								<li class="active grid"><a href="<%=cp%>/admin/goodsmgmt/list.do">상품관리</a></li>
								<li class="grid"><a href="<%=cp%>/admin/ordermgmt/list.do">주문관리</a>
								<div class="megapanel">
										<div class="row">
											<ul>
												<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/admin/ordermgmt/beforePay.do">입금전 관리</a></li>
												<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">상품준비중 관리</a></li>
												<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">배송준비중 관리</a></li>
												<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">배송완료 조회</a></li>
												<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">반품 관리</a></li>
												<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp %>/products.html">전체주문조회</a></li>
											</ul>
										</div>
									</div>
								</li>
								<li class="grid"><a href="#">통계</a></li>
								<li class="grid"><a href="#"></a></li>
							</c:when>
							
							<c:otherwise>		  
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
										
							</c:otherwise>
						</c:choose>
					</ul>  
				</div>
				
				<div class="cart box_1" style="padding-top: 30px;">
					<c:choose>
						<c:when test="${sessionScope.member.memberId=='admin'}">
							<div class="jclock"></div>
						</c:when>
						<c:otherwise>
							<a href="<%=cp %>/checkout.html">
								<span class="simpleCart_total"></span> (<span id="simpleCart_quantity" class="simpleCart_quantity"></span> items)
								<img src="<%=cp %>/images/cart.png" alt=""/>
							</a>
							<p><a href="javascript:;" class="simpleCart_empty"><img src="<%=cp %>/images/cart-c.png"  alt=""></a></p>
							<div class="clearfix"> </div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>