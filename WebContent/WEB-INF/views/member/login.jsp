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
<title>녹색매장</title>
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
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<script src="<%=cp%>/js/simpleCart.min.js"> </script>
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

<script src="<%=cp%>/js/simpleCart.min.js"> </script>
<style type="text/css">
* {
	margin:0px;
	padding:0px;
	font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", 돋움, sans-serif;
}
.loginTF {
  width: 230px; height:40px;
  padding-left: 15px;
  margin-top:7px; margin-bottom:7px;
}
.lbl {
   position:absolute; 
   margin-left:15px; margin-top: 15px;
   color: #999999;
   font-size: 13px;
   font-family: NanumGothic, 나눔고딕, "Malgun Gothic", "맑은 고딕", 돋움, sans-serif;
}
.loginButton{
	width: 150px; height: 100px;
	background: #006536;
	border: 1px solid #006536;
	font-size: 18px;
	color: white;
}
.lbutton{
	width: 120px; height: 45px;
	margin-right: 20px;
	font-size: 13px;
	color: #6b6b6b;
	background: none;
	border: 1px solid #6b6b6b;
}
.Joinbutton{
	width: 120px; height: 45px;
	margin-right: 20px;
	font-size: 13px;
	color: white;
	background: #6b6b6b;
	border: 1px solid #6b6b6b;
}
.loginButton:active, .loginButton:focus, .loginButton:hover{
	background: #005000;
	border: 1px solid #005000;
}
.lbutton:active, .lbutton:focus, .lbutton:hover{
	border: 1px solid black;
	color: black;
}
.Joinbutton:active, .Joinbutton:focus, .Joinbutton:hover{
	background: #444444;
	border: 1px solid #444444;
}

/* .form-signin {
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
.boxLayout {
    max-width:420px;
    padding:20px;
    border: 1px solid #DAD9FF;
} */
</style>

<script type="text/javascript">
function bgLabel(ob, id) {
    if(!ob.value) {
	    document.getElementById(id).style.display="";
    } else {
	    document.getElementById(id).style.display="none";
    }
}

function sendLogin() {
    var f = document.loginForm;

	var str = f.memberId.value;
    if(!str) {
        alert("아이디를 입력하세요. ");
        f.memberId.focus();
        return false;
    }

    str = f.password.value;
    if(!str) {
        alert("패스워드를 입력하세요. ");
        f.password.focus();
        return false;
    }

    f.action = "<%=cp%>/member/login_ok.do";
    f.submit();
}

function sendJoin() {
	var f = document.loginForm;
	
    f.action = "<%=cp%>/member/insert.do";
    f.submit();
}

function sendSId() {
	var f = document.loginForm;
	
	f.action = "<%=cp%>/member/sendId.do";
    f.submit();
}

function sendSPwd() {
	var f = document.loginForm;
	
	f.action = "<%=cp%>/member/sendPwd.do";
    f.submit();
}
</script>
</head>
<body style="background: white;">
<!--header-->	
<div class="layoutHeader">
	<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</div>

<div class="layoutBody" style="margin: 10px auto; padding-top: 140px; width:500px; height: 630px;">
<h3 style="margin-left: 35px;"><img src="<%=cp%>/images/arrow.jpg" alt="" style="padding-left: 5px; padding-right: 5px;"> 회원 로그인</h3>

<br>
	<form name="loginForm" method="post" action="">
		<table style="margin: 10px auto; width:500px; border-spacing: 0px;">
		<tr align="center">
			<td>
				<label for="memberId" id="lblMemberId" class="lbl">아이디</label>
				<input type="text" name="memberId" id="memberId" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('lblMemberId').style.display='none';"
                       onblur="bgLabel(this, 'lblMemberId');">
			</td>
		  <td rowspan="2" align="left">
	        	<input type="button"onclick="sendLogin();" class="loginButton" value="로그인">
	      </td>
		</tr>
		<tr align="center">
	      <td>
	        <label for="password" id="lblPassword" class="lbl">패스워드</label>
	        <input type="password" name="password" id="password" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('lblPassword').style.display='none';"
                       onblur="bgLabel(this, 'lblPassword');">
	      </td>
		</tr>
		<tr height="40">
			<td></td>
		</tr>
		<tr align="center">
			<td colspan="3">
			<input type="button" class="Joinbutton" value="회원가입" onclick="sendJoin();" >
			<input type="button" class="lbutton" value="아이디찾기" onclick="sendSId();">
			<input type="button" class="lbutton" value="비밀번호찾기" onclick="sendSPwd();">
			</td>
		</tr>
		</table>
		<div style="margin-top:10px; text-align: center;">${message}</div>
	</form>
</div>

<!--footer-->
<div class="layoutfooter">
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</div>
</body>
</html>
