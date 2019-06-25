<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<!-- 以下三段告訴瀏覽器不要快取 -->
<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE">
<META HTTP-EQUIV="EXPIRES" CONTENT="0">
<META HTTP-EQUIV="CACHE-CONTROL" CONTENT="NO-CACHE">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Whiloud Index.jsp</title>
</head>

<body>

<c:if test="${!empty LoginOK}">
	${LoginOK.account}登入成功<br>
	<a href="LogoutServlet">登出</a>
	</c:if>
<c:if test="${empty LoginOK}">
	尚未登入<br>
	<a href="<c:url value='/login/loginOld.jsp'/>">我要登入！</a>
	<a href= "<c:url value='/register/register.jsp'/>">註冊會員</a>
</c:if>


<font size="-1" color="#FF0000">${okMessage.InsertOK}</font> 
<% // 顯示MsgOK.InsertOK後，就要立刻移除，以免每次回到首 頁都會顯示新增成功的訊息
	session.removeAttribute("okMessage");  
%> 

<hr>

<br>

<c:if test="${empty LoginOK}">
	<table border="1">
		<tr>
			<td>以下功能需要登入再使用</td>
		</tr>
		<tr>
			<td>
</c:if>

	可查詢會員(個人資料、朋友列表、已發送的交友邀請、待確認朋友名單)<br>
	<form  method="POST" action="${pageContext.servletContext.contextPath}/member/GetMemberServlet">
		select by memberNo: <input type="text" name="memberNo">
		<input type="submit" value = "submit">
	</form>
	<a href="${pageContext.servletContext.contextPath}/member/GetAllMemberServlet">select all member</a>
	<hr>
	
	
	<form  method="POST" action="${pageContext.servletContext.contextPath}/chat/GetChatServlet">
		查詢會員No1和會員No3的聊天室: <input type="submit" value = "Go~!">
		<input type="hidden" name="memberNo1" value="2">
		<input type="hidden" name="memberNo2" value="4">
	</form>
	
<c:if test="${empty LoginOK}">
			</td>
		</tr>
	</table>
</c:if>

<hr>
<a href="${pageContext.servletContext.contextPath}/clip/GetAllClips">
我要配音！(查詢所有樣板影片)
</a>
<hr>

<%-- <a href="${pageContext.servletContext.contextPath}/post/GetPostDetail?postNo=1"> 查看postNo = 1</a><br> --%>
<!-- 已實作字幕追蹤、錄音同步等功能(內嵌javascript)<br> -->
<a href="${pageContext.servletContext.contextPath}/post/GetAllPosts">
查詢所有post，查看需要合作者的影片
</a>

</body>
</html>