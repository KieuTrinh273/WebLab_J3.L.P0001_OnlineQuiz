/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function  isDelete() {
    var result = confirm("Want to delete?");
    if (result) {
        var input = document.createElement('input');//prepare a new input DOM element
        input.setAttribute('quesId', document.getElementById('quesID'));//set the param name
        var form = document.getElementById('delete');
        form.appendChild(input);
        form.submit();

    }
}

function confirmation() {
      
    }

