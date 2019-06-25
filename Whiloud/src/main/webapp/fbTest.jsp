<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!--jQuery-->
<!-- <script src='//ajax.googleapis.com/ajax/libs/jquery/2.0.0/jquery.min.js'></script> -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<!--Bootstrap-->
<link
	href='//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css'
	rel='stylesheet'></link>

</head>
<body>
	<!--登入、登出按鈕-->
	<button id="FB_login" class="btn btn-large btn-primary">FB 連結</button>
	<button id="FB_logout" class="btn btn-large btn-warning">FB 登出</button>

	目前狀態：
	<span id="FB_STATUS_2"></span>
	<div id="status"></div>
	<form action="fbLoginOrRegisterServlet" method="POST" id="fbForm">
		<input type="hidden" name="account" id ="account">
		<input type="hidden" name="nickname" id ="nickname">
		<input type="hidden" name="gender" id ="gender">
		<input type="hidden" name="picturePath" id ="picturePath">
		<input type="file" name="pictureFile" id ="pictureFile">
	</form>
</body>
<script>
function fbLoginOrRegister(){
	
	var xhr=new XMLHttpRequest();
	xhr.onload=function(e) {
		//如果上傳都沒錯誤
		if(this.readyState === 4 && this.status===200) {
			console.log("Server returned: ",e.target.responseText);
			//window.location.href="../index.jsp";
			//parent.location.reload();
			window.location.href="main.jsp";
			//alert('upload');
			
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

			FB
					.api('/me?fields=id,name,email,gender,picture',
							function(response) {
								var FB_login =  document.getElementById('FB_login');
								console.log(response);
								console.log('Successful login for: '
										+ response.id);
								console.log('Successful login for: '
										+ response.name);
								console.log('Successful login for: '
										+ response.gender);
// 								console.log(response.picture);
// 								console.log(response.picture.data.url);
// 								blob = dataURItoBlob(response.picture.data.url);
// 								console.log(blob);
								
								pictureLink = 'https://graph.facebook.com/'
										+ response.id + '/picture?type=large&redirect=false';
								console.log(pictureLink);
								$.getJSON(pictureLink,function(result){
								    $.each(result, function(i, field){
								      $("#status").append(field.url + " ");
								    	//blob = dataURItoBlob(field.url);
								    	 
								    	//blob = getImageBlob(field.url);
		 								//console.log(blob);
		 								
// 		 								$.get('blob:'+ field.url).then(function(data) {
// 										 blob = new Blob([data], { type: 'image/jpeg' });
// 										});
 		 								blob=null;
										fetch(field.url).then(r => r.blob()).then(function(result) {
									        console.log(result);
									        formData = new FormData();
			 								formData.append("account", response.email);
			 								formData.append("nickname", response.name);
			 								formData.append("gender", response.gender);
			 								formData.append("picture", result, "picture" + ".jpg");
									    });
										//console.log(blob);
// 										let file = 
// 											fetch(field.url)
// 											.then(r => r.blob()).then(blobFile => new File([blobFile], "fileNameGoesHere", { type: "image/png" }));
// 										console.log(file);

								    });
								});
// 								console.log(pictureLink);
// 								console.log()
// 								document.getElementById('account').value = response.email;
// 								document.getElementById('nickname').value = response.name;
// 								document.getElementById('gender').value = response.gender;
// 								document.getElementById('picturePath').value = pictureLink;
								
								FB_login.innerText = 'FB登入/註冊';
								FB_login.addEventListener('click', fbLoginOrRegister);
							    
							});
		}

		// 登入 FB, 未偵測到加入會員
		else if (response.status === "not_authorized") {
			target.innerHTML = "已登入 FB，但未加入 WFU BLOG DEMO 應用程式";
		}

		// 未登入 FB
		else {
			target.innerHTML = "未登入 FB";
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

	// 點擊登出
	$("#FB_logout").click(function() {
		FB.logout(function(response) {
			statusChangeCallback(response);
		});
	});

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
	
// 	function dataURItoBlob(dataURI) {
// 	    // convert base64/URLEncoded data component to raw binary data held in a string
// 	    var byteString;
// 	    if (dataURI.split(',')[0].indexOf('base64') >= 0)
// 	        byteString = atob(dataURI.split(',')[1]);
// 	    else
// 	        byteString = unescape(dataURI.split(',')[1]);

// 	    // separate out the mime component
// 	    var mimeString = dataURI.split(',')[0].split(':')[1].split(';')[0];

// 	    // write the bytes of the string to a typed array
// 	    var ia = new Uint8Array(byteString.length);
// 	    for (var i = 0; i < byteString.length; i++) {
// 	        ia[i] = byteString.charCodeAt(i);
// 	    }

// 	    return new Blob([ia], {type:mimeString});
// 	}

	
</script>

</html>