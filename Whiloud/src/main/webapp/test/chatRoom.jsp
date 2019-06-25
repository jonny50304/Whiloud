<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- <script src="/Whiloud/test/chat.js"></script> -->
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS"
	crossorigin="anonymous">

</head>
<body>

	<div class="toast" role="alert" aria-live="assertive"
		aria-atomic="true" style="position: absolute; top: 60%; left: 300px;" data-delay="2000">
		<div class="toast-header">
			<img src="" class="rounded mr-2" id="broadcastImage" width="30">
			<strong
				class="mr-auto" id="broadcastMember">Bootstrap</strong> 
<!-- 				<small class="text-muted">11 mins ago</small> -->
					<small class="text-muted" id="sendTime"></small>
			<button type="button" class="ml-2 mb-1 close" data-dismiss="toast"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<div class="toast-body" id="broadcastMessage">Hello, world! This is a toast message.</div>
	</div>

	<span id="memberNo">${memberBean.memberNo}</span> |
	${memberBean.nickname} 已登入聊天室
	<br>
	<img
		src="${pageContext.servletContext.contextPath}${memberBean.picturePath}"
		width="70">
	<input type="text" id="inputText" value="hi">
	<button id="sendButton" disabled>send</button>
	<table border="1">
		<tr>
			<td style="vertical-align: text-top;"><c:forEach var="entity"
					items="${friendBeanList}">
					<div class="section">
						<span class="memberNo2">${entity.friendNo.memberNo}</span> | <span
							id="nicknameOf${entity.friendNo.memberNo}"> ${entity.friendNo.nickname}</span><br>
						<img 
							src="${pageContext.servletContext.contextPath}${entity.friendNo.picturePath}"
							width="70"
							id="imageOf${entity.friendNo.memberNo}"> <br> 
							狀態： <span id="statusOf${entity.friendNo.memberNo}" style="color: #ccc">offline</span><br>
						未讀：<span id="unreadOf${entity.friendNo.memberNo}">
						
							<c:if test="${entity.unread !=0}">
								<span style="color:red">${entity.unread}</span>
							</c:if>
							<c:if test="${entity.unread ==0}">
								${entity.unread}
							</c:if>
							
							</span>
						<br>
						<button class="openChatButton">開啟聊天室</button>
						<hr>
					</div>
				</c:forEach></td>
			<td width="300"><span id="chatBoard"></span></td>

		</tr>
	</table>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
		integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js"
		integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js"
		integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k"
		crossorigin="anonymous"></script>
</body>

<script type="text/javascript">
	function doFirst() {
		//一些控制用的變數
		firstLogin = true;
		//剛開始登入的500毫秒，不要顯示上線推播
		setTimeout(function(){
			firstLogin=false;
		},500);
		
		//跟畫面產生關聯
		memberNo1 = parseInt(document.getElementById('memberNo').innerText);
		memberNo2 = null;
		openChatButtons = document.getElementsByClassName('openChatButton');
		sections = document.getElementsByClassName('section');
		sendButton = document.getElementById('sendButton');
		chatBoard = document.getElementById('chatBoard');
		sendTime =document.getElementById('sendTime');
		
		broadcastMember =document.getElementById('broadcastMember');
		broadcastMessage =document.getElementById('broadcastMessage');
		broadcastImage =document.getElementById('broadcastImage');
		
		
		//建立事件聆聽功能
		for (var i = 0; i < openChatButtons.length; i++) {
			openChatButtons[i].addEventListener('click', openChat);
		}
		sendButton.addEventListener('click', sendMessage);
		
		//開啟socket
		openSession();

	}
	
	//送出聊天訊息
	function sendMessage() {
		var message = document.getElementById('inputText').value;

		var sendToSocketMessage = '' + memberNo1 + '|' + message + '|'
				+ memberNo2;

		ws.send(sendToSocketMessage);
	}
	
	
	//開啟聊天室窗，會做以下幾件重要的事
	//  1.確定memberNo2
	//  2.連上servlet請求聊天紀錄
	//  3.把unread歸零
	function openChat() {
		
		sendButton.disabled = false;	//開啟送信按鈕
		chatBoard.innerText = '';	//清空之前的聊天紀錄
		
		//先把所有開啟聊天室窗的按鈕和背景顏色回到原始狀態
		for (var i = 0; i < openChatButtons.length; i++) {
			openChatButtons[i].disabled = false;
			sections[i].style.backgroundColor = '#FFF';
		}
		
		//選到的朋友的開啟聊天室窗按鈕取消、並改變背景顏色
		this.parentNode.getElementsByClassName('openChatButton')[0].disabled = true;
		this.parentNode.style.backgroundColor = '#999';
		
		//確定memberNo2是誰，也就是當前的聊天人是誰，很重要
		memberNo2 = this.parentNode.getElementsByClassName('memberNo2')[0].innerText;
		
		//跟伺服器說要跟新資料庫的friend的unread欄位，我打開聊天室窗時，歸零，代表全部已讀
		//同時更改html裡的未讀數字歸零
		ws.send("resetUnread|" + memberNo2 + "|" + memberNo1);
		var unreadMember = document.getElementById('unreadOf' + memberNo2);
		unreadMember.innerText = '0';
		unreadMember.style.color = '#000';

		var xhr = new XMLHttpRequest();

		//送出URI請求，讀取聊天紀錄，回傳型態是JSONArray的List<ChatBean>
		xhr.onload = function(e) {
			//請求成功時，把紀錄加入聊天室窗
			if (this.readyState === 4) {
				console.log('readyState === 4')
				var jsonData = xhr.responseText;
				var jsonArray = JSON.parse(jsonData);

				//針對List<ChatBean>裡的ChatBean一項一項操作
				for (var i = 0; i < jsonArray.length; i++) {
					var creationDateTime = jsonArray[i].creationDateTime;
					var nickname = jsonArray[i].mb1.nickname;
					var message = jsonArray[i].message;
					var chatMessage = document.createElement('div');
					chatMessage.innerHTML = ""
						+ creationDateTime + " "
						+ nickname
						+ " says: <br>"
						+ message
						+ "<br><br>";
					chatBoard.appendChild(chatMessage);		//加到聊天室窗
				}

			}
		};
		
		//送出URI請求
		requestURI = '/Whiloud/chat/GetChatServlet?memberNo1=' + memberNo1
				+ '&memberNo2=' + memberNo2;
		xhr.open("POST", requestURI, true);
		xhr.send();
	}
	
	
	//--------------------------------
	//          socket相關
	//--------------------------------
	//一進頁面就會執行
	function openSession() {
		
		//與Server建立socket
		var wsLoc = "ws://localhost:8080/Whiloud/ChatSocketV2/" + memberNo1;
		//WebSocket可以選擇ws或是wss通訊協定，ws就相當於一般的http，wss則相當於https
		ws = new WebSocket(wsLoc);

		//當連線開啟時觸發
		ws.onopen = function() {
			console.log("Websocket is opened!!");

		};

		//收到訊息時觸發
		ws.onmessage = function(msgEvent) {
			console.log("Received Message!!", msgEvent);

			//收到的訊息格式為 action| (訊息,不一定會有) | 對象memberNo
			
			//先取出action，並做出對應的行動
			var action = msgEvent.data.substring(0, msgEvent.data.indexOf("|"));
			console.log("receive action = " + action);
			
			if (memberNo1 == parseInt(msgEvent.data.substr(msgEvent.data
					.lastIndexOf("|") + 1))) {
				console.log('對象是自己，不用處理action');
			} else if (action == "online") {	//代表誰上線了
				
				//找出送出訊息的來源是誰
				var onlineMember = msgEvent.data.substr(msgEvent.data
						.lastIndexOf("|") + 1);
				console.log("memberNo = " + onlineMember + " is online");
				
				//看看送出訊息的人，是不是自己的朋友，如果不是，則會為null
				var statusOfOnlineMember = document.getElementById('statusOf'
						+ onlineMember);
	
				//如果是自己的朋友
				if (statusOfOnlineMember != null) {
					
					//判斷要不要廣播，如果原本就顯示在線上的朋友，就不用廣播了
					//做以下的if判斷是為了排除：
					//	1.我自己才剛進頁面就收到一堆上線訊息會很惱人
					//	2.如果原本就在線上的人，可能只是按個重新整理，又會送出一次上線訊息，很惱人
					if(!firstLogin && statusOfOnlineMember.innerText.indexOf('offline') != -1){
						
						//準備廣播資訊
						var nickname = document.getElementById('nicknameOf' + onlineMember).innerText;
						var imageOf = document.getElementById('imageOf' + onlineMember);
						
						sendTime.innerHTML = 'now';
						broadcastMessage.innerHTML = '已上線';
						broadcastMember.innerHTML = nickname + ' &nbsp;&nbsp;';
						broadcastImage.src = imageOf.src;
						
						//送出廣播
						$('.toast').toast();
						$('.toast').toast('show');
					}
				
					//如果是從自己朋友送來的上線訊息，則顯示朋友的上線狀態
					statusOfOnlineMember.innerHTML = '<span style="color:green"> online </span>';
				}

			} else if (action == "offline") { //代表誰離線了
				//找出是誰離線
				var offlineMember = msgEvent.data.substr(msgEvent.data
						.lastIndexOf("|") + 1);
				console.log("memberNo = " + offlineMember + " is offline");
				var statusOfOnlineMember = document.getElementById('statusOf'
						+ offlineMember);
				
				//看看離線的是不是自己的朋友
				if (statusOfOnlineMember != null) {
					//如果是自己的朋友，就改變顯示狀態，並送出廣播
					statusOfOnlineMember.innerHTML = '<span style="color:#ccc"> offline </span>';
					
					//準備廣播資訊
					var nickname = document.getElementById('nicknameOf' + offlineMember).innerText;
					var imageOf = document.getElementById('imageOf' + offlineMember);
					
					sendTime.innerHTML = 'now';
					broadcastMessage.innerHTML = '已下線';
					broadcastMember.innerHTML = nickname + ' &nbsp;&nbsp;';
					broadcastImage.src = imageOf.src;
					
					//在伺服器端已做了防止重新整理的斷線狀態，所以可以安心廣播
					$('.toast').toast();
					$('.toast').toast('show');
				}
			} else if (action == "return") {	//如果自己的訊息確定被存到資料庫，server回傳自己講的話
				var message = msgEvent.data.substr(msgEvent.data
						.lastIndexOf("|") + 1);
				var chatMessage = document.createElement('div');
				chatMessage.innerHTML = "" + message + "<br>";
				chatBoard.appendChild(chatMessage);
			} else if (action == "message") {	//如果是別人送來的訊息
				var sendFrom = msgEvent.data.substr(msgEvent.data
						.lastIndexOf("|") + 1);
				var message = msgEvent.data.substring(msgEvent.data
						.indexOf("|") + 1, msgEvent.data.lastIndexOf("|"));
				//如果現在正在對話的人跟送來訊息的人同一個，就歸零未讀，不用廣播
				if (sendFrom == memberNo2) {
					var chatMessage = document.createElement('div');
					chatMessage.innerHTML = "" + message + "<br>";
					chatBoard.appendChild(chatMessage);
					ws.send("resetUnread|" + memberNo1 + "|" + memberNo2);
					var unreadMember = document.getElementById('unreadOf' + sendFrom);
					unreadMember.innerText = '0';
					unreadMember.style.color = '#000';
					
				} else {	//如果線自正在對話的人跟送來訊息的人不同，則未讀+1，顯示broadCast
					var unreadMember = document.getElementById('unreadOf'
							+ sendFrom);
					if(unreadMember!=null){	//如果送來的人確定是自己的朋友
						unreadMember.innerText = parseInt(unreadMember.innerText) + 1;
						unreadMember.style.color = 'red';
						
						//準備廣播資訊
						var nickname = document.getElementById('nicknameOf' + sendFrom).innerText;
						var time = message.substring(11, 16);
						var imageOf = document.getElementById('imageOf' + sendFrom);
						
						
						sendTime.innerHTML = time;
						message = message.substr(message.indexOf('says') + 10);
						broadcastMessage.innerHTML = message;
						broadcastMember.innerHTML = nickname + ' says: &nbsp;&nbsp;';
						broadcastImage.src = imageOf.src;
						
						$('.toast').toast();
						$('.toast').toast('show');
					}
					
				}
			}
		};

	}

	window.addEventListener('load', doFirst);
</script>
</html>