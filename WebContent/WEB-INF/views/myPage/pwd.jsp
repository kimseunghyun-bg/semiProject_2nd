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
<script type="text/javascript">
function bgLabel(ob, id) {
	    if(!ob.value) {
		    document.getElementById(id).style.display="";
	    } else {
		    document.getElementById(id).style.display="none";
	    }
}

function sendOk() {
        var f = document.confirm;

    	var str = f.memberId.value;
        if(!str) {
            f.memberId.focus();
            return false;
        }

        str = f.password.value;
        if(!str) {
            f.password.focus();
            return false;
        }

        f.action = "<%=cp%>/myPage/pwd_ok.do";
        f.submit();
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

<div class="layoutBody">	
	<div class="bodyFrame1" style="min-height: 450px;">
		<div class="container" role="main">            
	  	<div class="bodyFrame" style="background: #F0F0F0; padding:15px 0 15px 0; ">
			<jsp:include page="/WEB-INF/views/layout/header1.jsp"></jsp:include>
		</div>
		<div class="layoutBody" style="margin: 10px auto; margin-top: 50px; width:500px; height: 350px;">
			<div style="width:600px; height:30px">
			<h3>■ 패스워드 재확인</h3>
			<p style="font-size: 10pt; text-align: left; padding-top: 5px;"><span style="color: red; font-weight: bold">*</span> 정보보호를 위해 패스워드를 다시한번 입력해 주세요.</p>
			</div>
			<div style="height: 20px"></div>
			<div>
			<form name="confirm" method="post" action="">
				<table style="margin: 10px auto; width:500px; border-spacing: 0px;">
				<tr align="center">
					<td>				
						<input type="text" name="memberId" id="memberId" class="loginTF" maxlength="20"
		                      value="${sessionScope.member.memberId}" readonly="readonly">
					</td>
				  <td rowspan="2" align="left">
			        	<button type="button"onclick="sendOk();" class="loginButton">확인</button>
			      </td>
				</tr>
				<tr align="center">
			      <td>
			        <label for="password" id="lblPassword" class="lbl">패스워드</label>
			        <input type="password" name="password" id="password" class="loginTF" maxlength="20"
		                       onfocus="document.getElementById('lblPassword').style.display='none';"
		                       onblur="bgLabel(this, 'lblPassword');">
		            <input type="hidden" name="mode" value="${mode}">
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
				
				</table>
				<div style="margin-top:10px; text-align: center;">${message}</div>
			</form>
			</div>
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