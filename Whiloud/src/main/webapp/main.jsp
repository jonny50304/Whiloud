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
	
	<meta charset="UTF-8">
	<title>Whiloud</title>
	
<!-- 	<link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" -->
<!-- 	crossorigin="anonymous"> -->
	<link rel="stylesheet" href="css/bootstrap.min.css">
	<link rel="stylesheet" href="css/main.css">
	<link rel="stylesheet" href="css/flexslider.css">
	<link rel="stylesheet" href="css/thickbox.css">
	<link rel="stylesheet" href="css/animate.css">
	
</head>
<body>

<jsp:include page="title.jsp"/>
		<div class="move bg1" style="position:absolute; z-index:99999; ">
			<div class="container">
				<div id="activityButton" style="height:75px;text-align:center;line-height:75px;">最新影片</div>
				<div id="videoButton" style="height:75px;text-align:center;line-height:75px;">熱門影片</div>
				<div id="friendButton" style="height:75px;text-align:center;line-height:75px;">好友聊天</div>
			</div>
		</div>
		
<!-- 		<div class="move bg1" > -->
<!-- 			<div class="container"> -->
<!-- 				<div id="activityButton" >最新活動</div> -->
<!-- 				<div id="videoButton" >熱門影片</div> -->
<!-- 				<div id="friendButton">好友聊天</div> -->
<!-- 			</div> -->
<!-- 		</div> -->

	<div id="wrap">
		<div id="main">
			<div id="page1" class="page">
				<div id="showSign1">最新影片</div>
				<div class="flexslider">
					<ul class="slides">
						<c:forEach var="i" begin="0" end="3" step="1">
							<c:if test="${not empty clipBeanList[i]}">
								<li>
									<a href="GetClipServletV2?clipNo=${clipBeanList[i].clipNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants">
										<img src="${pageContext.servletContext.contextPath}${clipBeanList[i].picturePath}" alt="" >
									</a>
								</li>
							</c:if>
						</c:forEach>
<!-- 						<li><img src="images/T4.jpg" alt=""></li> -->
<!-- 						<li><img src="images/T7.jpg" alt=""></li> -->
<!-- 						<li><img src="images/T1.jpg" alt=""></li> -->
<!-- 						<li><img src="images/T5.jpg" alt=""></li> -->
					</ul>
				</div>
			</div>
			<div id="page2" class="page">
				<div id="showSign2" class="wow fadeInUpBig" data-wow-duration="1s">推薦好友</div>
				<div id="showSign3" class="wow fadeInUpBig" data-wow-duration="2s">熱門影片</div>
				<div id="showSign4" class="wow fadeInUpBig" data-wow-duration="3.2s">最新合作版</div>
				<div class="recommendFriend" id="friendButton">
				
					<c:forEach var="i" begin="0" end="8" step="1">
						<c:if test="${not empty suggestFriendList[i]}">
							<div class="boxPer wow fadeInDownBig" data-wow-duration="${3.6-(i*0.3)}s">
								<div>
									<a href="EnterFriendPageServlet?memberNo=${suggestFriendList[i].memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"><img src="${pageContext.servletContext.contextPath}${suggestFriendList[i].picturePath}" alt=""></a>
								</div>
								<h3>${suggestFriendList[i].nickname}</h3>
							</div>
						</c:if>
						
					</c:forEach>
					
					<div class="more three">
						<a href="">
							<p>More...</p>
						</a>
					</div>
					
					
				</div>

				<div class="hotVideos">
				
					<c:forEach var="i" begin="0" end="3" step="1">
						<c:if test="${not empty postBeanList[i]}">
						
							<div class="boxVideo wow slideInLeft" data-wow-duration="${3.6-0.7*i}s">
								<div>
									<a href="GetPostDetailServletV2?postNo=${postBeanList[i].postNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"><img src="${pageContext.servletContext.contextPath}${postBeanList[i].cb.picturePath}" alt="" class="videoCover"></a>
								</div>
								<h3>${postBeanList[i].postTitle}</h3>
								<a href="EnterFriendPageServlet?memberNo=${postBeanList[i].mb1.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"><img src="${pageContext.servletContext.contextPath}${postBeanList[i].mb1.picturePath}" alt="放影片的人" class="videoer"></a>
								<div id="author">
									<p>${postBeanList[i].mb1.nickname}
									<br><span style="font-size:10px;">
									<c:if test="${postBeanList[i].mb2.nickname != postBeanList[i].mb1.nickname && postBeanList[i].mb2.memberNo != 1}">
										<a href="EnterFriendPageServlet?memberNo=${postBeanList[i].mb2.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"  style="color:black; text-decoration:none;">
										合作者：${postBeanList[i].mb2.nickname} </a>
									</c:if>
									
									
									</span></p>
								</div>
								
							</div>
						
						</c:if>
						
						<c:if test="${empty postBeanList[i]}">
						
							<div class="boxVideo wow slideInLeft" data-wow-duration="${3.6-0.7*i}s">
								<div>
									<img src="images/video.png" alt="" class="videoCover">
								</div>
								<span style="visibility:hidden">
									<h3>【這就是街舞S2】</h3>
									<a href=""><img src="images/4.jpg" alt="放影片的人" class="videoer"></a>
									<div id="author">
										<p>作者名子</p>
									</div>
								</span>
							</div>
						
						</c:if>
					</c:forEach>
					
					
					<div class="more">
						<a href="EnterPostListServlet">
							<p>More...</p>
						</a>
					</div>
					
					
					
				</div>

				<div class="friendVideos">
					
					<c:forEach var="i" begin="0" end="3" step="1">
						<c:if test="${not empty cooperationBeanList[i]}">
							<div class="boxVideo wow slideInRight" data-wow-duration="${0.8*(i+1)}s">
								<div>
									<a href="GetPostDetailServletV2?postNo=${cooperationBeanList[i].postNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"><img src="${pageContext.servletContext.contextPath}${cooperationBeanList[i].cb.picturePath}" alt="" class="videoCover"></a>
								</div>
								<h3>${cooperationBeanList[i].postTitle}</h3>
								<a href="EnterFriendPageServlet?memberNo=${cooperationBeanList[i].mb1.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants">
								<img src="${pageContext.servletContext.contextPath}${cooperationBeanList[i].mb1.picturePath}" alt="放影片的人" class="videoer"></a>
								<div id="author">
									<p>${cooperationBeanList[i].mb1.nickname}</p>
								</div>
								
							</div>
						
						</c:if>
						<c:if test="${empty cooperationBeanList[i]}">
							<div class="boxVideo wow slideInRight" data-wow-duration="${0.8*(i+1.5)}s">
								<div>
									<a href="20190530/video.html?keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"><img src="images/video.png" alt="" class="videoCover"></a>
								</div>
								<span style="visibility:hidden">
								<h3>【這就是街舞S2】 EP1 羅志祥賽前就公開？</h3>
								<a href=""><img src="images/6.jpg" alt="放影片的人" class="videoer"></a>
								<div id="author">
									<p>作者名子</p>
								</div>
								</span>
							</div>
						</c:if>
					</c:forEach>
					

					<div class="more two">
						<a href="EnterCooperationListServlet">
							<p>More...</p>
						</a>
					</div>
				</div>
			</div>
			<div id="page3" class="page">
				<div id="introduce" style="position:absolute;top:2095px;z-index: 1;left: 108px;">
					<img src="images/123.JPG" alt="" width="1490px" height="735px" >
				</div>
				<div id="friend">
					<div id="friVideoView">
						<iframe src="player/video.html" frameborder="" style="width:100%;height:100%;" id="friendPage"></iframe>

<!-- 						<iframe src="EnterFriendPageServlet?memberNo=2" frameborder="" style="width:100%;height:100%;"></iframe> -->
					</div>
					<div id="friTalkView">
						<iframe src="chat.html" frameborder="" style="width:100%;height:100%;" id="chatBoardFrame"></iframe>
					</div>
					<div id="friFriView">
						<iframe src="myFriend.jsp" frameborder="" style="width:100%;height:100%;" id="friendList"></iframe>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script> -->
<!-- <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script> -->
<script src="js/jquery-3.3.1.slim.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/jquery.min.js"></script>
<script src="js/main.js"></script>
<script src="js/wow.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/moveView.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>

</html>