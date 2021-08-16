function startTimer() {
    var minutes, seconds;
    var timer = setInterval(function () {
        minutes = parseInt(duration / 60, 10);
        seconds = parseInt(duration % 60, 10);

        minutes = minutes < 10 ? "0" + minutes : minutes;
        seconds = seconds < 10 ? "0" + seconds : seconds;

        document.getElementById("timer").innerHTML = minutes + ":" + seconds;

        duration--;
        if (duration < 0) {
            clearInterval(timer);
//            document.getElementById("doQuiz").submit();
            
        }
    }, 1000);
}
var duration;

function createTimer(numOfQuestions){
    duration =  numOfQuestions*3;
    startTimer();
};