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
<script type="text/javascript">
//검색
	function searchList() {
		var f=document.searchForm;
		f.action="<%=cp%>/admin/ordermgmt/list.do";
		f.submit();
	}
</script>
<style type="text/css">
li{
	list-style: none;
}
.orderListContent td{
	text-align: center; font-size: 16px; border: 1px solid black; min-height: 100%;
}
.orderListContent .contentRow{
	height: 75px; vertical-align: middle;
}
.orderListContent .contentRow:HOVER{
	border: 2px solid red;
}
.orderListContent .col4{
	width: 55px;
}
.orderListContent .col5{
	width: 75px;
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
							<li style="width: 25%; float: left;">주문상태 :
								<select name="jumunState"> 
									<option value="">전체</option>
									<option value="주문">주문</option>
									<option value="주문취소">주문취소</option>
								</select>
							</li>
							<li style="width: 25%; float: left;">결제상태 : 
								<select name="payState">
									<option value="">전체</option>
									<option value="입금대기">입금대기</option>
									<option value="결제완료">결제완료</option>
								</select>
							</li>
							
							<li style="width: 50%; float: left; text-align: right;">
								<select name="searchKey">
									<option value="">전체</option>
									<option value="j.jumun_num">주문번호</option>
									<option value="m.name">주문자</option>
								</select>
								<input type="text" name="searchValue">
								<input type="button" value="검색" onclick="searchList()">
							</li>
						</ul>
					</form>
				</div>
				
				<!--content-->
				<div class="orderListContent">
				<form action="" name="contentForm" method="post">
					<table style="width: 100%;">
						<tr style="height: 35px; background:silver;">
							<td class="col5">주문번호</td>
							<td>상품</td>
							<td>주문일자</td>
							<td>주문자</td>
							<td>주문금액</td>
							<td class="col5">결제상태</td>
							<td class="col5">주문상태</td>
							<td class="col4">미배송</td>
							<td class="col4">배송중</td>
							<td class="col5">배송완료</td>
							<td class="col5">반품</td>
						</tr>
						
						
						<c:forEach var="dto" items="${orderList}">
							<tr class="contentRow" onclick="javascript:location.href='<%=cp%>/admin/ordermgmt/detail.do?page=${page}';">
								<td class="col5">${dto.jumunNum}</td>
								<td>${dto.panmaeName}<c:if test="${dto.extra!=0}"><br>외 ${dto.extra}개 상품</c:if></td>
								<td>${dto.created}</td>
								<td>이름 [${dto.memberName}]<br>ID [${dto.memberId}]<br>등급[${dto.rankName}]</td>
								<td>${dto.orderTotalpay}</td>
								<td class="col5">${dto.payState}</td>
								<td class="col5">${dto.jumunState}</td>
								<td class="col4">${dto.notSend}</td>
								<td class="col4">${dto.sending}</td>
								<td class="col5">${dto.arrived}</td>
								<td class="col6">${dto.returnProduct}</td>
							</tr>
						</c:forEach>
						
					</table>
					</form>		
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