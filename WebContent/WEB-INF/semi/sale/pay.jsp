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
					
function pay_ok(basket_num) {
var url="${pay_okUrl}?basket_num="+basket_num;
location.href=url;
}

//마이페이지로 및 관리자로 포워딩
</script>
<script>
function sample6_execDaumPostcode() {
new daum.Postcode({
oncomplete: function(data) {

var fullAddr = ''; 
var extraAddr = ''; 

if (data.userSelectedType === 'R') { 
fullAddr = data.roadAddress;

} else { 
fullAddr = data.jibunAddress;
}

if(data.userSelectedType === 'R'){
if(data.bname !== ''){
extraAddr += data.bname;
}
 if(data.buildingName !== ''){
extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
}
fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
}
document.getElementById('sample6_postcode').value = data.zonecode; 
document.getElementById('sample6_address').value = fullAddr;

document.getElementById('sample6_address2').focus();
}
}).open();
}
</script>
<!-- start menu -->
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script src="<%=cp%>/js/simpleCart.min.js"> </script>
</head>
<body> 
<!--header-->	
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
<div style="width: 800px; margin: 0px auto;">
		<form name="memberForm" action=" javascript:send();" method="post">
			<br>
			<div style="width: 800px; font-family : 맑은 고딕;font-size: 20px;">&nbsp;1. 주문 상품 확인 </div>
			<br>
			<div
				style="width: 800px;  border-top: 2px solid #000000;">
				<dl>
				<dt
				style="line-height : 40px;width: 500px; height: 40px;float: left; text-align: center; 
				font-weight: normal;border-right : 1px solid gray;border-bottom : 1px solid gray;border-left : 1px solid gray">
				상품정보</dt>
				<dt style="line-height : 40px ;float : left; width: 200px; height: 40px;
				font-weight: normal;text-align: center;border-right : 1px solid gray;border-bottom : 1px solid gray">
				지불하실금액</dt>
				<dt style="line-height : 40px ;float : left; width: 100px; height: 40px;
				font-weight: normal;text-align: center;border-right : 1px solid gray;border-bottom : 1px solid gray">
				생산자</dt>
				
				<c:forEach var="dto" items="${list}">
				<dt
				style="line-height : 40px;width: 500px; height: 40px;float: left; text-align: center;color:blue; 
				font-weight: normal;">
				${dto.panmae_num},${dto.name},${dto.buy_num}</dt>
				<dt style="line-height : 40px ;float : left; width: 200px; height: 40px;color:blue; 
				font-weight: normal;text-align: center;">
				${dto.price * dto.buy_num}</dt>	
				<dt style="line-height : 40px ;float : left; width: 100px; height: 40px;color:blue; 
				font-weight: normal;text-align: center;">
				${dto.produce_corpor_name}</dt>
				</c:forEach>			
				</dl>
				
				
				
			</div>
			<br><br><br>
			<div style="width: 800px; font-family : 맑은 고딕;font-size: 20px;">&nbsp;2. 배송지 정보 입력 </div>
			<br>
			<div
				style="width: 800px; height: 310px; border-top: 2px solid #000000; border-bottom: 2px solid #000000;">
				<dl style="line-height: 40px;">
					<dt
						style="float : left; width: 150px; height: 20px;float: left;">
						</dt>
					<dd style="float : left; width: 650px; height: 20px; background : white">
					</dd>
				</dl>
				<dl style="line-height: 40px;">
					<dt
						style="width: 150px; height: 40px;float: left; text-align: center;">
						받으시는분</dt>
					<dd style="float : left; width: 650px; height: 40px; background : white">
						&nbsp;&nbsp;<input type="text" name="userName" required="required"
							style="width : 80px; height: 25px;">

					</dd>
				</dl>
				<dl style="clear: both; line-height: 40px;">
					<dt
						style=";width: 150px; height: 40px;float: left; text-align: center;">
						휴대폰번호</dt>
					<dd style="float : left; width: 650px; height: 40px; float: left;background : white">
						&nbsp;&nbsp;<select name="tel1"
							style="width: 80px; height: 25px; font-weight : normal">
							<option value="선택" selected="selected">선택</option>
							<option value="010">010</option>
							<option value="011">011</option>
							<option value="016">016</option>
							<option value="017">017</option>
							<option value="018">018</option>
							<option value="019">019</option>
							<option value="0130">0130</option>
							<option value="0502">0502</option>
						    <option value="0504">0504</option>
						    <option value="0505">0505</option>
						    <option value="0506">0506</option>
							<option value="0507">0507</option>
							<option value="1541">1541</option>
							<option value="1595">1595</option>
							<option value="08217">08217</option>
						</select> <input type="tel" name="tel2" required="required" maxlength="4"
							style="width: 80px; height: 25px;">&nbsp;-&nbsp;<input
							type="tel" name="tel3" required="required" maxlength="4"
							style="width: 80px; height: 25px;">

					</dd>
				</dl>
				<dl style="clear: both; line-height: 40px;">
					<dt
						style="width: 150px; height: 80px;float: left; text-align: center;">
						배 송 지</dt>
					<dd style=" float : left; width: 650px; height: 40px;background : white ">
						&nbsp;&nbsp;
						<input type="text" name="zip" required="required"
							style="width: 150px; height: 25px;" id="sample6_postcode" placeholder="우편번호"> 
						<input type="button" value="우편번호검색 "
							style="height: 25px  ;font-weight : normal"  onclick="sample6_execDaumPostcode()" >

					</dd>
					<dd style="float : left; width: 650px; height: 40px;background : white; ">
						&nbsp;&nbsp;
							<input type="text" name="addr1" required="required"
							style="width: 250px; height: 25px;" id="sample6_address" placeholder="주소">&nbsp;&nbsp;-&nbsp;
							<input type="text" name="addr2" required="required"
							style="width: 250px; height: 25px;" id="sample6_address2" placeholder="상세주소">

					</dd>
				</dl>
				
				<dl style="clear: both; line-height: 40px;">
					<dt
						style="float : left; width: 150px; height: 20px;float: left;">
						</dt>
					<dd style="float : left; width: 650px; height: 20px; background : white">
					</dd>
					<dt
						style="width: 150px; height: 100px;float: left; text-align: center">
						배송 메세지</dt>
					<dd style=" float : left; width: 650px; height: 30px;background : white; color : red;font-weight : lighter">
					&nbsp;&nbsp;상품명 :
					</dd>
					<dd style=" float : left; width: 650px; height: 30px;background : white">
						&nbsp;&nbsp;<textarea rows="1" cols="50"></textarea><br>
					</dd>
					<dd style=" float : left; width: 650px; height: 20px;background : white; font-size : 13px; font-weight : normal;color : gray ">
						&nbsp;&nbsp;* 부재시 연락가능한 전화번호 또는 상품수령이 가능한 장소를 남겨주세요.
					</dd>
					<dd style=" float : left; width: 650px; height: 20px;background : white; font-size : 13px; font-weight : normal;color : gray  ">
						&nbsp;&nbsp;* 판매업체 전달사항이나 배송일지정은 판매업체와 사전 협의하여 주세요.
					</dd>
				</dl>
			</div>
			<br><br><br>
			<div style="width: 800px; font-family : 맑은 고딕;font-size: 20px;">&nbsp;3. 결제 정보 </div>
			<br>
			<div
				style="width: 800px; height:250px;  border-top: 2px solid #000000; border-bottom:2px solid #000000">
				<br>
				<div style="margin : auto;width : 600px; height : 100px ;border : 2px solid black;
				text-align : center; font-size : 12px; font-weight : normal;">
					<div style="margin : auto;width : 596px; height : 50px ;background:#C8FAC8;text-align:center">
					<br>입금은행&nbsp;&nbsp;<select name="tel1"
							style="width: 100px; height: 25px; font-weight : normal">
							<option value="은행선택" selected="selected" style = "font-weight : normal">은행선택</option>
							<option value="농협" style = "font-weight : normal">농협</option>
							<option value="신한은행" style = "font-weight : normal">신한은행</option>
							<option value="우리은행" style = "font-weight : normal">우리은행</option>
							<option value="하나은행" style = "font-weight : normal">하나은행</option>
							<option value="국민은행" style = "font-weight : normal">국민은행</option>
							<option value="우체국은행" style = "font-weight : normal">우체국은행</option>
							<option value="기업은행" style = "font-weight : normal">기업은행</option>
							<option value="부산은행" style = "font-weight : normal">부산은행</option>
						    <option value="대구은행" style = "font-weight : normal">대구은행</option>
						</select>
				   </div><br>*결제 완료시 입력하신 은행 계좌번호를 휴대폰번호로 전송해드립니다.
				</div><br>
				<div style="clear: both; width: 500px; margin : 0px auto; text-align: center;">
				
				<c:forEach var="dto" items="${list}">
				<button type="button"
					style="color : white; background : green;border-radius: 10px; width : 100px;height: 50px;" onclick="pay_ok('${dto.basket_num}');">결제하기</button>  
				<button type="button"
					style="border-radius: 10px; width : 100px;height: 50px;" onclick="javascript:location.href='<%=cp%>/sale/panmaeList.do';">취소하기</button>
				</c:forEach>
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