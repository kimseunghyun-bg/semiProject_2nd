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
<style type="text/css">
*{
	margin: 0px; padding: 0px;
}
body {
	font-size: 11pt; font-family: 돋움;
}
a{
	color: #000000;
	text-decoration: none;
	cursor: pointer;
}
a:active, a:hover {
	text-decoration: underline;
	color: tomato;
}
.title {
	font-weight: bold;
	font-size:13pt;
	margin-bottom:10px;
	font-family: 나눔고딕, 맑은 고딕, 돋움, sans-serif;
}
.btn {
	 font-size: 12px;
	 color:#333;
 	 font-weight:500;
	 font-family: 나눔고딕, 맑은 고딕, 돋움, sans-serif;
	 border:1px solid #ccc;
	 background-color:#FFF;
	 vertical-align:middle;
	 text-align:text-align;
	 cursor:cursor;
	 padding:4px 8px;
	 border-radius:4px;
	 margin-bottom: 3px;
}
/* 버튼을 클릭할때 */
.btn:active, .btn:focus, .btn:hover { 
	 background-color:#e6e6e6;
	 border-color: #adadad;
	 color: #333;
}
.boxTF {
	border:1px solid #999;
	padding:4px 6px;
	border-radius:4px;
	background-color:#ffffff;
	font-family: 나눔고딕, 맑은 고딕, 돋움, sans-serif;
	font-size: 9pt;
}
/* select박스 */
.selectField {
	border:1px solid #999;
	padding:3px 6px;
	border-radius:4px;
	font-family: 나눔고딕, 맑은 고딕, 돋움, sans-serif;
	font-size: 9pt;
}

#member table td{
    border-bottom: 1px solid #ccc;
}
</style>

<script type="text/javascript">
function memberOk() {
   var f = document.joinform;
   var str;
	
   // 아이디
   str = f.userId.value;
   str = str.trim();
   if(!str) {
      alert("아이디를 입력하세요. ");
      f.userId.focus();
      return;
   }
   if(!/^[a-z][a-z0-9_]{4,9}$/i.test(str)) { 
      alert("아이디는 5~10자이며 첫글자는 영문자이어야 합니다.");
      f.userId.focus();
      return;
   }
   f.userId.value = str;

   // 비밀번호
   str = f.userPwd.value;
   str = str.trim();
   if(!str) {
      alert("비밀번호를 입력하세요. ");
      f.userPwd.focus();
      return;
   }
   if(!/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str)) { 
      alert("비밀번호는 5~10자이며 하나 이상의 숫자나 특수문자가 포함되어야 합니다.");
      f.userPwd.focus();
      return;
   }
   f.userPwd.value = str;

   if(str!= f.userPwd1.value) {
        alert("비밀번호가 일치하지 않습니다. ");
        f.userPwd1.focus();
        return;
   }
   
   // 이름
   str = f.userName.value;
   str = str.trim();
    if(!str) {
        alert("이름을 입력하세요. ");
        f.userName.focus();
        return;
    }
    f.userName.value = str;

/*     // 생년월일
    str = f.birth.value;
    str = str.trim();
    if(!str || !isValidDateFormat(str)) {
        alert("생년월일을 입력하세요[YYYY-MM-DD]. ");
        f.birth.focus();
        return;
    } */
    
    // 이메일
    str = f.email1.value;
    str = str.trim();
    if(!str) {
        alert("이메일을 입력하세요. ");
        f.email1.focus();
        return;
    }

    str = f.email2.value;
   str = str.trim();
    if(!str) {
        alert("이메일을 입력하세요. ");
        f.email2.focus();
        return;
    }
    
    // 휴대폰번호
    str = f.tel1.value;
    str = str.trim();
    if(!str) {
        alert("휴대폰번호를 입력하세요. ");
        f.tel1.focus();
        return;
    }

    str = f.tel2.value;
   str = str.trim();
    if(!str) {
        alert("휴대폰번호를 입력하세요. ");
        f.tel2.focus();
        return;
    }
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.tel2.focus();
        return;
    }

    str = f.tel3.value;
   str = str.trim();
    if(!str) {
        alert("휴대폰번호를 입력하세요. ");
        f.tel3.focus();
        return;
    }
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.tel3.focus();
        return;
    }
    
 	// 전화번호
    str = f.hp1.value;
    str = str.trim();
    if(!str) {
        alert("전화번호를 입력하세요. ");
        f.hp1.focus();
        return;
    }

    str = f.hp2.value;
   str = str.trim();
    if(!str) {
        alert("전화번호를 입력하세요. ");
        f.hp2.focus();
        return;
    }
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.hp2.focus();
        return;
    }

    str = f.hp3.value;
   str = str.trim();
    if(!str) {
        alert("전화번호를 입력하세요. ");
        f.hp3.focus();
        return;
    }
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.hp3.focus();
        return;
    }
    

    var mode="${mode}";
    if(mode=="created") {
       f.action = "<%=cp%>/member/insert_ok.do";
    } else if(mode=="update") {
       f.action = "<%=cp%>/member/update_ok.do";
    }

    f.submit();
}

function changeEmail() {
    var f = document.joinform;
    
    var str = f.selectEmail.value;
    if(str!="direct") {
         f.email2.value=str; 
         f.email2.readOnly = true;
         f.email1.focus(); 
    }
    else {
        f.email2.value="";
        f.email2.readOnly = false;
        f.email2.focus();
    }
}
</script>

</head>
<body>

<div style="margin: 10px auto; margin-top:50px; width:600px; height:800px;">
	<div style="width:600px; height:30px">
	<h3>■ 회원가입</h3>
	<p style="font-size: 10pt; text-align: right;"><span style="color: red; font-weight: bold">*</span> 표시는 반드시 입력하셔야 하는 항목입니다.</p>
	</div>
	<br>
	<form name="joinform" method="post">
		<div id="member" style="margin: 20px auto 10px; width:600px;min-height: 400px;">
		<table style="margin: 10px auto; width:600px; height:600px; border-spacing: 0px;
				border-top: 1px solid #dbdbdb;">
		
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 아이디</td>
			<td align="left" style="padding-left: 5px;">
				<input type="text" name="userId" id="userId" size="30" maxlength="10" class="boxTF" value="${dto.userId}">
				<button style="margin-left: 10px;" type="button" class="btn">중복확인</button>
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 비밀번호</td>
			<td align="left" style="padding-left: 5px;">
				<input type="password" name="userPwd" id="userPwd" size="30" maxlength="10" class="boxTF">
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 비밀번호확인</td>
			<td align="left" style="padding-left: 5px;">
				<input type="password" name="userPwd1" id="userPwd1" size="30" maxlength="10" class="boxTF">
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 이름</td>
			<td align="left" style="padding-left: 5px;">
				<input type="text" name="userName" id="userName" size="30" maxlength="10" class="boxTF" value="${dto.userName}">
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 생년월일</td>
			<td align="left" style="padding-left: 5px;">
				<input type="text" name="birth" size="30" maxlength="10" class="boxTF" value="${dto.birth}">
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left: 23px;" >이메일 </td>
			<td align="left" style="padding-left: 5px;">
			  <input type="text" name="email1" size="11" maxlength="30"  class="boxTF" value="${dto.email1}"> @ 
			  <input type="text" name="email2" size="11" maxlength="30"  class="boxTF" value="${dto.email2}" readonly="readonly">
			  <select name="selectEmail" class="selectField" onchange="changeEmail();">
					<option value="">선 택</option>
					<option value="naver.com" ${dto.email2=="naver.com" ? "selected='selected'" : ""}>naver.com</option>
					<option value="hanmail.net" ${dto.email2=="hanmail.net" ? "selected='selected'" : ""}>hanmail.net</option>
					<option value="gmail.com" ${dto.email2=="gmail.com" ? "selected='selected'" : ""}>gmail.com</option>
					<option value="direct">직접입력</option>
			  </select>
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left: 10px;" >
			<span style="color: red; font-weight: bold">*</span> 휴대폰번호
			</td>
			<td align="left" style="padding-left: 5px;">
					  <select name="tel1" class="selectField">
							<option value="">선 택</option>
							<option value="010" ${dto.telephone1=="010" ? "selected='selected'" : ""}>010</option>
							<option value="011" ${dto.telephone1=="011" ? "selected='selected'" : ""}>011</option>
							<option value="016" ${dto.telephone1=="016" ? "selected='selected'" : ""}>016</option>
							<option value="017" ${dto.telephone1=="017" ? "selected='selected'" : ""}>017</option>
					  </select>
					  <input type="text" name="tel2" size="8" maxlength="4" class="boxTF" value="${dto.telephone2}"> -
					  <input type="text" name="tel3" size="8" maxlength="4" class="boxTF" value="${dto.telephone3}" >
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left: 23px;"> 전화번호</td>
			<td align="left" style="padding-left: 5px;">
					  <select name="hp1" class="selectField">
							<option value="">선 택</option>
							<option value="02" ${dto.housephone1=="02" ? "selected='selected'" : ""}>02</option>
							<option value="031" ${dto.housephone1=="031" ? "selected='selected'" : ""}>031</option>
							<option value="032" ${dto.housephone1=="032" ? "selected='selected'" : ""}>032</option>
							<option value="070" ${dto.housephone1=="070" ? "selected='selected'" : ""}>070</option>
					  </select>
					  <input type="text" name="hp2" size="8" maxlength="4" class="boxTF" value="${dto.housephone2}"> -
					  <input type="text" name="hp3" size="8" maxlength="4" class="boxTF" value="${dto.housephone3}" >
			</td>
		</tr>
		<tr height="40">
				<td width="100" align="left" bgcolor="#F6F6F6" style="padding-left: 23px;">
				우편번호
				</td>
				<td align="left" style="padding-left: 5px;">
					<input type="text" name="zip" size="25" maxlength="7"  class="boxTF" readonly="readonly" value="${dto.zip}">
					<button type="button" class="btn">우편번호검색</button>
				</td>
		</tr>
		<tr height="70">
				<td width="100" align="left" bgcolor="#F6F6F6" style="padding-left: 23px; padding-top: 15px;" valign="top">
				상세주소
				</td>
				<td align="left" style="padding-left: 5px;">
					<span style="display: block; margin-top: 5px;">
						<input type="text" name="addr1" size="40" maxlength="50" class="boxTF" readonly="readonly" value="${dto.addr1}">
					</span>
					<span style="display: block; margin-top: 5px; margin-bottom: 5px;">
						<input type="text" name="addr2" size="40" maxlength="50"  class="boxTF" value="${dto.addr2}">
					</span>
				</td>
		</tr>
		<tr height="50">
               <td style="border-bottom: none;"align="center" colspan="2" >
	               <input type="button" value=" 회원가입 " class="btn" onclick="memberOk();">
	               <input type="reset" value=" 다시입력 " class="btn" onclick="document.memberForm.userId">
	               <input type="button" value=" 가입취소 " class="btn" onclick="javascript:location.href='<%=cp%>/';">
               </td>
        </tr>
		</table>
		</div>
	</form>
</div>
</body>
</html>