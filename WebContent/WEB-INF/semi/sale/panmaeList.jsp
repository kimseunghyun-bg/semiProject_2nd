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
<style type="text/css">

.imgLayout{
	width: 350px;
	height: 450px;
	padding: 5px 5px 5px;
	margin: 5px;
	border: 1px solid black;
	float: left;
	background : white
}

.subject {
     width:330px;
     height:40px;
     line-height:35px;
     margin:20px auto 0px;
     display: inline-block;
     white-space:nowrap;
     overflow:hidden;
     text-overflow:ellipsis;
     cursor: pointer;
     text-align: center;
     font-size : 25px;
     color : black;
     font-family : 굴림;
}
</style>
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
function sangsae(panmae_num) {
	var url="${sangsaeUrl}&panmae_num="+panmae_num;
	location.href=url;
}

function basketlist() {
	var url = "${basketUrl}";
	

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
	
	
	    <div style="max-width:1080px; height : 1500px; margin: 0px auto;">
	<c:if test="${dataCount!=0 }">    
	        <div style="clear: both; height: 100px; line-height: 30px;">
	            <div style="float: left;">${dataCount}개(${page}/${total_page} 페이지)</div>
	            <div style="float: right;">&nbsp;</div>
	            <button style ="width : 100px; height: 30px;margin-top : 40px;float : right; font-size : 20px; border : 1px solid black" onclick = "basketlist()">장바구니</button>
	        </div>
	        
	        <div style="clear: both;">
	    <c:forEach var="dto" items="${list}" varStatus="status">
	                 <c:if test="${status.index==0}">
	                       <c:out value="<div style='clear: both; max-width:1100px; margin: 0px auto;'>" escapeXml="false"/>
	                 </c:if>
	                 <c:if test="${status.index!=0 && status.index%3==0}">
	                        <c:out value="</div><div style='clear: both; max-width:1100px; margin: 0px auto;'>" escapeXml="false"/>
	                 </c:if>
				      <div class="imgLayout">
		                     <img src="<%=cp%>/images/panmaeImg/${dto.image}" style="width: 340px; height: 220px;">
				             <span style = "background : yellow" class="subject" onclick="javascript:sangsae('${dto.panmae_num}');" >
				                   No : ${dto.panmae_num}&nbsp;&nbsp;${dto.name}
				             </span>
				             <span style = "height : 80px"class="subject" >
				                   판매단가 : ${dto.price}원 <br> ${dto.panmae_state}
				             </span>
				       </div>
	    </c:forEach>
		
	    <c:set var="n" value="${list.size()}"/>
	    <c:if test="${n>0&&n%3!=0}">
			        <c:forEach var="i" begin="${n%3+1}" end="3" step="1">
				             <div class="imgLayout">&nbsp;</div>
			        </c:forEach>
	    </c:if>
		
	    <c:if test="${n!=0 }">
			       <c:out value="</div>" escapeXml="false"/>
	    </c:if>
	        </div> 
	</c:if>
	
	       
	        
	       
</div>

 <div style="text-align: center; min-height: 50px; line-height: 50px;">
	            <c:if test="${dataCount==0 }">
	                  등록된 게시물이 없습니다.
	            </c:if>
	            <c:if test="${dataCount!=0 }">
	                ${paging}
	            </c:if>
	        </div>        

	        


<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>