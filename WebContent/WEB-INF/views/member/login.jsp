<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
	request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<meta name="keywords" content="Markito Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700' rel='stylesheet' type='text/css'>
<!--//fonts-->

<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript">
					jQuery(document).ready(function($) {
						$(".scroll").click(function(event){		
							event.preventDefault();
							$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
						});
					});
				</script>
<!-- start menu -->
<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
<script type="text/javascript" src="js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<script src="js/simpleCart.min.js"> </script>

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

<div class="layoutBody" style="margin: 10px auto; margin-top: 50px; width:500px; height: 350px;">
<h3>■ 회원 로그인</h3>
<br>
	<form name="loginForm" method="post" action="">
		<table style="margin: 10px auto; width:500px; border-spacing: 0px;">
		<tr align="center">
			<td>
				<label for="memberId" id="lblmemberId" class="lbl">아이디</label>
				<input type="text" name="memberId" id="memberId" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('lblmemberId').style.display='none';"
                       onblur="bgLabel(this, 'lblmemberId');">
			</td>
		  <td rowspan="2" align="left">
	        	<button type="button"onclick="sendLogin();" class="loginButton">로그인</button>
	      </td>
		</tr>
		<tr align="center">
	      <td>
	        <label for="password" id="lblpassword" class="lbl">패스워드</label>
	        <input type="password" name="password" id="password" class="loginTF" maxlength="20"
                       onfocus="document.getElementById('lblpassword').style.display='none';"
                       onblur="bgLabel(this, 'lblpassword');">
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

<!--footer-->
<div class="layoutfooter">
	<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
</div>

</body>
</html>