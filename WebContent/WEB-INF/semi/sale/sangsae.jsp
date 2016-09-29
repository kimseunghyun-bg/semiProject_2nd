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
<style type="text/css">
.imgLayout{
	width: 200px;
	height: 230px;
	padding: 5px 5px 5px;
	margin: 5px;
	border: 1px solid #DAD9FF;
	float: left;
}
</style>


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


function sangsae_pay() {
	 var f = document.memberForm;
	 f.action = "<%=cp%>/sale/sangsae_pay.do";
	 f.submit();
}

function basket() {
	 var f = document.memberForm;
	 f.action = "<%=cp%>/sale/basket.do";
	 f.submit();
}

function numcheck(val,jaego){
    if (val>jaego-1){
            alert("수량 선택 오류입니다.")
            document.memberForm.buynum.value=jaego}
    else if (val<2){
            document.memberForm.buynum.value=1        }
}
function up(val,jaego){
    if (val>jaego-1){
            alert("수량 선택 오류입니다.")
            document.memberForm.buynum.value=jaego}
    else{
            document.memberForm.buynum.value=(val/1)+1}
}
                                     
function down(val){
    if (val<2){
            document.memberForm.buynum.value=1}
    else{
            document.memberForm.buynum.value=val-1}
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
	
<div style="width: 800px; margin: 0px auto;" class = "pay">
		<form name="memberForm" method="post" onsubmit="return basket();">
			<br>
			<div style="width: 800px; font-family : 맑은 고딕;font-size: 30px;color:red">&nbsp;${dto.name}<${dto.panmae_num}></div>
			<br>
			
			<div style="width: 800px; height : 900px; border : 1px solid black; background : white;">
			<div style="float : left;margin : 30px; width: 200px; height : 300px; border : 1px solid black; background : white;
			text-align : center; line-height : 300px;">
<%-- 			<img src="<%=cp%>/uploads/photo/${dto.image}" style="width: 190px; height: 190px;" border="0"> --%>
			<img src="<%=cp%>/images/${dto.image}" style="width: 180px; height: 280px;">
			</div>
			<dl style="float : left;margin :10px;line-height:70px;width: 400px;font-size: 20px;color:red">${dto.name}&nbsp;&nbsp;<${dto.panmae_num}> </dl>
			<dl style="float : left;margin :10px;line-height:25px;width: 400px;font-size: 20px;">판매단가 : ${dto.price} </dl>
			<dl style="float : left;margin :10px;line-height:25px;width: 400px;font-size: 20px;">생산지(생산자) : ${dto.produce_corpor_name}</dl>
			<dl style="float : left;margin :10px;line-height:25px;width: 400px;font-size: 20px;">등록일 : ${dto.created}</dl>
			<dl style="float : left;margin :10px;line-height:25px;width: 450px;font-size: 20px;">${dto.panmae_state}</dl>
			<span style="float : left;margin :11px;line-height:25px;width: 100px;font-size: 20px;height:30px;">주문수량 :
			</span>
			<span style = "margin :10px;float : left; width : 200px; margin-right : 20px">&nbsp;&nbsp;
			<input type = "text" name="buynum"  value = "1" style = "float : left;width : 50px;height : 28px; text-align:center" 
			onblur="numcheck(this.form.buynum.value,99)" onkeyup="if(isNaN(this.value)) {alert('숫자만 입력해 주세요.');this.value=' '};">
			<input type="button" value = "▲" style = "float : left;width : 30px; height : 28px" onclick="up(this.form.buynum.value,99)">
			<input type="button" value = "▼" style = "float : left;width : 30px; height : 28px" onclick="down(this.form.buynum.value)">
			</span>
			<dl style="clear : both;margin :30px;line-height:50px;width: 400px; height : 400px;font-size: 20px;color:red">상품소개 : ${dto.introduce} </dl> 
			
			<div style="clear: both; width: 500px; margin : 0px auto; text-align: center;">

				<button type="button" style="color : white; background : green;border-radius: 10px ;width : 130px;height: 50px; text-align : center; font-weight : border"
					 onclick="sangsae_pay();">바로 구매</button> 
				<button type="button" style="border-radius: 10px ;width : 130px;height: 50px; text-align : center; font-weight : border"
					 onclick="javascript:location.href='<%=cp%>/sale/panmaeList.do';">쇼핑 계속</button>
				<button type="button" style="border-radius: 10px ;width : 130px;height: 50px; text-align : center; font-weight : border"
					 onclick="basket()">장바구니 담기</button>	  
				 
			     <input type="hidden" name="basket_num" value="${dto.basket_num}">
				 <input type="hidden" name="panmae_num" value="${dto.panmae_num}">
				 <input type="hidden" name="name" value="${dto.name}">
				 <input type="hidden" name="price" value="${dto.price}">
				 <input type="hidden" name="produce_corpor_name" value="${dto.produce_corpor_name}">
				 
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