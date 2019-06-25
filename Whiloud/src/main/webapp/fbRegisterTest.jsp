<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<button id="fbRegisterButton">Login</button>
<div id="status">
</div>
</body>
<script>
function doFirst(){
	fbRegisterButton = document.getElementById('fbRegisterButton');
	fbRegister.addEventListener('click',fbRegister);
}

function fbRegister(){
	FB.login(function(response) {
		statusChangeCallback(response);
	}, {
		scope : 'public_profile,email,user_gender'
	});
}

function statusChangeCallback(response) {
    console.log('statusChangeCallback');
    console.log(response);
    
    if (response.status === 'connected') {
      // Logged into your app and Facebook.
      processFbData()
    } else {
      // The person is not logged into your app or we are unable to tell.
      alert('FB登入失敗');
    }
}

function processFbData(){
	console.log('Welcome!  Fetching your information.... ');
    FB.api('/me?fields=id,name,email,gender,picture', function(response) {
    	console.log('response = ' + JSON.stringify(response));
    	console.log('Successful login for: ' + response.id);
    	console.log('Successful login for: ' + response.name);
    	console.log('Successful login for: ' + response.gender);
    	pictureLink = 'https://graph.facebook.com/' + response.id + '/picture';
// 		console.log(pictureLink);
	    document.getElementById('status').innerHTML =
	       'Thanks for logging in, ' + response.name + '!' 
	        +'<br>'+ response.email
	        +'<br>'+ response.gender
			+'<br><img src=" ' + pictureLink + '"> '
			+'<br><img src=" ' + pictureLink + '?type=large"> '
			+'<br>' + JSON.stringify(response);
	    //blob = dataURLtoBlob(pictureLink);
	    //console.log(blog);
		
    });
}

//複製來的程式碼
function dataURLtoBlob(dataurl) {
    var arr = dataurl.split(','), mime = arr[0].match(/:(.*?);/)[1],
    bstr = atob(arr[1]), n = bstr.length, u8arr = new Uint8Array(n);
    while(n--){
      u8arr[n] = bstr.charCodeAt(n);
    }
  return new Blob([u8arr], {type:mime});
}

window.addEventListener('load',doFirst);
</script>
</html>