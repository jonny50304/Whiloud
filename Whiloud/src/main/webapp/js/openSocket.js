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

	memberNo1 = document.getElementById('memberNo1').value;

	toastSection =  document.getElementById("toastSection");
	
	//獲取朋友清單，並把每位好友加上監聽器
	loadFriendList();

	//開啟Web Socket, 進來幾秒鐘後再開啟
	setTimeout(function(){
		openWebSocket();
	},300);
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
						'<div class="toast-body" id="toastMessageOf'+ friendNo +'">已下線</div>' +
						'</div>';
					document.getElementById('toastImageOf' + friendNo).src = "/Whiloud" + picturePath;
					
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

			
			//判斷要不要廣播，如果原本就顯示在線上的朋友，就不用廣播了
			//做以下的if判斷是為了排除：
			//	1.我自己才剛進頁面就收到一堆上線訊息會很惱人
			//	2.如果原本就在線上的人，可能只是按個重新整理，又會送出一次上線訊息，很惱人
			var toastMessage = parent.document.getElementById('toastMessageOf' + onlineMember);
			var toastTime = parent.document.getElementById('toastTimeOf' + onlineMember);
			if(!firstLogin && toastMessage.innerText == '已下線'){

				//準備廣播資訊
				//var toast = parent.document.getElementById('toastOf' + onlineMember);
				
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
				//把會員移到最前面
				$('#friendListBoard').prepend($('#sectionOf' + onlineMember));
				
			}

		} else if (action == "offline") { //代表誰離線了
			//找出是誰離線
			var offlineMember = msgEvent.data.substr(msgEvent.data
					.lastIndexOf("|") + 1);
			console.log("memberNo = " + offlineMember + " is offline");

				
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
				
				
			
		} else if (action == "message") {	//如果是別人送來的訊息
			var sendFrom = msgWithoutAction.substr(msgWithoutAction.lastIndexOf("|") + 1);
//			alert('memberNo2:' + memberNo2 + ", sendFrom: " +sendFrom);
			var DateTimeFormat = msgWithoutAction.substring(0, msgWithoutAction.indexOf("|"));
			var msgWithoutDate = msgWithoutAction.substr(msgWithoutAction.indexOf("|")+1);
			var nickname = msgWithoutDate.substring(0, msgWithoutDate.indexOf("|"));
			var message = msgWithoutDate.substring(msgWithoutDate.indexOf("|") + 1, msgWithoutDate.lastIndexOf("|"));
		
			//準備廣播資訊
			var toastMessage = parent.document.getElementById('toastMessageOf' + sendFrom);
			var toastTime = parent.document.getElementById('toastTimeOf' + sendFrom);
			toastMessage.innerText = message;
			toastTime.innerText = 'now';
			//alert(sendFrom);
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

				
		}
		
	};
	
	
}


window.addEventListener('load', doFirst);