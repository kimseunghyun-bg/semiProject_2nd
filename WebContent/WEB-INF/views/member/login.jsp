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

<script type="text/javascript" src="<%=cp%>js/move-top.js"></script>
<script type="text/javascript" src="<%=cp%>js/easing.js"></script>
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
	width: 150px; height: 90px;
	background: #006536;
	font-size: 15px;
	color: white;
}
.lbutton{
	width: 120px; height: 40px;
	font-size: 13px;
	margin-right: 20px;
}
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

	var str = f.userId.value;
    if(!str) {
        alert("아이디를 입력하세요. ");
        f.userId.focus();
        return;
    }

    str = f.userPwd.value;
    if(!str) {
        alert("패스워드를 입력하세요. ");
        f.userPwd.focus();
        return;
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
	f.action = "";
    f.submit();
}

function sendSPwd() {
	f.action = "";
    f.submit();
}
</script>
</head>
<body> 
<!--header-->	
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>	
	
	<div class="layoutBody">
	    <div class="bodyFrame1" style="min-height: 450px;">
		<div class="container" role="main">            
			  
				<div class="bodyFrame" align="center">
				    <h3>■ 회원 로그인</h3>
<br>
	<form name="loginForm" method="post" action="">
		<table style="margin: 10px auto; width:500px; border-spacing: 0px;">
		<tr align="center">
			<td>
				<label for="userId" id="lblUserId" class="lbl">아이디</label>
				<input type="text" name="userId" id="userId" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('lblUserId').style.display='none';"
                       onblur="bgLabel(this, 'lblUserId');">
			</td>
			<td rowspan="2" align="left">
	        	<input type="button" value="로그인 " onclick="sendLogin();" class="loginButton">
	      	</td>
		</tr>
		<tr align="center">
	      <td>
	        <label for="userPwd" id="lblUserPwd" class="lbl">패스워드</label>
	        <input type="password" name="userPwd" id="userPwd" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('lblUserPwd').style.display='none';"
                       onblur="bgLabel(this, 'lblUserPwd');">
	      </td>
		</tr>
		<tr height="20">
			<td></td>
		</tr>
		<tr>
			<td colspan="2" height="1" bgcolor="#006536"></td>
		</tr>
	    <tr height="20">
			<td></td>
		</tr>
		<tr>
			<td colspan="3" align="center">
			<input style="background: #6b6b6b; color: white;" type="button" class="lbutton" value="회원가입" onclick="sendJoin();" >
			<input type="button" class="lbutton" value="아이디찾기" onclick="sendSId();">
			<input type="button" class="lbutton" value="비밀번호찾기" onclick="sendSPwd();">
			</td>
		</tr>
		</table>
	</form>
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
