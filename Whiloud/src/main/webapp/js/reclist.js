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
});