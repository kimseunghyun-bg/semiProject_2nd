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
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--fonts-->
<link href='http://fonts.googleapis.com/css?family=Amaranth:400,700'
	rel='stylesheet' type='text/css'>
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
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>$(document).ready(function(){$(".megamenu").megamenu();});</script>

<script src="<%=cp%>/js/simpleCart.min.js"> </script>

<script type="text/javascript">
function changePaystmt(value){
	if(value=='결제완료'){
		document.getElementsByName("payTotal")[0].removeAttribute("disabled");
	}else{
		document.getElementsByName("payTotal")[0].setAttribute("disabled","disabled");
	}
}
</script>

<script type="text/javascript">
function check() {
	var f = document.detailForm;
	var mode="${mode}";
	
	if(mode=='updatePayment'){
		
		if(f.payState.value=='결제완료'){
			
			var str = f.payTotal.value;
			if(!str) {
				alert("입금금액을 입력해주세요.");
				f.payTotal.focus();
				return false;
			}
			
			var str = f.payRoot.value;
			if(!str) {
				alert("결제방법을 입력해주세요.");
				f.payRoot.focus();
				return false;
			}
		}else if(f.payState.value=='입금대기'){
			
		}else {
			
			var str = f.payState.value;
			if(!str) {
				alert("결제상태를 입력해주세요.");
				f.payState.focus();
				return false;
			}
		}
			
		f.action="<%=cp%>/admin/ordermgmt/updatePayment_ok.do";
		
	}else if(mode=='updateArrive'){
		
		f.action="<%=cp%>/admin/ordermgmt/updateArrive_ok.do";
	}else if(mode=='updateDelivery'){
		
		f.action="<%=cp%>/admin/ordermgmt/updateDelivery_ok.do";
	}
	
}
        
</script>
<script>
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

<style type="text/css">
.aa{
	width: auto; padding:10px; margin-top: 40px; border: 1px solid gray;
}
.aa ul{
	font-size:16px; list-style: none;
}
.aa > ul{
	border-bottom: 1px solid black; padding: 10px 10px 5px 10px; margin: 0px 10px;
}
.aa > .jumunja{
	height: 91px;
}
.aa li{
	float: left; width: 25%; padding: 4px 0px; height: 38px;
}
.aa .sangsaeDiv li{
	width: 23%;
}
.aa .payStmt > li{
	width: 23%;
}
.aa label{
	padding-left: 16px;
}
.aa > .payStmt {
	height: 53px;
}
.aa > .arrive{
	height: 129px;
}
.aa > .arrive li{
	width: 33%;
}
.refund li{
	width: 23%;
}
.aa > .sangsaeDiv > .ul{

}
</style>
</head>
<body>
	<!--header-->
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	<div class="layoutBody">
		<div class="bodyFrame1" style="min-height: 450px;">
			<div class="container">
				
				<form name="detailForm" method="post" onsubmit="return check();">
				<div class="aa">
					<!-- 주문자 정보 -->
					<ul class="jumunja">
						<li>주문번호<label>${dto.jumunNum}</label><input name="jumunNum" type="hidden" value="${dto.jumunNum}"><input name="page" type="hidden" value="${page}"></li>
						<li>이름(ID)<label>${dto.memberName}(${dto.memberId})</label></li>
						<li>등급<label>${dto.rankName}</label></li>
						<li>주문일자<label>${dto.created}</label></li>
						<li>전체금액<label>${dto.orderTotalpay}</label></li>
						<li>전화번호<label>${dto.telePhone}</label></li>
						<li>이메일<label>${dto.email}</label></li>
						<li style="width: 8%; float: right;">
						<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="button" value="주문취소" onclick="">
						</li>
					</ul>
					
					<!-- 결제상태 -->
					<ul class="payStmt">
						<li>결제상태
						<c:choose>
							<c:when test="${mode=='updatePayment'}">
								<select name="payState" style="height: 28px; font-size: 15px;" onchange="changePaystmt(this.value);"><option value="">선택</option>
									<option value="입금대기">입금대기</option>
									<option value="결제완료">결제완료</option>
								</select>
							</c:when>
							<c:otherwise>
								<label>${dto.payState}</label>
							</c:otherwise>
						</c:choose>
						</li>
						<li>입금금액
						<c:choose>
							<c:when test="${mode=='updatePayment'}">
								<input name="payTotal" type="text" style="width: 50%; height: 28px;" disabled="disabled">
							</c:when>
							<c:otherwise>
								<label>${dto.payTotal}</label>
							</c:otherwise>
						</c:choose>
						</li>
						<li>결제일자<label>${dto.payCreated}</label></li>
						<li>결제방법
						<c:choose>
							<c:when test="${mode=='updatePayment'}">
								<select name="payRoot" style="height: 28px;">
									<option value="">선택</option>
									<option value="신용카드" ${dto.payRoot=="신용카드"?"selected='selected'":"" }>신용카드</option>
									<option value="계좌이체" ${dto.payRoot=="계좌이체"?"selected='selected'":"" }>계좌이체</option>
									<option value="무통장입금" ${dto.payRoot=="무통장입금"?"selected='selected'":"" }>무통장입금</option>
								</select>
							</c:when>
							<c:otherwise>
								<label>${dto.payRoot}</label>
							</c:otherwise>
						</c:choose>
						</li>
						<li style="width: 8%;">
						<c:choose>
							<c:when test="${mode=='updatePayment'}">
								<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="submit" value="수정완료">
							</c:when>
							<c:otherwise>
								<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="button" value="결제수정" onclick="javascript:location.href='<%=cp%>/admin/ordermgmt/updatePayment.do?jumunNum=${dto.jumunNum}&page=${page}';">
							</c:otherwise>
						</c:choose>
						</li>
					</ul>
					
					<!-- 배송지정보 -->
					<ul class="arrive">
						<li>받는사람
						<c:choose>
							<c:when test="${mode=='updateArrive'}">
								<input value="${dto.sendName}" type="text" name="sendName" required="required" style="width: 100px; height: 28px; margin-left: 16px;">
							</c:when>
							<c:otherwise>
								<label>${dto.sendName}</label>
							</c:otherwise>
						</c:choose>
						</li>
						<li>전화번호
						<c:choose>
							<c:when test="${mode=='updateArrive'}">
								<input value="${dto.phone_1}" type="text" name="phone_1" required="required" style="width: 60px; height: 28px; margin: 0px 8px;">-
								<input value="${dto.phone_2}" type="text" name="phone_2" required="required" style="width: 60px; height: 28px; margin: 0px 8px;">-
								<input value="${dto.phone_3}" type="text" name="phone_3" required="required" style="width: 60px; height: 28px; margin: 0px 8px;">
							</c:when>
							<c:otherwise>
								<label>${dto.phone_1} - ${dto.phone_2} - ${dto.phone_3}</label>
							</c:otherwise>
						</c:choose>
						</li>
						<li style="float: right; width: 8%;">
						<c:choose>
							<c:when test="${mode=='updateArrive'}">
								<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="submit" value="수정완료">
							</c:when>
							<c:otherwise>
								<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="button" value="배송지수정" onclick="javascript:location.href='<%=cp%>/admin/ordermgmt/updateArrive.do?jumunNum=${dto.jumunNum}&page=${page}';">
							</c:otherwise>
						</c:choose>
						</li>
						<li style="width: 100%;">
						우편번호
						<c:choose>
							<c:when test="${mode=='updateArrive'}">
								<input value="${dto.zip}" type="text" name="zip" required="required" style="width: 150px; height: 28px; margin-left: 16px;" id="sample6_postcode" placeholder="우편번호">
							</c:when>
							<c:otherwise>
								<input value="${dto.zip}" readonly="readonly" type="text" name="zip" required="required" style="width: 150px; height: 28px; margin-left: 16px; border: 0px; background: none;" id="sample6_postcode" placeholder="우편번호">
							</c:otherwise>
						</c:choose>
						<c:if test="${mode=='updateArrive'}">
						<input type="button" value="우편번호검색 " style="height: 28px  ;font-weight : normal; margin-left: 16px;"  onclick="sample6_execDaumPostcode()" >
						</c:if> 
						
						<li style="width: 100%;">
						주 소
						<c:choose>
							<c:when test="${mode=='updateArrive'}">
								<input value="${dto.addr1}" type="text" name="addr1" required="required" style="width: 40%; height: 28px; margin-left: 40px;" id="sample6_address" placeholder="주소">&nbsp;&nbsp;-&nbsp;&nbsp;
                    			<input value="${dto.addr2}" type="text" name="addr2" required="required" style="width: 40%; height: 28px;" id="sample6_address2" placeholder="상세주소">
                    		</c:when>
							<c:otherwise>
								<input value="${dto.addr1}, ${dto.addr2}" readonly="readonly" type="text" name="addr1" required="required" style="width: 90%; height: 28px; margin-left: 40px; border: 0px; background: none;">
                    		</c:otherwise>
						</c:choose>
						</li>
					</ul>
					
					<!-- 주문상세 -->
					<div class="sangsaeDiv" style="border-bottom: 1px solid black; padding: 5px;">
						<!-- 주문상세 -->
						<c:forEach var="subdto" items="${list}">
							<div style="height: 110px; width: 90%; border: 1px solid gray; margin: 10px auto;">
								<ul>
									<li>상품번호<label>${subdto.panmaeNum}</label><input type="hidden" value="${subdto.panmaeNum}" name="panmaeNum"></li>
									<li>상품명<label>${subdto.panmaeName}</label></li>
									<li>대분류<label>${subdto.kindParentName}</label></li>
									<li>소분류<label>${subdto.kindName}</label></li>
									<li>배송상태
									<c:choose>
										<c:when test="${subdto.panmaeNum==param.panmaeNum && mode=='updateDelivery'}">
											<select name="sendState" style="margin-left: 16px;">
											<option value="">미배송</option>
											<option value="배송중" ${dto.payRoot=="배송중"?"selected='selected'":"" }>배송중</option>
											<option value="배송완료" ${dto.payRoot=="배송완료"?"selected='selected'":"" }>배송완료</option>
											</select>
										</c:when>
										<c:otherwise>
											<label>${subdto.sendState}</label>
										</c:otherwise>
									</c:choose>
									</li>
									<li>주문수량<label>${subdto.sellNum}</label></li>
									<li>판매금액<label>${subdto.sellPrice}</label></li>
									<li>합계금액<label>${subdto.totalPay}</label></li>
									<li style="width: 8%; float: right;">
									<c:choose>
										<c:when test="${subdto.panmaeNum==param.panmaeNum && mode=='updateDelivery'}">
											<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="submit" value="수정완료">
										</c:when>
										<c:otherwise>
											<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="button" value="배송수정" onclick="javascript:location.href='<%=cp%>/admin/ordermgmt/updateDelivery.do?jumunNum=${dto.jumunNum}&panmaeNum=${subdto.panmaeNum}&page=${page}';">
										</c:otherwise>
									</c:choose>
									</li>
								</ul>
								<!-- 배송정보 -->
								<ul>
									<li>배송업체<label>${subdto.sendCorporName}</label></li>
									<li>업체번호<label>${subdto.sendPhoneNum}</label></li>
								</ul>
								<!-- 반품정보 -->
								<ul>
									<li>반품사유<label>${subdto.reason}</label></li>
									<li>반품일자<label>${subdto.returnCreated}</label></li>
									<li style="width: 8%; float: right;">
									<c:choose>
										<c:when test="${mode=='updatePayment'}">
											<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="submit" value="수정완료">
										</c:when>
										<c:otherwise>
											<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="button" value="반품처리" onclick="">
										</c:otherwise>
									</c:choose>
									</li>
								</ul>
							</div>
						</c:forEach>
					</div>
					
					<!-- 환불정보 -->
					<ul class="refund" style="height: 45px;">
						<li>환불액<label>${dto.returnMoney}</label></li>
						<li>환불일자<label>${dto.returnPayCreated}</label></li>
						<li>은행명<label>${dto.bankName}</label></li>
						<li>환불계좌<label>${dto.bankNumber}</label></li>
						<li style="width: 8%; float: right;">
							<c:choose>
								<c:when test="${mode=='updatePayment'}">
									<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="submit" value="수정완료">
								</c:when>
								<c:otherwise>
									<input style="min-width:80%; height: 28px; padding: 0px 5px;" type="button" value="환불처리" onclick="">
								</c:otherwise>
							</c:choose>
						</li>
					</ul>
					
					<!-- 버튼메뉴 -->
					<ul style="height: 45px; border: none;">
						<li style="width: 100%; text-align: center;">
							<input type="button" value="뒤로" onclick="javascript:location.href='<%=cp%>/admin/ordermgmt/list.do?page=${page}';">&nbsp;&nbsp;
							<input type="hidden" value="주문취소" onclick="">&nbsp;&nbsp;
						<li><input type="hidden" value="주문상세수정" onclick=""></li>
						<li><input type="hidden" value="배송상태수정" onclick=""></li>
					</ul>
				</div>
				</form>
				
			</div>
		</div>
	</div>

	<!--footer-->
	<div class="layoutfooter">
		<jsp:include page="/WEB-INF/views/layout/footer.jsp"></jsp:include>
	</div>
</body>
</html>