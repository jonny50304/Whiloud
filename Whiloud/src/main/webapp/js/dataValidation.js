function doFirst(){
	theForm = document.getElementById('theForm');
	
	accountError = document.getElementById('accountError');
	passwordError = document.getElementById('passwordError');
	passwordCheckError = document.getElementById('passwordCheckError');
	nicknameError = document.getElementById('nicknameError');
	genderError = document.getElementById('genderError');
	
	
	
	submitButton = document.getElementById('submitButton');
	submitButton.addEventListener('click', submit);
	
}

function submit(){
	error = false;
	
	
	accountError.innerText = " ";
	passwordError.innerText = " ";
	passwordCheckError.innerText = " ";
	nicknameError.innerText = " ";
	genderError.innerText = " ";
	
	account = document.getElementById('account').value;
	password = document.getElementById('password').value;
	passwordCheck = document.getElementById('passwordCheck').value;
	nickname = document.getElementById('nickname').value;
	gender = document.getElementById('gender').value;

	if (account.search(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/) == -1){
		error = true;
		accountError.innerHTML += "帳號(信箱格式)有誤 | "
	}
	
	if(password.search(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/) == -1){
		passwordError.innerHTML +="密碼至少8位，同時由字母和數字所組成 | ";
		error = true;

	}else if(password!=passwordCheck){
		error = true;
		passwordError.innerHTML += "兩次密碼不一樣 | "
	}

	if(nickname.length<3 || nickname.length>11){
		nicknameError.innerHTML += "暱稱必須介於4~10個字 | ";
		error = true;
	}
	
	if(gender.length==""){
		genderError.innerHTML += "請選擇性別 | ";
		error = true;
	}
	
	if(error==false){
		
		var xhr = new XMLHttpRequest();
		xhr.onload = function(e) {
			//請求成功時，把紀錄加入聊天室窗
			if (this.readyState === 4) {
				console.log('readyState === 4')
				var jsonData = xhr.responseText;
				console.log(xhr.responseText);
				var jsonArray = JSON.parse(jsonData);
				
				for(var i=0; i<jsonArray.length; i++){
					var errorFromServer = jsonArray[i];
					if(errorFromServer == "accountExist"){
						accountError.innerText += "帳號已存在，請重新想一個新的 | ";
					}
					if(errorFromServer == "nicknameExist"){
						nicknameError.innerHTML += "暱稱已存在，請重新想一個新的 ";
					}
					if(errorFromServer == "none"){
						theForm.submit();
					}
				}
			}
		};
		
		//送出URI請求
		requestURI = '/Whiloud/CheckAccountServlet?account=' + account
				+ '&nickname=' + nickname;
		xhr.open("POST", requestURI, true);
		xhr.send();
	}
	
}


window.addEventListener('load', doFirst);