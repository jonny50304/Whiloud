<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <title>影片播放</title>
    <link href="${pageContext.servletContext.contextPath}/player/css/video.css" rel="stylesheet" type="text/css">
    <script src="${pageContext.servletContext.contextPath}/player/js/video.js"></script>
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
    <div class="leftFixed">
<!--       <video id="MY_VIDEO_1" class="video-js vjs-default-skin"  -->
<!--         controls preload="auto" width="480" height="300" poster="" -->
<!--         data-setup="{}"> -->
<!--         <source src="video/AAAAA.mp4" type='video/mp4'> -->
<!--       </video> -->
      <video src="${pageContext.servletContext.contextPath}${clipBean.clipPath}"
				width="480" height="300"
				id = "clip"
			>
				please use Chrome
			</video>

<!--       	<div style="width:100px; float:left;"> -->
<!-- 				&nbsp; -->
<!-- 			</div> -->
			<button id="playClip">Play </button>
				<button id="pauseButton">Pause </button>
				<button id="stopButton">Stop </button>
			<span style="clear: both;"/>
			
			<div id="defaultBar">
				<footer id="progressBar" ></footer>
			</div>
        
        <div class="leftButtton">
          <hr>
          <br>
          <h2>片名：${clipBean.clipTitle}</h2>
          <br>
          <h2>先選擇角色，就可以去錄音</h2>
          <form action="${pageContext.servletContext.contextPath}/EnterEditingPageServletV2" method="POST">
          	<input type = "hidden" name="clipNo" value="${clipBean.clipNo}">
	          <select id="roleSelect" name="roleSelect" style="width:220px;">  
	            <option selected="true"></option>
	            <option value="1">${clipBean.role1}</option>
	            <option value="2">${clipBean.role2}</option>
	            <option value="0">全部錄音</option>
	          </select>
          <br>
          		<input type="submit" class="recButton" id="goRec" value="選好去錄音"></a>
          </form>
        </div> 

      </div>
    </div>

    <div class="rightFloat">
      <div id="tab-demo">
        <ul class="tab-title">
          <li><a class="tabBtn" href="#tab01">台詞</a></li>
          <li><a class="tabBtn" href="#tab02">留言</a></li>
        </ul> 
        <div class="tab-inner" id="tab01">
         	<c:forEach var="entry" items="${scriptBeanList}">
	        	<div class="text section">
	        		<span class="scriptNo" style="display:none;">${entry.scriptNo}</span>
		        	<span class="startTime" style="display:none;">${entry.startTime}</span>
		        	<span class="endTime" style="display:none;">${entry.endTime}</span>
		        	<span class="script">
		            <h2>${entry.englishScript}</h2>
		            <br>
		            <h3>${entry.chineseScript}</h3>
		            <br>
		            </span>
	            	
		            <div class="allSoundBtm">
		            <span style="font-size:13px;">影片腳色: ${entry.roleName}<button class="Original playIntervalClip" id="playIntervalClip">聽原音</button> </span>
		             
		            </div>
	          </div>
	        </c:forEach>
        </div> 

        <div class="tab-inner" id="tab02">
          <form>
            <textarea class="inputMessage" type="text" placeholder="在此輸入留言內容"></textarea>
            <input type="submit" class="sendMessage" value="發送">
          </form>  
          <div class="message">
            <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a>
            <h4>留言留言留言留言留言留言留言留言留言留言留言留言留言留言留言留言留言</h4>
          </div>  
          <div class="message">  
            <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a>
            <h4>留</h4>
          </div>  
          <div class="message">
            <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a>
            <h4>灌水</h4>
          </div>  
          <div class="message">
            <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a>
            <h4>6666666666</h4>
          </div>  
          <div class="message">
            <a href=""><img src="images/8.jpg" alt="留言的人" class="messager"></a>
            <h4>7777777777777</h4>
          </div>
        </div>
      </div>
    </div>  
  </body>
  
<script>
function doFirst(){
	clip = document.getElementById('clip');

	playClip = document.getElementById('playClip');
	pauseButton = document.getElementById('pauseButton');
	stopButton = document.getElementById('stopButton');
	
	defalutBar = document.getElementById('defalutBar');
	progressBar = document.getElementById('progressBar');
	defaultBar.addEventListener('click',clickedBar);
	
	script = document.getElementsByClassName('script');
	startTime = document.getElementsByClassName('startTime');
	endTime = document.getElementsByClassName('endTime');

	playIntervalClip = document.getElementsByClassName('playIntervalClip');
	section = document.getElementsByClassName('section');
	
	barsize = parseInt(window.getComputedStyle(defaultBar).width);
	playClip.addEventListener('click',playClipClick);
	
	pauseButton.addEventListener('click', function(){
		clip.pause();
	});
	
	stopButton.addEventListener('click', function(){
		clip.currentTime = 0;
		clip.pause();
	})
	
	timeoutFunction = null;	//之後用來控制setTimeout要不要執行
	
	//利用迴圈全部加上click listener
	//總台詞數 == script.length == record.length == playIntervalClip.length
	for(var i=0; i<playIntervalClip.length; i++){	
		playIntervalClip[i].addEventListener('click',playIntervalClipClick);
	}
	
	clip.addEventListener('timeupdate',update);	//當影片時間改變就會觸發此listener，也就是影片在播放時就一直會被執行

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

}

//當'播全部錄音'被點擊
function playClipClick(){
	
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	
	
	clip.currentTime = 0;	//歸零
	clip.volume = 1;		
	clip.play();			//播影片
	
	//處理錄音檔和字幕顏色
	for(var i=0; i<script.length; i++){
		script[i].style.color = "#000";	//先把所有字幕顏色變黑色
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
			section[i].scrollIntoView({behavior: "smooth", block: "start", inline: "nearest"});
			
		}else{
			script[i].style.color="#000";	//如果影片當前時間不坐落在任何台詞時間內，則全部台詞為黑色

		}
		
		
	}
}

//按下播原聲的按鈕後
function playIntervalClipClick(){
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	
	var startTime = parseFloat(this.parentNode.parentNode.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.parentNode.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime;
	
	//準備撥放影片
	clip.volume = 1;
	clip.currentTime = startTime;
	clip.play();

	
	//設定在duration時間後，把影片暫停，並重新讓使用者可以按按鈕
	timeoutFunction = setTimeout(function(){
			clip.pause(); 
// 			enableButtons();
	}, duration*1000);
	


}


window.addEventListener('load',doFirst);

</script>
</html>