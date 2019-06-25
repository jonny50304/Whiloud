<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>設定 -Whiloud</title>

<link rel="stylesheet" href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"href="css/bootstrap-switch.min.css" />
<link rel="stylesheet" type="text/css" href="css/bootstrap-switch.css" />
<link rel="stylesheet" href="css/titleSet.css">
<link rel="stylesheet" href="css/thickbox.css">
<!-- <link rel="stylesheet" href="css/bootstrap.min.css"> -->
</head>

<body>

	<%
		java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("HHmmss");
		java.util.Date currentTime = new java.util.Date();
		String time = simpleDateFormat.format(currentTime).toString();
	%>


	<jsp:include page="title.jsp" />
	<table class="row">
		<tr>
			<td id="personlist">個人資料</td>
			<td id="personmage">頭像設定</td>
			<td id="personpassword">密碼修改</td>
			<td id="personprivacy">按讚內容</td>
		</tr>
	</table>
	<!-- 個人資料修改 -->
	<div id="infocontent01">
		<form ENCTYPE="multipart/form-data" method="POST"
			action="<c:url value='UpdateInformationServlet'/>" id="theForm">
			<div class="firstlist">
				<span style="font-size: 18px">${okMessage.UpdateOK}</span>
				<%
					// 顯示MsgOK.InsertOK後，就要立刻移除，以免每次回到首 頁都會顯示新增成功的訊息
					session.removeAttribute("okMessage");
				%>
				<p>名子 :</p>
				<input type="text" name="name" value="${memberBean.nickname}"
					disabled>
				<p>性別 :</p>

				<c:choose>
					<c:when test="${memberBean.gender=='M'}">
						<c:set var="gender" value="男" />
					</c:when>
					<c:when test="${memberBean.gender=='F'}">
						<c:set var="gender" value="女" />
					</c:when>
					<c:when test="${memberBean.gender=='O'}">
						<c:set var="gender" value="保密" />
					</c:when>
				</c:choose>

				<input type="text" name="name" value="${gender}" disabled>

				<p>生日 :</p>
				<input class="text" type="text" onfocus="(this.type='date')"
					name="birthday" value="${memberBean.birthday}" />
				<p>電話號碼 :</p>
				<input type="text" name="phone" value="${memberBean.phone}">
				<div id="self">
				<p>自我介紹 :</p>
				<input type="text" name="introduction"
					value="${memberBean.introduction}">
				</div>
				<div class="interest">
					<p>興趣 :</p>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'閱讀')}">
							<input type="checkbox" name="interest" value="閱讀" checked>閱讀
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="閱讀">閱讀
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'遊戲')}">
							<input type="checkbox" name="interest" value="遊戲" checked>遊戲
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="遊戲">遊戲
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'電影')}">
							<input type="checkbox" name="interest" value="電影" checked>電影
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="電影">電影
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'逛街')}">
							<input type="checkbox" name="interest" value="逛街" checked>逛街
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="逛街">逛街
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'打球')}">
							<input type="checkbox" name="interest" value="打球" checked>打球
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="打球">打球
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'音樂')}">
							<input type="checkbox" name="interest" value="音樂" checked>音樂
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="音樂">音樂
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'旅行')}">
							<input type="checkbox" name="interest" value="旅行" checked>旅行
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="旅行">旅行
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'魔術')}">
							<input type="checkbox" name="interest" value="魔術" checked>魔術
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="魔術">魔術
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'健身')}">
							<input type="checkbox" name="interest" value="健身" checked>健身
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="健身">健身
				</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${fn:contains(memberBean.interest,'烹飪')}">
							<input type="checkbox" name="interest" value="電影" checked>烹飪
				</c:when>
						<c:otherwise>
							<input type="checkbox" name="interest" value="烹飪">烹飪
				</c:otherwise>
					</c:choose>
				</div>
				<div class="firstbutton">
					<input type="submit" value="傳送出去">
				</div>
			</div>
		</form>
	</div>
	<!-- 頭像設定 -->
	<div id="infocontent02">
		<form ENCTYPE="multipart/form-data" method="POST"
			action="<c:url value='UpdatePictureServlet'/>">
			<div class="preview">
				<p>原大頭貼 :</p>
				<br> <img
					src="${pageContext.servletContext.contextPath}${memberBean.picturePath}?<%=time%>"
					alt="">
			</div>
			<div class="preview">
				<p>準大頭貼 :</p>
				<br> <img id="image" alt="">
			</div>
			<div class="secondlist">
				<input type="file" id="theFile" name="file">
				<div class="secondbutton">
					<input type="submit" value="傳送出去" name="file">
				</div>
			</div>
		</form>
	</div>
	<!-- 密碼修改 -->
	<div id="infocontent03">
		<form class="thirdlist" action="UpdatePasswordServlet"
			id="submitPasswordForm" action="POST">
			<p>
				原密碼 :<span style="font-size: 13px; color: red;" id="passwordError"></span>
			</p>
			<input type="password" name="password" id="password">
			<p>
				新密碼 :<span style="font-size: 13px; color: red;"
					id="newPasswordError"></span>
			</p>
			<input type="password" name="newPassword" id="newPassword">
			<p>再輸入一次新密碼 :</p>
			<input type="password" name="newPasswordCheck" id="newPasswordCheck">
						<div class="thirdbutton" id="submitPasswordButton">
							<input type="submit" value="傳送出去">
						</div>
		</form>
<!-- 		<br> <input type="submit" value="傳送出去" id="submitPasswordButton"> -->
	</div>



	<!-- 按讚內容 -->
	<div id="infocontent04" style="overflow-x:hidden;">
		<!-- 		<form class="fourthlist"> -->

		<!-- 		</form> -->
		<table border="0" style="margin-left:auto; margin-right:auto;border:3px #FFD382 dashed;padding:10px;margin-top:10px">
			<tr id="nicetable">
				<td  align='center'>
				<span style="font-size: 20px">影片</span>
				</td>
				<td align='center'>
				<span style="font-size: 20px" >錄音</span>
				</td>
			</tr>
			<tr id="nicetable">
				<td width="400" valign="top">
					<br>
					<c:forEach var="entry" items="${LikePostBeanList}">
						<a href="GetPostDetailServletV2?postNo=${entry.pb.postNo}&keepThis=true&TB_iframe=true" class="thickbox"
											rel="gallery-plants">
							<img src="${pageContext.servletContext.contextPath}${entry.pb.cb.picturePath}" width="250">
						</a>
						<br>
						${entry.pb.postTitle}<br>
						<a href="EnterFriendPageServlet?memberNo=${entry.pb.mb1.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
											rel="gallery-plants">
							<img src="${pageContext.servletContext.contextPath}${entry.pb.mb1.picturePath}" width="30" class="man">
						</a>
						原作者：${entry.pb.mb1.nickname}<br>
						<a href="EnterFriendPageServlet?memberNo=${entry.pb.mb2.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
											rel="gallery-plants">
							<img src="${pageContext.servletContext.contextPath}${entry.pb.mb2.picturePath}" width="30" class="man">
						</a>
						合作者：${entry.pb.mb2.nickname}<br>
						<hr><br><br>
					</c:forEach>
				</td>
				
				<td width="400" valign="top">
					<br>
					<c:forEach var="entry" items="${LikeRecordBeanList}">
						<a href="GetPostDetailServletV2?postNo=${entry.rb.pb.postNo}&keepThis=true&TB_iframe=true" class="thickbox"
											rel="gallery-plants"  >
							<img src="${pageContext.servletContext.contextPath}${entry.rb.pb.cb.picturePath}" width="250">
						</a>
						<br>
						${entry.rb.sb.englishScript}<br>
						${entry.rb.sb.chineseScript}<br>
						<a href="EnterFriendPageServlet?memberNo=${entry.rb.pb.mb1.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
											rel="gallery-plants" style="text-decoration:none;">
							<img src="${pageContext.servletContext.contextPath}${entry.rb.pb.mb1.picturePath}" width="50" class="man">
						</a>
						錄音者：${entry.rb.mb.nickname}<br>
						<audio src="${pageContext.servletContext.contextPath}${entry.rb.recordPath}" controls="controls"></audio>
						<br>
						<hr><br><br>
					</c:forEach>
				</td>
			</tr>
		</table>
<!-- 		<div> -->
<!-- 			<div style="width: 300px"> -->
				
<!-- 			</div> -->
<!-- 			<div style="width: 300px"> -->
<!-- 				<br> -->
<!-- 				<br> -->
<!-- 				___________________________ -->
<!-- 				<br> -->
				
<!-- 			</div> -->
<!-- 		</div> -->
	</div>

</body>
<script src="js/jquery.min.js"></script>
<script src="js/titleSet.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>
<script src="js/bootstrap-switch.js"></script>
<script src="js/bootstrap-switch.min.js"></script>

<script src="js/submitPassword.js?<%=time%>"></script>

</html>