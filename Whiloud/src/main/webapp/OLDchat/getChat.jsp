<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Whiloud Test Index.jsp</title>
</head>

<body>
	
	${title.mb1.nickname}和${title.mb2.nickname}的聊天室<br>
	
	<hr>
<c:forEach var='list' items='${chatList}'>
 	
	<c:if test="${memberNo1 eq list.mb1.memberNo}">
	${list.creationDateTime} | ${list.mb1.nickname}  說： ${list.message}
	
	<c:if test="${list.read}">
		<font size="1">(已讀)</font>
	</c:if>
	</c:if>
	<c:forEach var='listAnswer' items='${chatList}' >
	<c:if test="${listAnswer.chatNo eq (list.chatNo+1)}">
	<c:if test="${list.chatNo %2 != 0}">	
	${listAnswer.creationDateTime} | ${listAnswer.mb1.nickname}  說： ${listAnswer.message}	
	</c:if>
	</c:if>	
	</c:forEach>

	<br>
</c:forEach>
</body>
</html>