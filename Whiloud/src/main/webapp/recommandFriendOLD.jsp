<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<style>
		*{
			font-family: Microsoft JhengHei;
		}
		body{
			background: rgb(255,255,255)
		}
		header h1{
			text-align: center;
			font-size: 42px;
		}
		.personal{
			/*border: 1px solid black;*/
		}
		.personal img{
    		border-radius: 100%;
    		border: 1px solid #999;
    		background:#efefef;    
    		width: 27%;
    		position: relative;
    		left: 6%;
		}
		.personalData{
			/*border: 1px solid red;*/
			float: right;
			/*position: relative;
			top: 60px;
			left: -30px;	*/
		}
		.quantity td{
			/*border: 1px solid blue;*/
			font-size: 37px;
			color: orange;
			text-align: right;
		}		
		.quantity td:nth-child(1){
			position: relative;
			top: 50px;
			left: -150px;
		}			
		.quantity td:nth-child(2){
			position: relative;
			top: 50px;
			left: -110px;			
		}			
		.quantity td:nth-child(3){			
			position: relative;
			top: 50px;
			left: -70px;			
		}					
		.title td{
			/*border: 1px solid green;*/
			font-size: 20px;
			color: rgb(144,144,144,0.8);	
			text-align: right;
		}	
		.title td:nth-child(1){
			position: relative;
			top: 50px;
			left: -150px;	
		}			
		.title td:nth-child(2){
			position: relative;
			top: 50px;
			left: -110px;	
		}			
		.title td:nth-child(3){
			position: relative;
			top: 50px;
			left: -70px;
		}	

		.button	td:nth-child(1){	
			position: relative;
			top: 90px;
			left: -100px;	
		}	
		.button	td:nth-child(2){	
			position: relative;
			top: 90px;
			left: -80px;	
		}
		.button	td:nth-child(3){	
			position: relative;
			top: 90px;
			left: -60px;	
		}
		input[type="button"] {
			background-color: rgb(250,202,88);      
			color: #ffffff !important;        /*按鈕字的顏色*/
			border-radius: 4px;
			font-size: 17px;
			line-height: 1.7rem;
			padding: 0 1.5rem;
			text-align: center;
			text-decoration: none;
			white-space: nowrap;
			text-transform: uppercase;
		}
		.introduction{
			/*border: 1px solid orange;*/
			width: 98%;
			height: 150px;
			padding: 5px;
		}
		.videoboxs{
		    /*border: 1px solid red;*/
		    position: absolute;
		    top: 80%;
		    left: 10px;
			width: 97%;

		}
		.videobox{
		    /*border: 1px solid blue;*/
		    display: inline-block;
		    width: 20%;
		    height: 150px;
		    position: relative;
		    left: 3%;
		    margin: 10px;
		}
		.videoboxs .videobox h1{
		    position: relative;
		    top: -25px;
		    font-size: 20px;
		}
		.video{
		    width: 100%;
		}
		.videoer{   
		    border-radius: 100%;
		    border: 1px solid #999;
		    background:#efefef;    
		    width: 23%;
		    float: left;
		    position: relative;
		    top: -45px;
		}
	</style>
</head>
<body>
	<header>
		<h1>${memberBean.nickname}</h1>
		<span id="friendNo" style="display:none">${memberBean.memberNo}</span>
	</header>
	<hr>
	<div class="personal">
		<img src="${pageContext.servletContext.contextPath}${memberBean.picturePath}" alt="" >
		<table class="personalData">
			<tr class="quantity">
				<td>${fn:length(postBeanList)}</td>
				<td>${likeCounts}</td>
				<td>${fn:length(HisFriends)}</td>
			</tr>
			<tr class="title">
				<td>影片量</td>
				<td>獲得讚數</td>
				<td>好友人數</td>
			</tr>
			<tr class="button" >
<!-- 				<td><input type="button" value="傳送訊息"></td> -->
<!-- 				<td><input type="button" value="成為粉絲"></td> -->
				<td colspan="3" id="friendButton">
				
					<c:choose>
						<c:when test="${isInRequest}">
							<input type="button" value="等待對方同意....." id="cancelRequestButton">
						</c:when>
						
						<c:when test="${isWatingYouToAccept}">
							<input type="button" value="對方向你送出邀請，請於朋友列表確認">
						</c:when>
						
						<c:when test="${isFriend}">
							<input type="button" value="取消好友" id="cancelFriendButton">
						</c:when>
						
						<c:when test="${not isFriend and memberBean.memberNo != LoginOK.memberNo}">
							<input type="button" value="加入好友" id="sendRequestButton">
						</c:when>
					</c:choose>
					
				</td>
				
				
			</tr>
		</table>
		<div class="introduction" style="margin-left:125px;">
			<table>
				<tr>
					<td width="400">
						<p>自我介紹：</p><br>
						<c:if test='${empty memberBean.introduction}'>
							<span style="color:#ccc">這位會員沒有留下任何介紹</span>
						</c:if>
						&nbsp;&nbsp;&nbsp;<span style="font-size:12px">${memberBean.introduction}</span>
					</td>
					<td>
						<p>興趣：</p><br>
						<c:if test='${empty memberBean.interest}'>
							<span style="color:#ccc">這位會員沒有留下興趣</span>
						</c:if>
						&nbsp;&nbsp;&nbsp;<span style="font-size:12px">${memberBean.interest}</span>
					</td>
				</tr>

			</table>
		</div>
		
	</div>
	<hr>
	<section class="videoboxs">
		<c:forEach var="entry" items="${postBeanList}">
			<div class="videobox">
			<a href="GetPostDetailServletV2?postNo=${entry.postNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants">
				<img src="${pageContext.servletContext.contextPath}${entry.cb.picturePath}" alt="" class="video">
			</a>
				<h1>${entry.postTitle}<c:if test="${not entry.done}"><br>
					<span style="position:absolute; top:25px;font-size:10px; color:steelblue;">尋找合作中</span>
				</c:if></h1>
				
			</div>
		</c:forEach>
<!-- 	    <div class="videobox"> -->
<!-- 	      <img src="images/video.png" alt="" class="video"> -->
<!-- 	      <h1>我是片名</h1> -->
<!-- 	    </div> -->

	</section>
</body>


<script>
function doFirst(){
	sendRequestButton = document.getElementById('sendRequestButton');
	cancelRequestButton = document.getElementById('cancelRequestButton');
	cancelFriendButton = document.getElementById('cancelFriendButton');
	
	friendNo = document.getElementById('friendNo').innerText;
	friendButton = document.getElementById('friendButton');
	
	if(sendRequestButton != null){
		sendRequestButton.addEventListener('click', sendRequest);
	}
	if(cancelRequestButton!=null){
		cancelRequestButton.addEventListener('click', cancelRequest);
	}
	if(cancelFriendButton!=null){
		cancelFriendButton.addEventListener('click', cancelFriend);
	}
	
}


function sendRequest(){
	var action = "sendRequest";
	
	//送出URI請求
	var xhr = new XMLHttpRequest();

	xhr.onload = function(e) {
		//請求成功時，把紀錄加入聊天室窗
		if (this.readyState === 4) {
			console.log('readyState === 4');
			if (this.status == 200){
				console.log('status === 200');
				friendButton.innerHTML = '<input type="button" value="等待對方同意....." id="cancelRequestButton">';
				cancelRequestButton = document.getElementById('cancelRequestButton');
				cancelRequestButton.addEventListener('click', cancelRequest);
			} 
		}

	};

	//送出URI請求
	requestURI = '/Whiloud/ProcessFriendRelationServlet?action=' + action
			+ '&friendNo=' + friendNo;
	xhr.open("POST", requestURI, true);
	xhr.send();
	
}
function cancelFriend(){
var xhr = new XMLHttpRequest();
	
	xhr.onload =function(e){
		if (this.readyState === 4) {
			console.log('readyState === 4')
			cancelFriendButton.disabled=true;
			parent.location.reload();
		}
	}
	
	requestURI = '/Whiloud/ProcessFriendRelationServlet?action=cancelFriend&friendNo='+ friendNo;
	xhr.open("POST", requestURI, true);
	xhr.send();
}


function cancelRequest(){
	var action = "cancelRequest";
	
	//送出URI請求
	var xhr = new XMLHttpRequest();

	xhr.onload = function(e) {
		//請求成功時，把紀錄加入聊天室窗
		if (this.readyState === 4) {
			console.log('readyState === 4');
			if (this.status == 200){
				console.log('status === 200');
				friendButton.innerHTML = "<input type='button' value='加入好友' id='sendRequestButton'>";
				sendRequestButton = document.getElementById('sendRequestButton');
				sendRequestButton.addEventListener('click', sendRequest);
			} 
		}
	};

	//送出URI請求
	requestURI = '/Whiloud/ProcessFriendRelationServlet?action=' + action
			+ '&friendNo=' + friendNo;
	xhr.open("POST", requestURI, true);
	xhr.send();
}

window.addEventListener('load', doFirst());

</script>
</html>