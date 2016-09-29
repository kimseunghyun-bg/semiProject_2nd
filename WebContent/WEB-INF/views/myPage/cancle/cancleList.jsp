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
<title>마이페이지</title>
<link href="<%=cp%>/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=cp%>/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<style type="text/css">
.form-signin {
  max-width: 440px;
  padding: 15px;
  margin: 0 auto;
}

@media (min-width: 768px) {
  .form-signin {
    padding-top: 70px;
  }
}

.form-signin-heading {
  text-align: center;
  font-weight:bold;
  font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", sans-serif;
  margin-bottom: 30px;
}

.lbl {
   position:absolute; 
   margin-left:15px; margin-top: 13px;
   color: #999999;
   font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", 돋움, sans-serif;
}

.loginTF {
  max-width: 370px; height:45px;
  padding: 5px;
  padding-left: 15px;
  margin-top:5px; margin-bottom:15px;
}

.boxLayout {
    max-width:420px;
    padding:20px;
    border: 1px solid #DAD9FF;
}
</style>

<link href="<%=cp%>/css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<meta name="keywords" content="Markito Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,800' rel='stylesheet' type='text/css'>
<!--//fonts-->
<%-- 
<script type="text/javascript" src="<%=cp%>js/move-top.js"></script>
<script type="text/javascript" src="<%=cp%>js/easing.js"></script>
 --%>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event){		
			event.preventDefault();
			$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
		});
	});
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

<div class="layoutBody" >	
	<div class="bodyFrame1" style="min-height: 450px;">
		<div class="container" role="main">            
	  	<div class="bodyFrame" style="background: #F0F0F0; padding:15px 0 15px 0; ">
			<jsp:include page="/WEB-INF/views/layout/header1.jsp"></jsp:include>
		</div>
		<div class="bodyFrame" style="padding-top: 20px;" >	 
			<div style="min-height: 650px;">
				<div style="width: 100%; height: 30px;"></div>
				    <div style="width:800px; height:30px; line-height:30px; margin:5px auto;">
				        <img src="<%=cp%>/images/arrow.jpg" alt="" style="padding-left: 5px; padding-right: 5px;">
				        <span style="font-weight: bold;font-size:15pt;font-family: 나눔고딕, 맑은 고딕, 굴림;">주문취소/반품 내역</span>
				    </div>			
					<div style="margin: 10px auto; margin-top: 20px; width:800px; min-height: 400px;">
					<form action="" name="contentForm" method="post">
						<table style="width: 800px; margin: 0px auto; border-spacing: 0px;">
						  <tr align="center" bgcolor="#2F9D27" height="30" style="font-size: 11pt; color: #ffffff;  font-weight: bold;"> 
						      <td style="width: auto;"></td>
								<td>주문번호</td>
								<td>상품</td>
								<td>주문일자</td>								
								<td>주문자</td>
								<td>주문금액</td>
								<td>결제상태</td>
								<td>주문취소/반품 상태</td>	
						  </tr>
						 
						 <c:forEach var="dto" items="${orderList}">
						  <tr align="center" height="30" style="font-size: 11pt;"> 
						      <td style="width: auto;"><input name="jumunCheck" type="checkbox" style="width: 16px; height: 16px;"></td>
									<td>${dto.jumunNum}</td>
									<td>${dto.panmaeName}<c:if test="${dto.extra!=0}"> 외 ${dto.extra}개 상품</c:if></td>
									<td>${dto.created}</td>
									<td>${dto.memberName}<br>${dto.memberId}<br>${dto.rankName}</td>
									<td>${dto.payTotal}</td>
									<td>${dto.payState}</td>
									<td><c:if test="${dto.jumunState=='주문취소'}">주문취소</c:if>
										<c:if test="${dto.returnProduct==0}">반품</c:if>
									</td>
									
						  </tr>
						  <tr><td height="1" colspan="7" bgcolor="#65D35D"></td></tr> 
						</c:forEach>
						</table>
						
						<table style="width: 800px; margin: 0px auto; border-spacing: 0px;">
						   <tr height="45">
							<td align="center">
						        <c:if test="${dataCount==0 }">
				                       	등록된 주문이 없습니다.
				                </c:if>
				                <c:if test="${dataCount!=0 }">
				                    ${paging}
				                </c:if>
							</td>
						   </tr>
						</table>
						</form>						
					</div>
				</div>
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