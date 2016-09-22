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

<div style="margin: 10px auto; margin-top: 50px; width:500px; height: 350px;">
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

</body>
</html>