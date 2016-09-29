<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
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
<link rel="stylesheet" href="<%=cp%>/css/layout.css"
	type="text/css">


<script type="text/javascript">
	function deleteBoard(){
		var num="${dto.num}";
		var page="${page}";
		var params="num="+num+"&page="+page;
		var url="<%=cp%>/boardNotice/delete_ok.do?"+params;
		
		if(confirm("위 자료를 삭제하시겠습니까?"))
			location.href=url;
	}

	function updateBoard(){
		var num="${dto.num}";
		var page="${page}";
		var params="num="+num+"&page="+page;
		var url="<%=cp%>/boardNotice/update.do?"+params;

		location.href = url;
	}
</script>

</head>
<body>

	<div style="background-color: #FFFFFF;" class="layoutMain">
		<div class="layoutHeader">
			<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
		</div>

		<div class="layoutBody">

			<div style="min-height: 650px;">
			<div style="width: 100%; height: 30px;"></div>
				<div
					style="width: 100%; height: 40px; line-height: 40px; clear: both; border-top: 1px solid #DAD9FF; border-bottom: 1px solid #DAD9FF;">
					<div
						style="width: 800px; height: 30px; line-height: 30px; margin: 5px auto;">
						<img src="<%=cp%>/images/arrow.jpg" alt=""
							style="padding-left: 5px; padding-right: 5px;"> <span
							style="font-weight: bold; font-size: 13pt; font-family: 나눔고딕, 맑은 고딕, 굴림;">공지사항</span>
					</div>
				</div>

				<div
					style="margin: 10px auto; margin-top: 20px; width: 800px; min-height: 400px;">
					<table style="width: 800px; margin: 0px auto; border-spacing: 0px;">

						<tr height="35">
							<td style="font-size: 18px; font-weight: bold;" colspan="4" align="center">
								${dto.subject}
							</td>
						</tr>
						<tr>
							<td colspan="4" height="1" bgcolor="#2F9D27"></td>
						</tr>

						<tr height="30">
							<td width="80" bgcolor="#98F791" align="center">작성자</td>
							<td width="320" align="left" style="padding-left: 10px;">
								${dto.name}</td>
							<td width="80" height="30" bgcolor="#98F791" align="center">줄&nbsp;&nbsp;수</td>
							<td width="320" align="left" style="padding-left: 10px;">
								${linesu}</td>
						</tr>
						<tr>
							<td colspan="4" height="1" bgcolor="#2F9D27"></td>
						</tr>

						<tr height="30">
							<td width="80" bgcolor="#98F791" align="center">등록일</td>
							<td width="320" align="left" style="padding-left: 10px;">
								${dto.created}</td>
							<td width="80" bgcolor="#98F791" align="center">조회수</td>
							<td width="320" align="left" style="padding-left: 10px;">
								${dto.hitCount}</td>
						</tr>
						<tr>
							<td colspan="4" height="1" bgcolor="#2F9D27"></td>
						</tr>

						<tr>
							<td colspan="4" align="left"
								style="padding: 15px 30px 15px 30px;" valign="top" height="300">
								${dto.content}</td>
						</tr>
						<tr>
							<td colspan="4" height="1" bgcolor="#2F9D27"></td>
						</tr>

						<tr height="30">
							<td width="80" bgcolor="#98F791" align="center">첨 부</td>
							<td width="720" align="left" style="padding-left: 10px;"
								colspan="3">
								<!-- 주의 : 파일 업로드 미완성 --> <c:if
									test="${not empty dto.saveFilename}">
									<a href="<%=cp%>/boardNotice/download.do?num=${dto.num}">${dto.originalFilename}</a>
								</c:if>
							</td>
						</tr>
						<tr>
							<td colspan="4" height="1" bgcolor="#2F9D27"></td>
						</tr>

						<tr height="30">
							<td width="80" bgcolor="#98F791" align="center">이전글</td>
							<td width="720" align="left" style="padding-left: 10px;"
								colspan="3"><c:if test="${not empty preReadDTO}">
									<a
										href="<%=cp%>/boardNotice/article.do?num=${preReadDTO.num}&${params}">${preReadDTO.subject}</a>
								</c:if></td>
						</tr>
						<tr>
							<td colspan="4" height="1" bgcolor="#2F9D27"></td>
						</tr>

						<tr height="30">
							<td width="80" bgcolor="#98F791" align="center">다음글</td>
							<td width="720" align="left" style="padding-left: 10px;"
								colspan="3"><c:if test="${not empty nextReadDTO}">
									<a
										href="<%=cp%>/boardNotice/article.do?num=${nextReadDTO.num}&${params}">${nextReadDTO.subject}</a>
								</c:if></td>
						</tr>
						<tr>
							<td colspan="4" height="1" bgcolor="#2F9D27" align="center"></td>
						</tr>
					</table>

					<table style="width: 800px; margin: 0px auto; border-spacing: 0px;">
						<tr height="35">
							<td width="50%" align="left">
							
							<c:if test="${sessionScope.member.memberId=='admin'}">
								<input type="image" src="<%=cp%>/images/btn_modify.jpg" 
								onclick="updateBoard();">
							</c:if>
							<c:if test="${sessionScope.member.memberId=='admin'}">
								<input type="image" src="<%=cp%>/images/btn_delete.jpg" 
								onclick="deleteBoard();">
							</c:if>	
							</td>
							
							<td align="right"><input type="image"
								src="<%=cp%>/images/btn_list.jpg" 
								onclick="javascript:location.href='<%=cp%>/boardNotice/list.do';">
							</td>
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