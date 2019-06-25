<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>

body{
	background: #ccc;
	font: 110% Tahoma,Verdana;
}
form{
	background: #fff;
	width: 330px;
	padding: 15px;
	border: 1px solid #bbb;
	margin: 100px auto;
}
label{
	width:100px;
	float:left;
}
#theForm div{
	margin:8px 0px;
}
#image{
	width: 150px;
}
</style>

</head>
<body>
<form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='register.do'/>"  id="theForm" >
		<fieldset>
			<legend>Register</legend>
			<div>
				<label for="account">帳號: </label>
				<input type="email" name="account" value="${param.account}" id="account"><br>
				<font size="-1" color="#FF0000">${errorMessage.errorAccountEmpty}${errorMessage.errorIDDup}${errorMessage.errorAccountExists}</font>
			</div>
			<div>
				<label for="password">密碼: </label>
				<input type="password" name="password" value="${param.password}" id="password"><br>
				<font size="-1" color="#FF0000">${errorMessage.errorPasswordEmpty}${errorMessage.passwordError}</font>
			</div>
			<div>
				<label for="passwordCheck">密碼確認: </label>
				<input type="password" name="passwordCheck" value="${param.passwordCheck}" id="passwordCheck"><br>
				<font size="-1" color="#FF0000">${errorMessage.errorPasswordCheckEmpty}</font>
			</div>
			<div>
				<label for="nickname">暱稱: </label>
				<input type="text" name="nickname" value="${param.nickname}" id="nickname"><br>
				<font size="-1" color="#FF0000">${errorMessage.errorNicknameEmpty}${errorMessage.errorNicknameExists}</font>
			</div>
			<div>
			<c:set var = "boy" value="男"/>
			<c:set var = "girl" value="女"/>
			<c:set var = "nothing" value="null"/>
				<label for="gender">性別: </label>
				<select name="gender">
				 <c:choose>
				 <c:when test="${empty gender}">
					<option hidden="true">${gender}</option>
					</c:when>
					<c:otherwise>
					<option>${gender}</option>
					</c:otherwise>
					</c:choose>			
					<c:choose>
					<c:when test="${gender eq boy}">
                    <option value="男" hidden="true">男</option>
                    </c:when>
                    <c:otherwise> 
                      <option value="男">男</option>
                    </c:otherwise>
                    </c:choose>
                    <c:choose>
                    <c:when test="${gender eq girl}">
                    <option value="女" hidden="true">女</option>
                    </c:when>
                    <c:otherwise>
                    <option value="女">女</option>
                    </c:otherwise>
                    </c:choose>
                </select>	
				<font size="-1" color="#FF0000">${errorMessage.errorGenderEmpty}</font>
			</div>
			<div>	
				<label for="phone">電話: </label>
				<input type="text" name="phone" value ="${param.phone}" id="phone"><br>
				<font size="-1" color="#FF0000">${errorMessage.errorPhoneEmpty}</font>
			</div>
			<div>	
				<label for="image">照片: </label>
                <Input Type="file"  name="file" id="image">	<br>
               <font size="-1" color="#FF0000">${errorMessage.errorPicture}</font> 
             </div>
			<div>
				<input type="submit" value="註冊" id="submit">
				<font size="-1" color="#FF0000">${errorMessage.errorTitle}</font> 
			</div>
		</fieldset>
	</form>	  
</body>
</html>