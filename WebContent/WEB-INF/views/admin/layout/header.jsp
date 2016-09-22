<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String cp = request.getContextPath();
%>
<script src="<%=cp%>/js/jquery.jclock-1.2.0.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function($) {
		$('.jclock').jclock();
	});
</script>

<div class="header">
	<div class="header-top">
		<div class="container">
			<div class="head-top">
				<div class="logo">
					<a href="<%=cp%>/admin.do"><img src="<%=cp%>/images/logo.png"
						alt=""></a>
				</div>
				<div class="top-nav">
					<ul class="megamenu skyblue">
						<li class="active grid"><a
							href="<%=cp%>/admin/goodsmgmt/list.do">상품관리</a></li>
						<li class="grid"><a href="#">주문관리</a></li>
						<li class="grid"><a href="#">QnA</a></li>
						<li class="grid"><a href="#">통계</a></li>
						<li class="grid"><a href="#"></a></li>
					</ul>
				</div>
				<div class="cart box_1" style="font-size: 16px;">
					<div class="jclock"></div>
				</div>

				<div class="clearfix"></div>
			</div>
		</div>
	</div>
</div>

