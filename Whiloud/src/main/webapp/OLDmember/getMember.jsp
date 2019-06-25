<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${!empty memberList}">
<c:forEach var="entry" items="${memberList}">
	No: ${entry.memberNo} <br>
	Account: ${entry.account} <br>
	Nickname: ${entry.nickname} <br>
	CreationDateTime: ${entry.creationDateTime} <br>
	points: ${entry.points} <br>
</c:forEach>
</c:if>
	No: ${member.memberNo} <br>
	Account: ${member.account} <br>
	Nickname: ${member.nickname} <br>
	CreationDateTime: ${member.creationDateTime} <br>
	points: ${member.points} <br>
<hr>
我的朋友列表：<br>
<c:forEach var="list" items="${friendList}">
	Account: ${list.memberNo.account} <br>
	Nickname: ${list.memberNo.nickname} <br>
	Picture:  ${list.memberNo.picture} <br>
	<br>
</c:forEach>
<hr>
已送出的交友邀請：<br>
<c:forEach var="list" items="${friendRequest}">
	Account: ${list.memberNo.account} <br>
	Nickname: ${list.memberNo.nickname} <br>
	Picture:  ${list.memberNo.picture} <br>
	<br>
</c:forEach>
<hr>
待確認的朋友邀請：<br>
<c:forEach var="list" items="${friendConfirm}">
	Account: ${list.memberNo.account} <br>
	Nickname: ${list.memberNo.nickname} <br>
	Picture:  ${list.memberNo.picture} <br>
	<br>
</c:forEach>
</body>
</html>