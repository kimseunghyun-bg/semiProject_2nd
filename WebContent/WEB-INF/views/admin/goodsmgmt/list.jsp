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
<title>Markito A Ecommerce Category Flat Bootstarp Resposive
	Website Template | Home :: w3layouts</title>
<link href="<%=cp%>/css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=cp%>/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="<%=cp%>/css/style.css" rel="stylesheet" type="text/css"
	media="all" />
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<meta name="keywords"
	content="Markito Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700'
	rel='stylesheet' type='text/css'>
<!--//fonts-->

<script type="text/javascript" src="<%=cp%>/js/move-top.js"></script>
<script type="text/javascript" src="<%=cp%>/js/easing.js"></script>
<script type="text/javascript">
	jQuery(document).ready(function($) {
		$(".scroll").click(function(event) {
			event.preventDefault();
			$('html,body').animate({
				scrollTop : $(this.hash).offset().top
			}, 1000);
		});
	});
</script>
<script type="text/javascript">
//소분류 보이기
function minorGroup(value){
	var minor=document.getElementById('minor');
	opts=minor.options;
	for(var opt, i=0; opt=opts[i]; i++){
		if(opt.value.indexOf("parent:"+value)!=-1) {
			//alert(opt.getAttribute("hidden"));
			//opt.setAttribute("hidden",false);
			opt.removeAttribute("hidden");
		}else{
			opt.setAttribute("hidden","true");
		}
	}
}
</script>
<script type="text/javascript">
//소분류 코드입력
function selectMinor(value){
	var foo=value.split("parent:");
	document.getElementsByName("kindCode")[0].value=foo[0]
}
</script>
<script type="text/javascript">
//검색
	function searchList() {
		var f=document.searchForm;
		f.action="<%=cp%>/admin/goodsmgmt/list.do";
		f.submit();
	}
</script>

<!-- start menu -->
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>

</head>
<body>
	<!--header-->
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/admin/layout/header.jsp"></jsp:include>
	</div>
	<div class="layoutBody">
		<div class="bodyFrame1" style="min-height: 450px;">

			<div class="container">

				<!--search-->
				<div style="height: 50px; padding: 10px;">
					<form name="searchForm" action="" method="post">
					<ul style="list-style: none;">
						<li style="width: 20%; float: left;">상품상태 : <select
							name="panmaeState">
								<option value="">전체</option>
								<option value="sell">판매</option>
								<option value="soldOut">품절</option>
								<option value="finish">판매종료</option>
						</select></li>

						<li style="width: 15%; float: left;">대분류 : <select
							name="groupCode" onchange="minorGroup(this.value);">
								<option value="">전체</option>
								<c:forEach var="dto" items="${groupList}">
									<c:if test="${empty dto.kindParent}">
										<option value="${dto.kindCode}">${dto.kindName}</option>
									</c:if>
								</c:forEach>
						</select></li>

						<li style="width: 20%; float: left;">소분류 : <select id="minor"
							onchange="selectMinor(this.value)">
								<option value="">전체</option>
								<c:forEach var="dto" items="${groupList}">
									<c:if test="${not empty dto.kindParent}">
										<option value="${dto.kindCode}parent:${dto.kindParent}"
											hidden="true">${dto.kindName}</option>
									</c:if>
								</c:forEach>
						</select> <input type="hidden" value="" name="kindCode">
						</li>

						<li style="width: 40%; float: left;"><select name="searchKey">
								<option value="">전체</option>
								<option value="name">상품명</option>
								<option value="produce_code">생산자</option>
						</select><input type="text" name="searchValue"><input type="button" value="검색" onclick="searchList()"></li>
						<li style="width: 5%; float: right;"><input type="button"
							value="신규등록"
							onclick="javascript:location.href='<%=cp%>/admin/goodsmgmt/create.do';">
						</li>
					</ul>
				    </form>
				</div>

				<!--content-->
				<c:forEach var="dto" items="${panmaeList}">
					<div class="list"
						onclick="javascript:location.href='<%=cp%>/admin/goodsmgmt/update.do?panmaeNum=${dto.panmaeNum}';">
						<div>
							<img src="<%=cp%>/images/admin/${dto.image}"
								style="width: 123px; height: 123px; max-width: 100%; max-height: 100%">
						</div>
						<div style="width: 1013px;">
							<div style="padding: 4px 2px;">
								<h3>${dto.name}</h3>
							</div>
							<div style="padding: 2px; font-size: 16px;">

								<div class="list_col">
									<ul>
										<li>상품번호 <label>${dto.panmaeNum}</label>
										</li>
										<li>대분류 <label>${dto.kindParentName}</label>
										</li>
										<li>소분류 <label>${dto.kindName}</label>
										</li>
									</ul>
								</div>

								<div class="list_col">
									<ul>
										<li>총 수량 <label>${dto.saveNum}</label></li>
										<li>판매수량 <label>${dto.sellNum}</label></li>
										<li>잔여수량 <label>${dto.saveNum-dto.sellNum}</label></li>
									</ul>
								</div>

								<div class="list_col">
									<ul>
										<li>등록일 <label>${dto.created}</label></li>
										<li>상품상태 <label> <c:choose>
													<c:when test="${dto.panmaeState=='sell'}">판매중</c:when>
													<c:when test="${dto.panmaeState=='soldOut'}">품절</c:when>
													<c:when test="${dto.panmaeState=='finish'}">상품단종</c:when>
												</c:choose>
										</label></li>
										<li>원가 <label>${dto.price}</label></li>
									</ul>
								</div>

								<div class="list_col">
									<ul>
										<li>생산자코드 <label>${dto.produceCode}</label></li>
										<li>생산자이름 <label>${dto.produceCorporName}</label></li>
										<li>생산자전화 <label>${dto.produceCorporNum}</label></li>
									</ul>
								</div>

							</div>
						</div>
					</div>
				</c:forEach>

				<div>
					<c:if test="${dataCount==0 }">
			                       등록된 게시물이 없습니다.
			                </c:if>
					<c:if test="${dataCount!=0 }">
			                    ${paging}
			                </c:if>
				</div>
			</div>
		</div>

	</div>

	<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/admin/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>
