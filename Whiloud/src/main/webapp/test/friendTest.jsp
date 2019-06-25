<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<a href="${pageContext.request.contextPath}/friend/CheckFriendServlet?memberNo=2">查看memberNo2的朋友(執行後請見console)</a><br>
	<a href="${pageContext.request.contextPath}/friend/AcceptRequestServlet?memberNo=2&friendNo=10">memberNo2同意來自memberNo10的朋友邀請(只能執行一次)</a><br>
	<a href="${pageContext.request.contextPath}/friend/SendRequestServlet?memberNo=3&friendNo=10">memberNo3送交友邀請給memberNo10(只能執行一次)</a><br>
	<hr>
	<form action="${pageContext.request.contextPath}/friend/CheckFriendRelationServlet" method="GET">
		查詢朋友間的關係:<br>
		memberNo=<input type="text" name="memberNo" value="2">
		friendNo=<input type="text" name="friendNo" value="10"><br>
		<input type="submit">
	</form>
</body>
</html>