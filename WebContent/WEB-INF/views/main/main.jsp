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
				</script>
<!-- start menu -->
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<link rel="stylesheet" href="<%=cp%>/res/jquery/css/smoothness/jquery-ui.min.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/res/bootstrap/css/bootstrap.min.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/res/bootstrap/css/bootstrap-theme.min.css" type="text/css"/>

<link rel="stylesheet" href="<%=cp%>/res/css/style.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/res/css/layout/layout.css" type="text/css"/>

<script src="<%=cp%>/js/simpleCart.min.js"> </script>
</head>
<body> 
<!--header-->	
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	
	<div class="layoutBody" style="min-height: 600px; 
		background-image: url('<%=cp%>/images/MainScreenFarmer.jpg'); 
		background-repeat: no-repeat; 
		background-position: center; 
		background-size: contain; 
		background-size: cover;" align="center">
		
		<div>
			<div style="width: 700px; margin: 0px auto;"
			 id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-pause="hover">
			  <!-- Indicators -->
			  <ol class="carousel-indicators">
			    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
			    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
			  </ol>
			
			  <!-- Wrapper for slides -->
			  <div class="carousel-inner" role="listbox">
			    <div class="item active">
			      <img src="<%=cp%>/images/carousel-1.jpg">
			      <div class="carousel-caption">
			         <!-- 내용 없음 -->
			      </div>
			    </div>
			    <div class="item">
			      <img src="<%=cp%>/images/carousel-2.jpg" alt="...">
			      <div class="carousel-caption">
			         <!-- 내용 없음 -->
			      </div>
			    </div>
			    <div class="item">
			      <img src="<%=cp%>/images/carousel-3.jpg" alt="...">
			      <div class="carousel-caption">
			         <!-- 내용 없음 -->
			      </div>
			    </div>
			  </div>
			
			  <!-- Controls -->
			  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
			    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
			    <span class="sr-only">Previous</span>
			  </a>
			  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
			    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			    <span class="sr-only">Next</span>
			  </a>
			</div>
	
	
			<div style="margin: 0px auto;">
				<jsp:include page="/WEB-INF/views/mainScreen/mainScreenButton.jsp"></jsp:include>
			</div>
			
			<div style="height: 40px;"></div>
			
			<div style="width: 700px; height: 300px;">
				
				<div style="width: 300px; display: inline-block;">
					<table style="width: 300px; margin: 0px auto; border-spacing: 0px;">
						<tr align="center" bgcolor="#F2CB61" height="30">
							<td width="300" style="color: #ffffff; font-size: 15px; font-weight: bold;">
								<a href="<%=cp%>/boardNotice/list.do">공지사항</a>
							</td>
						</tr>
			
						<c:forEach var="dto" items="${list}">
							<tr align="center" bgcolor="#FFEF85" height="30">
								<td align="left" style="padding-left: 10px;">
									<a href='${articleUrl}&num=${dto.num}'>${dto.subject}</a>
								</td>
							</tr>
							<tr>
								<td height="1" colspan="6" bgcolor="#65D35D"></td>
							</tr>
						</c:forEach>
					</table>
			
					<c:if test="${dataCount==0 }">
					<table style="width: 300px; margin: 0px auto; border-spacing: 0px;">
						<tr height="35">
							<td align="center">
								등록된 게시물이 없습니다.
							</td>
						</tr>
					</table>
					</c:if>
				</div>
				
				&nbsp;&nbsp;&nbsp;&nbsp;
				<div style="display: inline-block;" align="right">
					<video style="width: 300px; height: 200px;" autoplay="autoplay" controls="controls">
						<source src="<%=cp%>/video/son.mp4" type="video/mp4">
					</video>
				</div>
				
			</div>
		</div>
    </div>

<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript" src="<%=cp%>/res/jquery/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=cp%>/res/jquery/js/jquery.ui.datepicker-ko.js"></script>
	<script type="text/javascript" src="<%=cp%>/res/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>