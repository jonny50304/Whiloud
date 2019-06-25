<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 取消快取狀態 -->
<% 
	response.setHeader("Pragma","no-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

已完成的post：<br>
<table border="1" width="800">
	<c:forEach var="entry" items="${postList}">
		<c:if test="${entry.done}">
		
			<tr>
				<td>
					<img src="${pageContext.request.contextPath}${entry.cb.picturePath}" width="300"/>
				</td>
				<td>
					postNo = ${entry.postNo}<br>
					postTitle = ${entry.postTitle}<br>
					使用的影片編號 =  ${entry.cb.clipNo}<br>
					影片標題 = ${entry.cb.clipTitle} <br>
					發文者 = ${entry.mb1.nickname}<br>
					合作者 = ${entry.mb2.nickname}<br>
					發文日期 = ${entry.creationDateTime}<br>
					<c:set var="postNo" value="${entry.postNo}"/>
					<form method="POST" action="${pageContext.servletContext.contextPath}/post/GetPostDetail">
						<input type="submit" value="查看postNo=${postNo}">
						<input type="hidden" name="postNo" value="${postNo}">
					</form>
				</td>
			</tr>
			
		</c:if>
		<br>
	</c:forEach>
</table>

<hr>
未完成的post：<br>
<table border="1" width="800">
	<c:forEach var="entry" items="${postList}">
		<c:if test="${not entry.done}">
			<tr>
				<td>
					<img src="${pageContext.servletContext.contextPath}${entry.cb.picturePath}" width="300"/>
				</td>
				<td>
					postNo = ${entry.postNo}<br>
					postTitle = ${entry.postTitle}<br>
					使用的影片編號 =  ${entry.cb.clipNo}<br>
					影片標題 =${entry.mb1.nickname} <br>
					發文者 = !!!合作者 尋找中!!!<br>
					發文日期 = ${entry.creationDateTime}<br>
					<c:set var="postNo" value="${entry.postNo}"/>
					<form method="POST" action="${pageContext.servletContext.contextPath}/post/GetPostDetail">
						<input type="submit" value="查看postNo=${postNo}">
						<input type="hidden" name="postNo" value="${postNo}">
					</form>
				</td>
			</tr>
			
		</c:if>
		<br>
	</c:forEach>
</table>


</body>
</html>