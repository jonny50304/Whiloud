<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<%response.setHeader("Pragma","No-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); 
		response.flushBuffer();%>
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
  <title>影片集 -Whiloud</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link href="css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/videoList.css">
  	<link rel="stylesheet" href="css/thickbox.css">

</head>

<body>
  <jsp:include page="title.jsp" />

	<section class="category">
		<p>
			
			排序 :&nbsp;&nbsp; 
<!-- 			<input type="button" value="依關聯性">&nbsp;&nbsp; -->
			<a href="EnterPostListServlet"><input type="button" value="依時間(新優先)">&nbsp;&nbsp; </a>
			<a href="EnterPostListByLikeServlet"><input type="button" value="依讚數(多優先)">&nbsp;&nbsp; </a>
<!-- 			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 影片長度 :&nbsp; <input -->
<!-- 				type="button" value="不限">&nbsp;&nbsp; <input type="button" -->
<!-- 				value="5分以內">&nbsp;&nbsp; <input type="button" -->
<!-- 				value="5-10分鐘">&nbsp;&nbsp; <input type="button" -->
<!-- 				value="10分鐘以上">&nbsp;&nbsp; -->
		</p>

	</section>
	
  <section class="videoboxs">
  
  	<c:forEach var="i" begin="0" end="11" step="1">
			<c:if test="${not empty postBeanList[i]}">
				<div class="videobox">
					<a href="GetPostDetailServletV2?postNo=${postBeanList[i].postNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants">
			      <img src="${pageContext.servletContext.contextPath}${postBeanList[i].cb.picturePath}" alt="" class="video">
			      </a>
			      <p>${postBeanList[i].postTitle}</p>
			      <a href="EnterFriendPageServlet?memberNo=${postBeanList[i].mb1.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"><img src="${pageContext.servletContext.contextPath}${postBeanList[i].mb1.picturePath}" alt="放影片的人" class="videoer"></a>
			      <div id="author">
			        <p>${postBeanList[i].mb1.nickname}
						<br>
						<span style="font-size:10px; ">
						<a href="EnterFriendPageServlet?memberNo=${postBeanList[i].mb2.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants" style="color:black; text-decoration:none;">
							合作者：${postBeanList[i].mb2.nickname} 
						</a>
						</span></p>
			      </div>
			    </div>
				
			
			</c:if>
						
			<c:if test="${empty postBeanList[i]}">
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
			</c:if>
		</c:forEach>
    
   
  </section>
</body>

<!-- <script src="js/jquery-3.3.1.slim.min.js"></script> -->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/videoList.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>
<script src="js/openSocket.js?<%=time%>"></script>
</html>