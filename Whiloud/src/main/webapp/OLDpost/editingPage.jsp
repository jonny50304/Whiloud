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
<script src="js/recorder.js"></script>
<script src="js/editingController.js"></script>
</head>
<body>
	
	
	<br>
	<button id="permissionButton">按我連接麥克風</button>
	<table border="1">
		<tr>
			<td width="350">
				clipNo = ${clipBean.clipNo}<br>
				clipPath = ${clipBean.clipPath}<br>
				picturePath = ${clipBean.picturePath}<br>
				creationDateTime = ${clipBean.creationDateTime}<br>
				role 1: ${clipBean.role1} | role 2: ${clipBean.role2}<br>
				<video src="${pageContext.servletContext.contextPath}${clipBean.clipPath}" width="400" controls="controls" id = "clip">
					please use Chrome
				</video>
				<br>
				text: ${clipBean.text}
				<br>
				==========<button id="playRecord">播全部錄音</button>========== <br>
				memberNo: <input type="hidden" id="memberNo" value="${LoginOK.memberNo}">${LoginOK.memberNo}<br>
				要錄的roleNo:<input type="hidden" id="roleNo" value="${roleNo}">${roleNo}<br>
				clipNo:<input type="hidden" id="clipNo" value="${clipBean.clipNo}">${clipBean.clipNo}<br>
				<c:if test="${not cooperate}">
					postTitle: <input type="text" name="postTitle" id="postTitle"><br>
					想說的話(text): <input type="text" name="text" id="text"><br>
					<c:if test="${roleNo != 0}">
						是否要合作: <input type="radio" name="done" value="false" checked="checked">true | <input type="radio" name="done" value="true">false<br>
						只能給朋友配: <input type="radio" name="friendOnly" value="true">true | <input type="radio" name="friendOnly" value="false" checked="checked">false<br>
					</c:if>
					
					<c:if test="${roleNo == 0}">
						<input type="hidden" id="done" value="true">
						<input type="hidden" id="friendOnly" value="false">
					</c:if>
					
					<input type="hidden" id="cooperate" value="false">
					
				</c:if>
				<c:if test="${cooperate}">
					<input type="hidden" id="postNo" value="${postNo}">
					<input type="hidden" id="cooperate" value="${cooperate}">
				</c:if>
				<input type="hidden" id="scriptCount" value="${scriptBeanList[0].scriptCount}">
				<input type="hidden" id="scriptStartNo" value="${scriptBeanList[0].scriptNo}">
					
				==========<button id="upload" disabled="true" >上傳</button>========== <br>
				<span id="hasToRecordText" style="color:red">請先錄完全部才能上傳</span>
				

			</td>
			<td>
				<c:forEach var="entry" items="${scriptBeanList}">
					<span id="section">
						scriptNo: <span class="scriptNo">${entry.scriptNo}</span>,  role: ${entry.roleName}<br>
						<span class="scriptPosition">${entry.scriptPosition}</span>/${entry.scriptCount}, <br>
						<span class="startTime">${entry.startTime}</span>
						~
						<span class="endTime">${entry.endTime}</span><br>
						<span class = "script">
						${entry.englishScript}<br>
						${entry.chineseScript}<br>
						</span>
						<button class = "playIntervalClip">播此段原聲</button>
						<c:choose>
						   <c:when test='${roleNo == 0}'>
						   		<button class = "recordStart" disabled="true">錄音</button>
						   		<span class="permissionText" style="color:red">請先連結麥克風才能開啟功能</span>
						   		<span class="hasToRecord"></span>
						   </c:when>
						   <c:when test='${entry.roleNo == roleNo}'>
						   		<button class = "recordStart" disabled="true">錄音</button>
						   		<span class="permissionText" style="color:red">請先連結麥克風才能開啟功能</span>
						   		<span class="hasToRecord"></span>
						   </c:when>
						   <c:otherwise>
						   		<button class = "recordStart" disabled="true"  style="display:none">錄音</button>
						   </c:otherwise>
						</c:choose>
						<span class="playIntervalRecord"></span>
						<span class="audioPlayer"></span>
						<hr/>
					</span>
				</c:forEach>

			</td>
		</tr>
	</table>


</body>
</html>