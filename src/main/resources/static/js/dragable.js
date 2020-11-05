// function drag(id) {
//     let elem = document.getElementById(id);
//
//     elem.onmousedown = function (e) {
//         let coords = getCoords(elem);
//         let shiftX = e.pageX - coords.left;
//         let shiftY = e.pageY - coords.top;
//
//         //elem.style.position = 'absolute';
//         document.body.appendChild(elem);
//         moveAt(e);
//
//         elem.style.zIndex = "1";
//
//         function moveAt(e) {
//             elem.style.left = e.pageX - shiftX + 'px';
//             elem.style.top = e.pageY - shiftY + 'px';
//         }
//
//         document.onmousemove = function (e) {
//             moveAt(e);
//         };
//
//         elem.onmouseup = function () {
//             document.onmousemove = null;
//             elem.onmouseup = null;
//         };
//     }
//
//     elem.ondragstart = function () {
//         return false;
//     };
//
//     function getCoords(elem) {
//         let box = elem.getBoundingClientRect();
//         return {
//             top: box.top + pageYOffset,
//             left: box.left + pageXOffset
//         };
//     }
// }

$(function() {
    $( "#divBlock1" ).draggable();
});