<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<!-- �H�U�T�q�i�D�s�������n�֨� -->
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
			==========<button id="playRecord">����������</button>========== <br>
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
						==========<input type="submit" value="�X�@">==========
					</form>
				</c:if>
			</c:if>
			<c:if test="${empty LoginOK}">
				<div style="color:red">�Х��n�J�H�}�ҦX�@�\��</div>
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
						<button class = "playIntervalClip">�����q���n</button>
						<button class = "playIntervalRecord">�����q����</button>
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
	
	timeoutFunction = null;	//����Ψӱ���setTimeout�n���n����
	
	//�Q�ΰj������[�Wclick listener
	//�`�x���� == script.length == record.length == playIntervalClip.length
	for(var i=0; i<playIntervalClip.length; i++){	
		playIntervalClip[i].addEventListener('click',playIntervalClipClick);
		playIntervalRecord[i].addEventListener('click',playIntervalRecordClick);
	}
	
	clip.addEventListener('timeupdate',update);	//��v���ɶ����ܴN�|Ĳ�o��listener�A�]�N�O�v���b����ɴN�@���|�Q����
	
}



//��'����������'�Q�I��
function playRecordClick(){
	
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	
	recordHasToPlay = true;	//���ᥲ��������(�|���o���O�]���A�p�G�����'�u�����n'�A�����⦹���]��false)
	
	clip.currentTime = 0;	//�k�s
	clip.volume = 0;		//�v���n���̤p�n
	clip.play();			//���v��
	
	//�B�z�����ɩM�r���C��
	for(var i=0; i<script.length; i++){
		script[i].style.color = "#000";	//����Ҧ��r���C���ܶ¦�
		record[i].currentTime = 0;		//�Ҧ������B��w�ƪ��A
		record[i].pause();				//�Y���e������@�b�������]���Ȱ�
	}
	
}

//��v���ɶ����ܴN�|Ĳ�o��listener�A�]�N�O�v���b����ɴN�@���|�Q����
function update(){
	for(var i=0; i < script.length; i++){	//�C�����泣��Ҧ����x���ɶ��P�_�@��
		//���P�_��v���ɶ��O�_�����b�Y�q�x�����ɶ���
		if(parseFloat(startTime[i].innerText)<= parseFloat(clip.currentTime)
			&&  parseFloat(clip.currentTime) <parseFloat(endTime[i].innerText)){
			for(var j=0; j<script.length; j++){	//�p�G�v���ɶ��b�Y�q�x�����ɶ���
				if(j!=i){	//���F��e�ɶ����x���~�A��L���ܬ��¦�
					script[j].style.color="#000";
				}
			}
			script[i].style.color="red";	//��e�x���ܬ���
			if(!record[i].ended && recordHasToPlay == true){
				//�p�G���������Q���A�ӥB�x�����e�S�Q���짹�L�A�N�������
				record[i].play();
			}
		}else{
			script[i].style.color="#000";	//�p�G�v����e�ɶ��������b����x���ɶ����A�h�����x�����¦�
		}
	}
}

//���U'�����n'�����s��
function playIntervalClipClick(){
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	recordHasToPlay = false;	//���ᤣ�༽�����ɡA�����]��false
	// 	disableButtons();
	var startTime = parseFloat(this.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime;
	
	//�ǳƼ���v��
	clip.volume = 1;
	clip.currentTime = startTime;
	clip.play();
	
	//������ɥ����k�s�üȰ��A
	for(i=0; i<script.length; i++){
		record[i].currentTime = 0;
		record[i].pause();
	}
	
	//�]�w�bduration�ɶ���A��v���Ȱ��A�í��s���ϥΪ̥i�H�����s
	timeoutFunction = setTimeout(function(){
			clip.pause(); 
// 			enableButtons();
	}, duration*1000);
	


}

//���U'�����q����'�����s��
function playIntervalRecordClick(){
	
	if (timeoutFunction != null){
		clearTimeout(timeoutFunction);
	}
	
	recordHasToPlay = true;	//�ǳƼ�������

// 	disableButtons();	
	var startTime = parseFloat(this.parentNode.getElementsByClassName('startTime')[0].innerText);
	var endTime = parseFloat(this.parentNode.getElementsByClassName('endTime')[0].innerText);
	var duration = endTime - startTime;
	
	//�ǳƼ���v���A�������������recordHasToPlay�Mupdate����Ʊ���
	clip.volume = 0;
	clip.currentTime = startTime;
	clip.play();
	
	//������ɥ����k�s�üȰ��A������update�ӱ����n��
	for(i=0; i<script.length; i++){
		record[i].currentTime = 0;
		record[i].pause();
	}
	
	//�]�w�bduration�ɶ���A��v���Ȱ��A�í��s���ϥΪ̥i�H�����s
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