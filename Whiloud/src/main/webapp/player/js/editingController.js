function doFirst(){
	//----------------------
	//      播放相關
	//----------------------
	clip = document.getElementById('clip');
	
	playRecord = document.getElementById('playRecord');

	script = document.getElementsByClassName('script');
	startTime = document.getElementsByClassName('startTime');
	endTime = document.getElementsByClassName('endTime');
	playIntervalRecord = document.getElementsByClassName('playIntervalRecord');
	playIntervalClip = document.getElementsByClassName('playIntervalClip');
	audioPlayer = document.getElementsByClassName('audioPlayer');
	playMemberNo1IntervalRecord = document.getElementsByClassName('playMemberNo1IntervalRecord');
	
	playRecord.addEventListener('click',playRecordClick);
	
	timeoutFunction = null;	//之後用來控制setTimeout要不要執行
	
	//利用迴圈全部加上click listener
	//總台詞數 == script.length == record.length == playIntervalClip.length
	for(var i=0; i<playIntervalClip.length; i++){	
		playIntervalClip[i].addEventListener('click',playIntervalClipClick);

	}
	
	for(var i=0; i<playMemberNo1IntervalRecord.length; i++){	
		playMemberNo1IntervalRecord[i].addEventListener('click',playMemberNo1IntervalRecordClick);

	}

	clip.addEventListener('timeupdate',update);	//當影片時間改變就會觸發此listener，也就是影片在播放時就一直會被執行
	
	
	//----------------------
	//      錄音相關
	//----------------------
	AudioContext = window.AudioContext || window.webkitAudioContext; //外來程式碼
	
	permissionButton = document.getElementById('permissionButton');
	nextButton = document.getElementById('nextButton');
	
	permissionText = document.getElementsByClassName('permissionText');
	recordStart = document.getElementsByClassName('recordStart');
	audioPlayer = document.getElementsByClassName('audioPlayer');

	
	for(var i=0; i<recordStart.length; i++){
		recordStart[i].addEventListener('click', recordStartClick);
	}
	permissionButton.addEventListener('click', permissionGranted);
	
	//----------------------
	//      上傳相關
	//----------------------
	upload =document.getElementById('upload');
	upload.addEventListener('click',uploadClick);
	//判斷總共需要錄幾句
	hasToRecord = document.getElementsByClassName('hasToRecord').length;
	counter = 0;
	//建立FormData清單
	formData = new FormData();

}

//上傳
function uploadClick(){
	var xhr=new XMLHttpRequest();
	xhr.onload=function(e) {
		//如果上傳都沒錯誤
		if(this.readyState === 4) {
			console.log("Server returned: ",e.target.responseText);
			//window.location.href="../index.jsp";
			parent.location.reload();
		}
	};
	
	//幫formData加上資料
	var cooperate = document.getElementById('cooperate').value;
	var memberNo = document.getElementById('memberNo').value;
	var clipNo = document.getElementById('clipNo').value;
	var roleNo = document.getElementById('roleNo').value;
	
	var scriptCount = document.getElementById('scriptCount').value;
	var scriptStartNo = document.getElementById('scriptStartNo').value;
	
	formData.append("memberNo", memberNo);
	formData.append("clipNo", clipNo);
	formData.append("roleNo", roleNo);

	formData.append("scriptCount", scriptCount);
	formData.append("scriptStartNo", scriptStartNo);
	
	//如果是發文者首發(主要是要處理要不要取出postTitle, text, friendOnly, done等值)
	if(cooperate == "false"){
		var postTitle = document.getElementById('postTitle').value;
		var text = document.getElementById('text').value;
		//如果發文者有選特定的角色配
		if(roleNo != 0){
//			var friendOnlyRadios = document.getElementsByName('friendOnly');
//			var friendOnly;
//			for (var i = 0, length = friendOnlyRadios.length; i < length; i++){
//				if (friendOnlyRadios[i].checked){
//					friendOnly = friendOnlyRadios[i].value;
//					break;
//				}
//			}
//			var doneRadios = document.getElementsByName('done');
//			var done;
//			for (var i = 0, length = doneRadios.length; i < length; i++){
//				if (doneRadios[i].checked){
//					done = doneRadios[i].value;
//					break;
//				}
//			}
			var done = document.getElementById('done').value;
		//如果發文者選全部自己配
		}else if(roleNo == 0){
//			var friendOnly = document.getElementById('friendOnly').value;
			var done = "true";
		}
//		formData.append("friendOnly", friendOnly);
		formData.append("done", done);
		formData.append("postTitle", postTitle);
		formData.append("text", text);
		xhr.open("POST","/Whiloud/post/PublishPostServlet",true);
		xhr.send(formData);
	//如果是memberNo2按合作進來的影片
	}else{
		var postNo = document.getElementById('postNo').value;
		var done = "true";
		formData.append("done", done);
		formData.append("postNo", postNo);
		xhr.open("POST","/Whiloud/post/PublishCooperatePostServlet",true);
		xhr.send(formData);
	}
	
	
	
}

//錄音相關
function permissionGranted(){
	
	
	//以下開啟麥克風權限
	 var constraints = { audio: true, video:false}
		/*
	    	We're using the standard promise based getUserMedia() 
	    	https://developer.mozilla.org/en-US/docs/Web/API/MediaDevices/getUserMedia
		*/
		navigator.mediaDevices.getUserMedia(constraints).then(function(stream) {
			console.log("getUserMedia() success, stream created, initializing Recorder.js ...");
			/*
				create an audio context after getUserMedia is called
				sampleRate might change after getUserMedia is called, like it does on macOS when recording through AirPods
				the sampleRate defaults to the one set in your OS for your playback device

			*/
			audioContext = new AudioContext();
			//update the format 
			//document.getElementById("formats").innerHTML="Format: 1 channel pcm @ "+audioContext.sampleRate/1000+"kHz"
			/*  assign to gumStream for later use  */
			//gumStream = stream;
			/* use the stream */
			input = audioContext.createMediaStreamSource(stream);
			/* 
				Create the Recorder object and configure to record mono sound (1 channel)
				Recording 2 channels  will double the file size
			*/
			rec = new Recorder(input,{numChannels:1});
			
			//取消提示文字，開啟錄音按鈕
			for(var i=0; i<recordStart.length; i++){
				if(permissionText[i] != null){
					permissionText[i].innerHTML = "";
				}
				recordStart[i].disabled = false;
				recordStart[i].style.color= "white";
			}
//			nextButton.style.display = "";
			console.log("permission Granted");
//			$("#myStep").step().nextStep();
			
		}).catch(function(err) {
			alert('連接麥克風失敗，請檢查錄音設備');
			window.location.reload();
			console.log(err);
		  	//enable the record button if getUserMedia() fails
		});
}

//錄音開始
function recordStartClick(){
	isInRecordProcess = true;
	
	var scriptPosition = parseInt(this.parentNode.parentNode.getElementsByClassName('scriptPosition')[0].innerText);
	var index = scriptPosition-1;

	var scriptNo = parseInt(this.parentNode.parentNode.getElementsByClassName('scriptNo')[0].innerText);
	var startTime = parseFloat(this.parentNode.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime;
	
	//取消其他按鈕的控制權
	buttons = document.querySelectorAll('button');
	for(var i=0; i<buttons.length; i++){
		buttons[i].disabled = true;
	}

	console.log("record start");
	permissionText[index].innerText="錄音中...";
	rec.record();
	
	
	if (audioPlayer[index].querySelector('audio')==null){
		counter++;
		//console.log(counter);
	}
	audioPlayer[index].innerHTML="";
	playIntervalRecord[index].innerHTML="";
	playIntervalRecordClick(startTime, duration);
	
	//錄音時間到了以後
	recordTimeout = setTimeout(function(){
		//打開除了upload以外其他按鈕的控制權
		for(var i=0; i<buttons.length; i++){
			buttons[i].disabled = false;
		}
		upload.disabled = true;
		
		rec.stop();
		permissionText[index].innerText='';
		rec.exportWAV(function(blob){
			var url = URL.createObjectURL(blob);
			
			//加上撥錄音按鈕
			//class=""
			var playIntervalRecordButton = document.createElement('button');
			playIntervalRecordButton.addEventListener('click', function(){
				playIntervalRecordClick(startTime, duration);
			});
			playIntervalRecordButton.setAttribute("class","soundButton listenRec" );
			playIntervalRecordButton.innerHTML="聽錄音";
			playIntervalRecord[index].appendChild(playIntervalRecordButton);
			
			//加上錄音檔案撥放器
			var au = document.createElement('audio');
			au.controls = true;
			au.src = url;
			au.style.display="none";
			
			audioPlayer[index].appendChild(au);
			
			//幫formdata加上錄音檔案
			formData.append(scriptNo, blob, scriptNo + ".wav");	
			
			//如果該錄的音都錄完了
			if(counter >= hasToRecord){
				upload.disabled = false;	//開啟upload按鈕
				upload.style.color = "white";	//開啟upload按鈕
				var hasToRecordText = document.getElementById('hasToRecordText');
				hasToRecordText.innerHTML="";	//消除必須錄完的提示文字
			}
			
		});
		rec.clear();
		isInRecordProcess = false;
		console.log("Recording end"); 
	}, (duration+0.5)*1000);
	
	
}

function playMemberNo1IntervalRecordClick(startTime, duration){
	var startTime = parseFloat(this.parentNode.parentNode.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.parentNode.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime; 
	
	playIntervalRecordClick(startTime, duration)
}

function playIntervalRecordClick(startTime, duration){
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	recordHasToPlay = true;	//準備播錄音檔

	//準備撥放影片，錄音的部分交由recordHasToPlay和update的函數控制
	clip.volume = 0;
	clip.currentTime = startTime;
	clip.play();
	
	//把錄音檔全部歸零並暫停，之後交由update來控制聲音
	for(i=0; i<script.length; i++){
		if(audioPlayer[i].querySelector('audio')!=null){
			audioPlayer[i].querySelector('audio').currentTime = 0;
			audioPlayer[i].querySelector('audio').pause();
		}
		
	}

	//設定在duration時間後，把影片暫停，並重新讓使用者可以按按鈕
	timeoutFunction = setTimeout(function(){
			clip.pause(); 
// 			enableButtons();
	}, duration*1000);
}
	


//當'播全部錄音'被點擊
function playRecordClick(){
	
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	
	recordHasToPlay = true;	//之後必須播錄音(會有這項是因為，如果之後按'只播原聲'，必須把此項設為false)
	
	clip.currentTime = 0;	//歸零
	clip.volume = 0;		//影片聲音最小聲
	clip.play();			//播影片
	
	//處理錄音檔和字幕顏色
	for(var i=0; i<script.length; i++){
		script[i].style.color = "#000";	//先把所有字幕顏色變黑色
		
		if(audioPlayer[i].querySelector('audio')!=null){
			audioPlayer[i].querySelector('audio').currentTime = 0; //所有錄音處於預備狀態
			audioPlayer[i].querySelector('audio').pause(); //若之前有播到一半的錄音也先暫停
		}			
	}
	
}

//當影片時間改變就會觸發此listener，也就是影片在播放時就一直會被執行
function update(){
	for(var i=0; i < script.length; i++){	//每次執行都把所有的台詞時間判斷一次
		//先判斷當影片時間是否坐落在某段台詞的時間內
		if(parseFloat(startTime[i].innerText)<= parseFloat(clip.currentTime)
			&&  parseFloat(clip.currentTime) <parseFloat(endTime[i].innerText)){
			for(var j=0; j<script.length; j++){	//如果影片時間在某段台詞的時間內
				if(j!=i){	//除了當前時間的台詞外，其他都變為黑色
					script[j].style.color="#000";
				}
			}
			script[i].style.color="red";	//當前台詞變紅色
			if(audioPlayer[i].querySelector('audio')!=null){
				if(!audioPlayer[i].querySelector('audio').ended && recordHasToPlay == true){
					//如果錄音必須被播，而且台詞之前沒被播到完過，就撥放錄音
						clip.volume = 0;
						audioPlayer[i].querySelector('audio').play();
				}
			}else if(!isInRecordProcess){//如果這段還沒被錄過音，而且也沒正在被錄音，就撥放影片的聲音
				clip.volume = 1;
			}
		}else{
			script[i].style.color="#000";	//如果影片當前時間不坐落在任何台詞時間內，則全部台詞為黑色
		}
	}
}

//按下'播原聲'的按鈕後
function playIntervalClipClick(){
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	recordHasToPlay = false;	//之後不能播錄音檔，此項設為false
	// 	disableButtons();
	var startTime = parseFloat(this.parentNode.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime;
	
	//準備撥放影片
	clip.volume = 1;
	clip.currentTime = startTime;
	clip.play();
	
	//把錄音檔全部歸零並暫停，
	for(i=0; i<script.length; i++){
		if(audioPlayer[i].querySelector('audio')!=null){
			audioPlayer[i].querySelector('audio').currentTime = 0;
			audioPlayer[i].querySelector('audio').pause();
		}
	}
	
	//設定在duration時間後，把影片暫停，並重新讓使用者可以按按鈕
	timeoutFunction = setTimeout(function(){
			clip.pause(); 
// 			enableButtons();
	}, duration*1000);
}

//複製過來的程式碼，等同於java裡的Thread.sleep();
function sleep(d){
	  for(var t = Date.now();Date.now() - t <= d;);
	}

window.addEventListener('load',doFirst);