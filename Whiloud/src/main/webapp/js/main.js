$(function() {

    

    animationSwitch = true;
    activityButton = document.getElementById('activityButton');
    videoButton = document.getElementById('videoButton');
    friendButton = document.getElementById('friendButton'); 
    friendList = document.getElementById('friendList'); //我的好友
    var main = document.getElementById("main");
    var obj = document.getElementsByTagName("div");
    var wrap = document.getElementById("wrap");


    friendList.addEventListener('click', function () {  //點好友欄  123.JPG none
    	$("#introduce").css('display','none');
    });
    
    //移動標籤第一頁的連結
    activityButton.addEventListener('click', function () {
        $("#main").css({ top: (0 + 'px') }, 0);
        $("#activityButton").css('background', 'rgba(228, 255, 166, 0.75)');
        $("#videoButton").css('background', 'none');
        $("#friendButton").css('background', 'none');
        console.log("目前位置 =" + parseInt(main.offsetTop));
    });
    //移動標籤第二頁的連結
    videoButton.addEventListener('click',function(){
            //移動視窗點第二頁動畫
        if (animationSwitch){
            var wow = new WOW({
　　　　        boxClass: "wow",
　　　　        animateClass: "animated",
　　　　        offset: -900,
　　　　        mobile: true,
　　　　        live: true,
    　　　　    }).init();
            animationSwitch = false;
        }
        $("#main").css({ top: (-969 + 'px') }, 0);
        $("#activityButton").css('background', 'none');
        $("#videoButton").css('background', 'rgba(228, 255, 166, 0.75)');
        $("#friendButton").css('background', 'none');
        console.log("目前位置 =" + parseInt(main.offsetTop));
    });
    //移動標籤第三頁的連結
    friendButton.addEventListener('click', function () {
        $("#main").css({ top: (-1938 + 'px') }, 0); 
        $("#activityButton").css('background', 'none');
        $("#videoButton").css('background', 'none');
        $("#friendButton").css('background', 'rgba(228, 255, 166, 0.75)');
        console.log("目前位置 =" + parseInt(main.offsetTop));
    });

    //分頁滾動
    // var hei = document.body.clientHeight;  ( hei=969 )
    wrap.style.height = 969 + "px";

    for (var i = 0; i < obj.length; i++) {
        if (obj[i].className == 'page') {
            obj[i].style.height = 969 + "px";
        }
    };

    var startTime = 0, //翻屏起始时间
        endTime = 0,
        now = 0;
    //浏览器兼容
    if ((navigator.userAgent.toLowerCase().indexOf("firefox") != -1)) {
        document.addEventListener("DOMMouseScroll", scrollFun, false);
    }
    else if (document.addEventListener) {
        document.addEventListener("mousewheel", scrollFun, false);
    }
    else if (document.attachEvent) {
        document.attachEvent("onmousewheel", scrollFun);
    }
    else {
        document.onmousewheel = scrollFun;
    }
    //滚动事件处理函数
    function scrollFun(event) {
        startTime = new Date().getTime();
        var delta = event.detail || (-event.wheelDelta);
    //mousewheel事件中的 “event.wheelDelta” 属性值：返回的如果是正值说明滚轮是向上滚动
    //DOMMouseScroll事件中的 “event.detail” 属性值：返回的如果是负值说明滚轮是向上滚动   
        if ((endTime - startTime) < -1000) {
    //向下滚动
            if (delta > 0 && parseInt(main.offsetTop) > -(969 * 2)) {
                console.log("移動前的位置 =" + parseInt(main.offsetTop));
                now = parseInt(main.offsetTop) - 969;
                toPage(now);
                if (animationSwitch){
                    var wow = new WOW({
　　　　            boxClass: "wow",
　　　　            animateClass: "animated",
　　　　            offset: -900,
　　　　            mobile: true,
　　　　            live: true,
    　　　　        }).init();
                animationSwitch = false;
                }
            }
    //向上滚动
            if (delta < 0 && parseInt(main.offsetTop) < 0) {
                console.log("移動前的位置 =" + parseInt(main.offsetTop));
                now = parseInt(main.offsetTop) + 969;
                toPage(now);
//              if (animationSwitch){
//                  var wow = new WOW({
// 　　             boxClass: "wow",
// 　　             animateClass: "animated",
// 　　             offset: -400,
// 　　             mobile: true,
// 　　             live: true,
//   　　           }).init();
//              animationSwitch = false;
//             }
            }
             endTime = new Date().getTime(); //滾到下一頁的緩衝時間  
        }
        else {
            // event.preventDefault();
        }
    }
    function toPage(now) {
        $("#main").animate({top: (now + 'px')},1000);     //jquery实现动画效果 animate會慢慢移動的下去  CSS會瞬間下去
        console.log("移動後的位置 =" + now);
        //樣子顯示
        if (parseInt(now) == 0) {
            $("#activityButton").css('background', 'rgba(228, 255, 166, 0.75)');
            $("#videoButton").css('background', 'none');
            $("#friendButton").css('background', 'none');
        }
        if (parseInt(now) == -969) {
            $("#activityButton").css('background', 'none');
            $("#videoButton").css('background', 'rgba(228, 255, 166, 0.75)');
            $("#friendButton").css('background', 'none');
        }
        if (parseInt(now) == -1938) {
            $("#activityButton").css('background', 'none');
            $("#videoButton").css('background', 'none');
            $("#friendButton").css('background', 'rgba(228, 255, 166, 0.75)');
        }
    }

//menu-------------------------------------------------------------------------------------
    var $window = $(window),
     $body = $('body'), 
     $header = $('#header');

    $('#menu')
            .append('<a href="#menu" class="close"></a>')
            .appendTo($body)
            .panel({
                delay: 500,
                hideOnClick: true,
                hideOnSwipe: true,
                resetScroll: true,
                resetForms: true,
                side: 'right'
    });
//第一頁影片轉換------------------------------------------------------------------------------------
    $(function() {
        $(".flexslider").flexslider({
            slideshowSpeed: 2000, //展示时间间隔ms
            animationSpeed: 400, //滚动时间ms
            touch: true //是否支持触屏滑动
        });
    }); 

});