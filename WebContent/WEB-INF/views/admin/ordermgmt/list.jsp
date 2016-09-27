<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
   String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<title>Markito A Ecommerce Category Flat Bootstarp Resposive
	Website Template | Home :: w3layouts</title>
<link href="<%=cp%>/css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=cp%>/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="<%=cp%>/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<meta name="keywords"
	content="Markito Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700'
	rel='stylesheet' type='text/css'>
<!--//fonts-->

<script type="text/javascript" src="<%=cp%>/js/move-top.js"></script>
<script type="text/javascript" src="<%=cp%>/js/easing.js"></script>
<script type="text/javascript">
					jQuery(document).ready(function($) {
						$(".scroll").click(function(event){		
							event.preventDefault();
							$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
						});
					});
				</script>
<!-- start menu -->
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<script src="<%=cp%>/js/simpleCart.min.js"> </script>
<style type="text/css">
li{
	list-style: none;
}
.orderListContent td{
	text-align: center; font-size: 16px; border: 1px solid black; min-height: 100%;
}
.orderListContent .col4{
	width: 55px;
}
.orderListContent .col5{
	width: 65px;
}
.orderListContent .col6{
	width: 40px;
}
</style>
</head>
<body>
	<!--header-->
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	<div class="layoutBody">
		<div class="bodyFrame1" style="min-height: 450px;">
			<div class="container">
				
				<!--search-->
				<div style="height: 50px; padding: 10px;">
					<form name="searchForm" action="" method="post">
						<ul style="list-style: none;">
							<li style="width: 20%; float: left;">
								<select>
									<option>주문상태</option>
									<option>주문</option>
									<option>주문취소</option>
								</select>
							</li>
							<li style="width: 20%; float: left;">
								<select>
									<option>결제상태</option>
									<option>입금대기</option>
									<option>결제완료</option>
								</select>
							</li>
							
							<li style="width: 60%; float: left;">
								<select name="searchKey">
									<option value="">전체</option>
									<option value="">주문번호</option>
									<option value="">주문자</option>
								</select>
								<input type="text" name="searchValue">
								<input type="button" value="검색" onclick="searchList()">
							</li>
						</ul>
					</form>
				</div>
				
				<!--content-->
				<div class="orderListContent" >
					<table style="height: 35px; width: 100%;">
						<tr>
							<td style="width: auto;"><input type="checkbox" style="width: 16px; height: 16px;"></td>
							<td>주문번호</td>
							<td>상품</td>
							<td>주문일자</td>
							<td>주문자</td>
							<td>주문금액</td>
							<td>결제상태</td>
							<td>주문상태</td>
							<td class="col4">미배송</td>
							<td class="col4">배송중</td>
							<td class="col5">배송완료</td>
							<td class="col5">반품</td>
						</tr>
						
						<c:forEach var="dto" items="${orderList}">
							<tr style="height: 75px; vertical-align: middle;" onclick="javascript:location.href='${articleUrl}&panmaeNum=${dto.panmaeNum}';">
								<td style="width: auto;"><input type="checkbox" style="width: 16px; height: 16px;"></td>
								<td>${dto.jumunNum}</td>
								<td>상품</td>
								<td>${dto.created}</td>
								<td>${dto.name}<br>${dto.memberId}<br>${dto.rankName}</td>
								<td>${dto.payTotal}</td>
								<td>${dto.payState}</td>
								<td>${dto.jumunState}</td>
								<td class="col4">${dto.notSend}</td>
								<td class="col4">${dto.sending}</td>
								<td class="col5">${dto.arrived}</td>
								<td class="col6">${dto.returnProduct}</td>
							</tr>
						</c:forEach>
						
					</table>		
				</div>
				
				<div>
					<c:if test="${dataCount==0 }">
						등록된 상품이 없습니다.
					</c:if>
					<c:if test="${dataCount!=0 }">
						${paging}
					</c:if>
				</div>
					
			</div>
		</div>
	</div>

	<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>