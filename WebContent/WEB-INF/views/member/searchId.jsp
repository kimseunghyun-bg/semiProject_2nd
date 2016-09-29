<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<script src="js/simpleCart.min.js"> </script>
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
	width: 150px; height: 100px;
	background: #006536;
	border: 1px solid #006536;
	font-size: 18px;
	color: white;
}
.lbutton{
	width: 150px; height: 45px;
	margin-right: 20px;
	font-size: 13px;
	color: #6b6b6b;
	background: none;
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
</style>
<script type="text/javascript">
function bgLabel(ob, id) {
    if(!ob.value) {
	    document.getElementById(id).style.display="";
    } else {
	    document.getElementById(id).style.display="none";
    }
}

function sendSId() {
	var f = document.searchIdForm;
	
	var str = f.name.value;
    if(!str) {
        alert("이름을 입력하세요. ");
        f.name.focus();
        return false;
    }

    str = f.email.value;
    if(!str) {
        alert("이메일을 입력하세요. ");
        f.email.focus();
        return false;
    }
	
	f.action = "<%=cp%>/member/sendId_ok.do";
    f.submit();
}

function sendSPwd() {
	var f = document.searchIdForm;
	
	f.action = "<%=cp%>/member/sendPwd.do";
    f.submit();
}

function sendlogin() {
	var f = document.searchIdForm;
	
    f.action = "<%=cp%>/member/login.do";
    f.submit();
}
</script>
</head>
<body>
<!--header-->	
<div class="layoutHeader">
	<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
</div>
<div style="margin: 10px auto; margin-top:50px; width:500px; height:400px;">
<h3>■ 아이디찾기</h3>
<br>
	<form name="searchIdForm" method="post" action="">
		<table style="margin: 10px auto; width:500px; border-spacing: 0px;">
		<tr align="center">
			<td>
				<label for="name" id="name" class="lbl">이름</label>
				<input type="text" name="name" id="name" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('name').style.display='none';"
                       onblur="bgLabel(this, 'name');">
			</td>
			<td rowspan="2">
			<input type="button"onclick="sendSId();" class="loginButton" value="아이디찾기">
			</td>
		</tr>
		<tr align="center">
			<td>
				<label for="email" id="email" class="lbl">이메일주소</label>
				<input type="text" name="email" id="email" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('email').style.display='none';"
                       onblur="bgLabel(this, 'email');">
			</td>
		</tr>
		<tr height="20">
			<td></td>
		</tr>
	    <tr height="20">
			<td></td>
		</tr>
		<tr align="center">
			<td colspan="2">
			<input type="button" class="lbutton" value="비밀번호찾기" onclick="sendSPwd();">
			<input type="button" class="lbutton" value="로그인하기" onclick="sendlogin();">
			</td>
		</tr>
		<tr height="50">
			<td></td>
		</tr>
		<tr align="center">
			<td colspan="2" style="font-size: 17px; text-align: center;">${message}</td>
		</tr>
		</table>
	</form>
</div>
<!--footer-->
<div class="layoutfooter">
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</div>
	
</body>
</html>