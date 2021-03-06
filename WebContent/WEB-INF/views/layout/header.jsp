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
	
	function basketlist() {
		var url = "<%=cp%>/sale/basketlist.do?";
		

		location.href=url;
	}
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
				<li><a href="<%=cp%>/myPage/myOrder/orderList.do">마이페이지</a>|</li>
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
								<li class="grid"><a href="<%=cp%>/admin/ordermgmt/list.do">주문관리</a></li>
								<li class="grid"><a href="#">통계</a></li>
								<li class="grid"><a href="#"></a></li>
							</c:when>
							
							<c:otherwise>		  
								<li class="active grid"><a  href="<%=cp %>/#"><img src="<%=cp%>/images/rice.png" alt="" > 곡류</a>
									<div class="megapanel">
										<div class="row">
											<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=4">쌀</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=7">잡곡</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=18">콩</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=19">밀</a></li>
											</ul>
										</div>
									</div>
								</li>
								
								<li class="grid"><a href="#"><img src="<%=cp %>/images/vegetable.png" alt="" > 채소</a>
									<div class="megapanel">
										<div class="row">
											<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=8">상추</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=9">배추</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=10">오이</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=11">당근</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=12">치커리</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=22">마늘</a></li>
											</ul>	
										</div>
									</div>
								</li>
								
								<li class="grid"><a  href="<%=cp %>/#"><img src="<%=cp %>/images/apple.png" alt="" > 과일</a>
									<div class="megapanel">
										<div class="row">
											<ul>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=13">바나나</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=14">사과</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=15">복숭아</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=16">수박</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=17">포도</a></li>
								<li style="float: left; padding: 0 25px 0 0;"><a href="<%=cp%>/sale/panmaeList.do?small_kind=23">자두</a></li>
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
						<img src="<%=cp %>/images/cart.png" style = "cursor : pointer" onclick = "basketlist()"> 장바구니
							<div class="clearfix"> </div>
						</c:otherwise>
					</c:choose>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>