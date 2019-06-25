<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>


<html lang="en">

<head>
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.flushBuffer();
%>
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
<title>募集版 -Whiloud</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
<link href="css/font-awesome.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/cooperationList.css">
	<link rel="stylesheet" href="css/thickbox.css">

</head>

<body>

	<jsp:include page="title.jsp" />
	
	<section class="category">

		<p style="text-align:center;"> 
    		選一部影片來完成Whiloud的合作配音吧！
    	</p>

	</section>
	<section class="videoboxs">
		<c:forEach var="i" begin="0" end="11" step="1">
			<c:choose>
				<c:when test="${not empty cooperationList[i]}">
					<div class="videobox">
						<a href="GetPostDetailServletV2?postNo=${cooperationList[i].postNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants">
							<img
								src="${pageContext.servletContext.contextPath}${cooperationList[i].cb.picturePath}"
								alt="" class="video">
						</a>
						<p>${cooperationList[i].postTitle}</p>
						<a href="EnterFriendPageServlet?memberNo=${cooperationList[i].mb1.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"><img
							src="${pageContext.servletContext.contextPath}${cooperationList[i].mb1.picturePath}"
							alt="放影片的人" class="videoer"></a>
						<div id="author">
							<p>${cooperationList[i].mb1.nickname}</p>
						</div>
					</div>
				</c:when>
				<c:otherwise>
					<div class="videobox">
					<img src="images/video.png" alt="" class="video">
					<span style="visibility:hidden">
						<p>我是片名</p>
						<a href=""><img src="images/2.jpg" alt="放影片的人" class="videoer"></a>
						<div id="author">
							<p>作者名子</p>
						</div>
					</span>
					</div>
				</c:otherwise>
			</c:choose>

				

		</c:forEach>


	</section>
</body>

<!-- <script src="js/jquery-3.3.1.slim.min.js"></script> -->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/cooperationList.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script src="js/openSocket.js?<%=time%>"></script>
<script type="text/javascript" src="js/thickbox.js"></script>
</html>