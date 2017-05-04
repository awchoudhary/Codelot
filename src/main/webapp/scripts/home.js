$(function() {
    $("#homeLink, #aboutLink").click(function() {
        var scrollid = $(this).text();
        $("html, body").animate({
            scrollTop: $("#" + scrollid).position().top,
            duration: 800
        });
    });

    $("#faqLink").click(function() {
        $("#faqModal").modal();
    });
});