<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix='c' uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>Join Us -Whiloud</title>
    <style>
        * {
            -webket-box-sizing: border-box;
            -moz-box-sizing: border-box;
            -o-box-sizing: border-box;
            box-sizing: border-box;
            font-size: 22px;
            font-family: Microsoft JhengHei;

        }

        h1 {
            font-size: 50px;
            color: orange;
            text-align: center;
            margin-top: 80px;
        }

        body {
            color: black;
            overflow-x: hidden;
            background-image: url("images/bg1.jpg") ;
        }

        form {
            width: 100%;
            max-width: 400px;
            margin: 80px auto;
        }

        form div div {
            position: relative;
            width: 100%;
            height: 30px;
            margin-top: 100px;
        }

        .input {
            width: 50%;
            padding: 5px;
            background: none;
            outline: none;
            border: none;
            border-bottom: 1px solid #ad3064;
            color: black;
        }

        .input,
        label {
            position: absolute;
        }

        #left {
            width: 35%;
            height: 750px;
            float: left;
            background-color: rgba(255, 255, 255, 0.7);
            position: absolute;
            left: 22%;
            top: 15%;

        }

        #right {
            width: 35%;
            height: 750px;
            float: left;
            background-color: rgba(255, 255, 255, 0.7);
            position: absolute;
            left: 57%;
            top: 15%;
            /*border: 1px solid #3B6273;*/
            
        }

        #button {
            position: absolute;
            top: 600px;
            left: -42%;
        }


        h2 {
            position: absolute;
            left: 20%;
            margin-top: 35px;

        }

        .input:focus {
            border-color: #5AA6ED;
        }

        a {
            text-decoration: none;
        }

        input[type="submit"],
        input[type="reset"],
        input[type="button"] {
            transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
            border-radius: 4px;
            border: 0;
            cursor: pointer;
            display: inline-block;
            font-weight: 400;
            height: 2rem;
        }

        input[type="submit"],
        input[type="reset"],
        input[type="button"] {
            background-color: orange;
            /*按鈕顏色*/
            color: #ffffff;
            /*按鈕字的顏色*/
        }
        
        #submitButton{
        transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
            border-radius: 4px;
            border: 0;
            cursor: pointer;
            display: inline-block;
            font-weight: 400;
            height: 2rem;
            background-color: orange;
            /*按鈕顏色*/
            color: #ffffff;
            /*按鈕字的顏色*/
        }

        body img {
            position: relative;
            /* top: 100px; */
        }

        .interest {
            border-bottom: 1px solid #ad3064;
            width: 420px;
        }
    </style>

</head>

<body>
    <h1>歡迎加入</h1>
    <form ENCTYPE="multipart/form-data" method="POST" action="<c:url value='register.do'/>"  id="theForm" >
        <div id="left">
        	
            <h2>必填資料</h2>
            <div>
                <label>帳號(EMAIL) : <span style="font-size:10px; color:red;" id="accountError">&nbsp;&nbsp;${errorMessage.errorAccountEmpty}${errorMessage.errorIDDup}${errorMessage.errorAccountExists}</span></label>
                
                <input class="input animateRequired" name="account" id="account" type="email" autocomplete="off" required type="email"  value="${param.account}"/>
            
            </div>
            <div>
                <label>密碼 :<span style="font-size:10px; color:red;" id="passwordError">&nbsp;&nbsp;${errorMessage.errorPasswordEmpty}${errorMessage.passwordError}</span></label>
                <input class="input animateRequired" type="password" autocomplete="off" required name="password" value="${param.password}" id="password" />
            </div>
            <div>
                <label>確認密碼 :<span style="font-size:10px; color:red;" id="passwordCheckError">&nbsp;&nbsp;${errorMessage.errorPasswordCheckEmpty}</span></label>
                <input class="input animateRequired" type="password" autocomplete="off" required name="passwordCheck" value="${param.passwordCheck}" id="passwordCheck" />
            </div>
            <div>
                <label>暱稱(顯示的名稱):<span style="font-size:10px; color:red;" id="nicknameError">&nbsp;&nbsp;${errorMessage.errorNicknameEmpty}${errorMessage.errorNicknameExists}</span></label>
                <input class="input animateRequired" type="text" autocomplete="off" name="nickname" value="${param.nickname}" id="nickname" required />
            </div>
            <div>
            <div style="font-size:10px; color:red; position:absolute; margin-top:20px;margin-left: 250px" id="genderError"></div>
                <select class="input animateRequired" name="gender" id="gender" required>
                    <option value="" disabled="disabled" selected="selected">性別 :
                    </option>
                    <option value="M">男性</option>
                    <option value="F">女性</option>
                    <option value="O">性別保密</option>
                </select>
                
            </div>
        </div>
        <div id="right">
            <h2>選填資料</h2>
            <div>
                <label >大頭貼 :</label>
                <input class="input upOnly" type="text" onfocus="(this.type='file')" name="file" id="image" />
            </div>
            
            <div>
                <label>電話號碼 :</label>
                <input class="input animateRequired" type="phone" autocomplete="off" name="phone" id="phone" />
            </div>
            <div>
                <label>生日 :</label>
                <input class="input upOnly" type="text" onfocus="(this.type='date')" name="birthday" id="birthday" />
            </div>
            <div>
                <label>自我介紹 :</label>
                <input class="input animateRequired" type="text" autocomplete="off" name="introduction" id="introduction" />
            </div>
            <div class="interest">
                <label>興趣 :</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                <input type="checkbox" name="interest" value="閱讀">閱讀
                <input type="checkbox" name="interest" value="遊戲">遊戲
                <input type="checkbox" name="interest" value="影視">影視
                <input type="checkbox" name="interest" value="逛街">逛街
                <input type="checkbox" name="interest" value="打球">打球 
                &emsp;&nbsp;&nbsp;&nbsp; &nbsp;    
                <input type="checkbox" name="interest" value="音樂">音樂
                <input type="checkbox" name="interest" value="旅行">旅行                
                <input type="checkbox" name="interest" value="魔術">魔術                
                <input type="checkbox" name="interest" value="健身">健身                
                <input type="checkbox" name="interest" value="烹飪">烹飪                
            </div>
            
            <div id="button">

                <button id="submitButton" type="button">傳送出去</button>
<!--                 <input type="submit" value="傳送出去"> -->
                <input type="reset" value="重新填寫">
                <a href="index.jsp"><input type="button" value="回首頁"></a>
                
            </div>
            

        </div>
        
    </form>
	
</body>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script src="js/dataValidation.js"></script>

<!-- 動畫相關 -->
<script>
    $('.animateRequired').on('focus', function () {
        if ($(this).val() === "") {
            $(this).prev('label').animate({
                fontSize: 25,
                top: -40
            }, 150);
        }
    });
    $('.animateRequired').on('blur', function () {
        if ($(this).val() === "") {
            $(this).prev('label').animate({
                fontSize: 22,
                top: 0
            }, 150);
        }
    });
    $('.upOnly').on('focus', function () {
        if ($(this).val() === "") {
            $(this).prev('label').animate({
                fontSize: 25,
                top: -40
            }, 150);
        }
    });
</script>

</html>