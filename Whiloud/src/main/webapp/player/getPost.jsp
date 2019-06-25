<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
  <head>
  	<% 
		response.setHeader("Pragma","no-cache"); 
		response.setHeader("Cache-Control","no-cache"); 
		response.setDateHeader("Expires", 0); 
	%>
    <title>影片播放</title>
    <link href="${pageContext.servletContext.contextPath}/player/css/video.css" rel="stylesheet" type="text/css">
<%--     <script src="${pageContext.servletContext.contextPath}/player/js/video.js"></script> --%>
    <script src="${pageContext.servletContext.contextPath}/player/js/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/player/js/tab.js"></script>    
    <style type="text/css">
      .vjs-default-skin { color: rgba(171,208,102,1); }
      .vjs-default-skin .vjs-play-progress,
      .vjs-default-skin .vjs-volume-level { background-color: rgba(214,232,181,0.7) }
      .vjs-default-skin .vjs-control-bar,
      .vjs-default-skin .vjs-big-play-button { background: rgba(40,124,134,0.8) }
      .vjs-default-skin .vjs-slider { background: rgba(0,9,219,0.23) }
      .vjs-default-skin .vjs-control-bar { font-size: 110% }
    </style>
  </head>

  <body>
   <input type="hidden" id="postNo" value = "${postDetailBean.postNo}">
    <div class="leftFixed">
<!--       <video id="MY_VIDEO_1" class="video-js vjs-default-skin"  -->
<!--         controls preload="auto" width="480" height="360" poster="" -->
<!--         data-setup="{}" id="clip"> -->
<%--         <source src="${pageContext.servletContext.contextPath}${postDetailBean.cb.clipPath}" type='video/mp4' > --%>
<!--       </video> -->
      		<video src="${pageContext.servletContext.contextPath}${postDetailBean.cb.clipPath}"
				width="480" height="300"
				
				id = "clip"
			>
				please use Chrome
			</video>

			<button id="playRecord">Play</button>
				<button id="pauseButton">Pause</button>
				<button id="stopButton">Stop</button>
			<span style="clear: both;"/>
			
			<div id="defaultBar">
				<footer id="progressBar"></footer>
			</div>
			
<!-- 			<button id="playRecord" style="width:50px;float: left;">Play </button> -->
<!-- 				<button id="pauseButton" style="width:50px;float: left;">Pause </button> -->
<!-- 				<button id="stopButton" style="width:50px;float: left;">Stop </button> -->
<!-- 			<span style="clear: both;"/> -->
			
<!-- 			<div style="left:200px; width:200px;height:20px; border:1px solid DarkTurquoise ; padding:2px;float: left;" id="defaultBar"> -->
<!-- 				<footer id="progressBar" style="width: 0px; height: 20px;background: DarkTurquoise ;float: left;"></footer> -->
<!-- 			</div> -->
			
			<span style="clear: both;"/>
	
      <div style="position: relative; left:20px; top:-350px;">
        <div class="member">
          <a href="EnterFriendPageServlet?memberNo=${postDetailBean.mb1.memberNo}">
          	<img src="${pageContext.servletContext.contextPath}${postDetailBean.mb1.picturePath}" alt="放影片的人" class="videoer">
          </a>
          <h1><br>作者:${postDetailBean.mb1.nickname}
          
          <br>
	          <c:if test="${postDetailBean.done && postDetailBean.mb2.memberNo != 1 && postDetailBean.mb2.memberNo != postDetailBean.mb1.memberNo}">
	          	<a href="EnterFriendPageServlet?memberNo=${postDetailBean.mb2.memberNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants"  style="color:black; text-decoration:none;">合作者： ${postDetailBean.mb2.nickname}</a>
	          </c:if>
	          <c:if test="${!postDetailBean.done && postDetailBean.mb1.memberNo != LoginOK.memberNo}">
          		<form action="${pageContext.servletContext.contextPath}/EnterSemiDonePageServletV2" method="POST">
						<input type="hidden" name="postNo" value = "${postDetailBean.postNo}">
						<input type="hidden" name="clipNo" value = "${postDetailBean.cb.clipNo}">
						<input type="hidden" name="memberNo2" value = "${LoginOK.memberNo}">
						<input type="hidden" name="notDoneRoleNo" value = "${postDetailBean.notDoneRoleNo}">
						<input type="submit" class="likeWork" value="合作配音Go!">
				</form>
 
          	</c:if>
          </h1>
          <br>
                 
        </div>
  
        <div class="work">     
        <br>
        <br>   
          <h5>片名：  ${postDetailBean.postTitle}<br></h5>
          <br>
          <h5>創作日期： ${postDetailBean.creationDateTime}<br></h5>
          <br>
          <h5>作者留言：</h5> <h6>${postDetailBean.postText}<br><h6>
          
          
          <c:set var="isLiked" value="0"/>
          <c:forEach var="entry" items="${postDetailBean.likePostBean}">
          	<c:if test="${entry.mb.memberNo == LoginOK.memberNo}">
          		<c:set var="isLiked" value="1"/>
          	</c:if>
          </c:forEach>
          
	        <c:choose>
	        	<c:when test="${postDetailBean.done && isLiked == 0}">
	        		<form action="LikeServlet?postNo=${postDetailBean.postNo}" method="POST">
	        			<button class="likeWork">讚!&nbsp;&nbsp;&nbsp;${fn:length(postDetailBean.likePostBean)}</button>
	        		</form>
	        	</c:when>
				<c:when test="${postDetailBean.done && isLiked == 1}">
					<form action="DislikeServlet?postNo=${postDetailBean.postNo}" method="POST">
						<button class="likeWork">收回讚!&nbsp;&nbsp;&nbsp;${fn:length(postDetailBean.likePostBean)}</button>
					</form>
				</c:when>
			</c:choose>

		
        </div>
        
      </div>
    </div>

    <div class="rightFloat">
      <div id="tab-demo">
        <ul class="tab-title">
<!--           <li><a class="tabBtn" href="#tab01">台詞</a></li> -->
<!--           <li><a class="tabBtn" href="#tab02">留言</a></li> -->
          <li>台詞<a class="tabBtn" href="#tab01"></a></li>
          <li>留言<a class="tabBtn" href="#tab02"></a></li>
        </ul>
	       <div class="tab-inner" id="tab01">
	        <c:forEach var="entry" items="${recordList}">
	        	<div class="text section">
		        	<span class="startTime" style="display:none;">${entry.sb.startTime}</span>
		        	<span class="endTime" style="display:none;">${entry.sb.endTime}</span>
		        	<span class="script">
		            <h2>${entry.sb.englishScript}</h2>
		            <br>
		            <h3>${entry.sb.chineseScript}</h3>
		            <br>
		            </span>
	            	<span style="font-size:13px">影片腳色: ${entry.sb.roleName}, 錄音者: ${entry.mb.nickname}<br></span>
	            	<audio
								src="${pageContext.servletContext.contextPath}${entry.recordPath}"
								controls="controls"
								class="record" 
								style="display:none;"
							>
					</audio>
		            <div class="allSoundBtm">
		              <button class="Original playIntervalClip" id="playIntervalClip">聽原音</button>
		              
		              <c:if test="${not empty entry.recordPath}">
		              	<button class="listenRec playIntervalRecord" id="playIntervalRecord">聽錄音</button>
		              </c:if>
		              
		              
		              <c:set var="isLiked" value="0"/>
			          <c:forEach var="entry2" items="${entry.likeRecordBean}">
			          	<c:if test="${entry2.mb.memberNo == LoginOK.memberNo}">
			          		<c:set var="isLiked" value="1"/>
			          	</c:if>
			          </c:forEach>
				          
				        <c:choose>
				        	<c:when test="${postDetailBean.done && isLiked == 0}">
				        		<div class="likeform">
					        		<form action="LikeServlet?postNo=${postDetailBean.postNo}&recordNo=${entry.recordNo}" method="POST">
					        			<button class="likeScript">${fn:length(entry.likeRecordBean)}&nbsp;讚!</button>
					        		</form>
				        		</div>
				        	</c:when>			        	
							<c:when test="${postDetailBean.done && isLiked == 1}">
								<div class="likeform">
									<form action="DislikeServlet?postNo=${postDetailBean.postNo}&recordNo=${entry.recordNo}" method="POST">
										<button class="likeScript">${fn:length(entry.likeRecordBean)}&nbsp;收回</button>
									</form>
								</div>	
							</c:when>
						</c:choose>

		            </div>
	          </div>
	        </c:forEach> 
       </div>

        <div class="tab-inner" id="tab02">

            <textarea class="inputMessage" type="text" placeholder="在此輸入留言內容" id="commentTextInput"></textarea>
            <input type="button" class="sendMessage" value="發送" id="sendCommentButton">

          <span id="commentTextBoard">
          <c:forEach var="entry" items="${commentBeanList}">
          	<div class="message" >
	            <a href="EnterFriendPageServlet?memberNo=${entry.mb.memberNo}">
	            	<img src="${pageContext.servletContext.contextPath}${entry.mb.picturePath}" alt="留言的人" class="messager">
	            </a>
	            
	            <h4>
	            	${entry.mb.nickname}說: ${entry.commentText}
	            	<span style="font-size:9px; color:#bbb;">
	            		<br>
	            		${fn:substring(entry.creationDateTime,0,16)} @${entry.ip}
	            	</span>
	            </h4>
	            
          	</div> 
          
          </c:forEach>
           </span>
<!--           <div class="message">   -->
<!--             <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a> -->
<!--             <h4>留</h4> -->
<!--           </div>   -->
<!--           <div class="message"> -->
<!--             <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a> -->
<!--             <h4>灌水</h4> -->
<!--           </div>   -->
<!--           <div class="message"> -->
<!--             <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a> -->
<!--             <h4>6666666666</h4> -->
<!--           </div>   -->
<!--           <div class="message"> -->
<!--             <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a> -->
<!--             <h4>7777777777777</h4> -->
<!--           </div> -->
        </div>
        
      </div>
    </div>  

    
  </body>
<script>

function doFirst(){
	
	
	clip = document.getElementById('clip');

	playRecord = document.getElementById('playRecord');
	pauseButton = document.getElementById('pauseButton');
	stopButton = document.getElementById('stopButton');
	sendCommentButton = document.getElementById('sendCommentButton');
	commentTextInput = document.getElementById('commentTextInput');
	commentTextBoard = document.getElementById('commentTextBoard');
	
	defalutBar = document.getElementById('defalutBar');
	progressBar = document.getElementById('progressBar');
	defaultBar.addEventListener('click',clickedBar);
	
	
	
	record = document.getElementsByClassName('record');
	script = document.getElementsByClassName('script');
	startTime = document.getElementsByClassName('startTime');
	endTime = document.getElementsByClassName('endTime');
	playIntervalRecord = document.getElementsByClassName('playIntervalRecord');
	playIntervalClip = document.getElementsByClassName('playIntervalClip');
	section = document.getElementsByClassName('section');
	
	barsize = parseInt(window.getComputedStyle(defaultBar).width);
	playRecord.addEventListener('click',playRecordClick);
	pauseButton.addEventListener('click', function(){
		clip.pause();
		for(i=0; i<script.length; i++){
			record[i].currentTime = 0;
			record[i].pause();
			recordHasToPlay = false;
		}
	});
	stopButton.addEventListener('click', function(){
		clip.currentTime = 0;
		clip.pause();
		for(i=0; i<script.length; i++){
			record[i].currentTime = 0;
			record[i].pause();
		}
	});
	sendCommentButton.addEventListener('click', sendComment);
	
	timeoutFunction = null;	//之後用來控制setTimeout要不要執行
	recordHasToPlay = null;
	
	//利用迴圈全部加上click listener
	//總台詞數 == script.length == record.length == playIntervalClip.length
	for(var i=0; i<playIntervalClip.length; i++){	
		playIntervalClip[i].addEventListener('click',playIntervalClipClick);
		try{
		playIntervalRecord[i].addEventListener('click',playIntervalRecordClick)
		}catch(e){};
	}
	
	clip.addEventListener('timeupdate',update);	//當影片時間改變就會觸發此listener，也就是影片在播放時就一直會被執行
	
}

function sendComment(){
	
	var xhr = new XMLHttpRequest();

	//送出URI請求
	xhr.onload = function(e) {
		//請求成功時，把紀錄加入評論
		if (this.readyState === 4) {
			console.log('readyState === 4');
			
			commentTextBoard.innerHTML = '';
			var jsonData = xhr.responseText;
			var jsonArray = JSON.parse(jsonData);

			//針對List<ChatBean>裡的ChatBean一項一項操作
			for (var i = 0; i < jsonArray.length; i++) {
				var memberNo = jsonArray[i].mb.memberNo;
				var nickname = jsonArray[i].mb.nickname;
				var picturePath = jsonArray[i].mb.picturePath;
				var commentText = jsonArray[i].commentText;
				var creationDateTime = jsonArray[i].creationDateTime;
				var ip = jsonArray[i].ip;
				
				var commentTextDiv = document.createElement('div')
				commentTextDiv.setAttribute("class",'message');
				
				commentTextDiv.innerHTML = ""
					+ '	<a href="EnterFriendPageServlet?memberNo='+ memberNo +'">'
					+ '	<img src="" alt="留言的人" class="messager" id="commentPictureOf'+ memberNo +'">'
					+ '	</a>'
					+ '	<h4>'
					+ 		nickname + '說: ' + commentText
					+ '	<span style="font-size:9px; color:#bbb;"><br>'
					+ '' + creationDateTime.substring(0,16) + ' @' + ip
					+ '</span></h4>';
					
				commentTextDiv.querySelector('img').src = '/Whiloud' + picturePath;
				commentTextBoard.appendChild(commentTextDiv);		//加到聊天室窗
				
				
// 				document.getElementById('commentPictureOf' + memberNo).src = '/Whiloud' + picturePath;
			}
			
		}
	};
	
	//送出URI請求
	var commentText = commentTextInput.value;
	var postNo = document.getElementById('postNo').value;
	requestURI = '/Whiloud/UploadCommentServletJSON?postNo=' + postNo + '&commentText=' + commentText;
	xhr.open("POST", requestURI, true);
	xhr.send();
}

function clickedBar(e){	
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	if(clip.paused){
		recordHasToPlay = false;
	}
	
 	var mouseX = e.pageX - progressBar.offsetLeft;
 	progressBar.style.width = mouseX +'px';	

 	var newTime = mouseX / (barsize / clip.duration);
 	
 	clip.currentTime = newTime;
 	for(i=0; i<script.length; i++){
		record[i].currentTime = 0;
		record[i].pause();
	}
}

//當'播全部錄音'被點擊
function playRecordClick(){
	
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	
	recordHasToPlay = true;	//之後必須播錄音(會有這項是因為，如果之後按'只播原聲'，必須把此項設為false)
	
	//clip.currentTime = 0;	//歸零
	clip.volume = 0;		//影片聲音最小聲
	clip.play();			//播影片
	
	//處理錄音檔和字幕顏色
	for(var i=0; i<script.length; i++){
		script[i].style.color = "#000";	//先把所有字幕顏色變黑色
		record[i].currentTime = 0;		//所有錄音處於預備狀態
		record[i].pause();				//若之前有播到一半的錄音也先暫停
	}
	
}

//當影片時間改變就會觸發此listener，也就是影片在播放時就一直會被執行
function update(){
	
	//更新progressBar
	if(!clip.ended){
 		var size = 	barsize / clip.duration * clip.currentTime;
 		progressBar.style.width = size +'px';
 	}else{	
 		progressBar.style.width = '0px';
 	}
	
	clipVolumeHasToPlay = true;
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
			
			section[i].scrollIntoView({behavior: "auto", block: "start", inline: "nearest"});
			if(parent.document.getElementById('main')!=null){			
				parent.document.getElementById('main').style.top = '-1938px';
				parent.document.getElementById('page3').scrollIntoView();
			};

// 			document.getElementById('tab-demo').style.position = 'absolute';
// 			document.getElementById('tab-demo').style.top = '' + (-151*i) + 'px';
			
			
			if(!record[i].ended && recordHasToPlay == true){
				//如果錄音必須被播，而且台詞之前沒被播到完過，就撥放錄音
				clip.volume = 0;
					//如果播放時丟出例外，代表沒有這個錄音，就播原音
					record[i].play().catch(function(error) {
						clip.volume = 1;
					});;

				
				clipVolumeHasToPlay  = false;
			}
		}else{
			script[i].style.color="#000";	//如果影片當前時間不坐落在任何台詞時間內，則全部台詞為黑色
			if(clipVolumeHasToPlay){
				clip.volume = 1;
			}
		}
		
		
	}
}

//按下播原聲的按鈕後
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
		record[i].currentTime = 0;
		record[i].pause();
	}
	
	//設定在duration時間後，把影片暫停，並重新讓使用者可以按按鈕
	timeoutFunction = setTimeout(function(){
			clip.pause(); 
// 			enableButtons();
	}, duration*1000);
}

//按下'播此段錄音'的按鈕後
function playIntervalRecordClick(){
	
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	
	recordHasToPlay = true;	//準備播錄音檔

// 	disableButtons();	
	var startTime = parseFloat(this.parentNode.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime;
	
	//準備撥放影片，錄音的部分交由recordHasToPlay和update的函數控制
	clip.volume = 0;
	clip.currentTime = startTime;
	clip.play();
	
	//把錄音檔全部歸零並暫停，之後交由update來控制聲音
	for(i=0; i<script.length; i++){
		record[i].currentTime = 0;
		record[i].pause();
	}
	
	//設定在duration時間後，把影片暫停，並重新讓使用者可以按按鈕
	timeoutFunction = setTimeout(function(){
			clip.pause(); 
// 			enableButtons();
	}, duration*1000);
}

window.addEventListener('load',doFirst);
</script>
</html>