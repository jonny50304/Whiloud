<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="/Whiloud/chat/EnterChatRoomServlet?memberNo=2" method="POST">
	<input type="submit" value="以memberNo=2的身分登入"><br>
</form>
<form action="/Whiloud/chat/EnterChatRoomServlet?memberNo=3" method="POST">
	<input type="submit" value="以memberNo=3的身分登入"><br>
</form>
<form action="/Whiloud/chat/EnterChatRoomServlet?memberNo=4" method="POST">
	<input type="submit" value="以memberNo=4的身分登入"><br>
</form>
<form action="/Whiloud/chat/EnterChatRoomServlet?memberNo=5" method="POST">
	<input type="submit" value="以memberNo=5的身分登入"><br>
</form>
<form action="/Whiloud/chat/EnterChatRoomServlet?memberNo=6" method="POST">
	<input type="submit" value="以memberNo=6的身分登入"><br>
</form>
</body>
</html>