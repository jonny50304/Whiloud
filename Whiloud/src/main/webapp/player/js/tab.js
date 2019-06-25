$(function(){
    var $li = $('ul.tab-title li');
    $($li. eq(0) .addClass('active').find('a').attr('href')).siblings('.tab-inner').hide();
    
    $li.click(function(){

    	$($(this).find('a'). attr ('href')).show().siblings ('.tab-inner').hide();
        $(this).addClass('active'). siblings ('.active').removeClass('active');
        
        //sleep(300);
//        parent.document.getElementById('main').style.top = '-1938px';
//    	parent.document.getElementById('page3').scrollIntoView();

    });
    
    
});

$(document).ready(function() {
    $("select").on('change',function() {
        var x = this.selectedIndex;

        if (x == "") {
           $("#goRec").hide();
        } else {
           $("#goRec").show();
        }
        
        //sleep(300);
//        parent.document.getElementById('main').style.top = '-1938px';
//    	parent.document.getElementById('page3').scrollIntoView();
    });
    $("#goRec").css("display","none");
}); 

//function sleep(d){
//	  for(var t = Date.now();Date.now() - t <= d;);
//}