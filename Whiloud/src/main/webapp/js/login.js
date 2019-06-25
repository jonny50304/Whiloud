function doFirst(){

	loginForm = document.getElementById('loginForm');
	loginButton = document.getElementById('loginButton');
	loginButton.addEventListener('click', login);
	
	
	
}
function login(){
	account = document.getElementById('account').value;
	password = document.getElementById('password').value;
	console.log(account);
	console.log(password);
	if(account.trim() == '' || password.trim() == ''){
		document.getElementById('errorMessage').innerText='帳號密碼不能為空';
	}else{
		loginForm.submit();
	}
	
}

window.addEventListener('load',doFirst);