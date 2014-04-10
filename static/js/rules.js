$(document).keydown(function(e) {
    if (e.keyCode === 37) {
        $('#leftControl').click();
        return false;
    }
    else if (e.keyCode === 39) {
        $('#rightControl').click();
        return false;
    }
});