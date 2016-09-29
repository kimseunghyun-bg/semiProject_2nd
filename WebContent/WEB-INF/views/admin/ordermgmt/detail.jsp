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
.aa{
	width: auto; padding:10px; margin-top: 40px; border: 1px solid gray;
}
.aa ul{
	font-size:16px; list-style: none;
}
.aa > ul{
	border-bottom: 1px solid black; padding: 10px 10px 5px 10px; margin: 0px 10px;
}
.aa > .jumunja{
	height: 75px;
}
.aa li{
	float: left; width: 25%; padding: 4px 0px;
}
.aa label{
	padding-left: 16px;
}
.aa > .payStmt {
	height: 45px;
}
.aa > .arrive{
	height: 105px;
}
.aa > .arrive li{
	width: 33%;
}
.aa > .sangsaeDiv > .ul{

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
				
				<div class="aa">
					<!-- 주문자 정보 -->
					<ul class="jumunja">
						<li>주문번호<label>${dto.jumunNum}</label></li>
						<li>이름(ID)<label>${dto.memberName}(${dto.memberId})</label></li>
						<li>등급<label>${dto.rankName}</label></li>
						<li>주문일자<label>${dto.created}</label></li>
						<li>전체금액<label>${dto.orderTotalpay}</label></li>
						<li>전화번호<label>${dto.telePhone}</label></li>
						<li>이메일<label>${dto.email}</label></li>
					</ul>
					
					<!-- 결제상태 -->
					<ul class="payStmt">
						<li>결제상태
						<c:choose>
							<c:when test="${mode=='updatePayment'}">
								<select name=""><option>선택</option>
									<option value="입금대기">입금대기</option>
									<option value="결제완료">결제완료</option>
								</select>
							</c:when>
							<c:otherwise>
								<label>${dto.payState}</label>
							</c:otherwise>
						</c:choose>
						</li>
						<li>입금금액<label>${dto.payTotal}</label></li>
						<li>결제일자<label>${dto.payCreated}</label></li>
						<li>결제방법<label>${dto.payRoot}</label></li>
					</ul>
					
					<!-- 배송지정보 -->
					<ul class="arrive">
						<li>받는사람<label>${dto.sendName}</label></li>
						<li>전화번호 1<label>${dto.phoneNum}</label></li>
						<li>전화번호 2<label>${dto.tel}</label></li>
						<li style="width: 100%;">주소<label style="padding-left: 15px;">${dto.addr1}</label></li>
						<li style="width: 100%;"><label style="padding-left: 130px;">${dto.addr2}</label></li>
					</ul>
					
					<!-- 주문상세 -->
					<div class="sangsaeDiv" style="border-bottom: 1px solid red; padding: 5px;">
						<!-- 주문상세 -->
						<c:forEach var="subdto" items="${list}">
							<div style="height: 100px; width: 90%; border: 1px solid gray; margin: 10px auto;">
								<ul>
									<li>상품번호<label>${subdto.panmaeNum}</label></li>
									<li>상품명<label>${subdto.panmaeName}</label></li>
									<li>대분류<label>${subdto.kindParentName}</label></li>
									<li>소분류<label>${subdto.kindName}</label></li>
									<li>배송상태<label>${subdto.sendState}</label></li>
									<li>주문수량<label>${subdto.sellNum}</label></li>
									<li>판매금액<label>${subdto.sellPrice}</label></li>
									<li>합계금액<label>${subdto.totalPay}</label></li>
								</ul>
								<!-- 배송정보 -->
								<ul>
									<li>배송업체<label>${subdto.sendCorporName}</label></li>
									<li>업체번호<label>${subdto.sendPhoneNum}</label></li>
								</ul>
								<!-- 반품정보 -->
								<ul>
									<li>반품사유<label>${subdto.reason}</label></li>
									<li>반품일자<label>${subdto.returnCreated}</label></li>
								</ul>
							</div>
						</c:forEach>
					</div>
					
					<!-- 환불정보 -->
					<ul style="height: 45px;">
						<li>환불액<label>${dto.returnMoney}</label></li>
						<li>환불일자<label>${dto.returnPayCreated}</label></li>
						<li>은행명<label>${dto.bankName}</label></li>
						<li>환불계좌<label>${dto.bankNumber}</label></li>
					</ul>
					
					<!-- 버튼메뉴 -->
					<ul style="height: 45px;">
						<li style="width: auto;">
						<input type="button" value="뒤로" onclick="javascript:location.href='<%=cp%>/admin/ordermgmt/list.do?page=${page}';">
						<input type="button" value="결제수정" onclick="javascript:location.href='<%=cp%>/admin/ordermgmt/updatePayment.do?jumunNum=${dto.jumunNum}';">
						<input type="button" value="배송지수정" onclick="">
						<input type="button" value="주문취소" onclick=""></li>
						
						<li><input type="button" value="주문상세수정" onclick=""></li>
						<li><input type="button" value="배송상태수정" onclick=""></li>
						
					</ul>
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