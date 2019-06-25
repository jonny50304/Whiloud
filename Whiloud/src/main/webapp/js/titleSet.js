$(function() {

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
//至那個地點(同網頁)--------------------------------------------------------
    $('#personlist').click(function(){
        $('#infocontent01').css('display','block');
        $('#personlist').css('background','rgba(140,140,140,0.75)');
        $('#infocontent02').css('display','none');
        $('#personmage').css('background','none');
        $('#infocontent03').css('display','none');
        $('#personpassword').css('background','none');
        $('#infocontent04').css('display','none');
        $('#personprivacy').css('background','none');
    });
    $('#personmage').click(function(){
        $('#infocontent01').css('display','none');
        $('#personlist').css('background','none');
        $('#infocontent02').css('display','block');
        $('#personmage').css('background','rgba(140,140,140,0.75)');
        $('#infocontent03').css('display','none');
        $('#personpassword').css('background','none');
        $('#infocontent04').css('display','none');
        $('#personprivacy').css('background','none');
    });
    $('#personpassword').click(function(){
        $('#infocontent01').css('display','none');
        $('#personlist').css('background','none');
        $('#infocontent02').css('display','none');
        $('#personmage').css('background','none');
        $('#infocontent03').css('display','block');
        $('#personpassword').css('background','rgba(140,140,140,0.75)');
        $('#infocontent04').css('display','none');
        $('#personprivacy').css('background','none');
    });
    $('#personprivacy').click(function(){
        $('#infocontent01').css('display','none');
        $('#personlist').css('background','none');
        $('#infocontent02').css('display','none');
        $('#personmage').css('background','none');
        $('#infocontent03').css('display','none');
        $('#personpassword').css('background','none');
        $('#infocontent04').css('display','block');
        $('#personprivacy').css('background','rgba(140,140,140,0.75)');
    });        
	//先跟畫面產生連結，在建立事件聆聽
	document.getElementById('theFile').onchange = fileChange;

    function fileChange(){

	    var file = document.getElementById('theFile').files[0];
	    //-------------
	    var readFile = new FileReader();
	    readFile.readAsDataURL(file);
	    readFile.addEventListener('load',function(){
	    	var image = document.getElementById('image');
	    	image.src = this.result;
	    	// image.style.maxWidth = '500px';
	    	// image.style.maxHeight = '300px';

	    });
    }
    $('#mySwitch input').bootstrapSwitch({
        onText:'On',
        offText:'Off'
    });
});

