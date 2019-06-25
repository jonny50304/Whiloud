<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>


<html lang="en">

<head>

<%
//請求網址後面加上日期時間防止快取
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(  
     "HHmmss");  
   java.util.Date currentTime = new java.util.Date();  
   String time = simpleDateFormat.format(currentTime).toString();
%>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>我的影片 -Whiloud</title>
  <link href="css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/myVideo.css">
   
   <link rel="stylesheet" href="css/bootstrap.min.css">
   <link rel="stylesheet" href="css/thickbox.css">
  
</head>

<body>
 <jsp:include page="title.jsp" />

	<section class="category">
	<p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
    	管理自己的Whiloud錄音<span style="font-size:13px; color:#ccc;">(自己是發文者的作品才能刪除)</span>
    </p>
	</section>
 
  <section class="videoboxs">
  <c:forEach var="entry" items="${postBeanList}">
  	<div class="videobox">
      <a href="GetPostDetailServletV2?postNo=${entry.postNo}&keepThis=true&TB_iframe=true">
      	<img src="${pageContext.servletContext.contextPath}${entry.cb.picturePath}" alt="" class="video">
      </a>
      
      <c:choose>
      	<c:when test="${entry.mb1.memberNo == LoginOK.memberNo}">
      		<a href="DeleteMyPostServlet?postNo=${entry.postNo}&keepThis=true&TB_iframe=true"><img src="images/cancel.png" alt="" class="icon"></a>
      	</c:when>
      </c:choose>
<!--       <a href="index.html" style="visibility:hidden"><img src="images/edit.png" alt="" class="icon"></a> -->
      <p>${entry.postTitle}
	      <c:if test="${not entry.done}"><br>
				<span style="position:absolute; top:170px; font-size:14px; color:steelblue;">
					尋找合作中
				</span>
			</c:if>
	 	</p>
    </div>
  </c:forEach>
<!--     <div class="videobox"> -->
<!--       <a href="index.html"><img src="images/video.png" alt="" class="video"></a> -->
<!--       <a href="index.html"><img src="images/edit.png" alt="" class="icon"></a> -->
<!--       <a href="index.html"><img src="images/cancel.png" alt="" class="icon"></a> -->
<%--       <p>${我是片名}</p> --%>
<!--     </div> -->
  </section>

</body>
<script src="js/jquery-3.3.1.slim.min.js"></script>
<!-- <script src="js/jquery.min.js"></script> -->
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>


<script src="js/myVideo.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>
<script src="js/openSocket.js?<%=time%>"></script>
</html>