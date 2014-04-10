$(document).ready(function() {
    $(".item").height(Math.max($(window).height()-60,586));
});
$(window).resize(function() {
    $(".item").height(Math.max($(window).height()-60,586));
});
