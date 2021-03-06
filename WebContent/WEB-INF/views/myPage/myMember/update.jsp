<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String cp=request.getContextPath();
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
<%-- 
<script type="text/javascript" src="<%=cp%>js/move-top.js"></script>
<script type="text/javascript" src="<%=cp%>js/easing.js"></script>
 --%>
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
	 /* border-radius:4px; */
	 margin-bottom: 3px;
}
/* 버튼을 클릭할때 */
.btn:active, .btn:focus, .btn:hover { 
	 background-color:#F6F6F6;
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

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

<script type="text/javascript">
function updateOk() {
   var f = document.updateform;
   var str;
	
   // 아이디
   str = f.memberId.value;
   str = str.trim();
   if(!str) {
      alert("아이디를 입력하세요.");
      f.memberId.focus();
      return;
   }
   if(!/^[a-z][a-z0-9_]{4,9}$/i.test(str)) { 
      alert("아이디는 5~10자이며 첫글자는 영문자이어야 합니다.");
      f.memberId.focus();
      return;
   }
   f.memberId.value = str;

   // 비밀번호
   str = f.password.value;
   str = str.trim();
   if(!str) {
      alert("비밀번호를 입력하세요.");
      f.password.focus();
      return;
   }
   if(!/^(?=.*[a-z])(?=.*[!@#$%^*+=-]|.*[0-9]).{5,10}$/i.test(str)) { 
      alert("비밀번호는 5~10자이며 하나 이상의 숫자나 특수문자가 포함되어야 합니다.");
      f.password.focus();
      return;
   }
   f.password.value = str;

   if(str!= f.password1.value) {
        alert("비밀번호가 일치하지 않습니다. ");
        f.password1.focus();
        return;
   }
   
   // 이름
   str = f.name.value;
   str = str.trim();
    if(!str) {
        alert("이름을 입력하세요. ");
        f.name.focus();
        return;
    }
    f.name.value = str;

	// 생년월일*
    str = f.birth.value;
    str = str.trim();
    if(!str) {
        alert("생년월일을 입력하세요[YYYY-MM-DD]. ");
        f.birth.focus();
        return;
    }
    
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
    str = f.telephone1.value;
    str = str.trim();
    if(!str) {
        alert("휴대폰번호를 입력하세요. ");
        f.telephone1.focus();
        return;
    }

    str = f.telephone2.value;
   str = str.trim();
    if(!str) {
        alert("휴대폰번호를 입력하세요. ");
        f.telephone2.focus();
        return;
    }
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.telephone2.focus();
        return;
    }

    str = f.telephone3.value;
   str = str.trim();
    if(!str) {
        alert("휴대폰번호를 입력하세요. ");
        f.telephone3.focus();
        return;
    }
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.telephone3.focus();
        return;
    }
    
 	/* // 전화번호
    str = f.housephone1.value;
    str = str.trim();
    if(!str) {
        alert("전화번호를 입력하세요. ");
        f.housephone1.focus();
        return;
    }

    str = f.housephone2.value;
   str = str.trim();
    if(!str) {
        alert("전화번호를 입력하세요. ");
        f.housephone2.focus();
        return;
    }
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.housephone2.focus();
        return;
    }

    str = f.housephone3.value;
   str = str.trim();
    if(!str) {
        alert("전화번호를 입력하세요. ");
        f.housephone3.focus();
        return;
    } */
    if(!/^(\d+)$/.test(str)) {
        alert("숫자만 가능합니다. ");
        f.housephone3.focus();
        return;
    }    

       f.action = "<%=cp%>/myPage/myMember/update_ok.do";

    f.submit();
}

function changeEmail() {
    var f = document.updateform;
    
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

function sample6_execDaumPostcode() {
	new daum.Postcode({
	oncomplete: function(data) {

	var fullAddr = ''; 
	var extraAddr = ''; 

	if (data.userSelectedType === 'R') { 
	fullAddr = data.roadAddress;

	} else { 
	fullAddr = data.jibunAddress;
	}

	if(data.userSelectedType === 'R'){
	if(data.bname !== ''){
	extraAddr += data.bname;
	}
	 if(data.buildingName !== ''){
	extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	}
	fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
	}
	document.getElementById('sample6_postcode').value = data.zonecode; 
	document.getElementById('sample6_address').value = fullAddr;

	document.getElementById('sample6_address2').focus();
	}
	}).open();
}
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>

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
	<div style="margin: 10px auto; margin-top:50px; width:600px; height:700px;">
	
	<div style="width:600px; height:30px">
	<h3>■ 회원정보수정</h3>
	<p style="font-size: 10pt; text-align: right;"><span style="color: red; font-weight: bold">*</span> 표시는 반드시 입력하셔야 하는 항목입니다.</p>
	</div>
	<br>
	<form name="updateform" method="post">
		<div id="member" style="margin: 20px auto 10px; width:600px;min-height: 400px;">
		<table style="margin: 10px auto; width:600px; height:600px; border-spacing: 0px;
				border-top: 1px solid #dbdbdb;">
		
		<tr height="40">
			<td width="130" align="left" bgcolor="#F7F8FA" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 아이디</td>
			<td align="left" style="padding-left: 5px;">
				<input type="text" name="memberId" id="memberId" size="30" maxlength="10" class="boxTF" value="${dto.memberId}" readonly="readonly" style="border:none;  background-color:#F7F8FA;">
				
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 비밀번호</td>
			<td align="left" style="padding-left: 5px;">
				<input type="password" name="password" id="password" size="30" maxlength="10" class="boxTF">
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 비밀번호확인</td>
			<td align="left" style="padding-left: 5px;">
				<input type="password" name="password1" id="password1" size="30" maxlength="10" class="boxTF">
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F7F8FA" style="padding-left:10px;">
			<span style="color: red; font-weight: bold">*</span> 이름</td>
			<td align="left" style="padding-left: 5px;">
				<input type="text" name="name" id="name" size="30" maxlength="10" class="boxTF" value="${dto.name}" readonly="readonly" style="border: none; background-color:#F7F8FA; ">
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
					  <select name="telephone1" class="selectField">
							<option value="">선 택</option>
							<option value="010" ${dto.telephone1=="010" ? "selected='selected'" : ""}>010</option>
							<option value="011" ${dto.telephone1=="011" ? "selected='selected'" : ""}>011</option>
							<option value="016" ${dto.telephone1=="016" ? "selected='selected'" : ""}>016</option>
							<option value="017" ${dto.telephone1=="017" ? "selected='selected'" : ""}>017</option>
					  </select>
					  <input type="text" name="telephone2" size="8" maxlength="4" class="boxTF" value="${dto.telephone2}"> -
					  <input type="text" name="telephone3" size="8" maxlength="4" class="boxTF" value="${dto.telephone3}" >
			</td>
		</tr>
		<tr height="40">
			<td width="130" align="left" bgcolor="#F6F6F6" style="padding-left: 23px;"> 전화번호</td>
			<td align="left" style="padding-left: 5px;">
					  <select name="housephone1" class="selectField">
							<option value="">선 택</option>
							<option value="02" ${dto.housephone1=="02" ? "selected='selected'" : ""}>02</option>
							<option value="031" ${dto.housephone1=="031" ? "selected='selected'" : ""}>031</option>
							<option value="032" ${dto.housephone1=="032" ? "selected='selected'" : ""}>032</option>
							<option value="070" ${dto.housephone1=="070" ? "selected='selected'" : ""}>070</option>
					  </select>
					  <input type="text" name="housephone2" size="8" maxlength="4" class="boxTF" value="${dto.housephone2}"> -
					  <input type="text" name="housephone3" size="8" maxlength="4" class="boxTF" value="${dto.housephone3}" >
			</td>
		</tr>
		<tr height="40">
				<td width="100" align="left" bgcolor="#F6F6F6" style="padding-left: 23px;">
				우편번호
				</td>
				<td align="left" style="padding-left: 5px;">
					<input type="text" id="sample6_postcode" name="zip" size="25" maxlength="7"  class="boxTF" readonly="readonly" value="${dto.zip}">
					<button type="button" class="btn" onclick="sample6_execDaumPostcode()">우편번호검색</button>
				</td>
		</tr>
		<tr height="70">			
		
				<td width="100" align="left" bgcolor="#F6F6F6" style="padding-left: 23px; padding-top: 15px;" valign="top">
				상세주소
				</td>
				<td align="left" style="padding-left: 5px;">
					<span style="display: block; margin-top: 5px;">
						<input type="text" id="sample6_address"  name="addr1" size="40" maxlength="50" class="boxTF" readonly="readonly" value="${dto.addr1}">
					</span>
					<span style="display: block; margin-top: 5px; margin-bottom: 5px;">
						<input type="text" id="sample6_address2" name="addr2" size="40" maxlength="50"  class="boxTF" value="${dto.addr2}">
					</span>
				</td>
		</tr>
		<tr height="50">
               <td style="border-bottom: none;"align="center" colspan="2" >
	               <input type="button" value=" 회원정보수정 " class="btn" onclick="updateOk();">
	               <input type="reset" value=" 다시입력 " class="btn" onclick="document.updateform.memberId">
	               <input type="button" value=" 수정취소 " class="btn" onclick="javascript:location.href='<%=cp%>/myPage/myOrder/orderList.do';">
               </td>
        </tr>
		</table>
		</div>
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
