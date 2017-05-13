$(function() {
    var footerHeight = parseInt($(".footer-bottom").css("height"));
    $(".content-row").height($("body").height() - footerHeight);
});
//# sourceURL=base.js