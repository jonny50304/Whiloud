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

<c:forEach var="entry" items="${clipList}">
	clipNo = ${entry.clipNo}<br>
	clipTitle = ${entry.clipTitle}<br>
	clipTitle = ${entry.text}<br>
	
	<img src="${pageContext.request.contextPath}${entry.picturePath}" width="300"/>
	<br>
	<c:set var="clipNo" value="${entry.clipNo}"/>
	<form method="POST" action="${pageContext.servletContext.contextPath}/clip/GetTemplateClip">
		<input type="submit" value="查看clipNo=${clipNo}">
		<input type="hidden" name="clipNo" value="${clipNo}">
	</form>
	<hr>
</c:forEach>
</body>
</html>