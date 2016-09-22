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
	function searchList() {
		var f=document.searchForm;
		f.action="<%=cp%>/boardFAQ/list.do";
		f.submit();
	}
</script>

</head>
<body>

<div style="background-color: #CEFBC9;" class="layoutMain">
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	
	<div class="layoutBody">

		<div style="min-height: 450px;">
				<div style="width:100%;	height: 40px; line-height:40px;clear: both;
				 border-top: 1px solid #DAD9FF;border-bottom: 1px solid #DAD9FF;">
				    <div style="width:700px; height:30px; line-height:30px; margin:5px auto;">
				        <img src="<%=cp%>/images/arrow.jpg" alt="" style="padding-left: 5px; padding-right: 5px;">
				        <span style="font-weight: bold;font-size:11pt;font-family: 나눔고딕, 맑은 고딕, 굴림;">자주하는 질문</span>
				    </div>
				</div>
			
				<div style="margin: 10px auto; margin-top: 20px; width:700px; min-height: 400px;">
				
					<table style="width: 300px; margin: 0px auto; border-spacing: 1px;">
						<tr align="center" bgcolor="#2F9D27" height="25">
							<td width="100">
								<img src="<%=cp%>/images/BoardFAQPoint.jpg">
								<a href="<%=cp%>/boardFAQ/list.do">전&nbsp;&nbsp;&nbsp;&nbsp;체</a>
							</td>
							<td width="100">
								<img src="<%=cp%>/images/BoardFAQPoint.jpg">
								<a href="<%=cp%>/boardFAQ/list.do?searchKey=subject&searchValue=회원가입">회원가입</a>
							</td>
							<td width="100">
								<img src="<%=cp%>/images/BoardFAQPoint.jpg">
								<a href="<%=cp%>/boardFAQ/list.do?searchKey=subject&searchValue=회원탈퇴">회원탈퇴</a>
							</td>
						</tr>
						<tr align="center" bgcolor="#2F9D27" height="25">
							<td width="100">
								<img src="<%=cp%>/images/BoardFAQPoint.jpg">
								<a href="<%=cp%>/boardFAQ/list.do?searchKey=subject&searchValue=개인정보">개인정보</a>
							</td>
							<td width="100">
								<img src="<%=cp%>/images/BoardFAQPoint.jpg">
								<a href="<%=cp%>/boardFAQ/list.do?searchKey=subject&searchValue=물품구매">물품구매</a>
							</td>
							<td width="100">
								<img src="<%=cp%>/images/BoardFAQPoint.jpg">
								<a href="<%=cp%>/boardFAQ/list.do?searchKey=subject&searchValue=구매취소">구매취소</a>
							</td>
						</tr>
					</table><br>
		
					<table style="width: 700px; margin: 0px auto; border-spacing: 0px;">
					  <tr align="center" bgcolor="#2F9D27" height="30"> 
					      <td width="60" style="color: #ffffff;">번호</td>
					      <td width="400" style="color: #ffffff;">제목</td>
					      <td width="100" style="color: #ffffff;">작성자</td>
					      <td width="80" style="color: #ffffff;">작성일</td>
					      <td width="60" style="color: #ffffff;">조회수</td>
					  </tr>
					 
					 <c:forEach var="dto" items="${list}">
					  <tr align="center" bgcolor="#CEFBC9" height="30"> 
					      <td align="center">${dto.listNum}</td>
					      <td align="left" style="padding-left: 10px;">
					          <a href='${articleUrl}&num=${dto.num}'>${dto.subject}</a>
					      </td>
					      <td align="center">${dto.name}</td>
					      <td align="center">${dto.created}</td>
					      <td align="center">${dto.hitCount}</td>
					  </tr>
					  <tr><td height="1" colspan="5" bgcolor="#65D35D"></td></tr> 
					 </c:forEach>
					</table>
							
					<table style="width: 700px; margin: 0px auto; border-spacing: 0px;">
					   <tr height="35">
						<td align="center">
					        <c:if test="${dataCount==0 }">
			                                        등록된 게시물이 없습니다.
			                </c:if>
			                <c:if test="${dataCount!=0 }">
			                    ${paging}
			                </c:if>
						</td>
					   </tr>
					</table>
					
					<table style="width: 700px; margin: 0px auto; border-spacing: 0px;">
					   <tr height="35">
					      <td align="center" width="100%">
					          <form name="searchForm" action="" method="post">
					              <select style="background: #2F9D27; color: #FFFFFF;" name="searchKey" class="selectField">
									<option value="subject">제목</option>
									<option value="userName">작성자</option>
									<option value="content">내용</option>
									<option value="created">등록일</option>
					            </select>
					            <input style="background: #CEFBC9;" type="text" name="searchValue" class="boxTF">
					            <input style="background: #2F9D27; color: #FFFFFF;" type="button" value=" 검 색 " class="btn" onclick="searchList()">
					        </form>
					      </td>
					   </tr>
					   
					   <tr>
					   	  <c:if test="${sessionScope.member.memberId=='admin'}">
						      <td align="right">
						          <input type="image" src="<%=cp%>/images/btn_write.jpg" 
						          onclick="javascript:location.href='<%=cp%>/boardFAQ/created.do';">
						      </td>
					      </c:if>
					   </tr>
					</table>
				</div>
		</div>

    </div>
	
	<div class="layoutFooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
</div>

</body>
</html>