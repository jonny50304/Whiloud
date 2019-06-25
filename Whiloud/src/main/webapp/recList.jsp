<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
	<%response.setHeader("Pragma","No-cache"); 
response.setHeader("Cache-Control","no-cache"); 
response.setDateHeader("Expires", 0); 
response.flushBuffer();%>

				<%
//請求網址後面加上日期時間防止快取
    java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat(  
     "HHmmss");  
   java.util.Date currentTime = new java.util.Date();  
   String time = simpleDateFormat.format(currentTime).toString();
%>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>範本集 -Whiloud</title>
  <link rel="stylesheet" href="css/bootstrap.min.css">
  <link href="css/font-awesome.min.css" rel="stylesheet">
  <link rel="stylesheet" href="css/reclist.css">
  <link rel="stylesheet" href="css/thickbox.css">
  
  
</head>

<body>

 <jsp:include page="title.jsp"/>
 <br>
  <section class="category">
    <p style="text-align:center;">
    	選一部影片來開始你的Whiloud配音吧!
    </p>
    </div>
  </section>
  <section class="videoboxs">
	  <c:forEach var="entry" items="${clipList}">

	  <div class="videobox">
	  	<a href="GetClipServletV2?clipNo=${entry.clipNo}&keepThis=true&TB_iframe=true" class="thickbox"
										rel="gallery-plants">
	      <img src="${pageContext.servletContext.contextPath}${entry.picturePath}" alt="" class="video">
	     </a>
	      <p title="${entry.clipTitle}">${entry.clipTitle}</p>
	    </div>

	  	
	  </c:forEach>
    
<!--     <div class="videobox"> -->
<!--       <img src="images/video.png" alt="" class="video"> -->
<!--       <p>我是片名</p> -->
<!--     </div> -->

  </section>
</body>
<!-- <script src="js/jquery-3.3.1.slim.min.js"></script> -->
<script src="js/jquery.min.js"></script>
<script src="js/popper.min.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/reclist.js"></script>
<script src="js/skel.min.js"></script>
<script src="js/util.js"></script>
<script src="js/jquery.flexslider.js"></script>
<script type="text/javascript" src="js/thickbox.js"></script>
<script src="js/openSocket.js?<%=time%>"></script>
</html>