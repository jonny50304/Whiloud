function fbLoginOrRegister(){
	
	var xhr=new XMLHttpRequest();
	xhr.onload=function(e) {
		//如果上傳都沒錯誤
		if(this.readyState === 4 && this.status===200) {
			console.log("Server returned: ",e.target.responseText);
			
			window.location.href="main.jsp";

		}
	};
	
	xhr.open("POST","/Whiloud/fbLoginOrRegisterServlet",true);
	xhr.send(formData);
	
}

	window.fbAsyncInit = function() {
		FB.init({
			appId : '358969268159045', // 填入 FB APP ID
			cookie : true,
			xfbml : true,
			version : 'v3.2'
		});

		FB.getLoginStatus(function(response) {
			statusChangeCallback(response);
		});
	};

	// 處理各種登入身份
	function statusChangeCallback(response) {
		console.log(response);
		var target = document.getElementById("FB_STATUS_2"), html = "";

		// 登入 FB 且已加入會員
		if (response.status === 'connected') {

			FB.api('/me?fields=id,name,email,gender,picture',
							function(response) {
								var FB_login =  document.getElementById('FB_login');
								console.log(response);
								console.log('Successful login for: '
										+ response.id);
								console.log('Successful login for: '
										+ response.name);
								console.log('Successful login for: '
										+ response.gender);

								
								pictureLink = 'https://graph.facebook.com/'
										+ response.id + '/picture?type=large&redirect=false';
								console.log(pictureLink);
								$.getJSON(pictureLink,function(result){
								    $.each(result, function(i, field){

 		 								blob=null;
										fetch(field.url).then(r => r.blob()).then(function(result) {
									        console.log(result);
									        formData = new FormData();
			 								formData.append("account", response.email);
			 								formData.append("nickname", response.name);
			 								formData.append("gender", response.gender);
			 								formData.append("picture", result, "picture" + ".jpg");
									    });

										FB_login.innerText = 'FB登入';
										if(document.getElementById('errorMessage').innerText.trim().length == 0){
											document.getElementById('errorMessage').innerText='連結FB成功,可用FB登入開始你的Whiloud';
										}
										FB_login.addEventListener('click', fbLoginOrRegister);
								    });
								});
							    
							});
		}

		// 登入 FB, 未偵測到加入會員
		else if (response.status === "not_authorized") {
			console.log("已登入 FB，但未加入 whiloud 應用程式");
		}

		// 未登入 FB
		else {
			console.log("未登入 FB");
		}
	}

	// 點擊登入
	$("#FB_login").click(function() {
		// 進行登入程序
		FB.login(function(response) {
			statusChangeCallback(response);
		}, {
			scope : 'public_profile,email,user_gender'
		});
	});

//	// 點擊登出
//	$("#FB_logout").click(function() {
//		FB.logout(function(response) {
//			statusChangeCallback(response);
//		});
//	});

	// 載入 FB SDK
	(function(d, s, id) {
		var js, fjs = d.getElementsByTagName(s)[0];
		if (d.getElementById(id))
			return;
		js = d.createElement(s);
		js.id = id;
		js.src = "https://connect.facebook.net/zh_TW/sdk.js";
		fjs.parentNode.insertBefore(js, fjs);
	}(document, 'script', 'facebook-jssdk'));
