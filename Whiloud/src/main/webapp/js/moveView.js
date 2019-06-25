function doFirst(){


  var elementSelected;
  var mouseX, mouseY;

  document.querySelectorAll(".move").forEach( function(element,index) {
    element.style.left = 400;
        element.addEventListener('mousedown', function(event) {
          elementSelected = element;
          mouseX = event.clientX - parseInt(getComputedStyle(elementSelected).left);
          mouseY = event.clientY - parseInt(getComputedStyle(elementSelected).top);
          // document.querySelector(".container").appendChild(elementSelected); // chomec會報錯
        })
    });
  
  document.addEventListener('mousemove', function(event) {
    if(elementSelected!==undefined) {
      elementSelected.style.left = event.clientX - mouseX + 'px';
      elementSelected.style.top = event.clientY - mouseY + 'px';
    }
  });

  document.addEventListener('mouseup', function(event) {
    elementSelected = undefined;
  });

}
window.addEventListener('load',doFirst);