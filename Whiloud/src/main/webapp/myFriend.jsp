<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
<!-- <link rel="stylesheet" -->
<!-- 	href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css" -->
<!-- 	integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" -->
<!-- 	crossorigin="anonymous"> -->
</head>

<style>
    *{
        font-family: Microsoft JhengHei;
    }
    body {
        width: 30%;
        height: 100%;
    }

    .myFriend {
        /* border: 2px solid slateblue; */
        width: 200px;
        height: 75px;
        margin: 0px;
        background: url("../images/123456.jpeg");
        border-radius: 10%;
    }

    .myFriend p {
        /* padding-left:80px;
        padding-top: 10px;  */
        position: relative;
        top: 10px;
        left: 10px;
    }
    
        .myFriend p {
        /* padding-left:80px;
        padding-top: 10px;  */
        position: relative;
        top: 10px;
        left: 10px;
        overflow:hidden;
text-overflow:ellipsis; 
white-space:nowrap;  */
    }

    .myFriend img {
        border-radius: 100%;
        border: 1px solid #999;
        width: 65px;
        float: left;
        margin: 5px;
    }

    .invite {
/*         background: url("../images/12345.jpg") !important; */
        background-color:skyblue;
    }
</style>

<body>
	<input type="hidden" id="memberNo1" value="${LoginOK.memberNo}">
	<span id="friendRequestBoard">
<!-- 		<div class="myFriend" id="invite"> -->
<!-- 	        <img src="images/11.jpg" alt=""> -->
<!-- 	        <p>如花</p> -->
<!-- 	        <p style="text-align: right"><input type="button" value="同意">&nbsp;&nbsp;&nbsp; -->
<!-- 	            &nbsp;&nbsp;&nbsp;<input type="button" value="拒絕"></p> -->
<!-- 	    </div> -->
			
	</span>
    <span id="friendListBoard">
<!-- 	    <div class="myFriend"> -->
<!-- 	        <img src="images/1.jpg" alt=""> -->
<!-- 	        <p>abcabc <button> click</button></p> -->
<!-- 	        <p style="text-align:right;">  未讀：3 | <span style="coler:orange">線上&nbsp; </span></p> -->
<!-- 	    </div> -->
    </span>
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

<script>
function doFirst(){
	//一些控制用的變數
	firstLoadingFriendList = true;
	firstLogin = true;
	memberNo2 = null;
	toastTimeout = null;
	//剛開始登入的500毫秒，不要顯示上線推播
	setTimeout(function(){
		firstLogin=false;
	},500);
	
	friendList = document.getElementById('friendList');
	friendListBoard = document.getElementById('friendListBoard');
	memberNo1 = document.getElementById('memberNo1').value;
	
	chatBoardFrame =  parent.document.getElementById("chatBoardFrame").contentDocument;
	sendButton = chatBoardFrame.getElementById('sendButton');
	chatBoard = chatBoardFrame.getElementById('chatBoard');
	msg_end = chatBoardFrame.getElementById('msg_end');
	
	toastSection =  parent.document.getElementById("toastSection");
	
	broadcastMember = parent.document.getElementById('broadcastMember');
	broadcastMessage = parent.document.getElementById('broadcastMessage');
	broadcastImage = parent.document.getElementById('broadcastImage');
	
	//加入click事件
	sendButton.addEventListener('click', sendMessage)
	
	//獲取朋友朋友邀請清單
	loadFriendRequest();
	
	//獲取朋友清單，並把每位好友加上監聽器
	loadFriendList();
	

	//開啟Web Socket, 進來幾秒鐘後再開啟
	setTimeout(function(){
		openWebSocket();
	},300);
	
	//複製過來的程式碼，有關於日期格式化
	Date.prototype.Format = function (fmt) { //author: meizz 
	    var o = {
	        "M+": this.getMonth() + 1, //月份 
	        "d+": this.getDate(), //日 
	        "h+": this.getHours(), //小时 
	        "m+": this.getMinutes(), //分 
	        "s+": this.getSeconds(), //秒 
	        "S": this.getMilliseconds() //毫秒 
	    };
	    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	    for (var k in o)
	    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    return fmt;
	}
	
}

function loadFriendRequest(){
	var xhr = new XMLHttpRequest();

	//送出URI請求，讀取聊天紀錄，回傳型態是JSONArray的List<ChatBean>
	xhr.onload = function(e) {
		//請求成功時，把紀錄加入聊天室窗
		if (this.readyState === 4) {
			console.log('readyState === 4')
			var jsonData = xhr.responseText;
			var jsonArray = JSON.parse(jsonData);

			//針對List一項一項操作
			for (var i = 0; i < jsonArray.length; i++) {
				
				var friendNo = jsonArray[i].memberNo.memberNo;
				var nickname = jsonArray[i].memberNo.nickname;
				var picturePath = jsonArray[i].memberNo.picturePath;
				
				console.log('讀入送出邀請的朋友friendNo: ' + friendNo);
				console.log('讀入送出邀請的朋友nickname: ' + nickname);

				
				//動態新增每個好友的div，並加上openChat的click事件
				friendRequestBoard.innerHTML += 
					'<div class="myFriend invite" id="requesterOf'+ friendNo +'">' +
					'	<img src="" alt="" id="requesterPictureOf'+ friendNo +'">' +
					'	<p><span style="font-size:10px">交友邀請: <br></span>'+ nickname +'</p>' +
					'	<p style="text-align: right; top:5px;">' +
					'		<input type="button" value="同意" onClick="becomeFriend('+friendNo+')">' +
					'		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;' +
					'		<input type="button" value="拒絕" onClick="rejectRequest('+friendNo+')">' +
					'	</p>' +
					'</div>';
				document.getElementById('requesterPictureOf' + friendNo).src = '/Whiloud' + picturePath;
			}
		}
	};
	//送出URI請求
	requestURI = '/Whiloud/GetFriendRequestServletJSON';
	xhr.open("POST", requestURI, true);
	xhr.send();
}

function becomeFriend(friendNo){
	var xhr = new XMLHttpRequest();
	
	xhr.onload =function(e){
		if (this.readyState === 4) {
			console.log('readyState === 4')
			
			$("#requesterOf"+ friendNo).remove();
			
			$("#friendListBoard").empty();
			loadFriendList();
		}
	}
	
	requestURI = '/Whiloud/AcceptRequestServletV2?friendNo='+ friendNo;
	xhr.open("POST", requestURI, true);
	xhr.send();
}

function rejectRequest(friendNo){
	var xhr = new XMLHttpRequest();
	
	xhr.onload =function(e){
		if (this.readyState === 4) {
			console.log('readyState === 4')
			
			$("#requesterOf"+ friendNo).remove();
		}
	}
	
	requestURI = '/Whiloud/AcceptRequestServletV2?action=rejectRequest&friendNo='+ friendNo;
	xhr.open("POST", requestURI, true);
	xhr.send();
}


function openWebSocket(){
	//與Server建立socket
	var wsLoc = null;
	if(location.protocol == "https:"){
		wsLoc = "wss://"+ location.hostname + ":" + location.port + "/Whiloud/ChatSocketV3/" + memberNo1;
	}else{
		wsLoc = "ws://"+ location.hostname + ":" + location.port + "/Whiloud/ChatSocketV3/" + memberNo1;
	}

// 	var wsLoc = "ws://192.168.12.10:8080/Whiloud/ChatSocketV3/" + memberNo1;
	//WebSocket可以選擇ws或是wss通訊協定，ws就相當於一般的http，wss則相當於https
	ws = new WebSocket(wsLoc);
	
	

	//當連線開啟時觸發
	ws.onopen = function() {
		console.log("Websocket is opened!!");
	}
	
	//收到訊息時觸發
	ws.onmessage = function(msgEvent) {
		console.log("Received Message!!", msgEvent);

		//收到的訊息格式為 action| (訊息,不一定會有) | 對象memberNo
		
		//先取出action，並做出對應的行動
		var action = msgEvent.data.substring(0, msgEvent.data.indexOf("|"));
		var msgWithoutAction = msgEvent.data.substr(msgEvent.data.indexOf("|") +1);
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
					//var toast = parent.document.getElementById('toastOf' + onlineMember);
					var toastMessage = parent.document.getElementById('toastMessageOf' + onlineMember);
					var toastTime = parent.document.getElementById('toastTimeOf' + onlineMember);
					toastMessage.innerText = '已上線';
					toastTime.innerText = 'now';
					
					//在伺服器端已做了防止重新整理的斷線狀態，所以可以安心廣播
					parent.document.getElementById('toastTop').style.zIndex='9999';
	 				$(window.parent.document).find('#toastOf'+onlineMember).toast();
	 				$(window.parent.document).find('#toastOf'+onlineMember).toast('show');
	 				
	 				if(toastTimeout!=null){
	 					clearTimeout(toastTimeout);
	 				}
	 				toastTimeout = setTimeout(function(){
	 					parent.document.getElementById('toastTop').style.zIndex='-1';
	 				},2000);
					
				}
			
				//如果是從自己朋友送來的上線訊息，則顯示朋友的上線狀態
				statusOfOnlineMember.innerHTML = '<span style="color:rgb(255, 94, 0);"> online </span>';
				
				//把會員移到最前面
				$('#friendListBoard').prepend($('#sectionOf' + onlineMember));
				
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
				
				var toastMessage = parent.document.getElementById('toastMessageOf' + offlineMember);
				var toastTime = parent.document.getElementById('toastTimeOf' + offlineMember);
				toastMessage.innerText = '已下線';
				toastTime.innerText = 'now';
				
				//在伺服器端已做了防止重新整理的斷線狀態，所以可以安心廣播
				parent.document.getElementById('toastTop').style.zIndex='9999';
 				$(window.parent.document).find('#toastOf'+offlineMember).toast();
 				$(window.parent.document).find('#toastOf'+offlineMember).toast('show');
 				if(toastTimeout!=null){
 					clearTimeout(toastTimeout);
 				}
 				toastTimeout = setTimeout(function(){
 					parent.document.getElementById('toastTop').style.zIndex='-1';
 				},2000);
				
				
			}
		} else if (action == "return") {	//如果自己的訊息確定被存到資料庫，server回傳自己講的話
			var message = msgWithoutAction.substr(msgWithoutAction.lastIndexOf("|") + 1);
			var DateTimeFormat = msgWithoutAction.substring(0, msgWithoutAction.indexOf("|"));	//已在伺服器端格式化
			var nickname = "我";

			var chatMessage = document.createElement('div');
			chatMessage.setAttribute("class","send");
			chatMessage.innerHTML = ""
				+ "<span style='font-size:10px; color:#ccc;'>" + DateTimeFormat + " </span>"
				+ nickname + ": "
				+ " <br>"
				+ message
				+ "<br><br>";
			chatBoard.appendChild(chatMessage);		//加到聊天室窗

			chatBoard.scrollTop = chatBoard.scrollHeight;	//滑到最底端
			
		} else if (action == "message") {	//如果是別人送來的訊息
			var sendFrom = msgWithoutAction.substr(msgWithoutAction.lastIndexOf("|") + 1);
			//alert('memberNo2:' + memberNo2 + ", sendFrom: " +sendFrom);
			var DateTimeFormat = msgWithoutAction.substring(0, msgWithoutAction.indexOf("|"));
			var msgWithoutDate = msgWithoutAction.substr(msgWithoutAction.indexOf("|")+1);
			var nickname = msgWithoutDate.substring(0, msgWithoutDate.indexOf("|"));
			var message = msgWithoutDate.substring(msgWithoutDate.indexOf("|") + 1, msgWithoutDate.lastIndexOf("|"));
			
			//如果現在正在對話的人跟送來訊息的人同一個，就歸零未讀，不用廣播
			if (sendFrom == memberNo2) {

				var chatMessage = document.createElement('div');
				chatMessage.setAttribute("class","receive");
				chatMessage.innerHTML = ""
					+ "<span style='font-size:10px; color:#ccc;'>" + DateTimeFormat + "</span>"
					+ nickname + ": "
					+ " <br>"
					+ message
					+ "<br><br>";
				chatBoard.appendChild(chatMessage);		//加到聊天室窗
				
				ws.send("resetUnread|" + memberNo1 + "|" + memberNo2);
				var unreadMember = document.getElementById('unreadOf' + sendFrom);
				unreadMember.innerText = '0';
				unreadMember.style.color = '#000';
				
				chatBoard.scrollTop = chatBoard.scrollHeight;	//滑到最底端
				
				//把會員移到最前面
				$('#friendListBoard').prepend($('#sectionOf' + sendFrom));
			}	
			 else {	//如果線自正在對話的人跟送來訊息的人不同，則未讀+1，顯示broadCast
				var unreadMember = document.getElementById('unreadOf' + sendFrom);
				if(unreadMember!=null){	//如果送來的人確定是自己的朋友
					unreadMember.innerText = parseInt(unreadMember.innerText) + 1;
					unreadMember.style.color = 'red';
					
					//準備廣播資訊
					//var toast = parent.document.getElementById('toastOf' + offlineMember);
					var toastMessage = parent.document.getElementById('toastMessageOf' + sendFrom);
					var toastTime = parent.document.getElementById('toastTimeOf' + sendFrom);
					toastMessage.innerText = message;
					toastTime.innerText = 'now';
					
					//在伺服器端已做了防止重新整理的斷線狀態，所以可以安心廣播
					parent.document.getElementById('toastTop').style.zIndex='9999';
	 				$(window.parent.document).find('#toastOf'+sendFrom).toast();
	 				$(window.parent.document).find('#toastOf'+sendFrom).toast('show');
	 				
	 				if(toastTimeout!=null){
	 					clearTimeout(toastTimeout);
	 				}
	 				toastTimeout = setTimeout(function(){
	 					parent.document.getElementById('toastTop').style.zIndex='-1';
	 				},2000);
					
					
	 				//把會員移到最前面
					$('#friendListBoard').prepend($('#sectionOf' + sendFrom));
				}
				
			}
		}
	};
	
	
}




function loadFriendList(){
	
	var xhr = new XMLHttpRequest();

	//送出URI請求，讀取聊天紀錄，回傳型態是JSONArray的List<ChatBean>
	xhr.onload = function(e) {
		//請求成功時，把紀錄加入聊天室窗
		if (this.readyState === 4) {
			console.log('readyState === 4')
			var jsonData = xhr.responseText;
			var jsonArray = JSON.parse(jsonData);

			//針對List一項一項操作
			for (var i = 0; i < jsonArray.length; i++) {
				
				var friendNo = jsonArray[i].friendNo.memberNo;
				var nickname = jsonArray[i].friendNo.nickname;
				var picturePath = jsonArray[i].friendNo.picturePath;
				console.log('讀入朋友friendNo: ' + friendNo);
				console.log('讀入朋友nickname: ' + nickname);
				var unread = jsonArray[i].unread;
				if (unread != "0"){
					unreadText = '     未讀：<span id="unreadOf'+ friendNo +'" style="color:red">' + unread+ '</span> | ';
					
				}else{
					unreadText = '     未讀：<span id="unreadOf'+ friendNo +'">' + unread + '</span> | ';
				}
				
				//動態新增每個好友的div，並加上openChat的click事件
				friendListBoard.innerHTML += 
					'<div class="myFriend"   onClick="openChat('+friendNo+')" style="z-index:9998" id="sectionOf'+ friendNo +'"> ' +
					'<img src="/Whiloud' + picturePath + ' " alt="" id="pictureOf'+ friendNo +'">' +
					' <p id="nicknameOf'+ friendNo +'"> ' + nickname + '</p> ' +
					' <p style="text-align:right;">'+
					'' + unreadText +
					' <span style="color:#ccc" id="statusOf'+ friendNo +'"> offline </span></p> ' +
					'</div> ';
				
				//有未讀的會員移到前面
				if (unread != "0"){
					$('#friendListBoard').prepend($('#sectionOf' + friendNo));
				}
				
				
				if(firstLoadingFriendList){
					toastSection.innerHTML +=
						'<div class="toast" id="toastOf'+ friendNo +'" role="alert" aria-live="assertive" aria-atomic="true"  data-delay="2000">' +
						'<div class="toast-header">' +
						'<img src="" class="rounded mr-2" id="toastImageOf'+ friendNo +'" width="30">' +
						'<strong class="mr-auto" id="toastNicknameOf'+ friendNo +'">' + nickname + '&nbsp;&nbsp;</strong>' +
						'<small class="text-muted" id="toastTimeOf'+ friendNo +'"></small>' +
						'<button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">' +
						'<span aria-hidden="true">&times;</span>' +
						'</button>' +
						'</div>' +
						'<div class="toast-body" id="toastMessageOf'+ friendNo +'">Hello, world! This is a toast message.</div>' +
						'</div>';
					parent.document.getElementById('toastImageOf' + friendNo).src = document.getElementById('pictureOf' + friendNo).src;
					
					
				}
				
				
				

			}
			firstLoadingFriendList=false;
		}
	};
	//送出URI請求
	requestURI = '/Whiloud/GetFriendListServletJSON';
	xhr.open("POST", requestURI, true);
	xhr.send();
}


//按下每位好友時要讀取聊天紀錄和聊天室窗
function openChat(friendNo){
	
	parent.document.getElementById('introduce').style.display='none';
	parent.document.getElementById('friendPage').src = '/Whiloud/EnterFriendPageServlet?memberNo=' + friendNo;
	
	memberNo2 = friendNo;
	var picturePath = document.getElementById('pictureOf' + friendNo).src;
	var nickname = document.getElementById('nicknameOf' + friendNo).innerText;
	
	var memberNo2Nickname = chatBoardFrame.getElementById('memberNo2Nickname');
	var memberNo2Picture = chatBoardFrame.getElementById('memberNo2Picture');

	
	
	//跟伺服器說要跟新資料庫的friend的unread欄位，我打開聊天室窗時，歸零，代表全部已讀
	//同時更改html裡的未讀數字歸零
	ws.send("resetUnread|" + memberNo2 + "|" + memberNo1);
	var unreadCounts = document.getElementById('unreadOf' + memberNo2);
	unreadCounts.innerText = '0';

	sendButton.disabled = false;	//開啟送信按鈕
	chatBoard.innerText = '';	//清空之前的聊天紀錄
	
	memberNo2Picture.src = picturePath;
	memberNo2Nickname.innerText = nickname;
	
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
				var DateTimeFormat = new Date(creationDateTime).Format("yyyy/MM/dd hh:mm");;
				console.log(DateTimeFormat);
				var nickname = jsonArray[i].mb1.nickname;
				var message = jsonArray[i].message;
				var chatMessage = document.createElement('div');
				
				//判斷是我傳的句子還是別人傳的
				if (nickname == memberNo2Nickname.innerText){
					chatMessage.setAttribute("class","receive");
					
					chatMessage.innerHTML = ""
						+ nickname + ": "
						+ "<span style='font-size:10px; color:#ccc;'>" + DateTimeFormat + " </span>"
						+ " <br>"
						+ message
						+ "<br><br>";
				}else{
					chatMessage.setAttribute("class","send");
					nickname = "我";
					
					chatMessage.innerHTML = ""
						+ "<span style='font-size:10px; color:#ccc;'>" + DateTimeFormat + " </span>"
						+ nickname + ": "
						+ " <br>"
						+ message
						+ "<br><br>";
				}

				chatBoard.appendChild(chatMessage);		//加到聊天室窗
			}
			
			chatBoard.scrollTop = chatBoard.scrollHeight;	//滑到最底端

		}
	};
	
	//送出URI請求
	requestURI = '/Whiloud/chat/GetChatServlet?memberNo1=' + memberNo1
			+ '&memberNo2=' + memberNo2;
	xhr.open("POST", requestURI, true);
	xhr.send();
}

//送出聊天訊息
function sendMessage() {
	var message = chatBoardFrame.getElementById('messageInput').value;
	chatBoardFrame.getElementById('messageInput').placeholder = '';
	chatBoardFrame.getElementById('messageInput').value = '';
	var sendToSocketMessage = '' + memberNo1 + '|' + message + '|'
			+ memberNo2;

	ws.send(sendToSocketMessage);
}

window.addEventListener('load', doFirst);
</script>
</html>