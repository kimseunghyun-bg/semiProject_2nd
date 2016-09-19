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
<script type="application/x-javascript">
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 




</script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700'
	rel='stylesheet' type='text/css'>
<!--//fonts-->

<script type="text/javascript" src="<%=cp%>/js/move-top.js"></script>
<script type="text/javascript" src="<%=cp%>/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<!-- start menu -->
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>

<script src="<%=cp%>/js/simpleCart.min.js">
	
</script>
</head>
<body>
	<!--header-->
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/admin/layout/header.jsp"></jsp:include>
	</div>
	<div class="layoutBody">
		<div class="bodyFrame1" style="min-height: 450px;">

			<div class="container">

				<div style="height: 50px; padding: 10px;">
					<ul style="list-style: none;">
						<li style="width: 20%; float: left;">상품상태 : <select>
								<option>전체</option>
								<option>판매중</option>
								<option>품절</option>
								<option>단품</option>
						</select></li>

						<li style="width: 20%; float: left;">대분류 : <select>
								<option>전체</option>
								<option>대분류</option>
						</select></li>

						<li style="width: 20%; float: left;">소분류 : <select>
								<option>전체</option>
								<option>소분류</option>
						</select></li>

						<li style="width: 40%; float: left;"><select>
								<option>상품번호</option>
								<option>상품명</option>
								<option>생산자</option>
						</select><input type="text"><input type="button" value="검색"><input type="button" value="신규등록" onclick="javascript:location.href='<%=cp%>/admin/goodsmgmt/create.do';"></li>
					</ul>
				</div>

				<div class="list"
					onclick="javascript:location.href='<%=cp%>/admin/goodsmgmt/create.do';">
					<div>
						<img src="<%=cp%>/images/ph6.jpg"
							style="width: 123px; height: 123px; max-width: 100%; max-height: 100%">
					</div>
					<div style="width: 1013px;">
						<div style="padding: 4px 2px;">
							<h3>상품명</h3>
						</div>
						<div style="padding: 2px; font-size: 16px;">

							<div class="list_col">
								<ul>
									<li>상품번호 <label>010101</label>
									</li>
									<li>대분류 <label>010101</label>
									</li>
									<li>소분류 <label>010101</label>
									</li>
								</ul>
							</div>

							<div class="list_col">
								<ul>
									<li>총 수량 <label>010101</label></li>
									<li>잔여수량 <label>010101</label></li>
									<li>원가 <label>010101</label></li>
								</ul>
							</div>

							<div class="list_col">
								<ul>
									<li>등록일 <label>010101</label></li>
									<li>상품상태 <label>010101</label></li>
								</ul>
							</div>

							<div class="list_col">
								<ul>
									<li>생산자코드 <label>010101</label></li>
									<li>생산자이름 <label>010101</label></li>
									<li>생산지 <label>010101</label></li>
								</ul>
							</div>

						</div>
					</div>
				</div>


			</div>

			<div>페이징</div>


		</div>

	</div>

	<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/admin/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>
