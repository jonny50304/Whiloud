<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>

<%
//請求網址後面加上日期時間防止快取
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(  
     "HHmmss");  
   java.util.Date currentTime = new java.util.Date();  
   String time = simpleDateFormat.format(currentTime).toString();
%>

<meta charset="UTF-8">
<title>Insert title here</title>

</head>

<body>
	<input type="hidden" id="memberNo1" value="${LoginOK.memberNo}">
		<div aria-live="polite" aria-atomic="true" id="toastTop" style="position: absolute;top: 15%; left: 85%; min-height: 200px;" >
  	<!-- Position it -->
	  <div style="position: absolute; top: 0; right: 0;" id="toastSection">
	  
	  
	  </div>
  </div>
	
	
	<!-- 頂部選單 -->
	<header id="header">
		<div class="logo"><a href="main.jsp">Whiloud</a></div>
		<div class="username">${LoginOK.nickname}</div>
		<div class="rec"><a href="EnterRecListServlet"><img src="images/mic.png">錄音</a></div>
		<div class="videoimg"><a href="EnterPostListServlet"><img src="images/videoimg.png">作品</a></div>
		<div class="cooperation"><a href="EnterCooperationListServlet"><img src="images/cooperation.png">合作</a></div>
		<a href="#menu" class="toggle"><span>Menu</span></a>
		
	</header>
	<!-- 漢堡Menu -->
	<nav id="menu">
		<ul class="links">
			<li><img src="${pageContext.servletContext.contextPath}${LoginOK.picturePath}?<%=time%>" alt=""></li>
			<li><a href="EnterMyVideo">我的影片</a></li>
			<li><a href="EnterRecListServlet">錄音樣板</a></li>
			<li><a href="EnterPostListServlet">作品總覽</a></li>
			<li><a href="EnterCooperationListServlet">合作募集</a></li>
			<li><a href="EnterSettingsServlet">設定</a></li>
			<li><a href="LogoutServlet">登出</a></li>
		</ul>
	</nav>
</body>


</html>