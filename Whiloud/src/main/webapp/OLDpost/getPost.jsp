<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- 以下三段告訴瀏覽器不要快取 -->
<% 
	response.setHeader("Pragma","no-cache"); 
	response.setHeader("Cache-Control","no-cache"); 
	response.setDateHeader("Expires", 0); 
%>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<body>
<table border="1" align="center">
	<tr>
		<td width="350">
			<img src="${pageContext.servletContext.contextPath}${postDetailBean.cb.picturePath}" width="300">
			<video src="${pageContext.servletContext.contextPath}${postDetailBean.cb.clipPath}"
				width="400"
				controls
				id = "clip">
				please use Chrome
			</video><br>
			==========<button id="playRecord">播全部錄音</button>========== <br>
			postNo = ${postDetailBean.postNo}<br>
			postTitle = ${postDetailBean.postTitle}<br>
			memberNo1 = ${postDetailBean.mb1.memberNo}<br>
			nickName1 = ${postDetailBean.mb1.nickname}<br>
			memberNo2 = ${postDetailBean.mb2.memberNo}<br>
			nickName2 = ${postDetailBean.mb2.nickname}<br>
			clipNo = ${postDetailBean.cb.clipNo}<br>
			clipPath = ${postDetailBean.cb.clipPath}<br>
			picturePath = ${postDetailBean.cb.picturePath}<br>
			clipText = ${postDetailBean.cb.text}<br>
			creationDateTime = ${postDetailBean.creationDateTime}<br>
			postText = ${postDetailBean.postText}<br>
			done = ${postDetailBean.done}<br>
			<c:if test="${!empty LoginOK && postDetailBean.mb1.memberNo!= postDetailBean.mb2.memberNo}">
				<c:if test="${not postDetailBean.done}">
					<form action="${pageContext.servletContext.contextPath}/post/EnterSemiDonePageServlet" method="POST">
						<input type="hidden" name="postNo" value = "${postDetailBean.postNo}">
						<input type="hidden" name="clipNo" value = "${postDetailBean.cb.clipNo}">
						<input type="hidden" name="memberNo2" value = "${LoginOK.memberNo}">
						<input type="hidden" name="notDoneRoleNo" value = "${postDetailBean.notDoneRoleNo}">
						==========<input type="submit" value="合作">==========
					</form>
				</c:if>
			</c:if>
			<c:if test="${empty LoginOK}">
				<div style="color:red">請先登入以開啟合作功能</div>
			</c:if>
		</td>
		<td>	
				<c:forEach var="entry" items="${recordList}">
					<span class = "section">
						nickname: ${entry.mb.nickname},  role: ${entry.sb.roleName}<br>
						${entry.sb.scriptPosition}/${entry.sb.scriptCount},
						<span class="startTime">${entry.sb.startTime}</span>
						~
						<span class="endTime">${entry.sb.endTime}</span><br>
						<span class = "script">
						${entry.sb.englishScript}<br>
						${entry.sb.chineseScript}<br>
						</span>
						<button class = "playIntervalClip">播此段原聲</button>
						<button class = "playIntervalRecord">播此段錄音</button>
						<audio
							src="${pageContext.servletContext.contextPath}${entry.recordPath}"
							controls="controls"
							class="record" 
						>
						</audio>
						<hr/>
					</span>
				</c:forEach>
		</td>
	</tr>

</table>

<script>
function doFirst(){
	clip = document.getElementById('clip');
	playRecord = document.getElementById('playRecord');
	
	record = document.getElementsByClassName('record');
	script = document.getElementsByClassName('script');
	startTime = document.getElementsByClassName('startTime');
	endTime = document.getElementsByClassName('endTime');
	playIntervalRecord = document.getElementsByClassName('playIntervalRecord');
	playIntervalClip = document.getElementsByClassName('playIntervalClip');

	playRecord.addEventListener('click',playRecordClick);
	
	timeoutFunction = null;	//之後用來控制setTimeout要不要執行
	
	//利用迴圈全部加上click listener
	//總台詞數 == script.length == record.length == playIntervalClip.length
	for(var i=0; i<playIntervalClip.length; i++){	
		playIntervalClip[i].addEventListener('click',playIntervalClipClick);
		playIntervalRecord[i].addEventListener('click',playIntervalRecordClick);
	}
	
	clip.addEventListener('timeupdate',update);	//當影片時間改變就會觸發此listener，也就是影片在播放時就一直會被執行
	
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
		record[i].currentTime = 0;		//所有錄音處於預備狀態
		record[i].pause();				//若之前有播到一半的錄音也先暫停
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
			if(!record[i].ended && recordHasToPlay == true){
				//如果錄音必須被播，而且台詞之前沒被播到完過，就撥放錄音
				record[i].play();
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
	var startTime = parseFloat(this.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.getElementsByClassName('endTime')[0].innerText);
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
	var startTime = parseFloat(this.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.getElementsByClassName('endTime')[0].innerText);
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

// function disableButtons(){
// 	var buttons = document.querySelectorAll("button");
// 	for(var i=0; i<buttons.length;i++){
// 		buttons[i].disabled= true;
// 	}
// }

// function enableButtons(){
// 	var buttons = document.querySelectorAll("button");
// 	for(var i=0; i<buttons.length;i++){
// 		buttons[i].disabled= false;
// 	}
// }


window.addEventListener('load',doFirst);
</script>
</body>
</html>