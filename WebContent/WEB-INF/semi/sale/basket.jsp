<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
   String cp = request.getContextPath();
%>

<!DOCTYPE html>
<html>
<head>
<title>Markito A Ecommerce Category Flat Bootstarp Resposive Website Template | Home :: w3layouts</title>
<link href="<%=cp%>/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=cp%>/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="<%=cp%>/css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<meta name="keywords" content="Markito Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700' rel='stylesheet' type='text/css'>
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
					
function basket_pay() {
	var f = document.memberForm;
	 f.action = "<%=cp%>/sale/basket_pay.do";
	 f.submit();
}

function deleteBasket(basket_num) {
	var url="${deleteUrl}?basket_num="+basket_num;
	 location.href=url;
}


</script>
<!-- start menu -->
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<script src="<%=cp%>/js/simpleCart.min.js"> </script>
</head>
<body> 
<!--header-->	
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	
<div style="width: 800px; height: 600px;margin: 0px auto;" class = "pay">
		<form name="memberForm"  method="post" >
			<br>
			<div style="width: 800px; font-family : 맑은 고딕;font-size: 20px;">&nbsp;장바구니 </div>
			<br>
			<div style="width: 800px;  border-top: 2px solid #000000;">
				<dl>
				<dt
				style="background : black; color : white;line-height : 40px;width: 500px; height: 40px;float: left; text-align: center; 
				font-weight: normal;border-right : 1px solid gray;border-bottom : 1px solid gray;border-left : 1px solid gray">
				상품정보</dt>
				<dt style="background : black; color : white;line-height : 40px ;float : left; width: 200px; height: 40px;
				font-weight: normal;text-align: center;border-right : 1px solid gray;border-bottom : 1px solid gray">
				지불하실금액</dt>
				<dt style="background : black; color : white;line-height : 40px ;float : left; width: 100px; height: 40px;
				font-weight: normal;text-align: center;border-right : 1px solid gray;border-bottom : 1px solid gray">
				생산자</dt>
				</dl>
				<c:forEach var="dto" items="${list}">
				<dl>
				<dt
				style="line-height : 60px;width: 500px; height: 40px;float: left; text-align: center; 
				font-weight: normal;">
				<input type="checkbox" name="panmae" value="${dto.basket_num}" style = "margin : 24px; float:left">${dto.name}      <${dto.panmae_num}>     ${dto.buy_num}개&nbsp;&nbsp;&nbsp;
				<button type="button" style = "color : red;line-height : 0px; width : 35px; height : 35px; margin : 13px; float: right" onclick="javascript:deleteBasket('${dto.basket_num}');">x</button>
				</dt>
				
			
				
				<dt style="line-height : 60px ;float : left; width: 200px; height: 40px;color:red; 
				font-weight: normal;text-align: center;">
				${dto.price * dto.buy_num} 원</dt>	
				<dt style="line-height : 60px ;float : left; width: 100px; height: 40px; 
				font-weight: normal;text-align: center;">
				${dto.produce_corpor_name}</dt>		
				</dl>
				</c:forEach>
				
				 <div style="text-align: center; min-height: 50px; line-height: 50px;">
	            <c:if test="${dataCount==0 }">
	                  담긴 상품이 없습니다.
	            </c:if>
	        </div>  
				
				<div style="clear: both; width: 500px; margin : 0px auto; text-align: center;">

				<button type="button" style="margin-top : 30px; color : white; background : green;border-radius: 10px ;width : 130px;height: 50px; text-align : center; font-weight : border"
					 onclick="basket_pay()">선택한 상품 구매</button> 
				<button type="button" style="margin-top : 10px;border-radius: 10px ;width : 130px;height: 50px; text-align : center; font-weight : border"
					 onclick="javascript:location.href='<%=cp%>/sale/panmaeList.do';">쇼핑 계속</button>
				</div>
				
			</div>
	</form>
	</div>
	
	
<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>