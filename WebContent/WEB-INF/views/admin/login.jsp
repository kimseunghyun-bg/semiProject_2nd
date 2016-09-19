<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->

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
	<div id="main_container">

		<div class="header_login">
			<div class="logo">
				<a href="#"><img src="images/logo.gif" alt="" title=""
					border="0" /></a>
			</div>

		</div>


		<div class="login_form">

			<h3>Admin Panel Login</h3>

			<a href="#" class="forgot_pass">Forgot password</a>

			<form action="" method="post" class="niceform">

				<fieldset>
					<dl>
						<dt>
							<label for="email">Username:</label>
						</dt>
						<dd>
							<input type="text" name="" id="" size="54" />
						</dd>
					</dl>
					<dl>
						<dt>
							<label for="password">Password:</label>
						</dt>
						<dd>
							<input type="text" name="" id="" size="54" />
						</dd>
					</dl>

					<dl>
						<dt>
							<label></label>
						</dt>
						<dd>
							<input type="checkbox" name="interests[]" id="" value="" /><label
								class="check_label">Remember me</label>
						</dd>
					</dl>

					<dl class="submit">
						<input type="submit" name="submit" id="submit" value="Enter" />
					</dl>

				</fieldset>

			</form>
		</div>



		<div class="footer_login">

			<div class="left_footer_login">
				IN ADMIN PANEL | Powered by <a href="http://indeziner.com">INDEZINER</a>
			</div>
			<div class="right_footer_login">
				<a href="http://indeziner.com"><img
					src="images/indeziner_logo.gif" alt="" title="" border="0" /></a>
			</div>

		</div>

	</div>
</body>
</html>