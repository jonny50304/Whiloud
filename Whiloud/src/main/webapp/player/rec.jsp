<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
  <title>錄音頁面</title>
  <link href="${pageContext.servletContext.contextPath}/player/css/rec.css" rel="stylesheet" type="text/css">
  <link href="${pageContext.servletContext.contextPath}/player/css/step.css" rel="stylesheet" type="text/css">
  <script src="${pageContext.servletContext.contextPath}/player/js/video.js"></script>
  <script src="${pageContext.servletContext.contextPath}/player/js/jquery.min.js"></script>
  <script src="${pageContext.servletContext.contextPath}/player/js/step.js"></script>
  
  <style type="text/css">
    .vjs-default-skin {
      color: rgba(171, 208, 102, 1);
    }

    .vjs-default-skin .vjs-play-progress,
    .vjs-default-skin .vjs-volume-level {
      background-color: rgba(214, 232, 181, 0.7)
    }

    .vjs-default-skin .vjs-control-bar,
    .vjs-default-skin .vjs-big-play-button {
      background: rgba(40, 124, 134, 0.8)
    }

    .vjs-default-skin .vjs-slider {
      background: rgba(0, 9, 219, 0.23)
    }

    .vjs-default-skin .vjs-control-bar {
      font-size: 110%
    }
  </style>
</head>

<body>
  <div class="leftFixed">
	<input type="hidden" id="memberNo" value="${LoginOK.memberNo}">
	<input type="hidden" id="roleNo" value="${roleNo}">
	<input type="hidden" id="clipNo" value="${clipBean.clipNo}">
	<input type="hidden" id="scriptCount" value="${scriptBeanList[0].scriptCount}">
	<input type="hidden" id="scriptStartNo" value="${scriptBeanList[0].scriptNo}">
	
	<c:if test="${not cooperate}">
		<input type="hidden" id="cooperate" value="false">
		<a class="back" href="GetClipServletV2?clipNo=${clipBean.clipNo}">返回影片 / 關閉視窗</a>
	</c:if>
	
	<c:if test="${cooperate}">
		<input type="hidden" id="postNo" value="${postNo}">
		<input type="hidden" id="cooperate" value="${cooperate}">
		<a class="back" href="GetPostDetailServletV2?postNo=${postNo}">返回影片 / 關閉視窗</a>
	</c:if>
	
    
      
      <video src="${pageContext.servletContext.contextPath}${clipBean.clipPath}"
				width="480" height="300"
				id = "clip"
			>
				please use Chrome
			</video>
			

    <div class="step-body" id="myStep">
      <div class="step-header">
        <ul>
          <li>
            <p>測試麥克風</p>
          </li>
          <li>
            <p>錄音及創作</p>
          </li>
          <li>
            <p>發佈設定</p>
          </li>
        </ul>
      </div>
      
      <div class="step-content">
        <div class="step-list">
<!--           <button class="findmic" onclick="ShowNext()">連接麥克風</button> -->
			<button class="findmic" id="permissionButton">連接麥克風</button>
          <br>
          <button class="nextBtn" id="nextButton" style="display:none">下一步</button>
        </div>
        <div class="step-list">
        <c:if test="${not cooperate}">
			作品名稱：<textarea class="inputMessage" type="text" placeholder="在此輸入作品名稱" cols="22" rows="1" name="postTitle" id="postTitle"></textarea>
			<br>
         	作品描述：<textarea class="inputMessage" type="text" placeholder="在此輸入想說的話"cols="30" rows="5" name="text" id="text"></textarea>
			<br>
			
		</c:if>
		<c:if test="${cooperate}">
			幫忙發文者一起完成配音吧!
		</c:if>
          <button class="nextBtn">下一步</button>
        </div>

        <div class="step-list">
        <button class="Original" id="playRecord">播放作品</button>
        
        <c:if test="${not cooperate && roleNo != 0}">
          	尋找合作：
	          <select id="done">
	            <option selected="true" value="false">需要</option>
	            <option value="true">不需要</option>
	          </select>
	          <br>
         </c:if>

         
          
<!-- 		          未配音角色選項： -->
<!-- 	          <select id="select2"> -->
<!-- 	            <option selected="true"></option> -->
<!-- 	            <option>只能給朋友配</option> -->
<!-- 	            <option>任何人都能配</option> -->
<!-- 	          </select> -->
          
          
          <br>
          <button class="postBtn" id="upload" disabled="true" style="color:gray">發佈</button><br>
		  <div id="hasToRecordText" style="color:red">請先錄完全部才能發佈</div>
				
<!--           <button class="postBtn" id="goPost">發佈</button> -->
          <button class="preBtn">上一步</button>
          
        </div>
      </div>
    </div>
  </div>
  </div>

  <div class="rightFloat">

        <div class="tab-inner" id="tab01">
         	<c:forEach var="entry" items="${scriptBeanList}" varStatus="idxStatus">
	        	<div class="text section">
	        		<span class="scriptNo" style="display:none;">		${entry.scriptNo}</span>
		        	<span class="scriptPosition" style="display:none;">	${entry.scriptPosition}</span>
		        	<span class="scriptCount" style="display:none;">	${entry.scriptCount}</span>
		        	<span class="startTime" style="display:none;">		${entry.startTime}</span>
		        	<span class="endTime" style="display:none;">		${entry.endTime}</span>
		        	<span class="script">
		            <h2>${entry.englishScript}</h2>
		            <br>
		            <h3>${entry.chineseScript}</h3>
		            <br>
		            </span>
	            	
		            <div class="allSoundBtm">
		            <span style="font-size:13px;">影片腳色: ${entry.roleName}<br> </span>
		             <button class="Original playIntervalClip">聽原音</button>
		            
		             <c:choose>
		             	<c:when test="${roleNo == 0 || entry.roleNo == roleNo }">
<!-- 				            <button class="soundButton listenRec" disabled="true">聽錄音</button> -->
		        			<button class="soundButton recButton recordStart" disabled="true" style="color:gray">錄音</button>
		        			 <span class="playIntervalRecord"></span>
		        			<br>
		        			<span class="permissionText" style="color:red">請先連結麥克風才能開啟功能</span>
		        			<span class="hasToRecord"></span>
		             	</c:when>
		             	<c:otherwise>
		             		<button class = "recordStart" disabled="true"  style="display:none">錄音</button>
		             		<span class="playIntervalRecord"></span>
		             		<span class="permissionText" style="color:red; display:none"></span>
		             	</c:otherwise>
		             </c:choose>
		             
		             <c:if test="${cooperate}">
						<span class="audioPlayer">
							
							<c:if test="${not empty recordList[idxStatus.index].recordPath}">
							
								<button class="listenRec playMemberNo1IntervalRecord">聽錄音</button>
								<audio
										src="${pageContext.servletContext.contextPath}${recordList[idxStatus.index].recordPath}"
										controls="controls"
										class="record" 
										style="display:none;"
									>
								</audio>
								
							</c:if>
							
						</span>
					</c:if>
					
					
					<c:if test="${not cooperate}">
						<span class="audioPlayer">
						</span>
					</c:if>
					
					
		             
		            </div>
	          </div>
	        </c:forEach>
        </div> 
    </div>

<script src="${pageContext.servletContext.contextPath}/player/js/recorder.js"></script>
<script src="${pageContext.servletContext.contextPath}/player/js/editingController.js"></script>
</body>
<script>

</script>

</html>