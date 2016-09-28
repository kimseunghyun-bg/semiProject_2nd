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
<meta charset="UTF-8">
<title>study</title>

<!-- css -->

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

<!-- css -->

<link rel="stylesheet" href="<%=cp%>/css/styleKim.css" type="text/css">
<link rel="stylesheet" href="<%=cp%>/css/layout.css" type="text/css">

<script type="text/javascript">
    function check() {
        var f = document.boardForm;

    	var str = f.subject.value;
        if(!str) {
            alert("제목을 입력하세요. ");
            f.subject.focus();
            return false;
        }

    	str = f.content.value;
        if(!str) {
            alert("내용을 입력하세요. ");
            f.content.focus();
            return false;
        }

    	var mode="${mode}";
    	if(mode=="created")
    		f.action="<%=cp%>/myPage/myQnA/created_ok.do";
    	else if(mode=="update")
    		f.action="<%=cp%>/myPage/myQnA/update_ok.do";
    	else if(mode=="reply"){
    		f.action="<%=cp%>/myPage/myQnA/reply_ok.do";
    	}

    	// image 버튼, submit은 submit() 메소드 호출하면 두번전송
        return true;
    }
</script>
</head>
<body>

<div style="background-color: #CEFBC9;" class="layoutMain">
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	
	<div class="layoutBody">

		<div style="min-height: 650px;">
		<div style="width: 100%; height: 30px;"></div>
				<div style="width:100%;	height: 40px; line-height:40px;clear: both; border-top: 1px solid #DAD9FF;border-bottom: 1px solid #DAD9FF;">
				    <div style="width:800px; height:30px; line-height:30px; margin:5px auto;">
				        <img src="<%=cp%>/images/arrow.jpg" alt="" style="padding-left: 5px; padding-right: 5px;">
				        <span style="font-weight: bold;font-size:13pt;font-family: 나눔고딕, 맑은 고딕, 굴림;">질문과 답변</span>
				    </div>
				</div>
			
				<div style="margin: 10px auto; margin-top: 20px; width:800px; min-height: 400px;">
		
					<form name="boardForm" method="post" onsubmit="return check();">
					  <table style="width: 800px; margin: 0px auto; border-spacing: 0px;">
					  <tr><td colspan="2" height="3" bgcolor="#2F9D27"></td></tr>
					
					  <tr align="left" height="40"> 
					      <td width="100" bgcolor="#93CC8D" style="text-align: center;">제&nbsp;&nbsp;&nbsp;&nbsp;목</td>
					      <td width="700" style="padding-left:10px;"> 
					        <input style="background: #CEFBC9;" type="text" name="subject" size="100" maxlength="100" class="boxTF" value="${dto.subject}">
					      </td>
					  </tr>
					  <tr><td colspan="2" height="1" bgcolor="#2F9D27"></td></tr>
					  
					  <tr align="left" height="40"> 
					      <td width="100" bgcolor="#93CC8D" style="text-align: center;">작 성 자</td>
					      <td width="700" style="padding-left:10px;"> 
					        ${sessionScope.member.name}
					      </td>
					  </tr>
				      <tr><td colspan="2" height="1" bgcolor="#2F9D27"></td></tr>
				      
					  <tr align="left"> 
					      <td width="100" bgcolor="#93CC8D" style="text-align: center; padding-top:5px;" valign="top">내&nbsp;&nbsp;&nbsp;&nbsp;용</td>
					      <td width="700" valign="top" style="padding:5px 0px 5px 10px;"> 
					        <textarea style="background: #CEFBC9;" name="content" cols="100" rows="50" class="boxTA">${dto.content}</textarea>
					      </td>
					  </tr>
					  <tr><td colspan="2" height="3" bgcolor="#2F9D27"></td></tr>
					  </table>
					
					  <table style="width: 800px; margin: 0px auto; border-spacing: 0px;">
					     <tr height="45"> 
					      <td align="center" >
						    <input type="image" src="<%=cp%>/images/btn_submit.jpg" >
		        		    <a href="javascript:location.href='<%=cp%>/myPage/myQnA/list.do';">
		        		    <img src="<%=cp%>/images/btn_cancel.jpg" border="0">
		        		    </a>
							
							<c:if test="${mode=='update'}">
								<input type="hidden" name="num" value="${dto.num}">
								<input type="hidden" name="page" value="${page}">
							</c:if>
							<c:if test="${mode=='reply'}">
								<input type="hidden" name="groupNum" value="${dto.groupNum}">
								<input type="hidden" name="orderNo" value="${dto.orderNo}">
								<input type="hidden" name="depth" value="${dto.depth}">
								<input type="hidden" name="parent" value="${dto.num}">
								<input type="hidden" name="page" value="${page}">
							</c:if>
					      </td>
					    </tr>
					  </table>
					</form>
				</div>
		</div>

    </div>
	
	<div class="layoutFooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>