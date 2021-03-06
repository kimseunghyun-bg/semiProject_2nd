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

<link rel="stylesheet" href="<%=cp%>/jquery/css/smoothness/jquery-ui.min.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/bootstrap/css/bootstrap.min.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/bootstrap/css/bootstrap-theme.min.css" type="text/css"/>

<link rel="stylesheet" href="<%=cp%>/css/style1.css" type="text/css"/>
<link rel="stylesheet" href="<%=cp%>/css/layout/layout.css" type="text/css"/>

<script src="<%=cp%>/js/simpleCart.min.js"> </script>
</head>
<body> 
<!--header-->	
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>


	<div class="layoutBody" style="min-height: 600px; 
		background-image: url('<%=cp%>/images/companyIntroduce.jpg'); 
		background-repeat: no-repeat; 
		background-position: center; 
		background-size: contain; 
		background-size: cover;" align="center">
		
		<div style="width:900px; height: 700px;  margin: 0px auto;">
			
			<div style="width: 900px; height: 220px; margin: 0px auto; clear: both;">
				<img src="<%=cp%>/images/companyIntroduceFarmer.jpg">
			</div>
			
			<div style="width: 900px; height: 20px;  clear: both; float: left;"></div>
			
			<div style="clear: both; display: inline-block; width: 590px; height: 450px; float: left;">
				<img src="<%=cp%>/images/companyIntroduceText.jpg"><br>
				<img src="<%=cp%>/images/companyInfo.jpg">
			</div>
			
			<div style="clear: both; display: inline-block; width: 300px; height: 450px; ">
				<img src="<%=cp%>/images/manager.jpg">
			</div>
		</div>
		
	</div>

<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
	
	<script type="text/javascript" src="<%=cp%>/jquery/js/jquery-ui.min.js"></script>
	<script type="text/javascript" src="<%=cp%>/jquery/js/jquery.ui.datepicker-ko.js"></script>
	<script type="text/javascript" src="<%=cp%>/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>