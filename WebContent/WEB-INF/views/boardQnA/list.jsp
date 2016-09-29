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

<%-- <script type="text/javascript" src="<%=cp%>/js/move-top.js"></script>
<script type="text/javascript" src="<%=cp%>/js/easing.js"></script> --%>
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
      f.action="<%=cp%>/boardQnA/list.do";
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

      <div style="min-height: 650px;">
      <div style="width: 100%; height: 30px;"></div>
            <div style="width:100%;   height: 40px; line-height:40px;clear: both;
             border-top: 1px solid #DAD9FF;border-bottom: 1px solid #DAD9FF;">
                <div style="width:1100px; height:35px; line-height:30px; margin:5px auto;">
                    <img src="<%=cp%>/images/arrow.jpg" alt="" style="padding-left: 5px; padding-right: 5px;">
                    <span style="font-weight: bold;font-size:15pt;font-family: 나눔고딕, 맑은 고딕, 굴림;">질문과 답변</span>
                </div>
            </div>
         
            <div style="margin: 10px auto; margin-top: 20px; width:1100px; min-height: 400px;">
      
               <table style="width: 1100px; margin: 0px auto; border-spacing: 0px;">
                 <tr align="center" bgcolor="#2F9D27" height="35"> 
                     <td width="60" style="font-size: 15px; font-weight: bold; color: #ffffff;">번호</td>
                     <td width="650" style="font-size: 15px; font-weight: bold; color: #ffffff;">제목</td>
                     <td width="150" style="font-size: 15px; font-weight: bold; color: #ffffff;">작성자</td>
                     <td width="120" style="font-size: 15px; font-weight: bold; color: #ffffff;">작성일</td>
                     <td width="110" style="font-size: 15px; font-weight: bold; color: #ffffff;">조회수</td>
                 </tr>
                
                <c:forEach var="dto" items="${list}">
                 <tr align="center" bgcolor="#CEFBC9" height="35"> 
                     <td align="center" style="font-size: 15px; font-weight: bold;">${dto.listNum}</td>
                     <td align="left" style="font-size: 15px; font-weight: bold; padding-left: 10px;">
                         <c:forEach var="n" begin="1" end="${dto.depth}">
                                   &nbsp;&nbsp;
                               </c:forEach>
                               <c:if test="${dto.depth!=0}">
                                   <img src='<%=cp%>/images/re.jpg'>
                               </c:if>
                               <a href='${articleUrl}&num=${dto.num}'>${dto.subject}</a>
                               <c:if test="${dto.gap < 1}">
                               <img src='<%=cp%>/images/new.jpg'>
                         </c:if>
                     </td>
                     <td align="center" style="font-size: 15px; font-weight: bold;">${dto.name}</td>
                     <td align="center" style="font-size: 15px; font-weight: bold;">${dto.created}</td>
                     <td align="center" style="font-size: 15px; font-weight: bold;">${dto.hitCount}</td>
                 </tr>
                 <tr><td height="1" colspan="5" bgcolor="#65D35D"></td></tr> 
                </c:forEach>
               </table>
               
               <table style="width: 1100px; margin: 0px auto; border-spacing: 0px;">
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
               
               <table style="width: 1100px; margin: 0px auto; border-spacing: 0px;">
                  <tr height="35">
                     <td align="right" width="63%">
                         <form name="searchForm" action="" method="post">
                             <select style="background: #2F9D27; color: #FFFFFF;" name="searchKey" class="selectField">
                           <option value="subject">제목</option>
                           <option value="userName">작성자</option>
                           <option value="content">내용</option>
                           <option value="created">등록일</option>
                           </select>
                           <input style="background: #CEFBC9;" type="text" name="searchValue" class="boxTF">
                           <input style="background: #2F9D27; color: #CEFBC9;" type="button" value=" 검 색 " class="btn" onclick="searchList()">
                       </form>
                     </td>
                     
                     <!-- 폼 태그 밖의 이미지는 submit 기능이 없다. -->
                     <td align="right">
                         <input type="image" src="<%=cp%>/images/btn_write.jpg" 
                         onclick="javascript:location.href='<%=cp%>/boardQnA/created.do';">
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