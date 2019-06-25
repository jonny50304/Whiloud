<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1">
		<tr>
			<td width="350">
				clipNo = ${clipBean.clipNo}<br>
				clipPath = ${clipBean.clipPath}<br>
				picturePath = ${clipBean.picturePath}<br>
				creationDateTime = ${clipBean.creationDateTime}<br>
				role 1: ${clipBean.role1} | role 2: ${clipBean.role2}<br>
				<video src="${pageContext.servletContext.contextPath}${clipBean.clipPath}" width="400" controls="controls" id = "clip">
					please use Chrome
				</video>
				<br>
				text: ${clipBean.text}
				<br>
				<form action="${pageContext.servletContext.contextPath}/post/EnterEditingPageServlet" method="POST">
					<input type = "hidden" name="clipNo" value="${clipBean.clipNo}">
					<input type = "hidden" name="memberNo" value="${LoginOK.memberNo}">
					配音腳色：<br>
					<input type="radio" name="roleNo" value = "1">${clipBean.role1}<br>
					<input type="radio" name="roleNo" value = "2">${clipBean.role2}<br>
					<input type="radio" name="roleNo" value = "0">全部自己配<br>
					<input type="submit" value="我要配音！">
				</form>
			</td>
			<td>
				<c:forEach var="entry" items="${scriptBeanList}">
					<span id="section">
						scriptNo: ${entry.scriptNo},  role: ${entry.roleName}<br>
						${entry.scriptPosition}/${entry.scriptCount}, <br>
						<span class="startTime">${entry.startTime}</span>
						~
						<span class="endTime">${entry.endTime}</span><br>
						<span class = "script">
						${entry.englishScript}<br>
						${entry.chineseScript}<br>
						</span>
						<button class = "playIntervalClip">播此段原聲</button>
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

	script = document.getElementsByClassName('script');
	startTime = document.getElementsByClassName('startTime');
	endTime = document.getElementsByClassName('endTime');
	playIntervalClip = document.getElementsByClassName('playIntervalClip');
	
	timeoutFunction = null;	//之後用來控制setTimeout要不要執行

	//利用迴圈全部加上click listener
	for(var i=0; i<playIntervalClip.length; i++){	
		playIntervalClip[i].addEventListener('click',playIntervalClipClick);
	}
	
	clip.addEventListener('timeupdate',update);	//當影片時間改變就會觸發此listener，也就是影片在播放時就一直會被執行
	
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
	
	var startTime = parseFloat(this.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime;
	
	//準備撥放影片
	clip.volume = 1;
	clip.currentTime = startTime;
	clip.play();
	
	//設定在duration時間後，把影片暫停，並重新讓使用者可以按按鈕
	setTimeout(function(){
			clip.pause(); 
	}, duration*1000);
}
window.addEventListener('load',doFirst);
</script>

</body>
</html>