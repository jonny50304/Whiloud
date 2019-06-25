<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>

<html>

<head>

	<%response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
response.flushBuffer();%>
	<title>Welcome to Whiloud</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="css/index.css" />
</head>

<body>
	<section id="banner" data-video="images/banner">
		<div class="inner">
			<h1>Whiloud</h1>
			<div class="divrightbottom"><a href="main.html"><img src="images/s.png"></a></div>
			<form action="${pageContext.servletContext.contextPath}/login.do" method="POST" id="loginForm">
				<div>
					<input name="account" id="account" type="text" placeholder="帳號" value="${requestScope.user}${param.userId}">
					${ErrorMsgKey.AccountEmptyError}
				</div>
				<div>
					<input name="password" id="password" type="password" placeholder="密碼" value="${requestScope.password}${param.pswd}">
					${ErrorMsgKey.PasswordEmptyError}
				</div>
				<input type="checkbox" type="checkbox" name="rememberMe" 
	               <c:if test='${requestScope.rememberMe==true}'>
	                  checked='checked'
	               </c:if> 
             value="true">記住帳密
				<input type="checkbox" name="autoLogin">自動登入&nbsp;&nbsp;&nbsp;
				 <span style="color:red; font-size:13px;" id="errorMessage">${ErrorMsgKey.LoginError}${okMessage.InsertOK}</span>
				 <% // 顯示MsgOK.InsertOK後，就要立刻移除，以免每次回到首 頁都會顯示新增成功的訊息
					session.removeAttribute("okMessage");  
				%> 
			</form>
			<button class="button special scrolly" id="loginButton">登入</button>
			<button class="button special scrolly" id="FB_login">FB連結</button>
			<a href="main.html" class="button special scrolly">Google登入</a>
			<a href="joinus.jsp" class="button special scrolly">註冊</a>
		</div>
	</section>
	<!-- <a href="text.html"><img src="images/s.png" class="divrightbottom"></a> -->
	<script src="js/jquery.min.js"></script>
	<!-- 上課用的 -->
	<script src="js/skel.min.js"></script>
	<!-- skelJS是一款輕量級前端框架，用於創建響應式網站和應用。 for: skel.breakpoints
			提供四個核心組件：CSS Grid System、Responsive Handler、 CSS Shortcuts 及 Plugin -->
	<script src="js/util.js"></script>
	<!-- 提供了一些常用函數 -->
	<script src="js/index.js"></script>
	<!-- 打CODE的 -->
	<script src="js/login.js"></script>
	<script src="js/fbLogin.js"></script>


</body>

</html>