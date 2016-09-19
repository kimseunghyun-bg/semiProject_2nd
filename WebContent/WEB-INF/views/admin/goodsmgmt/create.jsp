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
			<div class="container" style="margin-top: 50px;">
				<form action="">
					<ul	style="list-style: none; width: 375px; height: 425px; float: left;">
						<li><img src="<%=cp%>/images/ph6.jpg"
							style="width: 375px; height: 375px;"></li>
						<li style="float: left;"><button>이미지 삭제</button></li>
						<li style="float: left;"><button>이미지 등록</button></li>
					</ul>
					<ul style="list-style: none; font-size: 16px; float: left; width: auto; height:425px; padding-left: 20px;">
						<li style="float: left; margin: 10px 10px 20px 0px;">상품명&nbsp;&nbsp;<input type="text" name="subject"
							size="80px;" maxlength="100" value=""></li>
						<li style="clear: both; float: left; width: 25%; margin: 10px 0px 20px 0px;">상품번호  <label>010101</label>
						</li>
						<li style="float: left; width: 25%; margin: 10px 0px 20px 0px;">대분류  <select><option>::선택::</option>
								<option>대분류</option></select></li>
						<li style="float: left; width: 25%; margin: 10px 0px 20px 0px;">소분류  <select><option>::선택::</option>
								<option>소분류</option></select></li>
						<li style="float: left; width: 25%; margin: 10px 0px 20px 0px;">분류코드  <label>(010101)</label>
						</li>

						<li style="clear:both; width: 100%; margin: 20px 0px 10px 0px; text-align: left;">상품소개</li>
						<li style="width: 710px; height:225px">
						<textarea style="resize:none; width: 100%; height:100%;" rows="" cols="" ></textarea> </li>
					</ul >
					<ul style="clear:both; list-style: none; font-size: 16px; float: left; width: 25%; padding-top: 20px;">
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">누적&nbsp;입고&nbsp;수량</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">01010101</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">누적&nbsp;판매&nbsp;수량</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">01010101</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">재고&nbsp;수량</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">01010101</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">추가&nbsp;수량</li>
						<li><input type="text" style="margin: 5px 40px 5px 0px; width: 25%; text-align: right; float: right; "></li>
					</ul>
					<ul style="list-style: none; font-size: 16px; float: left; width: 25%; padding-top: 20px;">
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">등록일</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">01010101</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">상품정가</li>
						<li><input type="text" style="margin: 5px 40px 5px 0px; width: 25%; text-align: right; float: right; "></li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">상품상태</li>
						<li style="margin: 5px 40px 5px 0px; width: 25%; text-align: right; float: right;">
						<select>
						<option>판매</option>
						<option>품절</option>
						<option>단품</option>
						</select></li>
					</ul>
					<ul style="list-style: none; font-size: 16px; float: left; width: 50%; padding-top: 20px;">
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">생산자&nbsp;&nbsp;<select><option>::선택::</option><option>생산자명</option></select><label>&nbsp;&nbsp;(1234)</label></li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">전화번호&nbsp;&nbsp;<label>010&nbsp;-&nbsp;7777&nbsp;-&nbsp;7777</label></li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">생산자주소&nbsp;&nbsp;<label>주소</label></li>
					</ul>
					<ul style="clear:both; list-style: none; font-size: 16px; float: left; width: 100%; padding-top: 20px; text-align: center;">
					<li><button>취소</button>&nbsp;&nbsp;&nbsp;&nbsp;<button>등록</button></li>
					</ul>
				</form>
			</div>
		</div>
	</div>

	<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/admin/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>