$(function () {
    $('#carousel_item_1').click(function () {
        window.location="/home"
    });
    $('#carousel_item_2').click(function () {
        console.log('2');
    });
    $('#carousel_item_3').click(function () {
        console.log('3');
    });
    setTimeout(function () {
        $('#myCarousel').fadeIn();
    },1000);
});