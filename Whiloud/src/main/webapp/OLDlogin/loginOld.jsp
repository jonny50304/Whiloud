<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="loginOld.do" method="POST" name="loginForm">
 	<input type="text" name="account" value="${requestScope.user}${param.userId}${ErrorMsgKey.AccountEmptyError}">
	<br>
	<input type="password" name="password" value="${requestScope.password}${param.pswd}${ErrorMsgKey.PasswordEmptyError}">
	<br> 
	
	記住我：
	<input type="checkbox" name="rememberMe" 
               <c:if test='${requestScope.rememberMe==true}'>
                  checked='checked'
               </c:if> 
             value="true">
    <br>
             
	<input type="submit" value="submit"><br>
	 &nbsp;&nbsp;&nbsp;error:${ErrorMsgKey.LoginError}
	
</form>
</body>
</html>