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
<!-- start menu -->
<link href="<%=cp%>/css/megamenu.css" rel="stylesheet" type="text/css"
	media="all" />
<script type="text/javascript" src="<%=cp%>/js/megamenu.js"></script>
<script>
	$(document).ready(function() {
		$(".megamenu").megamenu();
	});
</script>

<script type="text/javascript">
//이미지 미리보기
function readURL(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $('#preview').attr('src', e.target.result);
        }
        reader.readAsDataURL(input.files[0]);
    }
}
</script>

<script type="text/javascript">
function inpProduceCode(){
	//생산자 선택에 따른 생산자코드, 전화번호, 주소 변경 함수
	var value=document.getElementById("producerId").value;
	var foo=value.split(':');
	for(var i=0; i<3; i++){
		if(foo[i]==null){
			foo[i]='';
		}
	}
	if(foo[0].length!=0){
		var code='&nbsp;&nbsp;&nbsp;&nbsp;'+foo[0];
	}else{
		code='';
	}
	
	document.getElementsByName("producer")[0].value=foo[0];
	$("#produceCodeId").html(code);
	$("#producerTelId").html(foo[1]);
	$("#producerAddrId").html(foo[2]);
}
</script>

<script type="text/javascript">
//소분류 보이기
function minorGroup(value){
	var minor=document.getElementById('minor');
	opts=minor.options;
	for(var opt, i=0; opt=opts[i]; i++){
		if(opt.value.indexOf("parent:"+value)!=-1) {
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
	document.getElementsByName("kindCode")[0].value=foo[0];
	$("#kindCode").html("&nbsp;&nbsp;&nbsp;(&nbsp;"+foo[0]+"&nbsp;)");
}
</script>

<script type="text/javascript">
    function check() {
        var f = document.goodsForm;
        
    	var str = f.subject.value;
        if(!str) {
            alert("제목을 입력하세요. ");
            f.subject.focus();
            return false;
        }
    	str = f.amount.value;
        if(!str) {
            alert("수량을 입력하세요. ");
            f.amount.focus();
            return false;
        }
        
        var mode="${mode}";
    	if(mode=="create"){
	    	str = f.kindCode.value;
	        if(!str) {
	            alert("분류를 선택하세요. ");
	            f.kindCode.focus();
	            return false;
	        }
    		
	    	str = f.price.value;
	        if(!str) {
	            alert("가격을 입력하세요. ");
	            f.price.focus();
	            return false;
	        }
	        
	    	str = f.producer.value;
	        if(!str) {
	            alert("생산자를 입력하세요. ");
	            f.producer.focus();
	            return false;
	        }
	        
    		f.action="<%=cp%>/admin/goodsmgmt/create_ok.do";
    	}else if(mode=="update")
    		f.action="<%=cp%>/admin/goodsmgmt/update_ok.do";
    	return true;
    }
</script>

</head>
<body>
	<!--header-->
	<div class="layoutHeader">
		<jsp:include page="/WEB-INF/views/layout/header.jsp"></jsp:include>
	</div>
	
	<!--body-->
	<div class="layoutBody">
		<div class="bodyFrame1" style="min-height: 450px;">
			<div class="container" style="margin-top: 50px;">
				<form name="goodsForm" method="post" onsubmit="return check();" enctype="multipart/form-data">
					<ul	style="list-style: none; width: 375px; height: 425px; float: left;">
						<c:if test="${mode=='create'}">
							<li><img id="preview" src="" style="width: 375px; height: 375px;"></li>
						</c:if>
						<c:if test="${mode=='update'}">
							<li><img id="preview" src="<%=cp%>/images/panmaeImg/${dto.image}" style="width: 375px; height: 375px;"></li>
						</c:if>
						<li><input type="file" name="picture" class="boxTF" size="40" id="pictureID" onchange="readURL(this);"></li>
					</ul>
					<ul style="list-style: none; font-size: 16px; float: left; width: auto; height:425px; padding-left: 20px;">
						<li style="float: left; margin: 10px 10px 20px 0px;">상품명&nbsp;&nbsp;<input type="text" name="subject" size="80px;" maxlength="100" value="${dto.name}"></li>
						<li style="clear: both; float: left; width: 25%; margin: 10px 0px 20px 0px;">상품번호&nbsp;&nbsp;
							<c:if test="${mode=='create'}">
								<label>신규상품</label>
							</c:if>
							<c:if test="${mode=='update'}">
								<label>${dto.panmaeNum}</label>
								<input type="hidden" value="${dto.panmaeNum}" name="panmaeNum">
							</c:if>
						</li>
						<li style="float: left; width: 25%; margin: 10px 0px 20px 0px;">대분류  
							<c:if test="${mode=='create'}">
								<select onchange="minorGroup(this.value);"><option>::선택::</option>
									<c:forEach var="groupdto" items="${groupList}">
										<c:if test="${empty groupdto.kindParent}">
											<option value="${groupdto.kindCode}">${groupdto.kindName}</option>
										</c:if>
									</c:forEach>
								</select>
							</c:if>
							<c:if test="${mode=='update'}">
								<label>&nbsp;&nbsp;${dto.kindParentName}</label>
							</c:if>
						</li>
						<li style="float: left; width: 25%; margin: 10px 0px 20px 0px;">소분류&nbsp;&nbsp;
							<c:if test="${mode=='create'}">
								<select id="minor" onchange="selectMinor(this.value)">
									<option>::선택::</option>
									<c:forEach var="groupdto" items="${groupList}">
										<c:if test="${not empty groupdto.kindParent}">
											<option value="${groupdto.kindCode}parent:${groupdto.kindParent}" hidden="true">${groupdto.kindName}</option>
										</c:if>
									</c:forEach>
								</select>
								<input type="hidden" value="" name="kindCode">
							</c:if>
							<c:if test="${mode=='update'}">
								<label>&nbsp;&nbsp;${dto.kindName}</label>
							</c:if>
						</li>
						<li style="float: left; width: 25%; margin: 10px 0px 20px 0px;">분류코드<label id="kindCode">&nbsp;&nbsp;${dto.kindCode}</label></li>
						<li style="clear:both; width: 100%; margin: 20px 0px 10px 0px; text-align: left;">상품소개</li>
						<li style="width: 710px; height:225px">
							<textarea name="introduce" style="resize:none; width: 100%; height:100%;">${dto.introduce}</textarea>
						</li>
					</ul >
					<ul style="clear:both; list-style: none; font-size: 16px; float: left; width: 25%; padding-top: 20px;">
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">누적&nbsp;입고&nbsp;수량</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">${dto.saveNum}</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">누적&nbsp;판매&nbsp;수량</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">${dto.sellNum}</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">재고&nbsp;수량</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">${dto.saveNum-dto.sellNum}</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">추가&nbsp;수량</li>
						<li><input name="amount" type="text" style="margin: 5px 40px 5px 0px; width: 25%; text-align: right; float: right;" value="0"></li>
					</ul>
					<ul style="list-style: none; font-size: 16px; float: left; width: 25%; padding-top: 20px;">
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">등록일</li>
						<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;">${dto.created}</li>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">상품정가</li>
						<c:if test="${mode=='create'}">
							<input name="price" type="text" style="margin: 5px 40px 5px 0px; width: 25%; text-align: right; float: right; ">
						</c:if>
						<c:if test="${mode=='update'}">
							<li style="margin: 5px 40px 5px 0px; text-align: right; float: right;"><label>${dto.price}</label></li>
						</c:if>
						<li style="clear:both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">상품상태</li>
						<li style="margin: 5px 40px 5px 0px; width: 25%; text-align: right; float: right;">
							<select name="state">
								<option value="sell" ${dto.panmaeState=="sell"?"selected='selected'":"" }>판매</option>
								<option value="soldOut" ${dto.panmaeState=="soldOut"?"selected='selected'":"" }>품절</option>
								<option value="finish" ${dto.panmaeState=="finish"?"selected='selected'":"" }>판매종료</option>
							</select>
						</li>
					</ul>
					<ul	style="list-style: none; font-size: 16px; float: left; width: 50%; padding-top: 20px;">
						<li	style="clear: both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">생산자&nbsp;&nbsp;
							<c:if test="${mode=='create'}">
								<select id="producerId"	onchange="inpProduceCode();"><option value="">::선택::</option>
									<c:forEach var="producerDto" items="${producerList}">
										<option value="${producerDto.produceCode}:${producerDto.produceCorporNum}:${producerDto.corporAddress}" >${producerDto.produceCorporName}</option>
									</c:forEach>
								</select>
								<label id="produceCodeId"></label>
								<input type="hidden" value="" name="producer">
							</c:if>
							<c:if test="${mode=='update'}">
								<input type="hidden" value="${dto.produceCode}" name="producer">
								<label>${dto.produceCorporName}&nbsp;&nbsp;(생산자코드:${dto.produceCode})</label>
							</c:if>
						</li>
						<li	style="clear: both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">전화번호&nbsp;&nbsp;<label id="producerTelId">${dto.produceCorporNum}</label></li>
						<li	style="clear: both; margin: 5px 0px 5px 20px; padding: 1px; float: left;">생산자주소&nbsp;&nbsp;<label id="producerAddrId">${dto.corporAddress}</label></li>
					</ul>
					<ul	style="clear: both; list-style: none; font-size: 16px; float: left; width: 100%; padding-top: 20px; text-align: center;">
						<li>
							<a href="javascript:location.href='<%=cp%>/admin/goodsmgmt/list.do?page=${page}';"><button type="button">취소</button></a>&nbsp;&nbsp;&nbsp;&nbsp; 
							<c:choose>
								<c:when test="${mode=='create'}">
									<input type="submit" value="등록">	
								</c:when>
								<c:when test="${mode=='update'}">
									<input type="submit" value="수정">
								</c:when>
							</c:choose>
						</li>
						<c:if test="${mode=='update'}">
							<input type="hidden" name="page" value="${page}">
							<input type="hidden" name="panmaeState" value="${param.panmaeState}">
							<input type="hidden" name="groupCode" value="${param.groupCode}">
							<input type="hidden" name="kindCode" value="${param.kindCode}">
							<input type="hidden" name="searchKey" value="${param.searchKey}">
							<input type="hidden" name="searchValue" value="${param.searchValue}">
						</c:if>
					</ul>
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