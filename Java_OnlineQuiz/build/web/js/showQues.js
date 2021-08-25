/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var quesIndex = 0;
showQues(quesIndex);

// Next/previous controls
function plusQues(n) {
    showQues(quesIndex += n);
    
}
function currentSlide(n) {
  showQues(quesIndex = n);
}
function showQues(n) {
    var i;
    var slides = document.getElementsByClassName("each-question");
    if (n > slides.length-1) {
        quesIndex = 0;
    }
    if (n < 0) {
        quesIndex = slides.length - 1;
    }
    for (i = 0; i < slides.length; i++) {
        slides[i].style.display = "none";
    }
    slides[quesIndex].style.display = "block";
}



