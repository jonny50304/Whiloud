function doFirst(){
	submitPasswordForm = document.getElementById('submitPasswordForm');
	
	//password = document.getElementById('password');
	//newPassword = document.getElementById('newPassword');
	//newPasswordCheck = document.getElementById('newPasswordCheck');
	
	passwordError = document.getElementById('passwordError');
	newPasswordError = document.getElementById('newPasswordError');
	
	submitPasswordButton = document.getElementById('submitPasswordButton');
	submitPasswordButton.addEventListener('click', submitPassword);
}

function submitPassword(){
error = false;

	passwordError.innerText = " ";
	newPasswordError.innerText = " ";
	
	password = document.getElementById('password').value;
	newPassword = document.getElementById('newPassword').value;
	newPasswordCheck = document.getElementById('newPasswordCheck').value;


	if(newPassword.search(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/) == -1){
		newPasswordError.innerHTML +="密碼至少8位，同時由字母和數字所組成 | ";
		error = true;

	}else if(newPassword!=newPasswordCheck){
		error = true;
		newPasswordError.innerHTML += "兩次密碼不一樣 | "
	}

	
	if(error==false){	
		var xhr = new XMLHttpRequest();
		xhr.onload = function(e) {

			if (this.readyState === 4) {
				console.log('readyState === 4')
				var jsonData = xhr.responseText;
				console.log(xhr.responseText);
				var jsonArray = JSON.parse(jsonData);
				
				for(var i=0; i<jsonArray.length; i++){
					var errorFromServer = jsonArray[i];
					if(errorFromServer == "passwordError"){
						passwordError.innerText += "原密碼輸入錯誤";
					}
					if(errorFromServer == "none"){
						submitPasswordForm.submit();
					}
				}
			}
		};
		
		//送出URI請求
		requestURI = '/Whiloud/CheckPasswordServletJSON?password=' + password;
		xhr.open("POST", requestURI, true);
		xhr.send();
	}
}


window.addEventListener('load', doFirst);